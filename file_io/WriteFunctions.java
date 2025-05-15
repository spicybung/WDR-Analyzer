package file_io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: WriteFunctions.class */
public class WriteFunctions {
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

    public FileOutputStream openFile(String name) {
        FileOutputStream file_out = null;
        try {
            File file = new File(name);
            file_out = new FileOutputStream(file);
        } catch (IOException e) {
        }
        return file_out;
    }

    public void closeFile(FileOutputStream file_out, DataOutputStream data_out) {
        try {
            data_out.close();
            file_out.close();
        } catch (IOException e) {
        }
    }

    public void writeByte(DataOutputStream data_out, int waarde) {
        try {
            data_out.writeByte(waarde);
        } catch (IOException e) {
        }
    }

    public void writeInt(DataOutputStream data_out, int waarde) {
        try {
            data_out.writeInt(waarde);
        } catch (IOException e) {
        }
    }

    public void writeShort(DataOutputStream data_out, int waarde) {
        try {
            data_out.writeShort(waarde);
        } catch (IOException e) {
        }
    }

    public void writeFloat(DataOutputStream data_in, float waarde) {
        try {
            data_in.writeFloat(waarde);
        } catch (IOException ex) {
            Logger.getLogger(WriteFunctions.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
    }

    public char writeChar(DataOutputStream data_in, char waarde) {
        try {
            data_in.writeByte(waarde);
        } catch (IOException e) {
        }
        return (char) 0;
    }

    public void writeString(DataOutputStream data_in, String waarde) {
        for (int i = 0; i < waarde.length(); i++) {
            writeChar(data_in, waarde.charAt(i));
        }
    }
}
