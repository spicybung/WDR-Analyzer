package main;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/* loaded from: FileChooser$1.class */
class FileChooser$1 extends FileFilter {
    final /* synthetic */ FileChooser this$0;

    FileChooser$1(FileChooser fileChooser) {
        this.this$0 = fileChooser;
    }

    public boolean accept(File file) {
        String sFilePath = file.getName();
        if (sFilePath.endsWith(".wdr") || file.isDirectory()) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "GTA IV Model (.wdr)";
    }
}
