package wdr;

import main.Util;

/* loaded from: Geometry.class */
public class Geometry {
    private int startOffset;
    private int VTable;
    private int Unknown1;
    private int Unknown2;
    public int vertexBuffersOffset;
    private int Unknown3;
    private int Unknown4;
    private int Unknown5;
    public int indexBuffersOffset;
    private int Unknown6;
    private int Unknown7;
    private int Unknown8;
    public int IndexCount;
    public int FaceCount;
    public int VertexCount;
    public int PrimitiveType;
    private int Unknown9;
    public int VertexStride;
    private int Unknown10;
    private int Unknown11;
    private int Unknown12;
    private int Unknown13;
    public VertexBuffer vertexBuffer;
    public IndexBuffer indexBuffer;

    public void Read(ByteReader br) {
        System.out.println("Geometry");
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        System.out.println("VTable: " + this.VTable);
        this.Unknown1 = br.ReadUInt32();
        this.Unknown2 = br.ReadUInt32();
        this.vertexBuffersOffset = br.ReadOffset();
        System.out.println("VertexBufferOffset: " + this.vertexBuffersOffset);
        this.Unknown3 = br.ReadUInt32();
        this.Unknown4 = br.ReadUInt32();
        this.Unknown5 = br.ReadUInt32();
        this.indexBuffersOffset = br.ReadOffset();
        System.out.println("IndexBufferOffset: " + this.indexBuffersOffset);
        this.Unknown6 = br.ReadUInt32();
        this.Unknown7 = br.ReadUInt32();
        this.Unknown8 = br.ReadUInt32();
        this.IndexCount = br.ReadUInt32();
        this.FaceCount = br.ReadUInt32();
        this.VertexCount = br.ReadUInt16();
        this.PrimitiveType = br.ReadUInt16();
        System.out.println("IC: " + this.IndexCount);
        System.out.println("FC: " + this.FaceCount);
        System.out.println("VC: " + this.VertexCount);
        System.out.println("PT: " + this.PrimitiveType);
        this.Unknown9 = br.ReadUInt32();
        this.VertexStride = br.ReadUInt16();
        this.Unknown10 = br.ReadUInt16();
        this.Unknown11 = br.ReadUInt32();
        this.Unknown12 = br.ReadUInt32();
        this.Unknown13 = br.ReadUInt32();
        br.setCurrentOffset(this.vertexBuffersOffset);
        this.vertexBuffer = new VertexBuffer(br);
        br.setCurrentOffset(this.indexBuffersOffset);
        this.indexBuffer = new IndexBuffer(br);
    }

    public String[] getDataNames() {
        String[] names = {"Unknown1", "Unknown2", "vertexBuffersOffset", "Unknown3", "Unknown4", "Unknown5", "indexBuffersOffset", "Unknown6", "Unknown7", "Unknown8", "IndexCount", "FaceCount", "VertexCount", "PrimitiveType", "Unknown9", "VertexStride", "Unknown10", "Unknown11", "Unknown12", "Unknown13"};
        return names;
    }

    public String[] getDataValues() {
        String[] values = {"" + this.Unknown1, "" + this.Unknown2, Util.getHexString(this.vertexBuffersOffset), "" + this.Unknown3, "" + this.Unknown4, "" + this.Unknown5, Util.getHexString(this.indexBuffersOffset), "" + this.Unknown6, "" + this.Unknown7, "" + this.Unknown8, "" + this.IndexCount, "" + this.FaceCount, "" + this.VertexCount, "" + this.PrimitiveType, "" + this.Unknown9, "" + this.VertexStride, "" + this.Unknown10, "" + this.Unknown11, "" + this.Unknown12, "" + this.Unknown13};
        return values;
    }

    public String toString() {
        return "Geometry";
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
