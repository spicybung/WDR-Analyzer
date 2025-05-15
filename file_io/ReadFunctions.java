package file_io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: ReadFunctions.class */
public class ReadFunctions {
    public boolean hasFlag(int flags, int flag) {
        boolean hasFlag = false;
        boolean finished = false;
        int waarde = 1024;
        int newFlag = flags;
        while (!finished) {
            newFlag -= waarde;
            if (waarde < flag) {
                finished = true;
            } else if (newFlag <= 0) {
                if (waarde == 1) {
                    finished = true;
                }
                newFlag = flags;
                waarde /= 2;
            } else {
                flags = newFlag;
                if (waarde == flag) {
                    hasFlag = true;
                    finished = true;
                } else {
                    waarde /= 2;
                }
            }
        }
        return hasFlag;
    }

    public FileInputStream openFile(String name) {
        FileInputStream file_in = null;
        try {
            File file = new File(name);
            file_in = new FileInputStream(file);
        } catch (IOException e) {
        }
        return file_in;
    }

    public void closeFile(FileInputStream file_in, DataInputStream data_in) {
        try {
            data_in.close();
            file_in.close();
        } catch (IOException e) {
        }
    }

    public void skipBytes(DataInputStream data_in, int aantal) {
        for (int i = 0; i < aantal; i++) {
            try {
                data_in.readByte();
            } catch (IOException e) {
            }
        }
    }

    public int readByteAsInt(DataInputStream data_in) {
        byte waarde;
        try {
            waarde = data_in.readByte();
        } catch (IOException e) {
            waarde = -1;
        }
        return waarde & 255;
    }

    public byte readByte(DataInputStream data_in) {
        byte waarde;
        try {
            waarde = data_in.readByte();
        } catch (IOException e) {
            waarde = -1;
        }
        return waarde;
    }

    public int readInt(DataInputStream data_in) {
        int waarde;
        try {
            waarde = swapInt(data_in.readInt());
        } catch (IOException e) {
            waarde = -1;
        }
        return waarde;
    }

    public int readShort(DataInputStream data_in) {
        int waarde;
        try {
            waarde = swapShort(data_in.readShort());
        } catch (IOException e) {
            waarde = -1;
        }
        return waarde;
    }

    public float readFloat(DataInputStream data_in) {
        byte[] bytes = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bytes[i] = readByte(data_in);
        }
        ByteBuffer data = ByteBuffer.wrap(bytes);
        float waarde = data.getFloat();
        return waarde;
    }

    public String readString(DataInputStream data_in, int size) {
        String woord = "";
        for (int i = 0; i < size; i++) {
            char letter = readChar(data_in);
            woord = woord + letter;
        }
        return woord;
    }

    public String readAndFixString(DataInputStream data_in, int size) {
        String woord = "";
        for (int i = 0; i < size; i++) {
            char letter = readChar(data_in);
            woord = woord + letter;
        }
        String fixt = "";
        for (int i2 = 0; i2 < woord.length(); i2++) {
            char w = woord.charAt(i2);
            if (w != 0) {
                fixt = fixt + w;
            }
        }
        return fixt;
    }

    public char readChar(DataInputStream data_in) {
        char letter = 0;
        try {
            letter = (char) data_in.readByte();
        } catch (IOException e) {
        }
        return letter;
    }

    public int swapInt(int v) {
        return (v >>> 24) | (v << 24) | ((v << 8) & 16711680) | ((v >> 8) & 65280);
    }

    public int swapShort(short i) {
        return ((i >> 8) & 255) + ((i << 8) & 65280);
    }

    public float swapFloat(float f) {
        int intValue = Float.floatToIntBits(f);
        return Float.intBitsToFloat(swapInt(intValue));
    }

    public String readString(DataInputStream data_in) {
        String woord = "";
        char readChar = readChar(data_in);
        while (true) {
            char letter = readChar;
            if (letter != 0) {
                woord = woord + letter;
                readChar = readChar(data_in);
            } else {
                return woord;
            }
        }
    }

    public int moreToRead(DataInputStream data_in) {
        try {
            return data_in.available();
        } catch (IOException ex) {
            Logger.getLogger(ReadFunctions.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            return 0;
        }
    }
}
