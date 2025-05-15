package wdr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;

/* loaded from: ResourceFile.class */
public class ResourceFile {
    private ResourceHeader _header;
    private Compression _codec;
    private Main main;

    public ResourceFile(Main main) {
        this.main = main;
    }

    public byte[] Read(FileInputStream data) throws Exception {
        this._header = new ResourceHeader();
        DataInputStream br = new DataInputStream(data);
        this._header.Read(br);
        if (this._header.Magic != ResourceHeader.MagicValue) {
            throw new Exception("Not a valid resource");
        }
        this._codec = new Compression();
        switch (1.$SwitchMap$wdr$CompressionType[this._header.CompressCodec.ordinal()]) {
            case 1:
                System.out.println("LZX");
                this.main.setCompression("LZX");
                this._codec.setCodec(this._header.CompressCodec);
                break;
            case 2:
                this.main.setCompression("Zlib (Deflate)");
                System.out.println("Deflate");
                this._codec.setCodec(this._header.CompressCodec);
                break;
            default:
                this.main.setCompression("Unknown");
                System.out.println("Compressie fail");
                break;
        }
        this.main.setType("Model (WDR)");
        int totalMemSize = this._header.GetSystemMemSize() + this._header.GetGraphicsMemSize();
        byte[] stream = this._codec.decompress(data, totalMemSize);
        this.main.setSystemSize(this._header.GetSystemMemSize());
        this.main.setGraphicSize(this._header.GetGraphicsMemSize());
        if (this.main.isSaveDEC()) {
            this._codec.writeToFile(stream, this.main);
        }
        return stream;
    }

    public int getGraphicSize() {
        return this._header.GetGraphicsMemSize();
    }

    public int getSystemSize() {
        return this._header.GetSystemMemSize();
    }

    public void Write(byte[] stream, String file) {
        FileOutputStream fs = null;
        try {
            try {
                fs = new FileOutputStream(file);
                DataOutputStream out = new DataOutputStream(fs);
                this._header.Write(out);
                this._codec.compress(out, stream);
                try {
                    fs.close();
                } catch (IOException ex) {
                    Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                }
            } catch (Throwable th) {
                try {
                    fs.close();
                } catch (IOException ex2) {
                    Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                }
                throw th;
            }
        } catch (FileNotFoundException ex3) {
            Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
            try {
                fs.close();
            } catch (IOException ex4) {
                Logger.getLogger(ResourceFile.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
            }
        }
    }
}
