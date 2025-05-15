package wdr;

import main.Util;

/* loaded from: VertexBuffer.class */
public class VertexBuffer {
    public int startOffset;
    public int VTable;
    public int VertexCount;
    public int Unknown1;
    public int DataOffset;
    public int StrideSize;
    public int vertexDeclOffset;
    public int Unknown2;
    public int DataOffset2;
    public int p2Offset;
    public byte[] RawData;
    public VertexDeclaration VertexDeclaration;

    public VertexBuffer() {
    }

    public VertexBuffer(ByteReader br) {
        Read(br);
    }

    public void ReadData(ByteReader br) {
        br.setSystemMemory(false);
        br.setCurrentOffset(this.DataOffset);
        this.RawData = br.ReadBytes(this.VertexCount * this.StrideSize);
        br.setSystemMemory(true);
    }

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        this.VertexCount = br.ReadUInt16();
        this.Unknown1 = br.ReadUInt16();
        this.DataOffset = br.ReadDataOffset();
        System.out.println("DataOffset: " + this.DataOffset);
        this.StrideSize = br.ReadUInt32();
        this.vertexDeclOffset = br.ReadOffset();
        this.Unknown2 = br.ReadUInt32();
        this.DataOffset2 = br.ReadDataOffset();
        System.out.println("DataOffset2: " + this.DataOffset2);
        this.p2Offset = br.ReadOffset();
        ReadData(br);
        br.setCurrentOffset(this.vertexDeclOffset);
        this.VertexDeclaration = new VertexDeclaration(br);
    }

    public String[] getDataNames() {
        String[] names = {"VTable", "VertexCount", "Unknown1", "DataOffset", "StrideSize", "vertexDeclOffset", "Unknown2", "DataOffset2", "p2Offset", "RawData(Length)"};
        return names;
    }

    public String[] getDataValues() {
        String[] values = {"" + this.VTable, "" + this.VertexCount, "" + this.Unknown1, Util.getHexString(this.DataOffset), "" + this.StrideSize, Util.getHexString(this.vertexDeclOffset), "" + this.Unknown2, Util.getHexString(this.DataOffset2), Util.getHexString(this.p2Offset), "" + this.RawData.length};
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
