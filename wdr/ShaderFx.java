package wdr;

import main.Util;

/* loaded from: ShaderFx.class */
public class ShaderFx {
    private int startOffset;
    public Shader shader;
    private int shaderNamePtr;
    private int shaderSpsPtr;
    private int Unknown14;
    private int Unknown15;
    private int Unknown16;
    private int Unknown17;
    public String ShaderName;
    public String ShaderSPS;

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.shader = new Shader(br);
        this.shaderNamePtr = br.ReadOffset();
        this.shaderSpsPtr = br.ReadOffset();
        System.out.println("ShaderNamePtr: " + this.shaderNamePtr);
        System.out.println("ShaderSpsPtr: " + this.shaderSpsPtr);
        this.Unknown14 = br.ReadUInt32();
        this.Unknown15 = br.ReadUInt32();
        this.Unknown16 = br.ReadUInt32();
        this.Unknown17 = br.ReadUInt32();
        br.setCurrentOffset(this.shaderNamePtr);
        this.ShaderName = br.ReadNullTerminatedString();
        br.setCurrentOffset(this.shaderSpsPtr);
        this.ShaderSPS = br.ReadNullTerminatedString();
        System.out.println("ShaderName: " + this.ShaderName);
        System.out.println("ShaderSPS: " + this.ShaderSPS);
    }

    public String[] getDataNames() {
        String[] names = {"shaderNamePtr", "shaderSpsPtr", "Unknown14", "Unknown15", "Unknown16", "Unknown17", "ShaderName", "ShaderSPS"};
        return names;
    }

    public String[] getDataValues() {
        String[] values = new String[10];
        values[0] = Util.getHexString(this.shaderNamePtr);
        values[1] = Util.getHexString(this.shaderSpsPtr);
        values[2] = "" + this.Unknown14;
        values[3] = "" + this.Unknown15;
        values[4] = "" + this.Unknown16;
        values[5] = "" + this.Unknown17;
        values[6] = "" + this.ShaderName;
        values[7] = "" + this.ShaderSPS;
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
