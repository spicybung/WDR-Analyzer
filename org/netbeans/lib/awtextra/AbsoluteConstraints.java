package org.netbeans.lib.awtextra;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;

/* loaded from: AbsoluteConstraints.class */
public class AbsoluteConstraints implements Serializable {
    static final long serialVersionUID = 5261460716622152494L;
    public int x;
    public int y;
    public int width;
    public int height;

    public AbsoluteConstraints(Point point) {
        this(point.x, point.y);
    }

    public AbsoluteConstraints(int i, int i2) {
        this.width = -1;
        this.height = -1;
        this.x = i;
        this.y = i2;
    }

    public AbsoluteConstraints(Point point, Dimension dimension) {
        this.width = -1;
        this.height = -1;
        this.x = point.x;
        this.y = point.y;
        if (dimension != null) {
            this.width = dimension.width;
            this.height = dimension.height;
        }
    }

    public AbsoluteConstraints(int i, int i2, int i3, int i4) {
        this.width = -1;
        this.height = -1;
        this.x = i;
        this.y = i2;
        this.width = i3;
        this.height = i4;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return new StringBuffer().append(super.toString()).append(" [x=").append(this.x).append(", y=").append(this.y).append(", width=").append(this.width).append(", height=").append(this.height).append("]").toString();
    }
}
