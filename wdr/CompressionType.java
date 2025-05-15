package wdr;

/* loaded from: CompressionType.class */
public enum CompressionType {
    LZX(62725),
    Deflate(55928);

    private int type;

    CompressionType(int type) {
        this.type = type;
    }

    public static CompressionType get(int type) {
        CompressionType ret = LZX;
        switch (type) {
            case 55928:
                ret = Deflate;
                break;
            case 62725:
                ret = LZX;
                break;
        }
        return ret;
    }
}
