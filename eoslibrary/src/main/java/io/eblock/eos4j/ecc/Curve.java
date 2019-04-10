/*     */ package io.eblock.eos4j.ecc;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Curve
/*     */ {
/*     */   private FieldElement a;
/*     */   private FieldElement b;
/*     */   private BigInteger q;
/*     */   private Point infinity;
/*     */   private BigInteger pOverFour;
/*     */   
/*     */   public Curve(BigInteger q, BigInteger a, BigInteger b)
/*     */   {
/*  24 */     this.q = q;
/*  25 */     this.a = fromBigInteger(a);
/*  26 */     this.b = fromBigInteger(b);
/*  27 */     this.infinity = new Point(this, null, null);
/*  28 */     this.pOverFour = q.add(BigInteger.ONE).shiftRight(2);
/*     */   }
/*     */   
/*     */   public FieldElement getA() {
/*  32 */     return this.a;
/*     */   }
/*     */   
/*     */   public FieldElement getB() {
/*  36 */     return this.b;
/*     */   }
/*     */   
/*     */   public BigInteger getQ() {
/*  40 */     return this.q;
/*     */   }
/*     */   
/*     */   public Point getInfinity() {
/*  44 */     return this.infinity;
/*     */   }
/*     */   
/*     */   public int getFieldSize() {
/*  48 */     return this.q.bitLength();
/*     */   }
/*     */   
/*     */   public FieldElement fromBigInteger(BigInteger x) {
/*  52 */     return new FieldElement(this.q, x);
/*     */   }
/*     */   
/*     */   public Point pointFromX(int isOdd, BigInteger x) {
/*  56 */     FieldElement f = this.a.multiply(fromBigInteger(x));
/*  57 */     BigInteger alpha = x.pow(3).add(f.toBigInteger()).add(this.b.toBigInteger()).mod(this.q);
/*  58 */     BigInteger beta = alpha.modPow(this.pOverFour, this.q);
/*  59 */     BigInteger y = beta;
/*  60 */     if (((beta.intValue() % 2 == 0 ? 1 : 0) ^ (isOdd == 0 ? 1 : 0)) != 0) {
/*  61 */       y = this.q.subtract(y);
/*     */     }
/*  63 */     return new Point(this, new FieldElement(this.q, x), new FieldElement(this.q, y), false);
/*     */   }
/*     */   
/*     */   public Point decodePoint(byte[] encodedPoint) {
/*  67 */     Point p = null;
/*  68 */     switch (encodedPoint[0]) {
/*     */     case 0: 
/*  70 */       p = getInfinity();
/*  71 */       break;
/*     */     case 2: 
/*     */     case 3: 
/*  74 */       int ytilde = encodedPoint[0] & 0x1;
/*  75 */       byte[] i = new byte[encodedPoint.length - 1];
/*  76 */       System.arraycopy(encodedPoint, 1, i, 0, i.length);
/*  77 */       FieldElement x = new FieldElement(this.q, new BigInteger(1, i));
/*  78 */       FieldElement alpha = x.multiply(x.square().add(this.a)).add(this.b);
/*  79 */       FieldElement beta = alpha.sqrt();
/*  80 */       if (beta == null) {
/*  81 */         throw new RuntimeException("Invalid compression");
/*     */       }
/*  83 */       int bit0 = beta.toBigInteger().testBit(0) ? 1 : 0;
/*  84 */       if (bit0 == ytilde) {
/*  85 */         p = new Point(this, x, beta, true);
/*     */       } else {
/*  87 */         p = new Point(this, x, new FieldElement(this.q, this.q.subtract(beta.toBigInteger())), true);
/*     */       }
/*  89 */       break;
/*     */     case 4: 
/*     */     case 6: 
/*     */     case 7: 
/*  93 */       byte[] xEnc = new byte[(encodedPoint.length - 1) / 2];
/*  94 */       byte[] yEnc = new byte[(encodedPoint.length - 1) / 2];
/*  95 */       System.arraycopy(encodedPoint, 1, xEnc, 0, xEnc.length);
/*  96 */       System.arraycopy(encodedPoint, xEnc.length + 1, yEnc, 0, yEnc.length);
/*  97 */       p = new Point(this, new FieldElement(this.q, new BigInteger(1, xEnc)), new FieldElement(this.q, new BigInteger(1, yEnc)));
/*     */       
/*  99 */       break;
/*     */     case 1: case 5: default: 
/* 101 */       throw new RuntimeException("Invalid encoding 0x" + Integer.toString(encodedPoint[0], 16));
/*     */     }
/* 103 */     return p;
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\ecc\Curve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */