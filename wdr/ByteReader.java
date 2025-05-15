package wdr;

/* loaded from: ByteReader.class */
public class ByteReader {
    private byte[] stream;
    private int currentOffset;
    private boolean system = true;
    private int sysSize = 0;

    public ByteReader(byte[] stream, int startOffset) {
        this.stream = stream;
        this.currentOffset = startOffset;
    }

    public int ReadUInt32() {
        int cnt = 0;
        byte[] tmp = new byte[4];
        for (int i = this.currentOffset; i < this.currentOffset + 4; i++) {
            tmp[cnt] = this.stream[i];
            cnt++;
        }
        long accum = 0;
        int i2 = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
            accum |= (tmp[i2] & 255) << shiftBy;
            i2++;
        }
        this.currentOffset += 4;
        return (int) accum;
    }

    public int ReadOffset() {
        int value;
        int offset = ReadUInt32();
        if (offset == 0) {
            value = 0;
        } else {
            if ((offset >> 28) != 5) {
                System.out.println("Geen offset");
            }
            value = offset & 268435455;
        }
        return value;
    }

    public Vector4 readVector() {
        float x = arr2float();
        float y = arr2float();
        float z = arr2float();
        float w = arr2float();
        Vector4 vec = new Vector4(x, y, z, w);
        return vec;
    }

    public float arr2float() {
        int accum = 0;
        int i = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
            accum = (int) (accum | ((this.stream[this.currentOffset + i] & 255) << shiftBy));
            i++;
        }
        this.currentOffset += 4;
        return Float.intBitsToFloat(accum);
    }

    public int ReadUInt16() {
        int low = this.stream[this.currentOffset] & 255;
        int high = this.stream[this.currentOffset + 1] & 255;
        this.currentOffset += 2;
        return (high << 8) | low;
    }

    public int ReadDataOffset() {
        int value;
        int offset = ReadUInt32();
        if (offset == 0) {
            value = 0;
        } else {
            if ((offset >> 28) != 6) {
                System.out.println("Expected a data offset.");
            }
            value = offset & 268435455;
        }
        return value;
    }

    public String ReadNullTerminatedString() {
        String sb = "";
        byte b = this.stream[this.currentOffset];
        while (true) {
            char c = (char) b;
            if (c != 0) {
                sb = sb + c;
                this.currentOffset++;
                b = this.stream[this.currentOffset];
            } else {
                return sb;
            }
        }
    }

    public byte[] ReadBytes(int bytes) {
        byte[] arr = new byte[bytes];
        for (int i = 0; i < bytes; i++) {
            arr[i] = this.stream[this.currentOffset];
            this.currentOffset++;
        }
        return arr;
    }

    public byte ReadByte() {
        this.currentOffset++;
        return this.stream[this.currentOffset - 1];
    }

    public int getCurrentOffset() {
        return this.currentOffset;
    }

    public void setCurrentOffset(int offset) {
        this.currentOffset = offset;
        System.out.println("CurrentOffset: " + this.currentOffset);
        if (!this.system) {
            this.currentOffset += this.sysSize;
            System.out.println("New currentOffset: " + this.currentOffset);
        }
    }

    public void setSysSize(int size) {
        this.sysSize = size;
    }

    public void setSystemMemory(boolean system) {
        this.system = system;
    }
}
