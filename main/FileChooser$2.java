package main;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/* loaded from: FileChooser$2.class */
class FileChooser$2 extends FileFilter {
    final /* synthetic */ FileChooser this$0;

    FileChooser$2(FileChooser fileChooser) {
        this.this$0 = fileChooser;
    }

    public boolean accept(File file) {
        String sFilePath = file.getName();
        if (sFilePath.endsWith(".dff") || file.isDirectory()) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "Renderware Model (.dff)";
    }
}
