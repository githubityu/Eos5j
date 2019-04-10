/*     */ package io.eblock.eos4j.ecc;
/*     */ 
/*     */ import io.eblock.eos4j.utils.ByteUtils;
/*     */ import io.eblock.eos4j.utils.EException;
/*     */ import io.eblock.eos4j.utils.Hex;
/*     */ import io.eblock.eos4j.utils.Sha;
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ecdsa
/*     */ {
/*  20 */   Secp256k curve = null;
/*     */   
/*     */   public Ecdsa(Secp256k curve) {
/*  23 */     this.curve = curve;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SignBigInt sign(String dataHash, BigInteger d, int nonce)
/*     */   {
/*  35 */     BigInteger n = this.curve.n();
/*  36 */     SignBigInt big = new SignBigInt();
/*  37 */     deterministicGenerateK(this.curve, dataHash, d, nonce, big);
/*  38 */     BigInteger N_OVER_TWO = n.shiftRight(1);
/*  39 */     if (big.getS().compareTo(N_OVER_TWO) > 0) {
/*  40 */       big.setS(n.subtract(big.getS()));
/*     */     }
/*  42 */     big.setDer(toDER(big));
/*  43 */     return big;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private byte[] toDER(SignBigInt big)
/*     */   {
/*  53 */     byte[] rBa = big.getR().toByteArray();
/*  54 */     byte[] sBa = big.getS().toByteArray();
/*  55 */     ArrayList<Byte> sequence = new ArrayList();
/*  56 */     sequence.add(new Byte((byte)2));
/*  57 */     sequence.add(new Byte((byte)rBa.length));
/*  58 */     for (int i = 0; i < rBa.length; i++) {
/*  59 */       sequence.add(Byte.valueOf(rBa[i]));
/*     */     }
/*  61 */     sequence.add(new Byte((byte)2));
/*  62 */     sequence.add(new Byte((byte)sBa.length));
/*  63 */     for (int i = 0; i < sBa.length; i++) {
/*  64 */       sequence.add(Byte.valueOf(sBa[i]));
/*     */     }
/*  66 */     int len = sequence.size();
/*  67 */     sequence.add(0, Byte.valueOf((byte)48));
/*  68 */     sequence.add(1, Byte.valueOf((byte)len));
/*  69 */     byte[] bf = new byte[sequence.size()];
/*  70 */     for (int i = 0; i < bf.length; i++) {
/*  71 */       bf[i] = ((Byte)sequence.get(i)).byteValue();
/*     */     }
/*  73 */     return bf;
/*     */   }
/*     */   
/*     */   private BigInteger deterministicGenerateK(Secp256k curve, String dataHash, BigInteger d, int nonce, SignBigInt big)
/*     */   {
/*  78 */     byte[] hash = Hex.hexStringToBytes(dataHash);
/*  79 */     if (nonce > 0) {
/*  80 */       hash = Sha.SHA256(ByteUtils.concat(hash, new byte[nonce]));
/*     */     }
/*  82 */     byte[] x = null;
/*  83 */     if (d.toByteArray()[0] == 0) {
/*  84 */       x = ByteUtils.copy(d.toByteArray(), 1, d.toByteArray().length - 1);
/*     */     } else {
/*  86 */       x = d.toByteArray();
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 105 */     byte[] k = new byte[32];
/* 106 */     byte[] v = new byte[32];
/*     */     
/*     */ 
/* 109 */     Arrays.fill(v, (byte)1);
/*     */     
/* 111 */     Arrays.fill(k, (byte)0);
/*     */     
/*     */ 
/* 114 */     byte[] db = ByteUtils.concat(ByteUtils.concat(ByteUtils.concat(v, new byte[] { 0 }), x), hash);
/*     */     
/* 116 */     k = Sha.HmacSHA256(db, k);
/*     */     
/*     */ 
/* 119 */     v = Sha.HmacSHA256(v, k);
/*     */     
/* 121 */     byte[] fb = ByteUtils.concat(ByteUtils.concat(ByteUtils.concat(v, new byte[] { 1 }), x), hash);
/* 122 */     k = Sha.HmacSHA256(fb, k);
/*     */     
/* 124 */     v = Sha.HmacSHA256(v, k);
/*     */     
/*     */ 
/* 127 */     v = Sha.HmacSHA256(v, k);
/*     */     
/* 129 */     BigInteger T = new BigInteger(Hex.bytesToHexString(v), 16);
/*     */     
/* 131 */     BigInteger e = new BigInteger(dataHash, 16);
/*     */     
/* 133 */     Boolean check = checkSig(T, d, e, big);
/* 134 */     while ((T.signum() <= 0) || (T.compareTo(curve.n()) >= 0) || (!check.booleanValue())) {
/* 135 */       k = Sha.HmacSHA256(ByteUtils.concat(v, new byte[] { 0 }), k);
/* 136 */       v = Sha.HmacSHA256(v, k);
/* 137 */       v = Sha.HmacSHA256(v, k);
/* 138 */       T = new BigInteger(v);
/*     */     }
/*     */     
/* 141 */     big.setK(T);
/* 142 */     return T;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Boolean checkSig(BigInteger k, BigInteger d, BigInteger e, SignBigInt big)
/*     */   {
/* 155 */     Point Q = this.curve.G().multiply(k);
/* 156 */     if (Q.isInfinity())
/* 157 */       return Boolean.valueOf(false);
/* 158 */     BigInteger r = Q.getX().toBigInteger().mod(this.curve.n());
/* 159 */     big.setR(r);
/* 160 */     if (r.signum() == 0)
/* 161 */       return Boolean.valueOf(false);
/* 162 */     BigInteger s = k.modInverse(this.curve.n()).multiply(e.add(d.multiply(r))).mod(this.curve.n());
/* 163 */     big.setS(s);
/* 164 */     if (s.signum() == 0)
/* 165 */       return Boolean.valueOf(false);
/* 166 */     return Boolean.valueOf(true);
/*     */   }
/*     */   
/*     */ 
/*     */   public static class SignBigInt
/*     */   {
/*     */     private BigInteger k;
/*     */     
/*     */     private BigInteger r;
/*     */     
/*     */     private BigInteger s;
/*     */     private byte[] der;
/*     */     
/*     */     public BigInteger getK()
/*     */     {
/* 181 */       return this.k;
/*     */     }
/*     */     
/*     */     public void setK(BigInteger k) {
/* 185 */       this.k = k;
/*     */     }
/*     */     
/*     */     public BigInteger getR() {
/* 189 */       return this.r;
/*     */     }
/*     */     
/*     */     public void setR(BigInteger r) {
/* 193 */       this.r = r;
/*     */     }
/*     */     
/*     */     public BigInteger getS() {
/* 197 */       return this.s;
/*     */     }
/*     */     
/*     */     public void setS(BigInteger s) {
/* 201 */       this.s = s;
/*     */     }
/*     */     
/*     */     public byte[] getDer() {
/* 205 */       return this.der;
/*     */     }
/*     */     
/*     */     public void setDer(byte[] der) {
/* 209 */       this.der = der;
/*     */     }
/*     */   }
/*     */   
/*     */   public int calcPubKeyRecoveryParam(BigInteger e, SignBigInt sign, Point Q) {
/* 214 */     for (int i = 0; i < 4; i++) {
/* 215 */       Point Qprime = recoverPubKey(e, sign, i);
/* 216 */       if (Qprime.equals(Q)) {
/* 217 */         return i;
/*     */       }
/*     */     }
/* 220 */     throw new EException("sign_error", "Unable to find valid recovery factor");
/*     */   }
/*     */   
/*     */   public Point recoverPubKey(BigInteger e, SignBigInt big, int i)
/*     */   {
/* 225 */     BigInteger n = this.curve.n();
/* 226 */     Point G = this.curve.G();
/*     */     
/* 228 */     BigInteger r = big.getR();
/* 229 */     BigInteger s = big.getS();
/*     */     
/* 231 */     if ((r.signum() <= 0) || (r.compareTo(n) >= 0)) {
/* 232 */       throw new EException("recover_pubkey_error", "Invalid r value");
/*     */     }
/* 234 */     if ((s.signum() <= 0) || (s.compareTo(n) >= 0)) {
/* 235 */       throw new EException("recover_pubkey_error", "Invalid r value");
/*     */     }
/*     */     
/*     */ 
/* 239 */     int isYOdd = i & 0x1;
/*     */     
/*     */ 
/*     */ 
/* 243 */     int isSecondKey = i >> 1;
/*     */     
/*     */ 
/* 246 */     BigInteger x = isSecondKey == 1 ? r.add(n) : r;
/*     */     
/* 248 */     Point R = this.curve.getCurve().pointFromX(isYOdd, x);
/*     */     
/*     */ 
/* 251 */     Point nR = R.multiply(n);
/*     */     
/* 253 */     if (!nR.isInfinity()) {
/* 254 */       throw new EException("sign_error", "nR is not a valid curve point");
/*     */     }
/*     */     
/* 257 */     BigInteger eNeg = e.negate().mod(n);
/*     */     
/* 259 */     BigInteger rInv = r.modInverse(n);
/*     */     
/* 261 */     Point Q = R.multiplyTwo(s, G, eNeg).multiply(rInv);
/*     */     
/* 263 */     if (Q.isInfinity()) {
/* 264 */       throw new EException("sign_error", "Point is at infinity");
/*     */     }
/*     */     
/* 267 */     return Q;
/*     */   }
/*     */ }


/* Location:              E:\项目\eos\eos4j-1.0.0.jar!\io\eblock\eos4j\ecc\Ecdsa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */