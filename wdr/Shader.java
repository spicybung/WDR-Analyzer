package wdr;

import Collections.SimpleArray;
import java.util.ArrayList;
import main.Util;

/* loaded from: Shader.class */
public class Shader {
    private int startOffset;
    private int VTable;
    private int BlockMapAdress;
    private int Unknown1;
    private byte Unknown2;
    private byte Unknown3;
    private int Unknown4;
    private int Unknown4_1;
    private int Unknown5;
    private int Unknown6;
    public int shaderParamOffsetsOffset;
    public int ShaderParamCount;
    private int Unknown8;
    public int shaderParamTypesOffset;
    public int shaderParamNameOffset;
    public int Hash;
    private int Unknown9;
    private int Unknown10;
    private int Unknown11;
    private int Unknown12;
    private int Unknown13;
    public SimpleArray<Integer> ShaderParamOffsets;
    public SimpleArray<Byte> ShaderParamTypes;
    public SimpleArray<Integer> ShaderParamNames;
    public ArrayList ShaderParams = new ArrayList();

    public Shader(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.VTable = br.ReadUInt32();
        this.BlockMapAdress = br.ReadOffset();
        this.Unknown1 = br.ReadUInt16();
        this.Unknown2 = br.ReadByte();
        this.Unknown3 = br.ReadByte();
        this.Unknown4 = br.ReadUInt16();
        this.Unknown4_1 = br.ReadUInt16();
        this.Unknown5 = br.ReadUInt32();
        this.shaderParamOffsetsOffset = br.ReadOffset();
        this.Unknown6 = br.ReadUInt32();
        this.ShaderParamCount = br.ReadUInt32();
        this.Unknown8 = br.ReadUInt32();
        this.shaderParamTypesOffset = br.ReadOffset();
        this.Hash = br.ReadUInt32();
        this.Unknown9 = br.ReadUInt32();
        this.Unknown10 = br.ReadUInt32();
        this.shaderParamNameOffset = br.ReadOffset();
        this.Unknown11 = br.ReadUInt32();
        this.Unknown12 = br.ReadUInt32();
        this.Unknown13 = br.ReadUInt32();
        int save = br.getCurrentOffset();
        br.setCurrentOffset(this.shaderParamOffsetsOffset);
        this.ShaderParamOffsets = new SimpleArray<>(br, this.ShaderParamCount, 1);
        br.setCurrentOffset(this.shaderParamTypesOffset);
        this.ShaderParamTypes = new SimpleArray<>(br, this.ShaderParamCount, 2);
        br.setCurrentOffset(this.shaderParamNameOffset);
        this.ShaderParamNames = new SimpleArray<>(br, this.ShaderParamCount, 0);
        for (int i = 0; i < this.ShaderParamCount; i++) {
            br.setCurrentOffset(((Integer) this.ShaderParamOffsets.Values.get(i)).intValue());
            switch (((Byte) this.ShaderParamTypes.Values.get(i)).byteValue()) {
                case 0:
                    ShaderParamTexture test = new ShaderParamTexture();
                    test.Read(br);
                    this.ShaderParams.add(test);
                    break;
                case 1:
                    ShaderParamVector test1 = new ShaderParamVector();
                    test1.Read(br);
                    this.ShaderParams.add(test1);
                    break;
                case 4:
                    ShaderParamMatrix test2 = new ShaderParamMatrix();
                    test2.Read(br);
                    this.ShaderParams.add(test2);
                    break;
            }
        }
        br.setCurrentOffset(save);
    }

    public String[] getDataNames() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("VTable");
        nameList.add("BlockMapAdress");
        nameList.add("Unknown1");
        nameList.add("Unknown2");
        nameList.add("Unknown3");
        nameList.add("Unknown4");
        nameList.add("Unknown4_1");
        nameList.add("Unknown5");
        nameList.add("shaderParamOffsetsOffset");
        nameList.add("Unknown6");
        nameList.add("ShaderParamCount");
        nameList.add("Unknown8");
        nameList.add("shaderParamTypesOffset");
        nameList.add("Hash");
        nameList.add("Unknown9");
        nameList.add("Unknown10");
        nameList.add("shaderParamNameOffset");
        nameList.add("Unknown11");
        nameList.add("Unknown12");
        nameList.add("Unknown13");
        nameList.add("[ShaderParamOffsets]");
        for (int i = 0; i < this.ShaderParamOffsets.Count; i++) {
            nameList.add("  ShaderParamOffset " + i);
        }
        nameList.add("[ShaderParamTypes]");
        for (int i2 = 0; i2 < this.ShaderParamTypes.Count; i2++) {
            nameList.add("  ShaderParamType " + i2);
        }
        nameList.add("[ShaderParamNames]");
        for (int i3 = 0; i3 < this.ShaderParamNames.Count; i3++) {
            nameList.add("  ShaderParamName " + i3);
        }
        String[] names = new String[nameList.size()];
        for (int i4 = 0; i4 < nameList.size(); i4++) {
            names[i4] = nameList.get(i4);
        }
        return names;
    }

    public String[] getDataValues() {
        ArrayList valueList = new ArrayList();
        valueList.add("" + this.VTable);
        valueList.add("" + this.BlockMapAdress);
        valueList.add("" + this.Unknown1);
        valueList.add("" + ((int) this.Unknown2));
        valueList.add("" + ((int) this.Unknown3));
        valueList.add("" + this.Unknown4);
        valueList.add("" + this.Unknown4_1);
        valueList.add("" + this.Unknown5);
        valueList.add("" + Util.getHexString(this.shaderParamOffsetsOffset));
        valueList.add("" + this.Unknown6);
        valueList.add("" + this.ShaderParamCount);
        valueList.add("" + this.Unknown8);
        valueList.add("" + Util.getHexString(this.shaderParamTypesOffset));
        valueList.add("" + this.Hash);
        valueList.add("" + this.Unknown9);
        valueList.add("" + this.Unknown10);
        valueList.add("" + Util.getHexString(this.shaderParamNameOffset));
        valueList.add("" + this.Unknown11);
        valueList.add("" + this.Unknown12);
        valueList.add("" + this.Unknown13);
        valueList.add("" + this.ShaderParamOffsets.Count);
        for (int i = 0; i < this.ShaderParamOffsets.Count; i++) {
            valueList.add("" + Util.getHexString(((Integer) this.ShaderParamOffsets.Values.get(i)).intValue()));
        }
        valueList.add("" + this.ShaderParamTypes.Count);
        for (int i2 = 0; i2 < this.ShaderParamTypes.Count; i2++) {
            valueList.add(Util.getShaderType(((Byte) this.ShaderParamTypes.Values.get(i2)).byteValue()));
        }
        valueList.add("" + this.ShaderParamNames.Count);
        for (int i3 = 0; i3 < this.ShaderParamNames.Count; i3++) {
            valueList.add(Util.getShaderName(((Integer) this.ShaderParamNames.Values.get(i3)).intValue()));
        }
        String[] values = new String[valueList.size()];
        for (int i4 = 0; i4 < valueList.size(); i4++) {
            values[i4] = (String) valueList.get(i4);
        }
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
