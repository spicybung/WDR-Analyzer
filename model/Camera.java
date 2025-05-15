package model;

/* loaded from: Camera.class */
public class Camera {
    private double posX;
    private double posY;
    private double posZ;
    private double viewX;
    private double viewY;
    private double viewZ;
    private double upX;
    private double upY;
    private double upZ;
    private double sposX;
    private double sposY;
    private double sposZ;
    private double sviewX;
    private double sviewY;
    private double sviewZ;
    private double supX;
    private double supY;
    private double supZ;
    private float camYaw = 0.0f;
    private float camPitch = 0.0f;
    private float scamYaw = 0.0f;
    private float scamPitch = 0.0f;
    private boolean saved = false;

    public Camera(double posX, double posY, double posZ, double viewX, double viewY, double viewZ, double upX, double upY, double upZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.viewX = viewX;
        this.viewY = viewY;
        this.viewZ = viewZ;
        this.upX = upX;
        this.upY = upY;
        this.upZ = upZ;
    }

    public void setCameraPosition(double posX, double posY, double posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public void setAllCamera(double posX, double posY, double posZ, double viewX, double viewY, double viewZ, double upX, double upY, double upZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.viewX = viewX;
        this.viewY = viewY;
        this.viewZ = viewZ;
        this.upX = upX;
        this.upY = upY;
        this.upZ = upZ;
    }

    public void pointCamera(double viewX, double viewY, double viewZ) {
        this.viewX = viewX;
        this.viewY = viewY;
        this.viewZ = viewZ;
    }

    public void moveCamera(double speed) {
        double x = this.viewX - this.posX;
        double y = this.viewY - this.posY;
        double z = this.viewZ - this.posZ;
        this.posX += x * speed;
        this.posY += y * speed;
        this.posZ += z * speed;
        this.viewX += x * speed;
        this.viewY += y * speed;
        this.viewZ += z * speed;
    }

    public void rotateView(double speed) {
        double x = this.viewX - this.posX;
        double z = this.viewZ - this.posZ;
        this.viewZ = this.posZ + (Math.sin(speed) * x) + (Math.cos(speed) * z);
        this.viewX = (this.posX + (Math.cos(speed) * x)) - (Math.sin(speed) * z);
    }

    public void strafeCamera(double speed) {
        double x = this.viewX - this.posX;
        double z = this.viewZ - this.posZ;
        double oX = -z;
        this.posX += oX * speed;
        this.posZ += x * speed;
        this.viewX += oX * speed;
        this.viewZ += x * speed;
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public double getPosZ() {
        return this.posZ;
    }

    public double getViewX() {
        return this.viewX;
    }

    public double getViewY() {
        return this.viewY;
    }

    public double getViewZ() {
        return this.viewZ;
    }

    public double getUpX() {
        return this.upX;
    }

    public double getUpY() {
        return this.upY;
    }

    public double getUpZ() {
        return this.upZ;
    }

    public void setViewX(double x) {
        this.viewX = x;
    }

    public void setViewY(double y) {
        this.viewY = y;
    }

    public void setViewZ(double z) {
        this.viewZ = z;
    }

    public void reset() {
        setAllCamera(-20.0d, 45.0d, -16.0d, -15.0d, 45.0d, -12.0d, 0.0d, 1.0d, 0.0d);
        this.camPitch = 0.0f;
        this.camYaw = 0.0f;
    }

    public void setPitch(float pitch) {
        this.camPitch = pitch;
    }

    public void setYaw(float yaw) {
        this.camYaw = yaw;
    }

    public float getPitch() {
        return this.camPitch;
    }

    public float getYaw() {
        return this.camYaw;
    }

    public void saveCamera() {
        if (!this.saved) {
            this.sposX = this.posX;
            this.sposY = this.posY;
            this.sposZ = this.posZ;
            this.sviewX = this.viewX;
            this.sviewY = this.viewY;
            this.sviewZ = this.viewZ;
            this.supX = this.upX;
            this.supY = this.upY;
            this.supZ = this.upZ;
            this.scamPitch = this.camPitch;
            this.scamYaw = this.camYaw;
            this.saved = true;
        }
    }

    public void loadCamera() {
        if (this.saved) {
            this.posX = this.sposX;
            this.posY = this.sposY;
            this.posZ = this.sposZ;
            this.viewX = this.sviewX;
            this.viewY = this.sviewY;
            this.viewZ = this.sviewZ;
            this.upX = this.supX;
            this.upY = this.supY;
            this.upZ = this.supZ;
            this.camPitch = this.scamPitch;
            this.camYaw = this.scamYaw;
            this.saved = false;
        }
    }
}
