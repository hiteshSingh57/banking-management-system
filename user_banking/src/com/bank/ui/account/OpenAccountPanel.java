package com.bank.ui.account;
import javax.swing.*;
import java.awt.*;

public class OpenAccountPanel extends JPanel{
    public OpenAccountPanel(CardLayout cardLayout,JPanel cardPanel) {
        CardLayout OpenCardLayout = new CardLayout();
        JPanel OpenPanel = new JPanel() {
        private Image bgImage = new ImageIcon(
                getClass().getResource("/image/img.jpg")
        ).getImage();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
        OpenPanel.setPreferredSize(new Dimension(1600,900));

        //THIS PANEL HOLDS CARDS
        JPanel cardContainer = new JPanel(OpenCardLayout);
        cardContainer.setOpaque(false);

        JPanel openCard = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        openCard.setBorder(BorderFactory.createEmptyBorder(100,200,100,200));
        openCard.setOpaque(false);

        // -------------------------------------------------------Open Card-------------------------------------------------
        JPanel OpenMainCard = new JPanel(new GridBagLayout());
        OpenMainCard.setPreferredSize(new Dimension(900,500));
        OpenMainCard.setLayout(new GridLayout(1,2));
        OpenMainCard.setBackground(Color.white);

        //-------------------------------------------------------LEFT CARD--------------------------------------------------
        JPanel leftCard = new JPanel(new GridBagLayout());
        leftCard.setBackground(new Color(210, 229, 244));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,40,8,40);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.gridy = 0;
        JPanel top = new JPanel();
        top.setOpaque(false);
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Monospaced",Font.PLAIN,35));
        title.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        title.setVerticalTextPosition(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(title);
        leftCard.add(top,gbc);

        gbc.gridy = 1;
        JPanel middle = new JPanel(new GridBagLayout());
        middle.setOpaque(false);

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.insets = new Insets(8,0,8,0);
        gbc_2.anchor = GridBagConstraints.WEST;
        gbc_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_2.gridx = 0;
        gbc_2.weightx = 1;

        gbc_2.gridy = 0;
        gbc_2.gridx = 0;
        gbc_2.gridwidth = 2;
        JLabel choose = new JLabel("Choose Account Type : - ");
        choose.setMaximumSize(new Dimension(100,100));
        choose.setFont(new Font("Monospaced",Font.PLAIN,22));
        middle.add(choose,gbc_2);

        gbc_2.gridy = 1;
        gbc_2.gridx = 0;
        gbc_2.gridwidth = 1;
        ImageIcon logIcon1 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/saving.png"))
                        .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)
        );
        JLabel logo_1 = new JLabel(logIcon1);
        logo_1.setBorder(BorderFactory.createEmptyBorder(5,20,5,0));
        middle.add(logo_1,gbc_2);

        gbc_2.gridy = 1;
        gbc_2.gridx = 1;
        JRadioButton Saving = new JRadioButton("Saving Account ");
        Saving.setIcon(new ImageIcon(" "));
        Saving.setHorizontalAlignment(SwingConstants.LEFT);
        Saving.setFont(new Font("Monospaced",Font.PLAIN, 22));
        Saving.setFocusPainted(false);
        Saving.setOpaque(false);
        Saving.setBorderPainted(false);
        middle.add(Saving,gbc_2);

        gbc_2.gridy = 2;
        gbc_2.gridx = 0;
        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/current.png"))
                        .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)
        );
        JLabel logo_2 = new JLabel(logIcon2);
        logo_2.setBorder(BorderFactory.createEmptyBorder(5,20,5,0));
        middle.add(logo_2,gbc_2);

        gbc_2.gridy = 2;
        gbc_2.gridx = 1;
        JRadioButton Current = new JRadioButton("Current Account ");
        Current.setHorizontalAlignment(SwingConstants.LEFT);
        Current.setIcon(new ImageIcon(" "));
        Current.setFont(new Font("Monospaced",Font.PLAIN, 22));
        Current.setFocusPainted(false);
        Current.setOpaque(false);
        Current.setBorderPainted(false);
        middle.add(Current,gbc_2);

        gbc_2.gridy = 3;
        gbc_2.gridx = 0;
        ImageIcon logIcon3 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/joint.png"))
                        .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)
        );
        JLabel logo_3 = new JLabel(logIcon3);
        logo_3.setBorder(BorderFactory.createEmptyBorder(5,20,5,0));
        middle.add(logo_3,gbc_2);

        gbc_2.gridy = 3;
        gbc_2.gridx = 1;
        JRadioButton Joint = new JRadioButton("Joint Account ");
        Joint.setIcon(new ImageIcon(" "));
        Joint.setHorizontalAlignment(SwingConstants.LEFT);
        Joint.setFont(new Font("Monospaced",Font.PLAIN, 22));
        Joint.setFocusPainted(false);
        Joint.setOpaque(false);
        Joint.setBorderPainted(false);
        middle.add(Joint,gbc_2);

        ButtonGroup btGrp = new ButtonGroup();
        btGrp.add(Saving);
        btGrp.add(Current);
        btGrp.add(Joint);

        Saving.addActionListener(_ -> cardLayout.show(cardPanel, "Saving ACCOUNT"));
        Current.addActionListener(_ -> cardLayout.show(cardPanel, "Current ACCOUNT"));
        Joint.addActionListener(_ -> cardLayout.show(cardPanel, "Joint ACCOUNT"));
        leftCard.add(middle,gbc);

        gbc.gridy = 2;
        JPanel bottom = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_3 = new GridBagConstraints();
        gbc_3.insets = new Insets(8,0,8,0);
        gbc_3.anchor = GridBagConstraints.CENTER;
        gbc_3.gridx = 0;
        gbc_3.weightx = 1;

        gbc_3.gridy = 0;
        bottom.setOpaque(false);
        JButton backButton = new JButton("Back To Dashboard");
        backButton.setFont(new Font("Monospaced",Font.PLAIN, 22));
        backButton.setFocusPainted(false);                        
        backButton.setOpaque(false);                              
        backButton.setBorderPainted(false);                       
        backButton.setHorizontalAlignment(SwingConstants.CENTER);

        backButton.setFocusPainted(false);
        backButton.addActionListener(_ -> cardLayout.show(cardPanel, "EMPLOYEE DASHBOARD"));
        bottom.add(backButton,gbc_3);
        leftCard.add(bottom,gbc);

        //-----------------------------------------------------RIGHT CARD --------------------------------------------------
        JPanel right = new JPanel();
        right.setBackground(new Color(33, 102, 255));
        right.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        ImageIcon logIcon4 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)
        );
        JLabel logo_4 = new JLabel(logIcon4);
        logo_4.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        topPanel.add(logo_4);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,30));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        topPanel.add(label);
        right.add(topPanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());

        JLabel welcome = new JLabel("<html><h1>Welcome to secure banking</h1><p>Secure your future.</p>" +
                "<p>Open your account with us today!</p></html>");
        welcome.setFont(new Font("Monospaced",Font.PLAIN,18));
        welcome.setForeground(Color.WHITE);
        centerPanel.add(welcome);
        right.add(centerPanel,BorderLayout.CENTER);

        OpenMainCard.add(leftCard);
        OpenMainCard.add(right);
        openCard.add(OpenMainCard);

        cardContainer.add(openCard,"Open Account");
        OpenPanel.add(cardContainer);
        add(OpenPanel);

    }
}
