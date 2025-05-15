package model;

import java.util.ArrayList;

/* loaded from: Strip.class */
public class Strip {
    private ArrayList<Polygon> poly = new ArrayList<>();
    private ArrayList<Vertex> vert = new ArrayList<>();
    private int polyCount;
    private int materialIndex;

    public Strip(int polyCount, int materialIndex) {
        this.polyCount = polyCount;
        this.materialIndex = materialIndex;
    }

    public void addPoly(Polygon polygon) {
        this.poly.add(polygon);
    }

    public int addVertex(Vertex vertex) {
        int ret;
        if (!this.vert.contains(vertex)) {
            ret = this.vert.size();
            this.vert.add(vertex);
        } else {
            ret = this.vert.indexOf(vertex);
        }
        return ret;
    }

    public void addVertexToStrip(Vertex vertex) {
        this.vert.add(vertex);
    }

    public Polygon getPoly(int id) {
        return this.poly.get(id);
    }

    public int getPolyCount() {
        return this.poly.size();
    }

    public int getShaderNumber() {
        return this.materialIndex;
    }

    public int getVertexCount() {
        return this.vert.size();
    }

    public Vertex getVertex(int i) {
        return this.vert.get(i);
    }
}
