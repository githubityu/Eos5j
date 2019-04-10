//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j.utils;

import android.os.Build;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class ByteUtils {
    static String charmap = ".12345abcdefghijklmnopqrstuvwxyz";

    public ByteUtils() {
    }

    public static int charidx(char c) {
        return charmap.indexOf(c);
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] copy(byte[] src, int start, int length) {
        byte[] c = new byte[length];
        System.arraycopy(src, start, c, 0, length);
        return c;
    }

    public static byte[] copy(byte[] src, int start, byte[] dest, int dstart, int length) {
        System.arraycopy(src, start, dest, dstart, length);
        return dest;
    }

//    public static int[] LongToBytes(Long n) {
//        ByteBuffer hi = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(n);
//        byte[] buf = hi.array();
//        int[] a = IntStream.range(0, buf.length).map((i) -> {
//            return buf[i] & 255;
//        }).toArray();
//        return a;
//    }

    public static int[] LongToBytes(Long n) {
        ByteBuffer hi = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(n);
        byte[] buf = hi.array();
            int[] b = new int[buf.length];
            for (int i = 0; i < buf.length ; i++) {
                b[i] = buf[i] & 255;
            }
            return b;
    }
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();

        for(int i = 0; i < chars.length; ++i) {
            if (i != chars.length - 1) {
                sbu.append(chars[i]);
            } else {
                sbu.append(chars[i]);
            }
        }

        return sbu.toString();
    }

    public static byte[] writerUnit32(String value) {
        Long l = Long.parseLong(value);
        if (l <= 2147483647L) {
            return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(Integer.parseInt(value)).array();
        } else {
            byte[] b = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(l).array();
            int j = 0;

            for(int i = b.length - 1; i >= 0 && b[i] == 0; --i) {
                ++j;
            }

            return copy(b, 0, b.length - j);
        }
    }

    public static byte[] writerUnit16(String value) {
        long vl = Long.parseLong(value);
        return new byte[]{(byte)((int)(vl & 255L)), (byte)((int)((vl & 65280L) >>> 8))};
    }

    public static byte[] writerUnit8(String value) {
        long vl = Long.parseLong(value);
        return new byte[]{(byte)((int)(vl & 255L))};
    }

    public static byte[] writerVarint32(String v) {
        long value = Long.parseLong(v);
        byte[] a = new byte[0];

        for(value >>>= 0; value >= 128L; value >>>= 7) {
            long b = value & 127L | 128L;
            a = concat(a, new byte[]{(byte)((int)b)});
        }

        a = concat(a, new byte[]{(byte)((int)value)});
        return a;
    }

    public static byte[] writerAsset(String v) {
        String[] _value = v.split(" ");
        String amount = _value[0];
        if (amount != null && amount.matches("^[0-9]+(.[0-9]+)?$")) {
            String sym = _value[1];
            String precision = sym.split(",")[0];
            String symbol = sym.split(",")[1].split("@")[0];
            String[] part = amount.split("[.]");
            int pad = Integer.parseInt(precision);
            StringBuffer bf = new StringBuffer(part[0] + ".");
            if (part.length > 1) {
                if (part[1].length() > pad) {
                    throw new EException("precision_error", "precision max " + pad);
                }

                pad = Integer.parseInt(precision) - part[1].length();
                bf.append(part[1]);
            }

            for(int i = 0; i < pad; ++i) {
                bf.append("0");
            }

            (new StringBuilder()).append(precision).append(",").append(symbol).toString();
            amount = bf.toString().replace(".", "");
            ByteBuffer ammount = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(Long.parseLong(amount));
            StringBuffer padStr = new StringBuffer();

            for(int i = 0; i < 7 - symbol.length(); ++i) {
                padStr.append("\u0000");
            }

            char c = (char)Integer.parseInt(precision);
            String asset = c + symbol + padStr;
            ByteBuffer ba = ByteBuffer.wrap(asset.getBytes());
            return concat(ammount.array(), ba.array());
        } else {
            throw new EException("amount_error", "amount error");
        }
    }

    public static byte[] writerSymbol(String v) {
        String[] _value = v.split(" ");
        String amount = _value[0];
        if (amount != null && amount.matches("^[0-9]+(.[0-9]+)?$")) {
            String sym = _value[1];
            String precision = sym.split(",")[0];
            String symbol = sym.split(",")[1].split("@")[0];
            String[] part = amount.split("[.]");
            int pad = Integer.parseInt(precision);
            StringBuffer bf = new StringBuffer(part[0] + ".");
            if (part.length > 1) {
                if (part[1].length() > pad) {
                    throw new EException("precision_error", "precision max " + pad);
                }

                pad = Integer.parseInt(precision) - part[1].length();
                bf.append(part[1]);
            }

            for(int i = 0; i < pad; ++i) {
                bf.append("0");
            }

            (new StringBuilder()).append(precision).append(",").append(symbol).toString();
            StringBuffer padStr = new StringBuffer();

            for(int i = 0; i < 7 - symbol.length(); ++i) {
                padStr.append("\u0000");
            }

            char c = (char)Integer.parseInt(precision);
            String asset = c + symbol + padStr;
            ByteBuffer ba = ByteBuffer.wrap(asset.getBytes());
            return ba.array();
        } else {
            throw new EException("amount_error", "amount error");
        }
    }

    public static byte[] writeName(String v) {
        StringBuffer bitstr = new StringBuffer();

        for(int i = 0; i <= 12; ++i) {
            int c = i < v.length() ? charidx(v.charAt(i)) : 0;
            int bitlen = i < 12 ? 5 : 4;
            String bits = Integer.toBinaryString(c);
            if (bits.length() > bitlen) {
                throw new EException("", "Invalid name " + v);
            }

            StringBuffer sb = new StringBuffer("");

            for(int j = 0; j < bitlen - bits.length(); ++j) {
                sb.append("0");
            }

            bits = sb + bits;
            bitstr.append(bits);
        }

        BigInteger lv = new BigInteger(bitstr.toString(), 2);
        StringBuffer leHex = new StringBuffer();
        int[] bytes = LongToBytes(lv.longValue());

        for(int i = 0; i < bytes.length; ++i) {
            int b = bytes[i];
            String n = Integer.toHexString(b);
            leHex.append(n.length() == 1 ? "0" : "").append(n);
        }

        BigInteger ulName = new BigInteger(leHex.toString(), 16);
        return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(ulName.longValue()).array();
    }

    private static long charCount(String v) {
        long c = 0L;
        char[] var3 = v.toCharArray();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char cp = var3[var5];
            if (cp < 128) {
                ++c;
            } else if (cp < 2048) {
                c += 2L;
            } else if (cp < 65536) {
                c += 3L;
            } else {
                c += 4L;
            }
        }

        return c;
    }

    public static byte[] writerString(String v) {
        long value = charCount(v);
        byte[] a = new byte[0];

        for(value >>>= 0; value >= 128L; value >>>= 7) {
            long b = value & 127L | 128L;
            a = concat(a, new byte[]{(byte)((int)b)});
        }

        a = concat(a, new byte[]{(byte)((int)value)});
        char[] var8 = v.toCharArray();
        int var5 = var8.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            char c = var8[var6];
            a = concat(a, decodeChar(c));
        }

        return a;
    }

    private static byte[] decodeChar(char ca) {
        long cp = (long)ca;
        long a;
        if (cp < 128L) {
            a = cp & 127L;
            return new byte[]{(byte)((int)a)};
        } else {
            long b;
            if (cp < 2048L) {
                a = cp >> 6 & 31L | 192L;
                b = cp & 63L | 128L;
                return new byte[]{(byte)((int)a), (byte)((int)b)};
            } else {
                long c;
                if (cp < 65536L) {
                    a = cp >> 12 & 15L | 224L;
                    b = cp >> 6 & 63L | 128L;
                    c = cp & 63L | 128L;
                    return new byte[]{(byte)((int)a), (byte)((int)b), (byte)((int)c)};
                } else {
                    a = cp >> 18 & 7L | 240L;
                    b = cp >> 12 & 63L | 128L;
                    c = cp >> 6 & 63L | 128L;
                    long d = cp & 63L | 128L;
                    return new byte[]{(byte)((int)a), (byte)((int)b), (byte)((int)c), (byte)((int)d)};
                }
            }
        }
    }

    private static byte[] writerKeyStr(String v) {
        v = v.replace("EOS", "");
        byte[] b = Base58.decode(v);
        b = ByteBuffer.allocate(b.length).order(ByteOrder.BIG_ENDIAN).put(b).array();
        byte[] key = copy(b, 0, b.length - 4);
        return key;
    }

    public static byte[] writerKey(String key) {
        io.eblock.eos4j.utils.ByteBuffer bf = new io.eblock.eos4j.utils.ByteBuffer();
        bf.concat(writerUnit32("1"));
        bf.concat(writerVarint32("1"));
        bf.concat(writerVarint32("0"));
        bf.concat(writerKeyStr(key));
        bf.concat(writerUnit16("1"));
        bf.concat(writerVarint32("0"));
        bf.concat(writerVarint32("0"));
        return bf.getBuffer();
    }

    public static byte[] writeUint64(String v) {
        return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(Long.parseLong(v)).array();
    }
}
