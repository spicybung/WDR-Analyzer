package model;

import com.sun.opengl.util.texture.Texture;

/* loaded from: Material.class */
public class Material {
    private Texture texture;
    private int red;
    private int green;
    private int blue;
    private int alpha;
    private boolean colors = true;

    public Material(int red, int green, int blue, int alpha, Texture texture) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.texture = texture;
    }

    public void setColors(boolean colors) {
        this.colors = colors;
    }

    public boolean getColors() {
        return this.colors;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
