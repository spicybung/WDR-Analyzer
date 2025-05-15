package main;

import com.ice.jni.registry.RegStringValue;
import com.ice.jni.registry.Registry;
import com.ice.jni.registry.RegistryException;
import com.ice.jni.registry.RegistryKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: Association.class */
public class Association {
    public Association() {
        addRegEntrys();
    }

    private void copyFile(String file, String copy) {
        System.out.println("Copying files");
        try {
            File fromFile = new File(file);
            File toFile = new File(copy);
            if (!fromFile.exists()) {
                System.out.println("Error");
            }
            if (!fromFile.isFile()) {
                System.out.println("Error");
            }
            if (!fromFile.canRead()) {
                System.out.println("Error");
            }
            if (toFile.isDirectory()) {
                toFile = new File(toFile, fromFile.getName());
            }
            FileInputStream from = new FileInputStream(fromFile);
            FileOutputStream to = new FileOutputStream(toFile);
            byte[] buffer = new byte[4096];
            while (true) {
                int bytesRead = from.read(buffer);
                if (bytesRead != -1) {
                    to.write(buffer, 0, bytesRead);
                } else {
                    from.close();
                    to.close();
                    return;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Association.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        }
    }

    private void copyFiles() {
        System.out.println(System.getenv("SystemRoot"));
        copyFile("gluegen-rt.dll", System.getenv("SystemRoot") + "/gluegen-rt.dll");
        copyFile("jogl.dll", System.getenv("SystemRoot") + "/jogl.dll");
        copyFile("jogl_awt.dll", System.getenv("SystemRoot") + "/jogl_awt.dll");
        copyFile("jogl_cg.dll", System.getenv("SystemRoot") + "/jogl_cg.dll");
    }

    private void deleteFiles() {
        File f = new File(System.getenv("SystemRoot") + "/gluegen-rt.dll");
        deleteFile(f);
        File f2 = new File(System.getenv("SystemRoot") + "/jogl.dll");
        deleteFile(f2);
        File f3 = new File(System.getenv("SystemRoot") + "/jogl_awt.dll");
        deleteFile(f3);
        File f4 = new File(System.getenv("SystemRoot") + "/jogl_cg.dll");
        deleteFile(f4);
    }

    private void deleteFile(File f) {
        if (f.exists()) {
            if (!f.delete()) {
                System.out.println("Failed");
                return;
            } else {
                System.out.println("File deleted");
                return;
            }
        }
        System.out.println("File doesn't exist");
    }

    private void addRegEntrys() {
        try {
            Registry registry = new Registry();
            registry.debugLevel = true;
            RegistryKey regkey = Registry.HKEY_CLASSES_ROOT;
            RegistryKey key = Registry.openSubkey(regkey, ".wdr", 4);
            System.out.println(key.getDefaultValue());
            RegStringValue stringValue = new RegStringValue(key, "javaci", 1);
            stringValue.setData("Hello");
            key.setValue(stringValue);
        } catch (RegistryException ex) {
            ex.printStackTrace();
        }
    }

    private void removeRegEntrys() {
    }
}
