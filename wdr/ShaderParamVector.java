package wdr;

import java.util.ArrayList;
import main.Util;

/* loaded from: ShaderParamVector.class */
public class ShaderParamVector {
    public int startOffset;
    public Vector4 Data;

    public void Read(ByteReader br) {
        this.startOffset = br.getCurrentOffset();
        this.Data = br.readVector();
        this.Data.print("Shader Vector: ");
    }

    public String[] getDataNames() {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Unkown Vector");
        String[] names = new String[nameList.size()];
        for (int i = 0; i < nameList.size(); i++) {
            names[i] = nameList.get(i);
        }
        return names;
    }

    public String[] getDataValues() {
        ArrayList<String> valueList = new ArrayList<>();
        valueList.add(this.Data.getX() + ", " + this.Data.getY() + ", " + this.Data.getZ() + ", " + this.Data.getW());
        String[] values = new String[valueList.size()];
        for (int i = 0; i < valueList.size(); i++) {
            values[i] = valueList.get(i);
        }
        return values;
    }

    public String getStartOffset() {
        return Util.getStartOffset(this.startOffset);
    }
}
