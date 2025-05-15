package Collections;

import java.util.ArrayList;
import wdr.ByteReader;

/* loaded from: SimpleArray.class */
public class SimpleArray<T> {
    public ArrayList<T> Values;
    public int Count;
    public int type;
    private static final int ReadUInt32 = 0;
    private static final int ReadOffset = 1;
    private static final int ReadByte = 2;
    private static final int ReadShort = 3;
    private static final int ReadVector4 = 4;

    public SimpleArray(ByteReader br, int count, int type) {
        this.Count = count;
        this.type = type;
        Read(br);
    }

    public void Read(ByteReader br) {
        this.Values = new ArrayList<>(this.Count);
        for (int i = ReadUInt32; i < this.Count; i += ReadOffset) {
            this.Values.add(ReadData(br));
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [wdr.Vector4, T] */
    public T ReadData(ByteReader byteReader) {
        switch (this.type) {
            case ReadUInt32 /* 0 */:
                T t = (T) Integer.valueOf(byteReader.ReadUInt32());
                System.out.println("ARRAYData: " + t);
                return t;
            case ReadOffset /* 1 */:
                T t2 = (T) Integer.valueOf(byteReader.ReadOffset());
                System.out.println("Offset: " + t2);
                return t2;
            case ReadByte /* 2 */:
                T t3 = (T) Byte.valueOf(byteReader.ReadByte());
                System.out.println("Byte: " + t3);
                return t3;
            case ReadShort /* 3 */:
                T t4 = (T) Integer.valueOf(byteReader.ReadUInt16());
                System.out.println("Short: " + t4);
                return t4;
            case ReadVector4 /* 4 */:
                ?? r0 = (T) byteReader.readVector();
                r0.print("SimpleArray");
                return r0;
            default:
                return (T) Integer.valueOf(byteReader.ReadUInt32());
        }
    }
}
