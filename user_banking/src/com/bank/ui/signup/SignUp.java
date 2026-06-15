package com.bank.ui.signup;
import com.bank.config.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUp extends  JPanel{
    CardLayout cardLayout;
    JPanel cardPanle;

    public SignUp(CardLayout cardLayout, JPanel cardPanel) {

        this.cardLayout = cardLayout;
        this.cardPanle = cardPanel;

        setLayout(new GridBagLayout());

        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.weightx = 0.20;
        gbcLeft.weighty = 1;
        gbcLeft.fill = GridBagConstraints.BOTH;
        //----------------------------------------------------- MIDDLE LEFT -------------------------------------
        JPanel leftMiddle = new JPanel();

        leftMiddle.setBackground(new Color(33, 102, 255));
        leftMiddle.setLayout(new BoxLayout(leftMiddle, BoxLayout.Y_AXIS));
        leftMiddle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        ImageIcon logIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH)
        );
        JLabel logo_3 = new JLabel(logIcon);
        topPanel.add(logo_3);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,30));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        topPanel.add(label);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());

        JLabel welcome = new JLabel("<html><h1>Welcome to Secure Banking</h1>" +
                "<p>Signup with your credentials details.</p></html>");
        welcome.setFont(new Font("Monospaced",Font.PLAIN,18));
        welcome.setForeground(Color.WHITE);
        centerPanel.add(welcome);

        leftMiddle.add(topPanel);
        leftMiddle.add(Box.createVerticalStrut(30));
        leftMiddle.add(centerPanel);


        add(leftMiddle, gbcLeft);

