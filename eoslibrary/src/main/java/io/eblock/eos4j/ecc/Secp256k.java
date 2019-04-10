/*    */ package io.eblock.eos4j.ecc;
/*    */ 
/*    */ import io.eblock.eos4j.utils.Hex;
/*    */ import java.math.BigInteger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Secp256k
/*    */ {
/*    */   public static final String P = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F";
/*    */   public static final String A = "0";
/*    */   public static final String B = "7";
/*    */   public static final String N = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141";
/*    */   public static final String GX = "79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798";
/*    */   public static final String GY = "483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8";
/*    */   private final Curve curve;
/*    */   private final Point G;
/*    */   private final BigInteger n;
/*    */   private final BigInteger HALF_CURVE_ORDER;
/*    */   
/*    */   public Secp256k()
/*    */   {
/* 31 */     this.n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
/* 32 */     this.HALF_CURVE_ORDER = this.n.shiftRight(1);
/* 33 */     this.curve = new Curve(new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16), new BigInteger("0", 16), new BigInteger("7", 16));
/* 34 */     this.G = this.curve.decodePoint(Hex.toBytes("0479BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8"));
/*    */   }
/*    */   
/*    */   public Point G() {
/* 38 */     return this.G;
/*    */   }
/*    */   
/*    */   public BigInteger n() {
/* 42 */     return this.n;
/*    */   }
/*    */   
/*    */   public BigInteger halfCurveOrder() {
/* 46 */     return this.HALF_CURVE_ORDER;
/*    */   }
/*    */   
/*    */   public Curve getCurve() {
/* 50 */     return this.curve;
/*    */   }
/*    */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\ecc\Secp256k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */