package com.bank.ui.login;
import com.bank.config.DBConnection;
import com.bank.ui.dashboard.AdminDashboard;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLogin extends  JPanel {
        private JTextField emm_acctext;
        private JTextField emm_userField;
        private JLabel emm_welcome;
        private CardLayout cardLayout;
        private JPanel cardPanel;

        public AdminLogin (CardLayout cardLayout, JPanel cardPanel ) {
            this.cardPanel = cardPanel;
            this.cardLayout = cardLayout;

            setLayout(new BorderLayout());
            JPanel adminPanel = new JPanel(){
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
            adminPanel.setPreferredSize(new Dimension(1500, 900));
            adminPanel.setBackground(new Color(3, 42, 71));

            //--------------------------------------------CARD CONTAINER---------------------------
            JPanel adminCardLayout = new JPanel(logCardLayout);
            adminCardLayout.setOpaque(false);

            //-------------------------------------------------------------------------LOG CARD----------------------------------------------------
            JPanel adminCard = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            adminCard.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
            adminCard.setOpaque(false);

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

            JLabel title  = new JLabel("Admin Login");
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
            JLabel adminID = new JLabel("Enter ID");
            adminID.setFont(new Font("Monospaced", Font.PLAIN,18));
            center.add(adminID,gbc);

            gbc.gridy = 1;
            JTextField admin_field = new JTextField(15);
            admin_field.setFont(new Font("Monospaced", Font.PLAIN,20));
            admin_field.setPreferredSize(new Dimension(120,25));
            center.add(admin_field,gbc);

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

                String admin_1 = admin_field.getText().trim();
                String adminPass = new String(passwordField.getPassword()).trim();
                if (admin_1.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please Enter Valid Email ID");
                    admin_field.requestFocus();
                    return;
                }
                if (adminPass.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please Enter Valid Password ");
                    passwordField.requestFocus();
                    return;
                }
                try{
                    Connection conn = DBConnection.getConnection();
                    String sql = "SELECT password_hash FROM admin WHERE user_id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, admin_1);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String storedHash = BCrypt.hashpw(adminPass, BCrypt.gensalt());

                        if (BCrypt.checkpw(adminPass, storedHash)) {
                            System.out.println("Login Success");
                            JOptionPane.showMessageDialog(null, "Login Successful!");

                            AdminDashboard dash = new AdminDashboard(cardLayout,cardPanel,new DefaultTableModel(),new DefaultTableModel());
                            cardPanel.add(dash, "ADMIN DASHBOARD");
                            cardLayout.show(cardPanel, "ADMIN DASHBOARD");

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid ID And Password");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin Not Registered in this Bank");
                        return;
                    }
                    admin_field.setText("");
                    passwordField.setText("");
                    admin_field.requestFocus();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    e.printStackTrace();
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
            adminCard.add(mainCard);

            adminCardLayout.add(adminCard);
            adminPanel.add(adminCardLayout,"ADMIN LOGIN");
            add(adminPanel);
        }
}
