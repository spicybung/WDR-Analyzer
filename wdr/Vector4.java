package wdr;

/* loaded from: Vector4.class */
public class Vector4 {
    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float getW() {
        return this.w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void print(String name) {
        System.out.println(name + ": " + this.x + ", " + this.y + ", " + this.z + ", " + this.w);
    }

    public String toString() {
        return this.x + ", " + this.y + ", " + this.z + ", " + this.w;
    }
}
