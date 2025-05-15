package wdr;

/* loaded from: ShaderParamMatrix.class */
public class ShaderParamMatrix {
    public void Read(ByteReader br) {
        float[] M = new float[16];
        System.out.println("Matrix: ");
        for (int i = 0; i < 16; i++) {
            M[i] = br.arr2float();
            System.out.print(M[i] + ", ");
            if ((i + 1) % 4 == 0) {
                System.out.print("\n");
            }
        }
    }
}
