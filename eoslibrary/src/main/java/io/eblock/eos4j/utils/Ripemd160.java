//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j.utils;

public class Ripemd160 {
    private final byte[] mDigestBytes;

    public Ripemd160(byte[] digest) {
        this.mDigestBytes = digest;
    }

    public static Ripemd160 from(byte[] data) {
        return from(data, 0, data != null ? data.length : 0);
    }

    public static Ripemd160 from(byte[] data, int startOffset, int length) {
        Digest digest = new Digest();
        digest.update(data, startOffset, length);
        byte[] result = new byte[20];
        digest.doFinal(result, 0);
        return new Ripemd160(result);
    }

    public byte[] bytes() {
        return this.mDigestBytes;
    }

    public static class Digest extends GeneralDigest {
        static final int DIGEST_LENGTH = 20;
        private int H0;
        private int H1;
        private int H2;
        private int H3;
        private int H4;
        private int[] X = new int[16];
        private int xOff;

        public Digest() {
            this.reset();
        }

        protected void processWord(byte[] in, int inOff) {
            this.X[this.xOff++] = in[inOff] & 255 | (in[inOff + 1] & 255) << 8 | (in[inOff + 2] & 255) << 16 | (in[inOff + 3] & 255) << 24;
            if (this.xOff == 16) {
                this.processBlock();
            }

        }

        protected void processLength(long bitLength) {
            if (this.xOff > 14) {
                this.processBlock();
            }

            this.X[14] = (int)(bitLength & -1L);
            this.X[15] = (int)(bitLength >>> 32);
        }

        private void unpackWord(int word, byte[] out, int outOff) {
            out[outOff] = (byte)word;
            out[outOff + 1] = (byte)(word >>> 8);
            out[outOff + 2] = (byte)(word >>> 16);
            out[outOff + 3] = (byte)(word >>> 24);
        }

        public int doFinal(byte[] out, int outOff) {
            this.finish();
            this.unpackWord(this.H0, out, outOff);
            this.unpackWord(this.H1, out, outOff + 4);
            this.unpackWord(this.H2, out, outOff + 8);
            this.unpackWord(this.H3, out, outOff + 12);
            this.unpackWord(this.H4, out, outOff + 16);
            this.reset();
            return 20;
        }

        public void reset() {
            super.reset();
            this.H0 = 1732584193;
            this.H1 = -271733879;
            this.H2 = -1732584194;
            this.H3 = 271733878;
            this.H4 = -1009589776;
            this.xOff = 0;

            for(int i = 0; i != this.X.length; ++i) {
                this.X[i] = 0;
            }

        }

        private int RL(int x, int n) {
            return x << n | x >>> 32 - n;
        }

        private int f1(int x, int y, int z) {
            return x ^ y ^ z;
        }

        private int f2(int x, int y, int z) {
            return x & y | ~x & z;
        }

        private int f3(int x, int y, int z) {
            return (x | ~y) ^ z;
        }

        private int f4(int x, int y, int z) {
            return x & z | y & ~z;
        }

        private int f5(int x, int y, int z) {
            return x ^ (y | ~z);
        }

