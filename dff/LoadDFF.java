package dff;

import dff2wdr.NewConvert;
import dff2wdr.Screen;
import file_io.ReadFunctions;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import model.Model;
import model.Polygon;

/* loaded from: LoadDFF.class */
public class LoadDFF extends Thread {
    private Screen screen;
    private String dffFile;
    private boolean kam;
    private ArrayList<Polygon> tempPoly = new ArrayList<>();

    public LoadDFF(Screen screen, String dffFile, boolean kam) {
        this.screen = screen;
        this.dffFile = dffFile;
        this.kam = kam;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        loadModel();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x00a5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x00fa. Please report as an issue. */
    public void loadModel() {
        this.screen.getLog().writeLine("Opened " + this.dffFile + " for analyzing");
        Model mdl = new Model();
        this.screen.setProgressText("Analyzing model " + this.dffFile);
        ReadFunctions rf = new ReadFunctions();
        FileInputStream file_in = rf.openFile(this.dffFile);
        DataInputStream data_in = new DataInputStream(file_in);
        int lastID = 0;
        int curTex = -1;
        boolean loadedTexture = false;
        int secID = rf.readInt(data_in);
        int secSize = rf.readInt(data_in);
        int secVersion = rf.readInt(data_in);
        while (secID != 0) {
            switch (secID) {
                case 1:
                    switch (lastID) {
                        case 6:
                            System.out.println("Texture filter mode flags: " + rf.readShort(data_in));
                            System.out.println("Unknown: " + rf.readShort(data_in));
                            break;
                        case 7:
                            curTex++;
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            int red = rf.readByteAsInt(data_in);
                            int green = rf.readByteAsInt(data_in);
                            int blue = rf.readByteAsInt(data_in);
                            rf.readByteAsInt(data_in);
                            System.out.println(red + "," + green + "," + blue);
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            System.out.println("Texture Count: " + rf.readInt(data_in));
                            System.out.println("Unknown Float: " + rf.readFloat(data_in));
                            System.out.println("Unknown Float: " + rf.readFloat(data_in));
                            System.out.println("Unknown Float: " + rf.readFloat(data_in));
                            break;
                        case 8:
                            int matCount = rf.readInt(data_in);
                            this.screen.getLog().writeLine("Number of materials: " + matCount);
                            System.out.println("Material Count: " + matCount);
                            for (int i = 0; i < matCount; i++) {
                                this.screen.getLog().writeLine("Material " + i + " Unknown int " + rf.readInt(data_in));
                            }
                            break;
                        case 14:
                            rf.skipBytes(data_in, secSize);
                            break;
                        case 15:
                            this.screen.setProgressText("Analyzing Geometry");
                            this.screen.setProgressBar(20);
                            int flags = rf.readShort(data_in);
                            this.screen.getLog().writeLine("Flags: " + Integer.toString(flags, 2));
                            System.out.println("Flags: " + Integer.toString(flags, 2));
                            System.out.println("Unknown: " + rf.readShort(data_in));
                            int triCount = rf.readInt(data_in);
                            mdl.setPolygons(triCount);
                            this.screen.getLog().writeLine("Triangle Count: " + triCount);
                            System.out.println("Triangle Count: " + triCount);
                            int vertCount = rf.readInt(data_in);
                            mdl.setVertices(vertCount);
                            this.screen.getLog().writeLine("Vertex Count: " + vertCount);
                            System.out.println("Vertex Count: " + vertCount);
                            System.out.println("Morph Target Count: " + rf.readInt(data_in));
                            if (secVersion == 4099) {
                                System.out.println("Ambient: " + rf.readFloat(data_in));
                                System.out.println("Diffuse: " + rf.readFloat(data_in));
                                System.out.println("Specular: " + rf.readFloat(data_in));
                            }
                            if (rf.hasFlag(flags, 8)) {
                                this.screen.getLog().writeLine("Model contains vertex colors");
                                for (int i2 = 0; i2 < vertCount; i2++) {
                                    rf.readByte(data_in);
                                    rf.readByte(data_in);
                                    rf.readByte(data_in);
                                    rf.readByte(data_in);
                                }
                            }
                            if (rf.hasFlag(flags, 4)) {
                                this.screen.getLog().writeLine("Model contains UV coords");
                                this.screen.setProgressText("Analyzing UV coords");
                                this.screen.setProgressBar(30);
                                for (int i3 = 0; i3 < vertCount; i3++) {
                                    float u = rf.readFloat(data_in);
                                    float v = rf.readFloat(data_in);
                                    if (!mdl.getHasVertexColors()) {
                                        mdl.createModelVertex(i3, 0.0f, 0.0f, 0.0f, u, v);
                                    } else {
                                        mdl.createModelVertex(i3, 0.0f, 0.0f, 0.0f, u, v);
                                    }
                                }
                            }
                            for (int i4 = 0; i4 < triCount; i4++) {
                                rf.readShort(data_in);
                                rf.readShort(data_in);
                                rf.readShort(data_in);
                                rf.readShort(data_in);
                            }
                            System.out.println("Bounding Sphere X: " + rf.readFloat(data_in));
                            System.out.println("Bounding Sphere Y: " + rf.readFloat(data_in));
                            System.out.println("Bounding Sphere Z: " + rf.readFloat(data_in));
                            System.out.println("Bounding Sphere Radius: " + rf.readFloat(data_in));
                            System.out.println("Has Position: " + rf.readInt(data_in));
                            System.out.println("Has Normals: " + rf.readInt(data_in));
                            this.screen.getLog().writeLine("Loading vertices");
                            for (int i5 = 0; i5 < vertCount; i5++) {
                                this.screen.setProgressText("Loading Vertices");
                                this.screen.setProgressBar(50);
                                float x = rf.readFloat(data_in);
                                float y = rf.readFloat(data_in);
                                float z = rf.readFloat(data_in);
                                mdl.createModelVertex(i5, x, y, z, -1.0f, -1.0f);
                            }
                            if (rf.hasFlag(flags, 16)) {
                                this.screen.getLog().writeLine("Model contains normals");
                                mdl.hasNormals(true);
                                for (int i6 = 0; i6 < vertCount; i6++) {
                                    float normX = rf.readFloat(data_in);
                                    float normY = rf.readFloat(data_in);
                                    float normZ = rf.readFloat(data_in);
                                    mdl.getVertex(i6).setNormals(normX, normY, normZ);
                                }
                                break;
                            }
                            break;
                        case 16:
                            System.out.println("Object Count: " + rf.readInt(data_in));
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            break;
                        case 20:
                            System.out.println("Frame Number: " + rf.readInt(data_in));
                            System.out.println("Geometry Number: " + rf.readInt(data_in));
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            System.out.println("Unknown: " + rf.readInt(data_in));
                            break;
                        case 26:
                            System.out.println("Geometry items: " + rf.readInt(data_in));
                            break;
                    }
                case 2:
                    String texName = rf.readAndFixString(data_in, secSize);
                    if (!loadedTexture) {
                        this.screen.getLog().writeLine("Texture: " + texName);
                        System.out.println("Added shader: " + texName + " " + texName.length());
                        mdl.addShader(texName);
                        loadedTexture = true;
                        this.screen.setProgressText("Analyzing Texture " + texName);
                        this.screen.setProgressBar(70);
                        break;
                    } else {
                        System.out.println("Texture name: " + texName);
                        loadedTexture = false;
                        break;
                    }
                case 31:
                    rf.skipBytes(data_in, secSize);
                    break;
                case 278:
                    rf.skipBytes(data_in, secSize);
                    break;
                case 286:
                    rf.skipBytes(data_in, secSize);
                    break;
                case 1294:
                    System.out.println("Triangle Strip: " + rf.readInt(data_in));
                    int splitCount = rf.readInt(data_in);
                    System.out.println("Split Count: " + splitCount);
                    System.out.println("Face Count: " + rf.readInt(data_in));
                    this.screen.setProgressText("Analyzing strips");
                    for (int i7 = 0; i7 < splitCount; i7++) {
                        this.screen.setProgressText("Anazlyzing strip " + i7);
                        this.screen.setProgressBar(90);
                        int faceIndex = rf.readInt(data_in);
                        int matIndex = rf.readInt(data_in);
                        System.out.println("FaceIndex: " + faceIndex);
                        System.out.println("MatIndex: " + matIndex);
                        mdl.createStrip(faceIndex, matIndex);
                        int polyB = -1;
                        int polyC = -1;
                        int i32 = 0;
                        int resetCount = 0;
                        if (this.kam) {
                            for (int i22 = 0; i22 < faceIndex / 3; i22++) {
                                int polyA = rf.readInt(data_in);
                                int polyB2 = rf.readInt(data_in);
                                int polyC2 = rf.readInt(data_in);
                                mdl.createModelPoly(polyA, polyB2, polyC2, i7, true);
                            }
                        } else {
                            for (int i23 = 0; i23 < faceIndex; i23++) {
                                int newPoly = rf.readInt(data_in);
                                int polyA2 = polyB;
                                polyB = polyC;
                                polyC = newPoly;
                                if (polyA2 != -1) {
                                    if (polyA2 != polyB && polyA2 != polyC && polyB != polyC) {
                                        if (resetCount % 2 == 0) {
                                            if (i32 % 2 == 0) {
                                                mdl.createModelPoly(polyA2, polyB, polyC, i7, true);
                                            } else {
                                                mdl.createModelPoly(polyB, polyA2, polyC, i7, true);
                                            }
                                        } else if (i32 % 2 != 0) {
                                            mdl.createModelPoly(polyA2, polyB, polyC, i7, true);
                                        } else {
                                            mdl.createModelPoly(polyB, polyA2, polyC, i7, true);
                                        }
                                        i32++;
                                    } else {
                                        resetCount++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 39056121:
                    rf.skipBytes(data_in, secSize);
                    break;
                case 39056125:
                    System.out.println("Unknown: " + rf.readInt(data_in));
                    break;
                case 39056126:
                    System.out.println("Object name: " + rf.readString(data_in, secSize));
                    break;
            }
            lastID = secID;
            secID = rf.readInt(data_in);
            secSize = rf.readInt(data_in);
            secVersion = rf.readInt(data_in);
            if (secID == -1) {
                this.screen.getLog().writeLine("Finished analyzing DFF");
                this.screen.setProgressText("Finished analyzing DFF");
                this.screen.setProgressBar(100);
                rf.closeFile(file_in, data_in);
                this.screen.setModel(mdl);
                mdl.createCenter();
                mdl.displayBounds();
                new NewConvert(this.screen, mdl, this.dffFile).start();
            }
        }
        this.screen.getLog().writeLine("Finished analyzing DFF");
        this.screen.setProgressText("Finished analyzing DFF");
        this.screen.setProgressBar(100);
        rf.closeFile(file_in, data_in);
        this.screen.setModel(mdl);
        mdl.createCenter();
        mdl.displayBounds();
        new NewConvert(this.screen, mdl, this.dffFile).start();
    }

    public void createPolysFromTemp(Model mdl) {
        System.out.println("Blaat");
        for (int i = 0; i < this.tempPoly.size(); i++) {
            mdl.createModelPoly(this.tempPoly.get(i).getPolygonA(), this.tempPoly.get(i).getPolygonB(), this.tempPoly.get(i).getPolygonC(), this.tempPoly.get(i).getMaterialIndex(), true);
        }
    }
}
