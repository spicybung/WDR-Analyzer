package wdr;

import main.Util;

/* loaded from: ShaderSPS.class */
public class ShaderSPS {
    private int startOffset;
    private int Unknown14;
    private int Unknown15;
    private int Unknown16;
    private int Unknown17;
    public String ShaderName;
    public String ShaderSPS;

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        new Shader(br);
        int shaderNamePtr = br.ReadOffset();
        int shaderSpsPtr = br.ReadOffset();
        System.out.println("ShaderNamePtr: " + shaderNamePtr);
        System.out.println("ShaderSpsPtr: " + shaderSpsPtr);
        this.Unknown14 = br.ReadUInt32();
        this.Unknown15 = br.ReadUInt32();
        this.Unknown16 = br.ReadUInt32();
        this.Unknown17 = br.ReadUInt32();
        br.setCurrentOffset(shaderNamePtr);
        this.ShaderName = br.ReadNullTerminatedString();
        System.out.println("Shadername: " + this.ShaderName);
        br.setCurrentOffset(shaderSpsPtr);
        this.ShaderSPS = br.ReadNullTerminatedString();
        System.out.println("ShaderSPS: " + this.ShaderSPS);
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
