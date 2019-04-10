/*     */ package io.eblock.eos4j.ecc;
/*     */ 
/*     */ import io.eblock.eos4j.utils.EException;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Random;
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
/*     */ public class FieldElement
/*     */ {
/*     */   private BigInteger q;
/*     */   private BigInteger x;
/*  20 */   private static final BigInteger TWO = BigInteger.valueOf(2L);
/*     */   
/*     */   public FieldElement(BigInteger q, BigInteger x) {
/*  23 */     this.x = x;
/*  24 */     if (x.compareTo(q) >= 0) {
/*  25 */       throw new EException("error", "x value too large in field element");
/*     */     }
/*  27 */     this.q = q;
/*     */   }
/*     */   
/*     */   public FieldElement add(FieldElement b) {
/*  31 */     return new FieldElement(this.q, this.x.add(b.toBigInteger()).mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement subtract(FieldElement b) {
/*  35 */     return new FieldElement(this.q, this.x.subtract(b.toBigInteger()).mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement multiply(FieldElement b) {
/*  39 */     return new FieldElement(this.q, this.x.multiply(b.toBigInteger()).mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement divide(FieldElement b) {
/*  43 */     return new FieldElement(this.q, this.x.multiply(b.toBigInteger().modInverse(this.q)).mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement negate() {
/*  47 */     return new FieldElement(this.q, this.x.negate().mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement square() {
/*  51 */     return new FieldElement(this.q, this.x.multiply(this.x).mod(this.q));
/*     */   }
/*     */   
/*     */   public FieldElement invert() {
/*  55 */     return new FieldElement(this.q, this.x.modInverse(this.q));
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  60 */     return toBigInteger().toString(16);
/*     */   }
/*     */   
/*     */   public FieldElement sqrt() {
/*  64 */     if (!this.q.testBit(0)) {
/*  65 */       throw new RuntimeException("not done yet");
/*     */     }
/*     */     
/*  68 */     if (this.q.testBit(1)) {
/*  69 */       FieldElement z = new FieldElement(this.q, this.x.modPow(this.q.shiftRight(2).add(BigInteger.ONE), this.q));
/*  70 */       return z.square().equals(this) ? z : null;
/*     */     }
/*  72 */     BigInteger qMinusOne = this.q.subtract(BigInteger.ONE);
/*  73 */     BigInteger legendreExponent = qMinusOne.shiftRight(1);
/*  74 */     if (!this.x.modPow(legendreExponent, this.q).equals(BigInteger.ONE)) {
/*  75 */       return null;
/*     */     }
/*  77 */     BigInteger u = qMinusOne.shiftRight(2);
/*  78 */     BigInteger k = u.shiftLeft(1).add(BigInteger.ONE);
/*  79 */     BigInteger Q = this.x;
/*  80 */     BigInteger fourQ = Q.shiftLeft(2).mod(this.q);
/*     */     
/*  82 */     Random rand = new Random();
/*     */     BigInteger U;
/*     */     do {
/*     */       BigInteger P;
/*  86 */       do { P = new BigInteger(this.q.bitLength(), rand);
/*  87 */       } while ((P.compareTo(this.q) >= 0) || 
/*  88 */         (!P.multiply(P).subtract(fourQ).modPow(legendreExponent, this.q).equals(qMinusOne)));
/*  89 */       BigInteger[] result = lucasSequence(this.q, P, Q, k);
/*  90 */       U = result[0];
/*  91 */       BigInteger V = result[1];
/*  92 */       if (V.multiply(V).mod(this.q).equals(fourQ)) {
/*  93 */         if (V.testBit(0)) {
/*  94 */           V = V.add(this.q);
/*     */         }
/*  96 */         V = V.shiftRight(1);
/*  97 */         return new FieldElement(this.q, V);
/*     */       }
/*  99 */     } while ((U.equals(BigInteger.ONE)) || (U.equals(qMinusOne)));
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   private static BigInteger[] lucasSequence(BigInteger p, BigInteger P, BigInteger Q, BigInteger k) {
/* 104 */     int n = k.bitLength();
/* 105 */     int s = k.getLowestSetBit();
/* 106 */     BigInteger Uh = BigInteger.ONE;
/* 107 */     BigInteger Vl = TWO;
/* 108 */     BigInteger Vh = P;
/* 109 */     BigInteger Ql = BigInteger.ONE;
/* 110 */     BigInteger Qh = BigInteger.ONE;
/* 111 */     for (int j = n - 1; j >= s + 1; j--) {
/* 112 */       Ql = Ql.multiply(Qh).mod(p);
/* 113 */       if (k.testBit(j)) {
/* 114 */         Qh = Ql.multiply(Q).mod(p);
/* 115 */         Uh = Uh.multiply(Vh).mod(p);
/* 116 */         Vl = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
/* 117 */         Vh = Vh.multiply(Vh).subtract(Qh.shiftLeft(1)).mod(p);
/*     */       } else {
/* 119 */         Qh = Ql;
/* 120 */         Uh = Uh.multiply(Vl).subtract(Ql).mod(p);
/* 121 */         Vh = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
/* 122 */         Vl = Vl.multiply(Vl).subtract(Ql.shiftLeft(1)).mod(p);
/*     */       }
/*     */     }
/* 125 */     Ql = Ql.multiply(Qh).mod(p);
/* 126 */     Qh = Ql.multiply(Q).mod(p);
/* 127 */     Uh = Uh.multiply(Vl).subtract(Ql).mod(p);
/* 128 */     Vl = Vh.multiply(Vl).subtract(P.multiply(Ql)).mod(p);
/* 129 */     Ql = Ql.multiply(Qh).mod(p);
/* 130 */     for (int j = 1; j <= s; j++) {
/* 131 */       Uh = Uh.multiply(Vl).mod(p);
/* 132 */       Vl = Vl.multiply(Vl).subtract(Ql.shiftLeft(1)).mod(p);
/* 133 */       Ql = Ql.multiply(Ql).mod(p);
/*     */     }
/* 135 */     return new BigInteger[] { Uh, Vl };
/*     */   }
/*     */   
/*     */   public BigInteger toBigInteger() {
/* 139 */     return this.x;
/*     */   }
/*     */   
/*     */   public int getFieldSize() {
/* 143 */     return this.q.bitLength();
/*     */   }
/*     */   
/*     */   public BigInteger getQ() {
/* 147 */     return this.q;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 151 */     if (other == this) {
/* 152 */       return true;
/*     */     }
/* 154 */     if (!(other instanceof FieldElement)) {
/* 155 */       return false;
/*     */     }
/* 157 */     FieldElement o = (FieldElement)other;
/* 158 */     return (this.q.equals(o.q)) && (this.x.equals(o.x));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 162 */     return this.q.hashCode() ^ this.x.hashCode();
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\ecc\FieldElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */