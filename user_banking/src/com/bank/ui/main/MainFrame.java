package com.bank.ui.main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JPanel {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(new BorderLayout());

        //------------------------------------------------ BACKGROUND PANEL --------------------------------
        JPanel rightmid = new JPanel() {
            private Image bgImage = new ImageIcon(
                    getClass().getResource("/image/img.jpg")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        rightmid.setLayout(new GridBagLayout());

        //------------------------------------------------ CENTER CONTENT --------------------------------
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 15, 15, 15);

        // ---------------- TOP (LOGO + NAME)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setOpaque(false);

        ImageIcon logIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)
        );
        JLabel logo = new JLabel(logIcon);
        topPanel.add(logo);

        JLabel title = new JLabel("yourBANK");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Monospaced", Font.BOLD, 48));
        topPanel.add(title);

        gbc.gridy = 0;
        rightmid.add(topPanel, gbc);

        // ---------------- WELCOME TEXT
        JLabel welcome = new JLabel("Welcome to yourBANK", SwingConstants.CENTER);
        welcome.setFont(new Font("Monospaced", Font.PLAIN, 26));
        welcome.setForeground(Color.WHITE);

        gbc.gridy = 1;
        rightmid.add(welcome, gbc);

        // ---------------- BUTTON PANEL
        JPanel loadingPanel = new JPanel();
        loadingPanel.setOpaque(false);
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));

        JLabel loadingLabel = new JLabel("Loading............");
        loadingLabel.setFont(new Font("Monospaced", Font.PLAIN, 22));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadingPanel.add(loadingLabel);
        loadingPanel.add(Box.createRigidArea(new Dimension(0,10)));

        // Progress Bar
        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setAlignmentX(Component.CENTER_ALIGNMENT);
        progress.setPreferredSize(new Dimension(200,0));

        loadingPanel.add(progress);

        gbc.gridy = 2;
        rightmid.add(loadingPanel, gbc);

        //------------------------------------------------ ADD TO FRAME --------------------------------
        add(rightmid, BorderLayout.CENTER);
    }
}