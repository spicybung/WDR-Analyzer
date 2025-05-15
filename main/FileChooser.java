package main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/* loaded from: FileChooser.class */
public class FileChooser implements ActionListener {
    public FileChooser(Main main2, String type) {
        JFileChooser fileChooser = new JFileChooser(".");
        FileFilter filter = type.equals("WDR") ? createWDRFilter() : createDFFFilter();
        fileChooser.setFileFilter(filter);
        int status = fileChooser.showOpenDialog((Component) null);
        if (status == 0) {
            File selectedFile = fileChooser.getSelectedFile();
            main2.enableLabels();
            main2.setOpenedFile(selectedFile.getName());
            if (type.equals("WDR")) {
                main2.openWDRFile(selectedFile.getAbsolutePath());
            }
            if (status == 1) {
                System.out.println(1);
            }
        }
    }

    public FileChooser(Main main2, boolean save) {
        JFileChooser fileChooser = new JFileChooser(".");
        FileFilter wdrFilter = createWDRFilter();
        fileChooser.setFileFilter(wdrFilter);
        int status = fileChooser.showSaveDialog((Component) null);
        if (status == 0) {
            File selectedFile = fileChooser.getSelectedFile();
            main2.saveFile(selectedFile.getAbsolutePath());
            if (status == 1) {
                System.out.println(1);
            }
        }
    }

    private FileFilter createWDRFilter() {
        return new 1(this);
    }

    private FileFilter createDFFFilter() {
        return new 2(this);
    }

    public void actionPerformed(ActionEvent arg0) {
    }
}
