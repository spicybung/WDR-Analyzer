package model;

/* loaded from: Vertex.class */
public class Vertex {
    private float x;
    private float y;
    private float z;
    private float u;
    private float v;
    private byte red;
    private byte green;
    private byte blue;
    private byte alpha;

    public Vertex(float x, float y, float z, float u, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }

    public void setVertexColors(byte red, byte green, byte blue, byte alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public byte getRed() {
        return this.red;
    }

    public byte getGreen() {
        return this.red;
    }

    public byte getBlue() {
        return this.red;
    }

    public byte getAlpha() {
        return this.red;
    }

    public float getVertexX() {
        return this.x;
    }

    public float getVertexY() {
        return this.y;
    }

    public float getVertexZ() {
        return this.z;
    }

    public float getVertexU() {
        return this.u;
    }

    public float getVertexV() {
        return this.v;
    }

    public void setVertexX(float x) {
        this.x = x;
    }

    public void setVertexY(float y) {
        this.y = y;
    }

    public void setVertexZ(float z) {
        this.z = z;
    }

    public void setVertexU(float u) {
        this.u = u;
    }

    public void setVertexV(float v) {
        this.v = v;
    }

    public void setVertexPostion(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
