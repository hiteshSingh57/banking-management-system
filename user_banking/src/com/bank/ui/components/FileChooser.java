package com.bank.ui.components;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FileChooser {
   public File ChooseImage(JLabel previewLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(
                new FileNameExtensionFilter("image file","jpg","jpeg","png","pdf")
        );
        int result = fileChooser.showOpenDialog(null);
        if (result==JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            Image img = imageIcon.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
            previewLabel.setIcon(new ImageIcon(img));

            return file;
        }
        return null;
    }
}
