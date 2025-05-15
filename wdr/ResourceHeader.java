package wdr;

import file_io.ReadFunctions;
import file_io.WriteFunctions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: ResourceHeader.class */
public class ResourceHeader {
    private int MagicBigEndian = 1381188357;
    public static int MagicValue = 88298322;
    public int Magic;
    public ResourceType Type;
    public int Flags;
    public CompressionType CompressCodec;

    public int GetSystemMemSize() {
        return (this.Flags & 2047) << (((this.Flags >> 11) & 15) + 8);
    }

    public int GetGraphicsMemSize() {
        return ((this.Flags >> 15) & 2047) << (((this.Flags >> 26) & 15) + 8);
    }

    public void SetMemSizes(int systemMemSize, int graphicsMemSize) {
        int sysA = systemMemSize >> 8;
        int sysB = 0;
        while (sysA > 63) {
            if ((sysA & 1) != 0) {
                sysA += 2;
            }
            sysA >>= 1;
            sysB++;
        }
        int gfxA = graphicsMemSize >> 8;
        int gfxB = 0;
        while (gfxA > 63) {
            if ((gfxA & 1) != 0) {
                gfxA += 2;
            }
            gfxA >>= 1;
            gfxB++;
        }
        this.Flags = (this.Flags & (-1073741824)) | sysA | (sysB << 11) | (gfxA << 15) | (gfxB << 26);
    }

    public void Read(DataInputStream br) {
        ReadFunctions rf = new ReadFunctions();
        this.Magic = rf.readInt(br);
        int type = rf.readInt(br);
        this.Type = ResourceType.get(type);
        this.Flags = rf.readInt(br);
        this.CompressCodec = CompressionType.get(rf.readShort(br));
        if (this.Magic == this.MagicBigEndian) {
            System.out.println("SwapEndian");
            this.Magic = rf.swapInt(this.Magic);
            this.Type = ResourceType.get(rf.swapInt(type));
            this.Flags = rf.swapInt(this.Flags);
        }
        System.out.println("Magic: " + this.Magic);
        switch (1.$SwitchMap$wdr$ResourceType[this.Type.ordinal()]) {
            case 1:
                System.out.println("Model: " + type);
                return;
            default:
                System.out.println("Dunnow: " + type);
                return;
        }
    }

    public void Write(DataOutputStream bw) {
        try {
            WriteFunctions wf = new WriteFunctions();
            ReadFunctions rf = new ReadFunctions();
            wf.writeInt(bw, rf.swapInt(MagicValue));
            bw.writeInt(1845493760);
            wf.writeInt(bw, rf.swapInt(this.Flags));
            bw.writeShort(30938);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
