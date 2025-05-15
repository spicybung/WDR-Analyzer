package Collections;

import java.util.ArrayList;
import wdr.ByteReader;

/* loaded from: SimpleCollection.class */
public class SimpleCollection<T> {
    public ArrayList<T> Values;
    public int Count;
    public int Size;
    public int type;

    public SimpleCollection(ByteReader br, int type) {
        this.type = type;
        Read(br);
    }

    public void Read(ByteReader br) {
        int offset = br.ReadOffset();
        System.out.println("Offset: " + offset);
        this.Count = br.ReadUInt16();
        this.Size = br.ReadUInt16();
        this.Values = new ArrayList<>(this.Count);
        int save = br.getCurrentOffset();
        br.setCurrentOffset(offset);
        for (int i = 0; i < this.Count; i++) {
            this.Values.add(ReadData(br));
        }
        br.setCurrentOffset(save);
    }

    public T ReadData(ByteReader byteReader) {
        switch (this.type) {
            case 0:
                T t = (T) Integer.valueOf(byteReader.ReadUInt32());
                System.out.println("COLLData: " + t);
                return t;
            default:
                return (T) Integer.valueOf(byteReader.ReadUInt32());
        }
    }
}
