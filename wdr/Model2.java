package wdr;

import Collections.PtrCollection;
import Collections.SimpleArray;
import main.Util;

/* loaded from: Model2.class */
public class Model2 {
    public int startOffset;
    public int VTable;
    public PtrCollection<Geometry> Geometries;
    public int unknownVectorOffsets;
    public int materialMappingOffset;
    private int Unknown1;
    private int Unknown2;
    private int Unknown3;
    private int GeoCount;
    public SimpleArray<Vector4> UnknownVectors;
    public SimpleArray<Integer> ShaderMappings;

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        this.Geometries = new PtrCollection<>(br, 2);
        this.unknownVectorOffsets = br.ReadOffset();
        this.materialMappingOffset = br.ReadOffset();
        System.out.println("UnkownVectorOffsets: " + this.unknownVectorOffsets);
        System.out.println("MaterialMappingOffset: " + this.materialMappingOffset);
        this.Unknown1 = br.ReadUInt16();
        this.Unknown2 = br.ReadUInt16();
        this.Unknown3 = br.ReadUInt16();
        this.GeoCount = br.ReadUInt16();
        br.setCurrentOffset(this.unknownVectorOffsets);
        this.UnknownVectors = new SimpleArray<>(br, this.GeoCount, 4);
        br.setCurrentOffset(this.materialMappingOffset);
        this.ShaderMappings = new SimpleArray<>(br, this.Geometries.Count, 3);
    }

    public String toString() {
        return "Model";
    }

    public String[] getDataNames() {
        String[] names = new String[9 + this.UnknownVectors.Count + this.ShaderMappings.Count];
        names[0] = "VTable";
        int i = 0 + 1;
        names[i] = "unknownVectorOffsets";
        int i2 = i + 1;
        names[i2] = "materialMappingOffset";
        int i3 = i2 + 1;
        names[i3] = "Unknown1";
        int i4 = i3 + 1;
        names[i4] = "Unknown2";
        int i5 = i4 + 1;
        names[i5] = "Unknown3";
        int i6 = i5 + 1;
        names[i6] = "GeoCount";
        int i7 = i6 + 1;
        names[i7] = "[UnknownVectors]";
        int i8 = i7 + 1;
        for (int i22 = 0; i22 < this.UnknownVectors.Count; i22++) {
            names[i8] = "  Vector " + (i22 + 1);
            i8++;
        }
        names[i8] = "[ShaderMappings]";
        int i9 = i8 + 1;
        for (int i23 = 0; i23 < this.ShaderMappings.Count; i23++) {
            names[i9] = "  ShaderMapping " + (i23 + 1);
            i9++;
        }
        return names;
    }

    public String[] getDataValues() {
        String[] values = new String[9 + this.UnknownVectors.Count + this.ShaderMappings.Count];
        values[0] = "" + this.VTable;
        int i = 0 + 1;
        values[i] = Util.getHexString(this.unknownVectorOffsets);
        int i2 = i + 1;
        values[i2] = Util.getHexString(this.materialMappingOffset);
        int i3 = i2 + 1;
        values[i3] = "" + this.Unknown1;
        int i4 = i3 + 1;
        values[i4] = "" + this.Unknown2;
        int i5 = i4 + 1;
        values[i5] = "" + this.Unknown3;
        int i6 = i5 + 1;
        values[i6] = "" + this.GeoCount;
        int i7 = i6 + 1;
        values[i7] = "";
        int i8 = i7 + 1;
        for (int i22 = 0; i22 < this.UnknownVectors.Count; i22++) {
            values[i8] = "" + this.UnknownVectors.Values.get(i22);
            i8++;
        }
        values[i8] = "";
        int i9 = i8 + 1;
        for (int i23 = 0; i23 < this.ShaderMappings.Count; i23++) {
            values[i9] = "" + this.ShaderMappings.Values.get(i23);
            i9++;
        }
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
