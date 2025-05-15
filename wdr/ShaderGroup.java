package wdr;

import Collections.PtrCollection;
import Collections.SimpleArray;
import Collections.SimpleCollection;
import main.Util;

/* loaded from: ShaderGroup.class */
public class ShaderGroup {
    private int startOffset;
    public int VTable;
    public int TextureDictionaryOffset;
    public PtrCollection<ShaderFx> Shaders;
    private SimpleArray<Integer> Zeros;
    private SimpleCollection<Integer> VertexDeclarationUsageFlags;
    private SimpleCollection<Integer> Data3;

    public ShaderGroup(ByteReader br) {
        Read(br);
    }

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        this.TextureDictionaryOffset = br.ReadOffset();
        this.Shaders = new PtrCollection<>(br, 3);
        this.Zeros = new SimpleArray<>(br, 12, 0);
        this.VertexDeclarationUsageFlags = new SimpleCollection<>(br, 0);
        this.Data3 = new SimpleCollection<>(br, 0);
    }

    public String[] getDataNames() {
        String[] names = {"VTable", "TextureDictionaryOffset", "Shaders(Length)", "Zeros(Length)", "VertexDeclarationUsageFlags(Length)", "Data3(Length)"};
        return names;
    }

    public String[] getDataValues() {
        String[] values = {"" + this.VTable, Util.getHexString(this.TextureDictionaryOffset), "" + this.Shaders.Count, "" + this.Zeros.Count, "" + this.VertexDeclarationUsageFlags.Count, "" + this.Data3.Count};
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
