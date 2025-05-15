package wdr;

import model.Model;

/* loaded from: ModelFile.class */
public class ModelFile {
    public Model loadModel(byte[] stream, int vertOffset, int polyOffset, int vertSize, int polySize, int StrideSize) {
        Model mdl = new Model();
        mdl.setPolygons(polySize);
        mdl.setVertices(vertSize);
        for (int i = 0; i < vertSize; i++) {
            byte[] miniArr = {stream[vertOffset + (i * StrideSize)], stream[vertOffset + (i * StrideSize) + 1], stream[vertOffset + (i * StrideSize) + 2], stream[vertOffset + (i * StrideSize) + 3]};
            float x = arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 4];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 5];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 6];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 7];
            float y = arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 8];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 9];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 10];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 11];
            float z = arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 12];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 13];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 14];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 15];
            arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 16];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 17];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 18];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 19];
            arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 20];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 21];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 22];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 23];
            arr2float(miniArr);
            byte b = stream[vertOffset + (i * StrideSize) + 24];
            byte b2 = stream[vertOffset + (i * StrideSize) + 25];
            byte b3 = stream[vertOffset + (i * StrideSize) + 26];
            byte b4 = stream[vertOffset + (i * StrideSize) + 27];
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 28];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 29];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 30];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 31];
            float u = arr2float(miniArr);
            miniArr[0] = stream[vertOffset + (i * StrideSize) + 32];
            miniArr[1] = stream[vertOffset + (i * StrideSize) + 33];
            miniArr[2] = stream[vertOffset + (i * StrideSize) + 34];
            miniArr[3] = stream[vertOffset + (i * StrideSize) + 35];
            float v = arr2float(miniArr);
            mdl.createModelVertex(i, x, y, z, u, v);
        }
        for (int i2 = 0; i2 < polySize; i2++) {
            byte[] miniArr2 = {stream[polyOffset + (i2 * 6)], stream[polyOffset + (i2 * 6) + 1]};
            int a = arr2int(miniArr2);
            miniArr2[0] = stream[polyOffset + (i2 * 6) + 2];
            miniArr2[1] = stream[polyOffset + (i2 * 6) + 3];
            int b5 = arr2int(miniArr2);
            miniArr2[0] = stream[polyOffset + (i2 * 6) + 4];
            miniArr2[1] = stream[polyOffset + (i2 * 6) + 5];
            int c = arr2int(miniArr2);
            mdl.createModelPoly(a, b5, c);
        }
        mdl.setModelLoaded(true);
        return mdl;
    }

    public static float arr2float(byte[] arr) {
        int accum = 0;
        int i = 0;
        for (int shiftBy = 0; shiftBy < 32; shiftBy += 8) {
            accum = (int) (accum | ((arr[i] & 255) << shiftBy));
            i++;
        }
        return Float.intBitsToFloat(accum);
    }

    public static int arr2int(byte[] arr) {
        int low = arr[0] & 255;
        int high = arr[1] & 255;
        return (high << 8) | low;
    }

    public static byte[] float2arr(float f) {
        int n = Float.floatToIntBits(f);
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((n >> (i * 8)) & 255);
        }
        return bytes;
    }

    public static byte[] short2arr(int i) {
        short s = (short) i;
        byte[] bytes = {(byte) (s & 255), (byte) ((s >> 8) & 255)};
        return bytes;
    }
}
