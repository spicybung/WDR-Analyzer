package wdr;

import java.util.ArrayList;
import main.Util;

/* loaded from: ShaderParamTexture.class */
public class ShaderParamTexture {
    public int startOffset;
    private int VTable;
    private int Unknown1;
    private int Unknown2;
    private int Unknown3;
    private int Unknown4;
    private int Unknown5;
    private int TextureNameOffset;
    private int Unknown7;
    public String TextureName;

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        this.Unknown1 = br.ReadUInt32();
        this.Unknown2 = br.ReadUInt16();
        this.Unknown3 = br.ReadUInt16();
        this.Unknown4 = br.ReadUInt32();
        this.Unknown5 = br.ReadUInt32();
        this.TextureNameOffset = br.ReadOffset();
        this.Unknown7 = br.ReadUInt32();
        br.setCurrentOffset(this.TextureNameOffset);
        this.TextureName = br.ReadNullTerminatedString();
        System.out.println("Texture name: " + this.TextureName);
    }

    public String[] getDataNames() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("VTable");
        nameList.add("Unknown1");
        nameList.add("Unknown2");
        nameList.add("Unknown3");
        nameList.add("Unknown4");
        nameList.add("Unknown5");
        nameList.add("TextureNameOffset");
        nameList.add("Unknown7");
        nameList.add("TextureName");
        String[] names = new String[nameList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = nameList.get(i);
        }
        return names;
    }

    public String[] getDataValues() {
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add("" + this.VTable);
        valueList.add("" + this.Unknown1);
        valueList.add("" + this.Unknown2);
        valueList.add("" + this.Unknown3);
        valueList.add("" + this.Unknown4);
        valueList.add("" + this.Unknown5);
        valueList.add("" + Util.getHexString(this.TextureNameOffset));
        valueList.add("" + this.Unknown7);
        valueList.add("" + this.TextureName);
        String[] values = new String[valueList.size()];
        for (int i = 0; i < valueList.size(); i++) {
            values[i] = valueList.get(i);
        }
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
