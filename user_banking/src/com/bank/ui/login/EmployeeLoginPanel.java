package com.bank.ui.login;
import com.bank.model.EmployeeModel.EmployeeAccount;
import com.bank.service.EmployeeService.EmployeeService;
import com.bank.ui.dashboard.EmployeeDashboard;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;

public class EmployeeLoginPanel extends JPanel {
    private JTextField emm_acctext;
    private JTextField emm_userField;
    private JLabel emm_welcome;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public EmployeeLoginPanel (CardLayout cardLayout, JPanel cardPanel ) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());

        JPanel employeePanel = new JPanel(){
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
        employeePanel.setPreferredSize(new Dimension(1500, 900));
        employeePanel.setBackground(new Color(3, 42, 71));

        //--------------------------------------------CARD CONTAINER---------------------------
        JPanel employeeCardContainer = new JPanel(logCardLayout);
        employeeCardContainer.setOpaque(false);

        //-------------------------------------------------------------------------LOG CARD----------------------------------------------------
        JPanel employeeCard = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        employeeCard.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        employeeCard.setOpaque(false);

        JPanel mainCard = new JPanel();
        mainCard.setBackground(new Color(3, 42, 71));
        mainCard.setPreferredSize(new Dimension(900, 500));
        mainCard.setLayout(new GridLayout(1, 2));
        //---------------------------------------------------------------------------LEFT CARD---------------------------------------------------
        JPanel left = new JPanel(new GridBagLayout());
        left.setBackground(new Color(210, 229, 244));
        left.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        ImageIcon logIcon = new ImageIcon("C:/Users/Yashv/Desktop/yourBank/user_banking/src/image/bankem.png");
        Image scaled = logIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        top.add(logo);

        JLabel title  = new JLabel("Employee Login");
        title.setFont(new Font("Monospaced",Font.PLAIN,35));
        title.setVerticalTextPosition(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        top.add(title);
        left.add(top,BorderLayout.NORTH);

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
        JLabel employeeID = new JLabel("Enter Employee ID");
        employeeID.setFont(new Font("Monospaced", Font.PLAIN,18));
        center.add(employeeID,gbc);

        gbc.gridy = 1;
        JTextField employeeID_field = new JTextField(15);
        employeeID_field.setFont(new Font("Monospaced", Font.PLAIN,20));
        employeeID_field.setPreferredSize(new Dimension(120,25));
        center.add(employeeID_field,gbc);

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
            String employeeID_1 = employeeID_field.getText();
            String pass = new String(passwordField.getPassword());

            if (employeeID_1.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Email ID");
                employeeID_field.requestFocus();
                return;
            }
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Password ");
                passwordField.requestFocus();
                return;
            }
            try {
                EmployeeService service = new EmployeeService();
                EmployeeAccount employee = service.login(employeeID_1, pass);
                JOptionPane.showMessageDialog(null, "Login Successful!");
                EmployeeDashboard dash = new EmployeeDashboard(
                        cardLayout,
                        cardPanel,
                        employee.getEmployeeId(),
                        employee.getName()
                );
                cardPanel.add(dash, "EMPLOYEE DASHBOARD");
                cardLayout.show(cardPanel, "EMPLOYEE DASHBOARD");

                employeeID_field.setText("");
                passwordField.setText("");
                employeeID_field.requestFocus();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
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
        back.addActionListener(_ -> cardLayout.show(cardPanel,"LOGIN"));
        center.add(back,gbc);
        left.add(center);

        //------------------------------------------------------------------------RIGHT CARD----------------------------------------------
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
        employeeCard.add(mainCard);
        employeeCardContainer.add(employeeCard);
        employeePanel.add(employeeCardContainer,"EMPLOYEE LOGIN");
        add(employeePanel);
    }

}
