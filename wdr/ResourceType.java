package wdr;

/* loaded from: ResourceType.class */
public enum ResourceType {
    TextureXBOX(7),
    ModelXBOX(109),
    Generic(1),
    Bounds(32),
    Particles(36),
    Particles2(27),
    Texture(8),
    Model(110),
    ModelFrag(112);

    private int type;

    ResourceType(int type) {
        this.type = type;
    }

    public static ResourceType get(int type) {
        return Model;
    }
}
