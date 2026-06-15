package com.bank.service;

import java.awt.*;
import java.awt.print.*;

public class PrintService {
    public static void printReceipt(String content) {

        PrinterJob job = PrinterJob.getPrinterJob();

        job.setPrintable((graphics, pageFormat, pageIndex) -> {

            if (pageIndex > 0) return Printable.NO_SUCH_PAGE;

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));

            int y = 100;
            for (String line : content.split("\n")) {
                g2d.drawString(line, 100, y);
                y += 15;
            }

            return Printable.PAGE_EXISTS;
        });

        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
