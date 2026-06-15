package com.bank.ui.login;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JPanel {
    CardLayout cardLayout;
    JPanel cardPanle;

    public  LoginFrame(CardLayout cardLayout,JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanle = cardPanel;

        setLayout(new BorderLayout());
        JPanel loginPanel = new JPanel(){
            private Image bgImage = new ImageIcon(
                    getClass().getResource("/image/img.jpg")
            ).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel.setPreferredSize(new Dimension(1600,900));

        //-------------------------------------------------card container-----------------------------------------------
        CardLayout LogCardLayout = new CardLayout();
        JPanel loginCardContainer = new JPanel(LogCardLayout);
        loginCardContainer.setOpaque(false);

        //---------------------------------------------------LOG CARD---------------------------------------------------
        JPanel logCard = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        logCard.setBorder(BorderFactory.createEmptyBorder(100,200,100,200));
        logCard.setOpaque(false);

        JPanel mainCard = new JPanel();
        mainCard.setPreferredSize(new Dimension(900,500));
        mainCard.setLayout(new GridLayout(1,2));

        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(210, 229, 244));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,5,40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.weightx = 1;

        //-----------------------------------------------------TITLE----------------------------------------------------
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JLabel title  = new JLabel("Login");
        title.setFont(new Font("Monospaced",Font.PLAIN,30));
        title.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        left.add(title,gbc);

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //-------------------------------------------------------BUTTON-----------------------------------------------------
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        ImageIcon logIcon = new ImageIcon("C:/Users/Yashv/Desktop/yourBank/user_banking/src/image/user.png");
        Image scaled = logIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setBorder(BorderFactory.createEmptyBorder(5,30,5,0));
        left.add(logo,gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton customerLogin = new JButton("Customer Login");
        customerLogin.setMaximumSize(new Dimension(100,100));
        customerLogin.setFont(new Font("Monospaced",Font.PLAIN, 22));
        customerLogin.setFocusPainted(false);
        customerLogin.setOpaque(false);
        customerLogin.setBorderPainted(false);                       
        customerLogin.addActionListener(_ -> cardLayout.show(cardPanel, "CUSTOMER"));
        left.add(customerLogin,gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        ImageIcon logIcon_2 = new ImageIcon("C:/Users/Yashv/Desktop/yourBank/user_banking/src/image/bankem.png");
        Image scaled_2 = logIcon_2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel logo_2 = new JLabel(new ImageIcon(scaled_2));
        logo_2.setBorder(BorderFactory.createEmptyBorder(5,30,5,0));
        left.add(logo_2,gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton bankEmployee = new JButton("Employee Login");
        bankEmployee.setMaximumSize(new Dimension(100,100));
        bankEmployee.setFont(new Font("Monospaced",Font.PLAIN, 22));
        bankEmployee.setFocusPainted(false);
        bankEmployee.setOpaque(false);
        bankEmployee.setBorderPainted(false);
        bankEmployee.addActionListener(_ -> cardLayout.show(cardPanel,"EMPLOYEE LOGIN"));
        left.add(bankEmployee,gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        ImageIcon adminIcon = new ImageIcon("C:/Users/Yashv/Desktop/yourBank/user_banking/src/image/bankem.png");
        Image adminscaled = adminIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel adminlogo = new JLabel(new ImageIcon(adminscaled));
        adminlogo.setBorder(BorderFactory.createEmptyBorder(5,30,5,0));
        left.add(adminlogo,gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton adminLogin = new JButton("Admin Login");
        adminLogin.setMaximumSize(new Dimension(100,100));
        adminLogin.setFont(new Font("Monospaced",Font.PLAIN, 22));
        adminLogin.setFocusPainted(false);
        adminLogin.setOpaque(false);
        adminLogin.setBorderPainted(false);
        adminLogin.addActionListener(_ -> cardLayout.show(cardPanel, "ADMIN LOGIN"));
        left.add(adminLogin,gbc);

        GridBagConstraints gbc_Button = new GridBagConstraints();
        gbc_Button.insets = new Insets(5,40,20,40);
        gbc_Button.fill = GridBagConstraints.HORIZONTAL;
        gbc_Button.gridx = 1;
        gbc_Button.weightx = 1;

        gbc_Button.gridy=4;
        gbc_Button.gridx = 0;
        gbc_Button.gridwidth = 2;
        gbc_Button.weighty = 0.5;

        JPanel BackTo = new JPanel();
        BackTo.setOpaque(false);

        left.add(BackTo,gbc_Button);
        gbc_Button.gridwidth = 1;

        //---------------------------------------------------------RIGHT CARD-----------------------------------------------
        JPanel right = new JPanel();
        right.setBackground(new Color(33, 102, 255));
        right.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)
        );
        JLabel logo_3 = new JLabel(logIcon2);
        logo_3.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        topPanel.add(logo_3);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,30));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        topPanel.add(label);
        right.add(topPanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());

        JLabel welcome = new JLabel("<html><h1>Welcome to Secure Banking</h1><p>Login with your credentials details.</p></html>");
        welcome.setFont(new Font("Monospaced",Font.PLAIN,18));
        welcome.setForeground(Color.WHITE);
        centerPanel.add(welcome);
        right.add(centerPanel,BorderLayout.CENTER);

        mainCard.add(left);
        mainCard.add(right);

        logCard.add(mainCard);
        loginCardContainer.add(logCard);
        loginPanel.add(loginCardContainer,"LOGIN");
        add(loginPanel);
    }
}
