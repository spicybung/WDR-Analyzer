package main;

/* loaded from: Util.class */
public class Util {
    public static String getHexString(int value) {
        String hex = Integer.toString(value, 16).toUpperCase();
        int size = 4;
        if (hex.length() > 4) {
            size = 8;
        }
        while (hex.length() != size) {
            hex = "0" + hex;
        }
        return "0x" + hex;
    }

    public static String getStartOffset(int offset) {
        return " - (" + getHexString(offset) + ")";
    }

    public static String getShaderName(int type) {
        String ret = "Unknown";
        switch (type) {
            case 726757629:
                ret = "Texture";
                break;
            case 1186448975:
                ret = "NormalTexture";
                break;
            case 1619499462:
                ret = "SpecularTexture";
                break;
        }
        return ret;
    }

    public static String getShaderType(int type) {
        String ret = "Unknown " + type;
        switch (type) {
            case 0:
                ret = "Texture";
                break;
            case 1:
                ret = "Vector";
                break;
            case 4:
                ret = "Matrix";
                break;
        }
        return ret;
    }
}
