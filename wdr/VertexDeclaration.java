package wdr;

import java.util.ArrayList;

/* loaded from: VertexDeclaration.class */
public class VertexDeclaration {
    public int UsageFlags;
    public int Stride;
    public byte AlterateDecoder;
    public byte Type;
    public long DeclarationTypes;

    public VertexDeclaration(ByteReader br) {
        Read(br);
    }

    public void Read(ByteReader br) {
        this.UsageFlags = br.ReadUInt32();
        this.Stride = br.ReadUInt16();
        this.AlterateDecoder = br.ReadByte();
        this.Type = br.ReadByte();
        br.ReadUInt32();
        br.ReadUInt32();
    }

    public String[] getDataNames() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("UsageFlags");
        nameList.add("Stride");
        nameList.add("AlterateDecoder");
        nameList.add("Type");
        String[] names = new String[nameList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = nameList.get(i);
        }
        return names;
    }

    public String[] getDataValues() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("" + this.UsageFlags);
        nameList.add("" + this.Stride);
        nameList.add("" + ((int) this.AlterateDecoder));
        nameList.add("" + ((int) this.Type));
        String[] names = new String[nameList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = nameList.get(i);
        }
        return names;
    }
}
