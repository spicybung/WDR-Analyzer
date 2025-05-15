package wdr;

import main.Util;

/* loaded from: IndexBuffer.class */
public class IndexBuffer {
    public int startOffset;
    public int VTable;
    public int IndexCount;
    public int DataOffset;
    public int p1Offset;
    public byte[] RawData;

    public IndexBuffer(ByteReader br) {
        Read(br);
    }

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        System.out.println("VTable: " + this.VTable);
        this.IndexCount = br.ReadUInt32();
        System.out.println("IndexCount: " + this.IndexCount);
        this.DataOffset = br.ReadDataOffset();
        this.p1Offset = br.ReadOffset();
        ReadData(br);
    }

    public void ReadData(ByteReader br) {
        br.setSystemMemory(false);
        br.setCurrentOffset(this.DataOffset);
        this.RawData = br.ReadBytes(this.IndexCount * 2);
        br.setSystemMemory(true);
    }

    public String[] getDataNames() {
        String[] names = {"IndexCount", "DataOffset", "p1Offset", "RawData(Length)"};
        return names;
    }

    public String[] getDataValues() {
        String[] values = {"" + this.IndexCount, Util.getHexString(this.DataOffset), Util.getHexString(this.p1Offset), "" + this.RawData.length};
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
