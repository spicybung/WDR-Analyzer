package wdr;

import Collections.PtrCollection;
import main.Util;

/* loaded from: DrawableModel.class */
public class DrawableModel {
    public int startOffset;
    public ShaderGroup shaderGroup;
    public Skeleton skeleton;
    public int shaderGroupOffset;
    public int skeletonOffset;
    public Vector4 Center;
    public Vector4 BoundsMin;
    public Vector4 BoundsMax;
    int[] modelOffsets;
    public int levelOfDetailCount;
    public PtrCollection<Model2>[] ModelCollection;
    public Vector4 AbsoluteMax;
    private int Unk1;
    private int Neg1;
    private int Neg2;
    private int Neg3;
    private float Unk2;
    private int Unk3;
    private int Unk4;
    private int Unk5;
    private int Unk6;
    private int Unk7;

    public void readSystemMemory(byte[] stream, ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        System.out.println("VTable: " + br.ReadUInt32());
        System.out.println("BlockMapAdress: " + br.ReadOffset());
        this.shaderGroupOffset = br.ReadOffset();
        this.skeletonOffset = br.ReadOffset();
        System.out.println("ShaderGroupOffset: " + this.shaderGroupOffset);
        System.out.println("skeletonOffset: " + this.skeletonOffset);
        this.Center = br.readVector();
        this.Center.print("Center");
        this.BoundsMin = br.readVector();
        this.BoundsMin.print("BoundsMin");
        this.BoundsMax = br.readVector();
        this.BoundsMax.print("BoundsMax");
        this.levelOfDetailCount = 0;
        this.modelOffsets = new int[4];
        for (int i = 0; i < 4; i++) {
            this.modelOffsets[i] = br.ReadOffset();
            if (this.modelOffsets[i] != 0) {
                System.out.println("Level " + i + " at offset " + this.modelOffsets[i]);
                this.levelOfDetailCount++;
            }
        }
        System.out.println("Level of detail: " + this.levelOfDetailCount);
        this.AbsoluteMax = br.readVector();
        this.AbsoluteMax.print("AbsoluteMax");
        this.Unk1 = br.ReadUInt32();
        this.Neg1 = br.ReadUInt32();
        this.Neg2 = br.ReadUInt32();
        this.Neg3 = br.ReadUInt32();
        this.Unk2 = br.arr2float();
        this.Unk3 = br.ReadUInt32();
        this.Unk4 = br.ReadUInt32();
        this.Unk5 = br.ReadUInt32();
        this.Unk6 = br.ReadUInt32();
        this.Unk7 = br.ReadUInt32();
        System.out.println("Unknown: " + this.Unk1);
        System.out.println("Neg: " + this.Neg1 + ", " + this.Neg2 + ", " + this.Neg3);
        System.out.println("Unknown float: " + this.Unk2);
        System.out.println("Unknown: " + this.Unk3 + ", " + this.Unk4 + ", " + this.Unk5);
        System.out.println("Unknown: " + this.Unk6 + ", " + this.Unk7);
        if (this.shaderGroupOffset != 0) {
            System.out.println("Setting shader offset " + this.shaderGroupOffset);
            br.setCurrentOffset(this.shaderGroupOffset);
            this.shaderGroup = new ShaderGroup(br);
        }
        if (this.skeletonOffset != 0) {
            br.setCurrentOffset(this.skeletonOffset);
            this.skeleton = new Skeleton();
        }
        System.out.println("Created new PtrCollection");
        this.ModelCollection = new PtrCollection[this.levelOfDetailCount];
        for (int i2 = 0; i2 < this.levelOfDetailCount; i2++) {
            System.out.println("Offset: " + this.modelOffsets[i2]);
            br.setCurrentOffset(this.modelOffsets[i2]);
            this.ModelCollection[i2] = new PtrCollection<>(br, 1);
        }
    }

    public String[] getDataNames() {
        String[] names = new String[17 + this.levelOfDetailCount];
        names[0] = "shaderGroupOffset";
        int i = 0 + 1;
        names[i] = "skeletonOffset";
        int i2 = i + 1;
        names[i2] = "Center";
        int i3 = i2 + 1;
        names[i3] = "BoundsMin";
        int i4 = i3 + 1;
        names[i4] = "BoundsMax";
        int i5 = i4 + 1;
        names[i5] = "levelOfDetailCount";
        int i6 = i5 + 1;
        for (int i22 = 0; i22 < this.levelOfDetailCount; i22++) {
            names[i6] = "  DetailOffset " + (i22 + 1);
            i6++;
        }
        names[i6] = "AbsoluteMax";
        int i7 = i6 + 1;
        names[i7] = "Unk1";
        int i8 = i7 + 1;
        names[i8] = "Neg1";
        int i9 = i8 + 1;
        names[i9] = "Neg2";
        int i10 = i9 + 1;
        names[i10] = "Neg3";
        int i11 = i10 + 1;
        names[i11] = "Unk2";
        int i12 = i11 + 1;
        names[i12] = "Unk3";
        int i13 = i12 + 1;
        names[i13] = "Unk4";
        int i14 = i13 + 1;
        names[i14] = "Unk5";
        int i15 = i14 + 1;
        names[i15] = "Unk6";
        names[i15 + 1] = "Unk7";
        return names;
    }

    public String[] getDataValues() {
        String[] values = new String[17 + this.levelOfDetailCount];
        values[0] = Util.getHexString(this.shaderGroupOffset);
        int i = 0 + 1;
        values[i] = Util.getHexString(this.skeletonOffset);
        int i2 = i + 1;
        values[i2] = "" + this.Center;
        int i3 = i2 + 1;
        values[i3] = "" + this.BoundsMin;
        int i4 = i3 + 1;
        values[i4] = "" + this.BoundsMax;
        int i5 = i4 + 1;
        values[i5] = "" + this.levelOfDetailCount;
        int i6 = i5 + 1;
        for (int i22 = 0; i22 < this.levelOfDetailCount; i22++) {
            values[i6] = Util.getHexString(this.modelOffsets[i22]);
            i6++;
        }
        values[i6] = "" + this.AbsoluteMax;
        int i7 = i6 + 1;
        values[i7] = "" + this.Unk1;
        int i8 = i7 + 1;
        values[i8] = "" + this.Neg1;
        int i9 = i8 + 1;
        values[i9] = "" + this.Neg2;
        int i10 = i9 + 1;
        values[i10] = "" + this.Neg3;
        int i11 = i10 + 1;
        values[i11] = "" + this.Unk2;
        int i12 = i11 + 1;
        values[i12] = "" + this.Unk3;
        int i13 = i12 + 1;
        values[i13] = "" + this.Unk4;
        int i14 = i13 + 1;
        values[i14] = "" + this.Unk5;
        int i15 = i14 + 1;
        values[i15] = "" + this.Unk6;
        values[i15 + 1] = "" + this.Unk7;
        return values;
    }
}
