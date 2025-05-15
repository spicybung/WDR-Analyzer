package wdr;

import file_io.WriteFunctions;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import main.Main;

/* loaded from: Compression.class */
public class Compression {
    private CompressionType type;
    private static int CopyBufferSize = 1048576;

    public void setCodec(CompressionType type) {
        this.type = type;
    }

    public void compress(DataOutputStream destination, byte[] source) {
        DeflaterOutputStream deflater = new DeflaterOutputStream(destination, new Deflater(-1, true));
        for (int i = 0; i < source.length - 1; i++) {
            try {
                deflater.write(source[i]);
            } catch (IOException ex) {
                Logger.getLogger(Compression.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            }
        }
        try {
            deflater.finish();
        } catch (IOException ex2) {
            Logger.getLogger(Compression.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
        }
        System.out.println("Deflate finished");
    }

    public byte[] decompress(InputStream source, int totalSize) {
        byte[] dataBuffer = new byte[totalSize + 1];
        try {
            int i = 0;
            InflaterInputStream inflater = new InflaterInputStream(source, new Inflater(true));
            while (inflater.available() != 0) {
                try {
                    dataBuffer[i] = (byte) inflater.read();
                    i++;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } catch (IOException ex2) {
            Logger.getLogger(Compression.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
        }
        return dataBuffer;
    }

    public void writeToFile(byte[] source, Main main) {
        WriteFunctions wf = new WriteFunctions();
        FileOutputStream file_out = wf.openFile(main.getFileName() + ".dec");
        DataOutputStream data_out = new DataOutputStream(file_out);
        for (int i = 0; i < source.length - 1; i++) {
            wf.writeByte(data_out, source[i]);
        }
        wf.closeFile(file_out, data_out);
    }
}