//---------------------------------------------------- MIDDLE RIGHT -------------------------------------
        JPanel rightMiddle = new JPanel(new BorderLayout());
        rightMiddle.setBackground(new Color(210, 229, 244));

        JPanel topbar = new JPanel();
        topbar.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        topbar.setLayout(new BoxLayout(topbar, BoxLayout.Y_AXIS));
        topbar.setOpaque(false);

        ImageIcon logIcon2 = new ImageIcon(new ImageIcon(
                getClass().getResource("/image/back.png"))
                .getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));

        JButton back = new JButton(logIcon2);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.setOpaque(false);
        back.setBorderPainted(false);
        back.addActionListener( _-> cardLayout.show(cardPanel, "CUSTOMER"));
        topbar.add(back);

        JLabel title = new JLabel("Fill Out All Fields ");
        title.setFont(new Font("Monospaced",Font.PLAIN, 26));
        topbar.add(title);

        rightMiddle.add(topbar,BorderLayout.NORTH);


        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        form.setOpaque(false);

        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.weightx = 1;
        gbcForm.insets = new Insets(10,30,10,30);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;
        gbcForm.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Monospaced", Font.PLAIN, 18);
        Font fieldFont = new Font("Monospaced", Font.PLAIN, 18);

        JTextField accountTxt = new JTextField();
        JTextField crnTxt = new JTextField();
        JTextField nameTxt = new JTextField();
        JTextField date = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JPasswordField passTxt = new JPasswordField();
        JPasswordField confirmTxt = new JPasswordField();

        //row 1
        gbcForm.gridy = 0;
        gbcForm.gridx = 0;
        form.add(new JLabel("A/C no. "), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("CRN Number "), gbcForm);

        gbcForm.gridy = 1;
        gbcForm.gridx = 0;
        form.add(accountTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(crnTxt, gbcForm);

        //row 2
        gbcForm.gridy = 2;
        gbcForm.gridx = 0;
        form.add(new JLabel("Name "), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Date "), gbcForm);

        gbcForm.gridy = 3;
        gbcForm.gridx = 0;
        form.add(nameTxt, gbcForm);
        gbcForm.gridx = 1;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(editor);
        dobSpinner.setFont(new Font("Monospaced", Font.PLAIN, 18));
        form.add(dobSpinner, gbcForm);

        // row 3
        gbcForm.gridy = 4;
        gbcForm.gridx = 0;
        form.add(new JLabel("Email Id "), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Phome Number "), gbcForm);

        gbcForm.gridy = 5;
        gbcForm.gridx = 0;
        form.add(emailTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(phoneTxt, gbcForm);

        //row 4
        gbcForm.gridy = 6;
        gbcForm.gridx = 0;
        form.add(new JLabel("Set Password "), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Confirm Password"), gbcForm);

        gbcForm.gridy = 7;
        gbcForm.gridx = 0;
        form.add(passTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(confirmTxt, gbcForm);

        Dimension fieldSize = new Dimension(200,30);

        accountTxt.setPreferredSize(fieldSize);
        crnTxt.setPreferredSize(fieldSize);
        nameTxt.setPreferredSize(fieldSize);
        date.setPreferredSize(fieldSize);
        emailTxt.setPreferredSize(fieldSize);
        phoneTxt.setPreferredSize(fieldSize);
        passTxt.setPreferredSize(fieldSize);
        confirmTxt.setPreferredSize(fieldSize);

        dobSpinner.setPreferredSize(fieldSize);

        for (Component comp : form.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
            }
            if (comp instanceof JTextField ) {
                comp.setFont(fieldFont);
            }
        }

        rightMiddle.add(form, BorderLayout.CENTER);

        JPanel bottomBar = new JPanel();
        bottomBar.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.Y_AXIS));
        bottomBar.setOpaque(false);

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Monospaced",Font.PLAIN, 22));
        submit.setFocusPainted(false);
        submit.setOpaque(false);
        submit.setBorderPainted(false);
        submit.addActionListener( _-> {

            String accountNo = accountTxt.getText().trim();
            String crn = crnTxt.getText().trim();
            String name = nameTxt.getText().trim();
            java.util.Date utilDate = (java.util.Date) dobSpinner.getValue();
            java.sql.Date sqlDob = new java.sql.Date(utilDate.getTime());
            String email = emailTxt.getText().trim();
            String mobile = phoneTxt.getText().trim();
            String rawPassword = new String(passTxt.getPassword());
            String confirmPassword = new String(confirmTxt.getPassword());

            if (accountNo.isEmpty() || crn.isEmpty() || name.isEmpty() ||
                   email.isEmpty() || mobile.isEmpty() ||
                    rawPassword.isEmpty() || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill all fields");
                return;
            }

            if (!emailTxt.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Email");
                return;
            }
            if (!phoneTxt.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null,"Enter valid 10 digit Mobile Number");
                return;
            }

            if(!rawPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null,"Password do not match!");
                return;
            }

            try {
                Connection conn = DBConnection.getConnection();
                conn.setAutoCommit(false);

                String sql = "SELECT u.user_id FROM users u " +
                        "JOIN accounts a ON u.user_id = a.user_id " +
                        "WHERE u.full_name=? AND u.email=? AND u.mobile=? AND u.dob=? " +
                        "AND a.account_number=? AND a.CRN=?";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, mobile);
                stmt.setDate(4, sqlDob);
                stmt.setString(5, accountNo);
                stmt.setString(6, crn);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    int userId = rs.getInt("user_id");

                    String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

                    String updateSQL = "UPDATE users SET password_hash=? WHERE user_id=?";
                    PreparedStatement psUpdate = conn.prepareStatement(updateSQL);
                    psUpdate.setString(1, hashedPassword);
                    psUpdate.setInt(2, userId);
                    psUpdate.executeUpdate();

                    conn.commit();

                    JOptionPane.showMessageDialog(null, "Signup Successful! Now Login");
                    nameTxt.setText("");
                    accountTxt.setText("");
                    crnTxt.setText("");
                    emailTxt.setText("");
                    phoneTxt.setText("");
                    passTxt.setText("");
                    confirmTxt.setText("");
                    accountTxt.requestFocus();
                    cardLayout.show(cardPanle, "CUSTOMER LOGIN");


                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Details! Please check your information.");
                }


            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
                throw new RuntimeException(e);
            }

        });
        bottomBar.add(submit);

        JLabel bot = new JLabel("By providing your contact details, you agree to our Terms & Conditions and Privacy Policy.");
        bot.setFont(new Font("Segoe UI",Font.PLAIN, 12));
        bottomBar.add(bot);

        rightMiddle.add(bottomBar, BorderLayout.SOUTH);
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        gbcRight.weightx = 0.80;
        gbcRight.weighty = 1;
        gbcRight.fill = GridBagConstraints.BOTH;

        add(rightMiddle, gbcRight);

    }
}