        protected void processBlock() {
            int aa = this.H0;
            int a = this.H0;
            int bb = this.H1;
            int b = this.H1;
            int cc = this.H2;
            int c = this.H2;
            int dd = this.H3;
            int d = this.H3;
            int ee = this.H4;
            int e = this.H4;
            a = this.RL(a + this.f1(b, c, d) + this.X[0], 11) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f1(a, b, c) + this.X[1], 14) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f1(e, a, b) + this.X[2], 15) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f1(d, e, a) + this.X[3], 12) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f1(c, d, e) + this.X[4], 5) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f1(b, c, d) + this.X[5], 8) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f1(a, b, c) + this.X[6], 7) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f1(e, a, b) + this.X[7], 9) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f1(d, e, a) + this.X[8], 11) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f1(c, d, e) + this.X[9], 13) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f1(b, c, d) + this.X[10], 14) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f1(a, b, c) + this.X[11], 15) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f1(e, a, b) + this.X[12], 6) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f1(d, e, a) + this.X[13], 7) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f1(c, d, e) + this.X[14], 9) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f1(b, c, d) + this.X[15], 8) + e;
            c = this.RL(c, 10);
            aa = this.RL(aa + this.f5(bb, cc, dd) + this.X[5] + 1352829926, 8) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f5(aa, bb, cc) + this.X[14] + 1352829926, 9) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f5(ee, aa, bb) + this.X[7] + 1352829926, 9) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f5(dd, ee, aa) + this.X[0] + 1352829926, 11) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f5(cc, dd, ee) + this.X[9] + 1352829926, 13) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f5(bb, cc, dd) + this.X[2] + 1352829926, 15) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f5(aa, bb, cc) + this.X[11] + 1352829926, 15) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f5(ee, aa, bb) + this.X[4] + 1352829926, 5) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f5(dd, ee, aa) + this.X[13] + 1352829926, 7) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f5(cc, dd, ee) + this.X[6] + 1352829926, 7) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f5(bb, cc, dd) + this.X[15] + 1352829926, 8) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f5(aa, bb, cc) + this.X[8] + 1352829926, 11) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f5(ee, aa, bb) + this.X[1] + 1352829926, 14) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f5(dd, ee, aa) + this.X[10] + 1352829926, 14) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f5(cc, dd, ee) + this.X[3] + 1352829926, 12) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f5(bb, cc, dd) + this.X[12] + 1352829926, 6) + ee;
            cc = this.RL(cc, 10);
            e = this.RL(e + this.f2(a, b, c) + this.X[7] + 1518500249, 7) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f2(e, a, b) + this.X[4] + 1518500249, 6) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f2(d, e, a) + this.X[13] + 1518500249, 8) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f2(c, d, e) + this.X[1] + 1518500249, 13) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f2(b, c, d) + this.X[10] + 1518500249, 11) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f2(a, b, c) + this.X[6] + 1518500249, 9) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f2(e, a, b) + this.X[15] + 1518500249, 7) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f2(d, e, a) + this.X[3] + 1518500249, 15) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f2(c, d, e) + this.X[12] + 1518500249, 7) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f2(b, c, d) + this.X[0] + 1518500249, 12) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f2(a, b, c) + this.X[9] + 1518500249, 15) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f2(e, a, b) + this.X[5] + 1518500249, 9) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f2(d, e, a) + this.X[2] + 1518500249, 11) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f2(c, d, e) + this.X[14] + 1518500249, 7) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f2(b, c, d) + this.X[11] + 1518500249, 13) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f2(a, b, c) + this.X[8] + 1518500249, 12) + d;
            b = this.RL(b, 10);
            ee = this.RL(ee + this.f4(aa, bb, cc) + this.X[6] + 1548603684, 9) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f4(ee, aa, bb) + this.X[11] + 1548603684, 13) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f4(dd, ee, aa) + this.X[3] + 1548603684, 15) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f4(cc, dd, ee) + this.X[7] + 1548603684, 7) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f4(bb, cc, dd) + this.X[0] + 1548603684, 12) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f4(aa, bb, cc) + this.X[13] + 1548603684, 8) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f4(ee, aa, bb) + this.X[5] + 1548603684, 9) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f4(dd, ee, aa) + this.X[10] + 1548603684, 11) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f4(cc, dd, ee) + this.X[14] + 1548603684, 7) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f4(bb, cc, dd) + this.X[15] + 1548603684, 7) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f4(aa, bb, cc) + this.X[8] + 1548603684, 12) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f4(ee, aa, bb) + this.X[12] + 1548603684, 7) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f4(dd, ee, aa) + this.X[4] + 1548603684, 6) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f4(cc, dd, ee) + this.X[9] + 1548603684, 15) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f4(bb, cc, dd) + this.X[1] + 1548603684, 13) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f4(aa, bb, cc) + this.X[2] + 1548603684, 11) + dd;
            bb = this.RL(bb, 10);
            d = this.RL(d + this.f3(e, a, b) + this.X[3] + 1859775393, 11) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f3(d, e, a) + this.X[10] + 1859775393, 13) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f3(c, d, e) + this.X[14] + 1859775393, 6) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f3(b, c, d) + this.X[4] + 1859775393, 7) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f3(a, b, c) + this.X[9] + 1859775393, 14) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f3(e, a, b) + this.X[15] + 1859775393, 9) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f3(d, e, a) + this.X[8] + 1859775393, 13) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f3(c, d, e) + this.X[1] + 1859775393, 15) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f3(b, c, d) + this.X[2] + 1859775393, 14) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f3(a, b, c) + this.X[7] + 1859775393, 8) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f3(e, a, b) + this.X[0] + 1859775393, 13) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f3(d, e, a) + this.X[6] + 1859775393, 6) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f3(c, d, e) + this.X[13] + 1859775393, 5) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f3(b, c, d) + this.X[11] + 1859775393, 12) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f3(a, b, c) + this.X[5] + 1859775393, 7) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f3(e, a, b) + this.X[12] + 1859775393, 5) + c;
            a = this.RL(a, 10);
            dd = this.RL(dd + this.f3(ee, aa, bb) + this.X[15] + 1836072691, 9) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f3(dd, ee, aa) + this.X[5] + 1836072691, 7) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f3(cc, dd, ee) + this.X[1] + 1836072691, 15) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f3(bb, cc, dd) + this.X[3] + 1836072691, 11) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f3(aa, bb, cc) + this.X[7] + 1836072691, 8) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f3(ee, aa, bb) + this.X[14] + 1836072691, 6) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f3(dd, ee, aa) + this.X[6] + 1836072691, 6) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f3(cc, dd, ee) + this.X[9] + 1836072691, 14) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f3(bb, cc, dd) + this.X[11] + 1836072691, 12) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f3(aa, bb, cc) + this.X[8] + 1836072691, 13) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f3(ee, aa, bb) + this.X[12] + 1836072691, 5) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f3(dd, ee, aa) + this.X[2] + 1836072691, 14) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f3(cc, dd, ee) + this.X[10] + 1836072691, 13) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f3(bb, cc, dd) + this.X[0] + 1836072691, 13) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f3(aa, bb, cc) + this.X[4] + 1836072691, 7) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f3(ee, aa, bb) + this.X[13] + 1836072691, 5) + cc;
            aa = this.RL(aa, 10);
            c = this.RL(c + this.f4(d, e, a) + this.X[1] + -1894007588, 11) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f4(c, d, e) + this.X[9] + -1894007588, 12) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f4(b, c, d) + this.X[11] + -1894007588, 14) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f4(a, b, c) + this.X[10] + -1894007588, 15) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f4(e, a, b) + this.X[0] + -1894007588, 14) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f4(d, e, a) + this.X[8] + -1894007588, 15) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f4(c, d, e) + this.X[12] + -1894007588, 9) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f4(b, c, d) + this.X[4] + -1894007588, 8) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f4(a, b, c) + this.X[13] + -1894007588, 9) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f4(e, a, b) + this.X[3] + -1894007588, 14) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f4(d, e, a) + this.X[7] + -1894007588, 5) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f4(c, d, e) + this.X[15] + -1894007588, 6) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f4(b, c, d) + this.X[14] + -1894007588, 8) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f4(a, b, c) + this.X[5] + -1894007588, 6) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f4(e, a, b) + this.X[6] + -1894007588, 5) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f4(d, e, a) + this.X[2] + -1894007588, 12) + b;
            e = this.RL(e, 10);
            cc = this.RL(cc + this.f2(dd, ee, aa) + this.X[8] + 2053994217, 15) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f2(cc, dd, ee) + this.X[6] + 2053994217, 5) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f2(bb, cc, dd) + this.X[4] + 2053994217, 8) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f2(aa, bb, cc) + this.X[1] + 2053994217, 11) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f2(ee, aa, bb) + this.X[3] + 2053994217, 14) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f2(dd, ee, aa) + this.X[11] + 2053994217, 14) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f2(cc, dd, ee) + this.X[15] + 2053994217, 6) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f2(bb, cc, dd) + this.X[0] + 2053994217, 14) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f2(aa, bb, cc) + this.X[5] + 2053994217, 6) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f2(ee, aa, bb) + this.X[12] + 2053994217, 9) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f2(dd, ee, aa) + this.X[2] + 2053994217, 12) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f2(cc, dd, ee) + this.X[13] + 2053994217, 9) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f2(bb, cc, dd) + this.X[9] + 2053994217, 12) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f2(aa, bb, cc) + this.X[7] + 2053994217, 5) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f2(ee, aa, bb) + this.X[10] + 2053994217, 15) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f2(dd, ee, aa) + this.X[14] + 2053994217, 8) + bb;
            ee = this.RL(ee, 10);
            b = this.RL(b + this.f5(c, d, e) + this.X[4] + -1454113458, 9) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f5(b, c, d) + this.X[0] + -1454113458, 15) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f5(a, b, c) + this.X[5] + -1454113458, 5) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f5(e, a, b) + this.X[9] + -1454113458, 11) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f5(d, e, a) + this.X[7] + -1454113458, 6) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f5(c, d, e) + this.X[12] + -1454113458, 8) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f5(b, c, d) + this.X[2] + -1454113458, 13) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f5(a, b, c) + this.X[10] + -1454113458, 12) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f5(e, a, b) + this.X[14] + -1454113458, 5) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f5(d, e, a) + this.X[1] + -1454113458, 12) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f5(c, d, e) + this.X[3] + -1454113458, 13) + a;
            d = this.RL(d, 10);
            a = this.RL(a + this.f5(b, c, d) + this.X[8] + -1454113458, 14) + e;
            c = this.RL(c, 10);
            e = this.RL(e + this.f5(a, b, c) + this.X[11] + -1454113458, 11) + d;
            b = this.RL(b, 10);
            d = this.RL(d + this.f5(e, a, b) + this.X[6] + -1454113458, 8) + c;
            a = this.RL(a, 10);
            c = this.RL(c + this.f5(d, e, a) + this.X[15] + -1454113458, 5) + b;
            e = this.RL(e, 10);
            b = this.RL(b + this.f5(c, d, e) + this.X[13] + -1454113458, 6) + a;
            d = this.RL(d, 10);
            bb = this.RL(bb + this.f1(cc, dd, ee) + this.X[12], 8) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f1(bb, cc, dd) + this.X[15], 5) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f1(aa, bb, cc) + this.X[10], 12) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f1(ee, aa, bb) + this.X[4], 9) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f1(dd, ee, aa) + this.X[1], 12) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f1(cc, dd, ee) + this.X[5], 5) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f1(bb, cc, dd) + this.X[8], 14) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f1(aa, bb, cc) + this.X[7], 6) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f1(ee, aa, bb) + this.X[6], 8) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f1(dd, ee, aa) + this.X[2], 13) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f1(cc, dd, ee) + this.X[13], 6) + aa;
            dd = this.RL(dd, 10);
            aa = this.RL(aa + this.f1(bb, cc, dd) + this.X[14], 5) + ee;
            cc = this.RL(cc, 10);
            ee = this.RL(ee + this.f1(aa, bb, cc) + this.X[0], 15) + dd;
            bb = this.RL(bb, 10);
            dd = this.RL(dd + this.f1(ee, aa, bb) + this.X[3], 13) + cc;
            aa = this.RL(aa, 10);
            cc = this.RL(cc + this.f1(dd, ee, aa) + this.X[9], 11) + bb;
            ee = this.RL(ee, 10);
            bb = this.RL(bb + this.f1(cc, dd, ee) + this.X[11], 11) + aa;
            dd = this.RL(dd, 10);
            dd += c + this.H1;
            this.H1 = this.H2 + d + ee;
            this.H2 = this.H3 + e + aa;
            this.H3 = this.H4 + a + bb;
            this.H4 = this.H0 + b + cc;
            this.H0 = dd;
            this.xOff = 0;

            for(int i = 0; i != this.X.length; ++i) {
                this.X[i] = 0;
            }

        }
    }
}
