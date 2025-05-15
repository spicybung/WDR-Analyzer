package org.netbeans.lib.awtextra;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: AbsoluteLayout.class */
public class AbsoluteLayout implements LayoutManager2, Serializable {
    static final long serialVersionUID = -1919857869177070440L;
    protected Hashtable constraints = new Hashtable();

    public void addLayoutComponent(String str, Component component) {
        throw new IllegalArgumentException();
    }

    public void removeLayoutComponent(Component component) {
        this.constraints.remove(component);
    }

    public Dimension preferredLayoutSize(Container container) {
        int i = 0;
        int i2 = 0;
        Enumeration keys = this.constraints.keys();
        while (keys.hasMoreElements()) {
            Component component = (Component) keys.nextElement();
            AbsoluteConstraints absoluteConstraints = (AbsoluteConstraints) this.constraints.get(component);
            Dimension preferredSize = component.getPreferredSize();
            int width = absoluteConstraints.getWidth();
            if (width == -1) {
                width = preferredSize.width;
            }
            int height = absoluteConstraints.getHeight();
            if (height == -1) {
                height = preferredSize.height;
            }
            if (absoluteConstraints.x + width > i) {
                i = absoluteConstraints.x + width;
            }
            if (absoluteConstraints.y + height > i2) {
                i2 = absoluteConstraints.y + height;
            }
        }
        return new Dimension(i, i2);
    }

    public Dimension minimumLayoutSize(Container container) {
        int i = 0;
        int i2 = 0;
        Enumeration keys = this.constraints.keys();
        while (keys.hasMoreElements()) {
            Component component = (Component) keys.nextElement();
            AbsoluteConstraints absoluteConstraints = (AbsoluteConstraints) this.constraints.get(component);
            Dimension minimumSize = component.getMinimumSize();
            int width = absoluteConstraints.getWidth();
            if (width == -1) {
                width = minimumSize.width;
            }
            int height = absoluteConstraints.getHeight();
            if (height == -1) {
                height = minimumSize.height;
            }
            if (absoluteConstraints.x + width > i) {
                i = absoluteConstraints.x + width;
            }
            if (absoluteConstraints.y + height > i2) {
                i2 = absoluteConstraints.y + height;
            }
        }
        return new Dimension(i, i2);
    }

    public void layoutContainer(Container container) {
        Enumeration keys = this.constraints.keys();
        while (keys.hasMoreElements()) {
            Component component = (Component) keys.nextElement();
            AbsoluteConstraints absoluteConstraints = (AbsoluteConstraints) this.constraints.get(component);
            Dimension preferredSize = component.getPreferredSize();
            int width = absoluteConstraints.getWidth();
            if (width == -1) {
                width = preferredSize.width;
            }
            int height = absoluteConstraints.getHeight();
            if (height == -1) {
                height = preferredSize.height;
            }
            component.setBounds(absoluteConstraints.x, absoluteConstraints.y, width, height);
        }
    }

    public void addLayoutComponent(Component component, Object obj) {
        if (!(obj instanceof AbsoluteConstraints)) {
            throw new IllegalArgumentException();
        }
        this.constraints.put(component, obj);
    }

    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public float getLayoutAlignmentX(Container container) {
        return 0.0f;
    }

    public float getLayoutAlignmentY(Container container) {
        return 0.0f;
    }

    public void invalidateLayout(Container container) {
    }
}
