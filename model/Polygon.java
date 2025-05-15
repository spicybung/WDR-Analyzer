package model;

/* loaded from: Polygon.class */
public class Polygon {
    private int a;
    private int b;
    private int c;
    private int matIndex;
    private byte red;
    private byte green;
    private byte blue;
    private byte alpha;

    public Polygon(int a, int b, int c, int matIndex) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.matIndex = matIndex;
    }

    public Polygon(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getPolygonA() {
        return this.a;
    }

    public int getPolygonB() {
        return this.b;
    }

    public int getPolygonC() {
        return this.c;
    }

    public void setPolygonA(int a) {
        this.a = a;
    }

    public void setPolygonB(int b) {
        this.b = b;
    }

    public void setPolygonC(int c) {
        this.c = c;
    }

    public void setMaterialIndex(int index) {
        this.matIndex = index;
    }

    public int getMaterialIndex() {
        return this.matIndex;
    }

    public byte getAlpha() {
        return this.alpha;
    }

    public void setAlpha(byte alpha) {
        this.alpha = alpha;
    }

    public byte getBlue() {
        return this.blue;
    }

    public void setBlue(byte blue) {
        this.blue = blue;
    }

    public byte getGreen() {
        return this.green;
    }

    public void setGreen(byte green) {
        this.green = green;
    }

    public byte getRed() {
        return this.red;
    }

    public void setRed(byte red) {
        this.red = red;
    }
}
