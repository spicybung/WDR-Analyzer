package Collections;

import java.util.ArrayList;
import main.Util;
import wdr.ByteReader;
import wdr.Geometry;
import wdr.Model2;
import wdr.ShaderFx;

/* loaded from: PtrCollection.class */
public class PtrCollection<T> {
    private static final int modelCollection = 1;
    private static final int geometry = 2;
    private static final int shaderfx = 3;
    private int[] _itemOffsets;
    public ArrayList<T> _items;
    private int startOffset;
    public int ptrListOffset;
    public int Count;
    public int Size;
    private int type;
    private ByteReader br;

    public PtrCollection() {
    }

    public PtrCollection(ByteReader br, int type) {
        this.type = type;
        this.br = br;
        Read();
    }

    private void Read() {
        this.startOffset = this.br.getCurrentOffset();
        this.ptrListOffset = this.br.ReadOffset();
        this.Count = this.br.ReadUInt16();
        this.Size = this.br.ReadUInt16();
        this._itemOffsets = new int[this.Count];
        this._items = new ArrayList<>();
        int save = this.br.getCurrentOffset();
        this.br.setCurrentOffset(this.ptrListOffset);
        for (int i = 0; i < this.Count; i += modelCollection) {
            this._itemOffsets[i] = this.br.ReadOffset();
            System.out.println("PCOL Item offset " + i + ": " + this._itemOffsets[i]);
        }
        for (int i2 = 0; i2 < this.Count; i2 += modelCollection) {
            this.br.setCurrentOffset(this._itemOffsets[i2]);
            T item = getType();
            this._items.add(item);
        }
        this.br.setCurrentOffset(save);
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [wdr.Model2, T] */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, wdr.ShaderFx] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, wdr.Geometry] */
    private T getType() {
        switch (this.type) {
            case modelCollection /* 1 */:
                ?? r0 = (T) new Model2();
                r0.Read(this.br);
                return r0;
            case geometry /* 2 */:
                ?? r02 = (T) new Geometry();
                r02.Read(this.br);
                return r02;
            case shaderfx /* 3 */:
                ?? r03 = (T) new ShaderFx();
                r03.Read(this.br);
                return r03;
            default:
                return (T) new Model2();
        }
    }

    public String[] getDataNames() {
        String[] names = new String[4 + this.Count];
        names[0] = "ptrListOffset";
        int i = 0 + modelCollection;
        names[i] = "Count";
        int i2 = i + modelCollection;
        names[i2] = "Size";
        int i3 = i2 + modelCollection;
        names[i3] = "[Start PtrList]";
        int i4 = i3 + modelCollection;
        for (int i22 = 0; i22 < this.Count; i22 += modelCollection) {
            names[i4] = "  Pointer " + (i22 + modelCollection) + this._items;
            i4 += modelCollection;
        }
        return names;
    }

    public String[] getDataValues() {
        String[] values = new String[4 + this.Count];
        values[0] = Util.getHexString(this.ptrListOffset);
        int i = 0 + modelCollection;
        values[i] = "" + this.Count;
        int i2 = i + modelCollection;
        values[i2] = "" + this.Size;
        int i3 = i2 + modelCollection;
        values[i3] = "";
        int i4 = i3 + modelCollection;
        for (int i22 = 0; i22 < this.Count; i22 += modelCollection) {
            values[i4] = Util.getHexString(this._itemOffsets[i22]);
            i4 += modelCollection;
        }
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
