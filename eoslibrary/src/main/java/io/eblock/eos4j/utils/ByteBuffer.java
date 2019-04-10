//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.eblock.eos4j.utils;

public class ByteBuffer {
    private byte[] buffer = new byte[0];

    public ByteBuffer() {
    }

    public void concat(byte[] b) {
        this.buffer = ByteUtils.concat(this.buffer, b);
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }
}
