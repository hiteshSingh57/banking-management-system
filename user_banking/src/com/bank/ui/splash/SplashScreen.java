package com.bank.ui.splash;
import com.bank.ui.main.BankApp;
import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
        JLabel text;
        JLabel bottomtext;
        public SplashScreen() {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(152, 24, 227));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            //================logo=========================
            JPanel bankLogo = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,5));
            bankLogo.setMaximumSize(new Dimension(700,100));
            bankLogo.setOpaque(false);

            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/image/logo.png"));
            Image img = logoIcon.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(img));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            bankLogo.add(logo);

            JLabel label = new JLabel("yourBANK");
            label.setForeground(Color.white);
            label.setFont(new Font("Monospaced", Font.PLAIN,26));
            label.setVerticalTextPosition(SwingConstants.CENTER);
            bankLogo.add(label);

            //==================welcome text============================
            text = new JLabel("");
            text.setForeground(Color.WHITE);
            text.setFont(new Font("Monospaced", Font.PLAIN,28));
            text.setAlignmentX(Component.CENTER_ALIGNMENT);
            text.setPreferredSize(new Dimension(400,50));

            //=====================bottom==============================
            bottomtext = new JLabel("");
            bottomtext.setForeground(Color.WHITE);
            bottomtext.setFont(new Font("Monospaced", Font.PLAIN,20));
            bottomtext.setAlignmentX(Component.CENTER_ALIGNMENT);
            bottomtext.setPreferredSize(new Dimension(400,50));

            panel.add(Box.createVerticalStrut(10));
            panel.add(bankLogo,BorderLayout.NORTH);
            panel.add(Box.createVerticalStrut(10));
            panel.add(text);
            panel.add(Box.createVerticalStrut(15));
            panel.add(bottomtext);
            add(panel);

            setSize(600,300);
            setLocationRelativeTo(null);
            setVisible(true);

            startTyping();
        }
        private void startTyping() {
            String message = "Welcome to yourBank";
            String message2 = "APKA APNA BANK";

            new Thread(() -> {
                try {
                    for (int i=0; i<message.length();i++) {
                        String t = message.substring(0,i+1);
                        SwingUtilities.invokeLater(()-> text.setText(t));
                        Thread.sleep(100);
                    }
                    for(int j=0;j<message2.length();j++) {
                        String t2 = message2.substring(0,j+1);
                        SwingUtilities.invokeLater(()-> bottomtext.setText(t2));
                        Thread.sleep(100);
                    }
                    Thread.sleep(400);
                    dispose();
                    BankApp app = new BankApp();
                    app.MAINFRAME();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } ).start();
        }
}
