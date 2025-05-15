package model;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;

/* loaded from: Model.class */
public class Model {
    private int polygons;
    private int vertices;
    private ArrayList<Polygon> poly = new ArrayList<>();
    private ArrayList<Vertex> vert = new ArrayList<>();
    private ArrayList<Material> mat = new ArrayList<>();
    private Vertex max = new Vertex(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    private Vertex min = new Vertex(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    private Vertex center = new Vertex(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    private boolean hasVertexColors = false;
    private boolean finished = false;

    public Model(int polygons, int vertices) {
        this.polygons = polygons;
        this.vertices = vertices;
    }

    public Model() {
    }

    public boolean isModelLoaded() {
        return this.finished;
    }

    public int getNumberOfTextures() {
        return this.mat.size();
    }

    public void setModelLoaded(boolean finished) {
        this.finished = finished;
    }

    public void reset() {
        this.poly.clear();
        this.vert.clear();
        this.mat.clear();
        this.hasVertexColors = false;
        this.polygons = 0;
        this.vertices = 0;
        this.finished = false;
    }

    public void checkBounds(Vertex vertex) {
        System.out.println("Checking bounds");
        if (vertex.getVertexX() > this.max.getVertexX()) {
            this.max.setVertexX(vertex.getVertexX());
        }
        if (vertex.getVertexY() > this.max.getVertexY()) {
            this.max.setVertexY(vertex.getVertexY());
        }
        if (vertex.getVertexZ() > this.max.getVertexZ()) {
            this.max.setVertexZ(vertex.getVertexZ());
        }
        if (vertex.getVertexX() < this.min.getVertexX()) {
            this.min.setVertexX(vertex.getVertexX());
        }
        if (vertex.getVertexY() < this.min.getVertexY()) {
            this.min.setVertexY(vertex.getVertexY());
        }
        if (vertex.getVertexZ() < this.min.getVertexZ()) {
            this.min.setVertexZ(vertex.getVertexZ());
        }
    }

    public void createCenter() {
        this.center.setVertexX((this.max.getVertexX() + this.min.getVertexX()) / 2.0f);
        this.center.setVertexY((this.max.getVertexY() + this.min.getVertexY()) / 2.0f);
        this.center.setVertexZ((this.max.getVertexZ() + this.min.getVertexZ()) / 2.0f);
    }

    public void displayBounds() {
        System.out.println("Max: ");
        System.out.println(this.max.getVertexX() + ", " + this.max.getVertexY() + ", " + this.max.getVertexZ());
        System.out.println("Min: ");
        System.out.println(this.min.getVertexX() + ", " + this.min.getVertexY() + ", " + this.min.getVertexZ());
        System.out.println("Center: ");
        System.out.println(this.center.getVertexX() + ", " + this.center.getVertexY() + ", " + this.center.getVertexZ());
    }

    public Vertex getCenter() {
        return this.center;
    }

    public Vertex getMax() {
        return this.max;
    }

    public Vertex getMin() {
        return this.min;
    }

    public void createModelPoly(int id, int a, int b, int c, int materialIndex) {
        if (this.poly.size() <= id) {
            this.poly.add(new Polygon(a, b, c, materialIndex));
        } else {
            this.poly.set(id, new Polygon(a, b, c, materialIndex));
        }
    }

    public void createModelPoly(int a, int b, int c) {
        this.poly.add(new Polygon(a, b, c));
    }

    public void createModelVertex(int id, float x, float y, float z, float u, float v) {
        if (this.vert.size() <= id) {
            this.vert.add(new Vertex(x, y, z, u, v));
            return;
        }
        if (u == -1.0f) {
            u = this.vert.get(id).getVertexU();
            v = this.vert.get(id).getVertexV();
        }
        this.vert.set(id, new Vertex(x, y, z, u, v));
    }

    public void setModelUV(int id, float u, float v) {
        Vertex vertx = this.vert.get(id);
        vertx.setVertexU(u);
        vertx.setVertexV(v);
    }

    public float getModelMapU(int id) {
        Vertex vertx = this.vert.get(id);
        return vertx.getVertexU();
    }

    public float getModelMapV(int id) {
        Vertex vertx = this.vert.get(id);
        return vertx.getVertexV();
    }

    public float getVertexX(int id) {
        Vertex vertx = this.vert.get(id);
        return vertx.getVertexX();
    }

    public float getVertexY(int id) {
        Vertex vertx = this.vert.get(id);
        return vertx.getVertexY();
    }

    public float getVertexZ(int id) {
        Vertex vertx = this.vert.get(id);
        return vertx.getVertexZ();
    }

    public int getPolygonA(int id) {
        Polygon polyn = this.poly.get(id);
        return polyn.getPolygonA();
    }

    public int getPolygonB(int id) {
        Polygon polyn = this.poly.get(id);
        return polyn.getPolygonB();
    }

    public int getPolygonC(int id) {
        Polygon polyn = this.poly.get(id);
        return polyn.getPolygonC();
    }

    public int getPolygons() {
        return this.polygons;
    }

    public int getVertices() {
        return this.vertices;
    }

    public void setPolygons(int polygons) {
        this.polygons = polygons;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setHasVertexColors(boolean vertexColors) {
        this.hasVertexColors = vertexColors;
    }

    public boolean getHasVertexColors() {
        return this.hasVertexColors;
    }

    public void addMaterial(int red, int green, int blue, int alpha, Texture texture) {
        System.out.println("Added material with colors: " + red + "," + green + "," + blue);
        this.mat.add(new Material(red, green, blue, alpha, texture));
    }

    public void setMaterial(int id, Texture texture) {
        this.mat.get(id).setTexture(texture);
        this.mat.get(id).setColors(false);
    }

    public Vertex getVertex(int id) {
        return this.vert.get(id);
    }

    public Polygon getPolygon(int id) {
        return this.poly.get(id);
    }

    public void render(GL gl) {
        for (int i = 0; i < this.polygons; i++) {
            gl.glBegin(4);
            gl.glTexCoord2f(getModelMapU(getPolygonA(i)), getModelMapV(getPolygonA(i)));
            gl.glVertex3f(getVertexX(getPolygonA(i)), getVertexY(getPolygonA(i)), getVertexZ(getPolygonA(i)));
            gl.glTexCoord2f(getModelMapU(getPolygonB(i)), getModelMapV(getPolygonB(i)));
            gl.glVertex3f(getVertexX(getPolygonB(i)), getVertexY(getPolygonB(i)), getVertexZ(getPolygonB(i)));
            gl.glTexCoord2f(getModelMapU(getPolygonC(i)), getModelMapV(getPolygonC(i)));
            gl.glVertex3f(getVertexX(getPolygonC(i)), getVertexY(getPolygonC(i)), getVertexZ(getPolygonC(i)));
            gl.glEnd();
        }
    }
}
