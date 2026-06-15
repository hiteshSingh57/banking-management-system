package com.bank.ui.login;
import com.bank.model.UserModel.UserAccount;
import com.bank.service.UserService.UserService;
import com.bank.ui.dashboard.CustomerDashboard;

import javax.swing.*;
import java.awt.*;

public class CustomerLoginPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private  long cus_number;
    private  int cus_id;
    public CustomerLoginPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(new BorderLayout());
        JPanel customerPanel = new JPanel(){
            private Image bgImage = new ImageIcon(
                    getClass().getResource("/image/img.jpg")
            ).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        CardLayout logCardLayout = new CardLayout();
        customerPanel.setPreferredSize(new Dimension(1600,900));
        customerPanel.setBackground(new Color(3, 42, 71));

        //----------------------------------------------------CARD CONTAINER------------------------------------------------
        JPanel customerCardContainer = new JPanel(logCardLayout);
        customerCardContainer.setOpaque(false);

        //------------------------------------------------------LOG CARD----------------------------------------------------
        JPanel customerCard = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        customerCard.setBorder(BorderFactory.createEmptyBorder(100,200,100,200));
        customerCard.setOpaque(false);

        JPanel mainCard = new JPanel();
        mainCard.setBackground(new Color(3, 42, 71));
        mainCard.setPreferredSize(new Dimension(900,500));
        mainCard.setLayout(new GridLayout(1,2));

        //===================================================LEFT PANEL=====================================================
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(210, 229, 244));
        left.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        ImageIcon logIcon = new ImageIcon("C:/Users/Yashv/Desktop/yourBank/user_banking/src/image/user.png");
        Image scaled = logIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        top.add(logo);

        JLabel title = new JLabel("Customer Login");
        title.setFont(new Font("Monospaced",Font.PLAIN,35));
        title.setVerticalTextPosition(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        top.add(title);
        left.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,40,8,40);
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill= GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        JLabel accountNum = new JLabel("Enter Account Number");
        accountNum.setFont(new Font("Monospaced", Font.PLAIN,18  ));
        center.add(accountNum,gbc);

        gbc.gridy = 1;
        JTextField numberField = new JTextField(15);
        numberField.setFont(new Font("Monospaced", Font.PLAIN,20));
        numberField.setPreferredSize(new Dimension(120,25));
        center.add(numberField,gbc);

        gbc.gridy = 2;
        JLabel password = new JLabel("Enter Password");
        password.setFont(new Font("Monospaced", Font.PLAIN,18));
        center.add(password,gbc);

        gbc.gridy = 3;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Monospaced", Font.PLAIN,20));
        passwordField.setPreferredSize(new Dimension(120,25));
        center.add(passwordField,gbc);

        gbc.gridy = 4;
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(120,30));
        login.setFont(new Font("Monospaced",Font.PLAIN, 22));
        login.setFocusPainted(false);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);

        login.addActionListener(_ -> {
            String account_number = numberField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();

            if (account_number.isEmpty() || account_number.equals("Enter Account Number")) {
                JOptionPane.showMessageDialog(null, "Please Enter a valid Account Number");
                numberField.requestFocus();
                return;
            }
            long account_Number;
            try {
                account_Number = Long.parseLong(account_number);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Account Number must be numerical");
                return;
            }
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Password ");
                passwordField.requestFocus();
                return;
            }

            try{
                UserService service = new UserService();
                UserAccount user = service.login(account_Number,pass);

                JOptionPane.showMessageDialog(null,"Login Successful !");
                CustomerDashboard dashboard =
                        new CustomerDashboard(cardLayout, cardPanel,
                                user.getAccountNumber(),
                                user.getUserName(),
                                user.getUserId()
                        );

                cardPanel.add(dashboard, "CUSTOMER DASHBOARD");
                cardLayout.show(cardPanel, "CUSTOMER DASHBOARD");

                numberField.setText("");
                passwordField.setText("");
                numberField.requestFocus();

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                ex.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        center.add(login,gbc);

        gbc.gridy = 5;
        JButton back = new JButton("Back to login");
        back.setPreferredSize(new Dimension(120,30));
        back.setFont(new Font("Monospaced",Font.PLAIN, 22));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(_ -> cardLayout.show(cardPanel,"CUSTOMER"));
        center.add(back,gbc);

        left.add(center);
        mainCard.add(left);

        //========================================================RIGHT PANEL===============================================
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

        mainCard.add(right);
        customerCard.add(mainCard);

        customerCardContainer.add(customerCard);
        customerPanel.add(customerCardContainer,"CUSTOMER LOGIN");
        add(customerPanel);
    }
}
