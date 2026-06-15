package com.bank.ui.dashboard;
import com.bank.config.DBConnection;
import com.bank.service.UserService.CustomerService;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDashboard extends JPanel {
    private  JPanel rightmid_2;
    private  CardLayout rightCardLayout_2;
    private  long cus_number ;
    private  double cus_balance;
    private  int cus_id;
    private DefaultTableModel model;
    private CustomerService service;
    private  JTextField actext;
    private  JTextField crnTxt;
    private JLabel welcome_0;
    private  JTextField userField;

    public CustomerDashboard(CardLayout cardLayout,JPanel cardPanel, long accountNumber,String fullName , int user_id) throws Exception {

        this.model = model;
        service = new CustomerService();
        String[] column = {
                "Date ",
                "Sender",
                "Receiver",
                "Amount",
                "Remark"
        };

        this.cus_number = accountNumber;
        this.cus_id = user_id;
        setLayout(new BorderLayout());

        JPanel customerPanel = new JPanel(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        //-------------------------------------------------------------TOP PANEL--------------------------------------------
        JPanel topJPanel = new JPanel(new BorderLayout());
        topJPanel.setBackground(new Color(13,27,42));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left.setOpaque(false);
        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo = new JLabel(logIcon2);
        logo.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        left.add(logo,BorderLayout.WEST);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,28));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        left.add(label);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setOpaque(false);

        welcome_0 = new JLabel( "Welcome " + fullName);
        welcome_0.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        welcome_0.setForeground(Color.WHITE);
        welcome_0.setFont(new Font("Monospaced",Font.PLAIN,26));
        welcome_0.setVerticalTextPosition(SwingConstants.EAST);
        right.add(welcome_0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.1;

        topJPanel.add(left,BorderLayout.WEST);
        topJPanel.add(right,BorderLayout.EAST);
        customerPanel.add(topJPanel, BorderLayout.NORTH);

        //---------------------------------------------------------MIDDLE LEFT----------------------------------------------
        JPanel leftMiddle = new JPanel();
        leftMiddle.setBackground(new Color(26, 52, 117));
        leftMiddle.setPreferredSize(new Dimension(250, 0));
        leftMiddle.setLayout(new BoxLayout(leftMiddle, BoxLayout.Y_AXIS));
        leftMiddle.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));

        JButton dashboard = new JButton("Dashboard");
        dashboard.setFont(new Font("Monospaced",Font.PLAIN,18));
        dashboard.setBackground(new Color(21, 143, 131));
        dashboard.setForeground(Color.WHITE);
        dashboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboard.setMaximumSize(new Dimension(240,40));
        dashboard.addActionListener(e-> rightCardLayout_2.show(rightmid_2,"WELCOME"));
        dashboard.setFocusable(false);

        JButton checkBalance = new JButton("Check Balance");
        checkBalance.setFont(new Font("Monospaced",Font.PLAIN,18));
        checkBalance.setBackground(new Color(21, 143, 131));
        checkBalance.setForeground(Color.WHITE);
        checkBalance.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkBalance.setMaximumSize(new Dimension(240,40));
        checkBalance.setFocusable(false);
        checkBalance.addActionListener(_ ->rightCardLayout_2.show(rightmid_2, "CHECK BALANCE"));

        JButton sendMoney = new JButton("Send Money");
        sendMoney.setFont(new Font("Monospaced",Font.PLAIN,18));
        sendMoney.setBackground(new Color(21, 143, 131));
        sendMoney.setForeground(Color.WHITE);
        sendMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMoney.setMaximumSize(new Dimension(240,40));
        sendMoney.addActionListener( e-> rightCardLayout_2.show(rightmid_2, "SEND MONEY"));
        sendMoney.setFocusable(false);

        JButton transaction = new JButton("Transaction");
        transaction.setFont(new Font("Monospaced",Font.PLAIN,18));
        transaction.setBackground(new Color(21, 143, 131));
        transaction.setForeground(Color.WHITE);
        transaction.setAlignmentX(Component.CENTER_ALIGNMENT);
        transaction.setMaximumSize(new Dimension(240,40));
        transaction.addActionListener(e-> rightCardLayout_2.show(rightmid_2,"TRANSACTION"));
        transaction.setFocusable(false);

        JButton update = new JButton("Update Password");
        update.setFont(new Font("Monospaced",Font.PLAIN,18));
        update.setBackground(new Color(21, 143, 131));
        update.setForeground(Color.WHITE);
        update.setAlignmentX(Component.CENTER_ALIGNMENT);
        update.setMaximumSize(new Dimension(240,40));
        update.addActionListener(e-> rightCardLayout_2.show(rightmid_2,"UPDATE"));
        update.setFocusable(false);

        JButton logJButton = new JButton("Logout");
        logJButton.setFont(new Font("Monospaced",Font.PLAIN,18));
        logJButton.setBackground(new Color(21, 143, 131));
        logJButton.setForeground(Color.WHITE);
        logJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logJButton.setMaximumSize(new Dimension(240,40));
        logJButton.setFocusable(false);

        logJButton.addActionListener(_ -> {
            JOptionPane.showMessageDialog(null, "Log out Successfully !!");
            cardLayout.show(cardPanel, "LOGIN");
        });

        leftMiddle.add(dashboard);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(checkBalance);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(sendMoney);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(transaction);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(update);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(logJButton);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.85;
        customerPanel.add(leftMiddle,BorderLayout.WEST);

        //------------------------------------------------------MIDDLE RIGHT------------------------------------------------
        rightmid_2 = new JPanel();
        rightCardLayout_2 = new CardLayout();
        rightmid_2.setLayout(rightCardLayout_2);
        //-----------------------------------------------WELCOME PANEL---------------------------------------------------------
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(12, 147, 207));

        GridBagConstraints gbc_m = new GridBagConstraints();
        gbc_m.insets = new Insets(10,10,10,10);
        gbc_m.gridx = 0;

        gbc_m.gridy = 0;
        JLabel wel = new JLabel("DASHBOARD");
        wel.setForeground(Color.WHITE);
        wel.setFont(new Font("Monospaced",Font.PLAIN,30));
        mainPanel.add(wel,gbc_m);

        gbc_m.gridy = 1;
        JPanel dasboardright = new JPanel(new GridBagLayout());
        dasboardright.setOpaque(false);
        dasboardright.setPreferredSize(new Dimension(500,300));
        TitledBorder border_das = BorderFactory.createTitledBorder("ACCOUNT DETAILS");
        border_das.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        border_das.setTitleColor(Color.WHITE);
        dasboardright.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbc_13 = new GridBagConstraints();
        gbc_13.insets = new Insets(10, 10, 10, 10);
        gbc_13.anchor = GridBagConstraints.WEST;
        gbc_13.fill = GridBagConstraints.HORIZONTAL;
        gbc_13.weightx = 1;

        gbc_13.gridy = 0;
        gbc_13.gridx = 0;
        JLabel acnum = new JLabel("A/C Number :");
        acnum.setFont(new Font("Monospaced",Font.PLAIN,20));
        acnum.setForeground(Color.WHITE);
        dasboardright.add(acnum, gbc_13);

        gbc_13.gridy = 0;
        gbc_13.gridx = 1;
        actext = new JTextField(15);
        actext.setFont(new Font("Monospaced",Font.PLAIN,20));
        actext.setText(String.valueOf(accountNumber));
        actext.setEditable(false);
        dasboardright.add(actext,gbc_13);

        gbc_13.gridy = 0;
        gbc_13.gridx = 0;
        JLabel crn = new JLabel("CRN ");
        crn.setFont(new Font("Monospaced",Font.PLAIN,20));
        crn.setForeground(Color.WHITE);
        dasboardright.add(crn, gbc_13);

        gbc_13.gridy = 0;
        gbc_13.gridx = 1;
        crnTxt = new JTextField(15);
        crnTxt.setFont(new Font("Monospaced",Font.PLAIN,20));
        crnTxt.setText(String.valueOf(accountNumber));
        crnTxt.setEditable(false);
        dasboardright.add(crnTxt,gbc_13);

        gbc_13.gridy = 1;
        gbc_13.gridx = 0;
        JLabel userName = new JLabel("A/C Holder Name :");
        userName.setFont(new Font("Monospaced",Font.PLAIN,20));
        userName.setForeground(Color.WHITE);
        dasboardright.add(userName, gbc_13);

        gbc_13.gridy = 1;
        gbc_13.gridx = 1;
        userField = new JTextField(15);
        userField.setFont(new Font("Monospaced",Font.PLAIN,20));
        userField.setText(fullName);
        userField.setEditable(false);
        dasboardright.add(userField,gbc_13);

        mainPanel.add(dasboardright,gbc_m);

        //------------------------------------------------CHECK BALANCE PANEL-------------------------------------------
        JPanel check = new JPanel(new GridBagLayout());
        check.setBackground(new Color(129, 28, 213));

        GridBagConstraints gbc_7= new GridBagConstraints();
        gbc_7.insets = new Insets(10,10,10,10);
        gbc_7.weightx = 0;

        gbc_7.gridy = 0;
        JLabel cl1 = new JLabel("CHECK BALANCE ");
        cl1.setForeground(Color.WHITE);
        cl1.setFont(new Font("Monospaced",Font.PLAIN,30));
        check.add(cl1,gbc_7);

        gbc_7.gridy = 1;
        JPanel balancePanel = new JPanel(new GridBagLayout());
        balancePanel.setPreferredSize(new Dimension(500,300));
        balancePanel.setOpaque(false);

        TitledBorder border_2 = BorderFactory.createTitledBorder("Balance");
        border_2.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        border_2.setTitleColor(Color.WHITE);
        balancePanel.setBorder(BorderFactory.createCompoundBorder(
                border_2,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.insets = new Insets(10,10,10,10);
        gbc_2.anchor = GridBagConstraints.WEST;
        gbc_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_2.weightx = 1;

        gbc_2.gridx = 0;
        gbc_2.gridy = 0;
        JLabel passLabel = new JLabel("Enter Password :");
        passLabel.setFont(new Font("Monospaced",Font.PLAIN,20));
        passLabel.setForeground(Color.WHITE);
        balancePanel.add(passLabel, gbc_2);

        gbc_2.gridy = 0;
        gbc_2.gridx = 1;
        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Monospaced",Font.PLAIN,20));
        balancePanel.add(passField, gbc_2);

        gbc_2.gridx = 0;
        gbc_2.gridy = 1;
        gbc_2.gridwidth = 2;
        JButton checkButton = new JButton("Check Balance");
        checkButton.setFont(new Font("Monospaced",Font.PLAIN,20));
        balancePanel.add(checkButton, gbc_2);

        gbc_2.gridy = 2;
        gbc_2.gridx = 0;
        JLabel balanceLabel = new JLabel("Balance :");
        balanceLabel.setFont(new Font("Monospaced",Font.PLAIN,20));
        balanceLabel.setForeground(Color.WHITE);
        balancePanel.add(balanceLabel, gbc_2);

        gbc_2.gridy = 2;
        gbc_2.gridx = 1;
        JTextField balance = new JTextField(15);
        balance.setFont(new Font("Monospaced",Font.PLAIN,20));
        balance.setEditable(false);
        balancePanel.add(balance,gbc_2);

        checkButton.addActionListener(_-> {
            String pass = new String(passField.getPassword()).trim();
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Password ");
                passField.requestFocus();
                return;
            }
            try(Connection conn = DBConnection.getConnection()) {

                String checkSql = "SELECT u.password_hash, a.balance " +
                        "FROM accounts a JOIN users u ON a.user_id  = u.user_id WHERE a.account_number = ?";

                PreparedStatement checkstate = conn.prepareStatement(checkSql);
                checkstate.setLong(1, cus_number);

                ResultSet rs = checkstate.executeQuery();
                if (rs.next()) {
                    String DBhash = rs.getString("password_hash");
                    cus_balance = rs.getDouble("balance");

                    if (BCrypt.checkpw(pass, DBhash)) {
                        double userBalance = rs.getDouble("balance");
                        balance.setText(String.valueOf(userBalance));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Account Not Found");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            passField.setText("");
            passField.requestFocus();
        });
        check.add(balancePanel,gbc_7);

        //-------------------------------------------------------SEND MOANEY PANEL------------------------------------------
        JPanel sendMoneypanel = new JPanel(new GridBagLayout());
        sendMoneypanel.setBackground(new Color(193, 71, 213));

        GridBagConstraints gbc_4= new GridBagConstraints();
        gbc_4.insets = new Insets(10,10,10,10);

        gbc_4.gridy = 0;
        JLabel ul1 = new JLabel("SEND MONEY");
        ul1.setForeground(Color.WHITE);
        ul1.setFont(new Font("Monospaced",Font.PLAIN,30));
        sendMoneypanel.add(ul1,gbc_4);

        gbc_4.gridy = 1;
        gbc_4.fill = GridBagConstraints.HORIZONTAL;

        JPanel fundPanel = new JPanel(new GridBagLayout());
        fundPanel.setPreferredSize(new Dimension(600,400));
        fundPanel.setOpaque(false);
        TitledBorder border = BorderFactory.createTitledBorder("Send Money");
        border.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        border.setTitleColor(Color.WHITE);
        fundPanel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbc_41 = new GridBagConstraints();
        gbc_41.insets = new Insets(10,10,10,10);
        gbc_41.anchor = GridBagConstraints.WEST;
        gbc_41.fill = GridBagConstraints.HORIZONTAL;
        gbc_41.weightx = 1;

        gbc_41.gridy = 0;
        gbc_41.gridx = 0;
        JLabel account = new JLabel("Enter A/C number :");
        account.setForeground(Color.WHITE);
        account.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(account,gbc_41);

        gbc_41.gridy = 0;
        gbc_41.gridx = 1;
        JTextField accountF = new JTextField(15);
        accountF.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(accountF,gbc_41);

        gbc_41.gridy = 1;
        gbc_41.gridx = 0;
        JLabel amount = new JLabel("Amount :");
        amount.setForeground(Color.WHITE);
        amount.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(amount,gbc_41);

        gbc_41.gridy = 1;
        gbc_41.gridx = 1;
        JTextField ammountF = new JTextField(15);
        ammountF.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(ammountF,gbc_41);

        gbc_41.gridy = 2;
        gbc_41.gridx = 0;
        JLabel remark = new JLabel("Remark :");
        remark.setForeground(Color.WHITE);
        remark.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(remark,gbc_41);

        gbc_41.gridy = 2;
        gbc_41.gridx = 1;
        JTextField remarkF = new JTextField(15);
        remarkF.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(remarkF,gbc_41);

        gbc_41.gridy = 3;
        gbc_41.gridx = 0;
        JLabel pass = new JLabel("Password :");
        pass.setForeground(Color.WHITE);
        pass.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(pass,gbc_41);

        gbc_41.gridy = 3;
        gbc_41.gridx = 1;
        JPasswordField passF = new JPasswordField(15);
        passF.setFont(new Font("Monospaced",Font.PLAIN,20));
        fundPanel.add(passF,gbc_41);

        gbc_41.gridx = 0;
        gbc_41.gridy = 4;
        gbc_41.gridwidth = 2;
        JButton sendButton = new JButton("Send");
        sendButton.setFocusable(false);
        sendButton.setFont(new Font("Monospaced",Font.PLAIN,20));
        sendButton.setPreferredSize(new Dimension(200,35));
        fundPanel.add(sendButton,gbc_41);

        sendButton.addActionListener(_-> {
            String sendAcc  = accountF.getText().trim();
            String transferAmm = ammountF.getText().trim();
            String rema = remarkF.getText().trim();
            String sendPass = new String(passF.getPassword()).trim();

            if (sendAcc.isEmpty() || transferAmm.isEmpty() || sendPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
                passField.requestFocus();
                return;
            }

            long receiverAcc;
            double sendamount;
            try {
                receiverAcc  = Long.parseLong(sendAcc);
                sendamount = Double.parseDouble(transferAmm);
                if (sendamount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Amount");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null , "Account Number and Amount invalid.");
                e.printStackTrace();
                return;
            }
            Connection conn;
            try {
                conn  = DBConnection.getConnection();
                conn.setAutoCommit(false);
                String checkSql = "SELECT u.password_hash, a.balance  FROM accounts a " +
                        "JOIN users u ON a.user_id  = u.user_id WHERE a.account_number = ?";
                PreparedStatement checkstate = conn.prepareStatement(checkSql);
                checkstate.setLong(1, cus_number);

                ResultSet rs = checkstate.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null,"Sender Account Number not Found.");
                    return;
                }
                String DBhash = rs.getString("password_hash");
                cus_balance = rs.getDouble("balance");

                if (!BCrypt.checkpw(sendPass, DBhash)) {
                    JOptionPane.showMessageDialog(null,"Invalid Password.");
                    return;
                }
                if ( cus_balance < sendamount  ) {
                    JOptionPane.showMessageDialog(null,"Insufficient Balance.");
                    return;
                }
                String reciveAcc = "SELECT * FROM accounts WHERE account_number = ?";
                PreparedStatement sendState = conn.prepareStatement(reciveAcc);
                sendState.setLong(1, receiverAcc);

                ResultSet resultSet = sendState.executeQuery();

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Destination account Not found.");
                    return;
                }
                double reciverBalance = resultSet.getDouble("balance");

                String updateSql = "UPDATE  accounts SET balance = ? WHERE account_number = ?";
                PreparedStatement  updateState = conn.prepareStatement(updateSql);
                updateState.setDouble(1, cus_balance - sendamount);
                updateState.setLong(2,cus_number);
                updateState.executeUpdate();

                String updateSql2 = "UPDATE  accounts SET balance = ? WHERE account_number = ?";
                PreparedStatement  updateState2 = conn.prepareStatement(updateSql2);
                updateState2.setDouble(1, reciverBalance + sendamount);
                updateState2.setLong(2,receiverAcc);
                updateState2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Money Transferred Successfully.");
                balance.setText(String.valueOf(cus_balance - sendamount));

                String txnSql = "INSERT INTO transactions (sender_acc, reciver_acc, amount, type, remark) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement txnState = conn.prepareStatement(txnSql);

                txnState.setString(1, String.valueOf(cus_number));  // sender
                txnState.setString(2, String.valueOf(receiverAcc));  // receiver
                txnState.setDouble(3, sendamount);
                txnState.setString(4, "TRANSFER");
                txnState.setString(5, rema);

                txnState.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Transaction Failed.");
                ex.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            accountF.setText("");
            ammountF.setText("");
            remarkF.setText("");
            passF.setText("");
            accountF.requestFocus();
        });

        sendMoneypanel.add(fundPanel,gbc_4);
        //-----------------------------------------------------TRANSACTION PANEL--------------------------------------------
            JPanel transactionpanel = new JPanel(new GridBagLayout());
            transactionpanel.setBackground(new Color(195, 79, 24));

            GridBagConstraints gbc_23 = new GridBagConstraints();
            gbc_23.insets = new Insets(10,10,10,10);
            gbc_23.gridx = 0;
            gbc_23.weighty = 1;
            gbc_23.fill = GridBagConstraints.BOTH;

            gbc_23.gridy = 0;
            JLabel trans = new JLabel("TRANSACTION HISTORY");
            trans.setFont(new Font("Monospaced",Font.PLAIN,30));
            trans.setForeground(Color.WHITE);
            trans.setVerticalTextPosition(SwingConstants.NORTH);
            transactionpanel.add(trans,gbc_23);

            gbc_23.gridy = 1;
            JPanel balance_1 = new JPanel();
            balance_1.setPreferredSize(new Dimension(800,400));
            balance_1.setOpaque(false);
            balance_1.setLayout(new BorderLayout());

            TitledBorder bordertrans = BorderFactory.createTitledBorder("Transaction History");
            bordertrans.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
            bordertrans.setTitleColor(Color.WHITE);
            balance_1.setBorder(BorderFactory.createCompoundBorder(
                    bordertrans,
                    BorderFactory.createEmptyBorder(20,20,10,20)));

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"Date", "Sender", "Receiver", "Amount", "Remark"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            try (Connection con = DBConnection.getConnection()) {

                String sql = "SELECT t.date, t.sender_acc, s.full_name AS sender_name, " +
                        "t.reciver_acc,  r.full_name AS reciver_name, t.amount,  t.remark " +
                        "FROM transactions t " +
                        "JOIN accounts sa  ON t.sender_acc = sa.account_number " +
                        "JOIN users s ON sa.user_id = s.user_id " +
                        "JOIN accounts ra ON t.reciver_acc = ra.account_number " +
                        "JOIN users r ON  ra.user_id = r.user_id " +
                        "WHERE sender_acc = ? OR reciver_acc = ? " +
                        "ORDER BY t.date DESC LIMIT 10";

                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, String.valueOf(cus_number));
                stmt.setString(2, String.valueOf(cus_number));

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String senderAcc = rs.getString("sender_acc");
                    String receiverAcc = rs.getString("reciver_acc");

                    String senderName = rs.getString("sender_name");
                    String receiverName = rs.getString("reciver_name");

                    String from, to;

                    if (senderAcc.equals(String.valueOf(cus_number))) {
                        from = "You";
                        to = receiverName;
                    } else {
                        from = senderName;
                        to = "You";
                    }
                    model.addRow(new Object[]{
                            rs.getTimestamp("date"),
                            from,
                            to,
                            rs.getDouble("amount"),
                            rs.getString("remark")
                    });
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            }

            JTable table = new JTable(model);
            table.setFont(new Font("Monospaced",Font.PLAIN,18));
            table.setRowHeight(30);
            table.setFillsViewportHeight(true);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.getTableHeader().setFont(new Font("Monospaced",Font.PLAIN,20));

            JScrollPane scroll_2 = new JScrollPane(table);
            scroll_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            balance_1.add(scroll_2,BorderLayout.CENTER);
            transactionpanel.add(balance_1,gbc_23);
        //-------------------------------------------------------UPDATE PASSWORD--------------------------------------------
        JPanel update_2 = new JPanel(new GridBagLayout());
        update_2.setBackground(new Color(123, 6, 202));

        GridBagConstraints gbc_12= new GridBagConstraints();
        gbc_12.insets = new Insets(10,10,10,10);

        gbc_12.gridy = 0;
        JLabel title = new JLabel("UPDATE PASSWORD");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Monospaced",Font.PLAIN,30));
        update_2.add(title,gbc_12);

        gbc_12.gridy = 1;
        gbc_12.fill = GridBagConstraints.HORIZONTAL;

        JPanel updatepassword = new JPanel(new GridBagLayout());
        updatepassword.setPreferredSize(new Dimension(650,350));
        updatepassword.setOpaque(false);
        TitledBorder titleborder = BorderFactory.createTitledBorder("Update Password");
        titleborder.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        titleborder.setTitleColor(Color.WHITE);
        updatepassword.setBorder(BorderFactory.createCompoundBorder(
                titleborder,
                BorderFactory.createEmptyBorder(20,40,20,40)));

        GridBagConstraints gbc_44 = new GridBagConstraints();
        gbc_44.insets = new Insets(10,10,10,10);
        gbc_44.anchor = GridBagConstraints.WEST;
        gbc_44.fill = GridBagConstraints.HORIZONTAL;
        gbc_44.weightx = 1;


        gbc_44.gridy = 0;
        gbc_44.gridx = 0;
        JLabel oldpass = new JLabel("Enter Old Passowrd :");
        oldpass.setForeground(Color.WHITE);
        oldpass.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(oldpass,gbc_44);

        gbc_44.gridy = 0;
        gbc_44.gridx = 1;
        JPasswordField oldpassF = new JPasswordField(15);
        oldpassF.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(oldpassF,gbc_44);

        gbc_44.gridy = 1;
        gbc_44.gridx = 0;
        JLabel newpass = new JLabel("Enter New Password :");
        newpass.setForeground(Color.WHITE);
        newpass.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(newpass, gbc_44);

        gbc_44.gridy = 1;
        gbc_44.gridx = 1;
        JPasswordField newpassF = new JPasswordField(15);
        newpassF.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(newpassF, gbc_44);

        gbc_44.gridy = 2;
        gbc_44.gridx = 0;
        JLabel confirm = new JLabel("Confirm Password :");
        confirm.setForeground(Color.WHITE);
        confirm.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(confirm, gbc_44);

        gbc_44.gridy = 2;
        gbc_44.gridx = 1;
        JPasswordField confrimF = new JPasswordField(15);
        confrimF.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatepassword.add(confrimF,gbc_44);

        gbc_44.gridy = 3;
        gbc_44.gridx = 0;
        gbc_44.gridwidth = 2;
        JButton updatePass = new JButton("Update");
        updatePass.setFocusable(false);
        updatePass.setFont(new Font("Monospaced",Font.PLAIN,20));
        updatePass.setPreferredSize(new Dimension(200,35));
        updatepassword.add(updatePass,gbc_44);

        updatePass.addActionListener( _-> {
            String oldPass = new String(oldpassF.getPassword()).trim();
            String newPass = new String(newpassF.getPassword()).trim();
            String confirmPass = new String(confrimF.getPassword()).trim();

            if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
                return;
            }
            try (Connection conn = DBConnection.getConnection()){
                String checkPass = "SELECT password_hash FROM users WHERE user_id = ?";
                PreparedStatement passSate = conn.prepareStatement(checkPass);
                passSate.setInt(1,cus_id);

                ResultSet passS = passSate.executeQuery();
                if (passS.next()) {
                    String datahash = passS.getString("password_hash");

                    if (!BCrypt.checkpw(oldPass, datahash)) {
                        JOptionPane.showMessageDialog(null, "Inccorect Old Password.");
                        return;
                    }
                    if(!newPass.equals(confirmPass)){
                        JOptionPane.showMessageDialog(null,"Confirm password do not match");
                        return;
                    }

                    String newHash = BCrypt.hashpw(newPass, BCrypt.gensalt());

                    String sqlPass = "UPDATE users SET password_hash = ? WHERE user_id = ?";
                    PreparedStatement statePass = conn.prepareStatement(sqlPass);
                    statePass.setString(1,newHash);
                    statePass.setLong(2,cus_id);

                    statePass.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password update Successfully.");
                }
            } catch (SQLException e ) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "invalid password");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            oldpassF.setText("");
            newpassF.setText("");
            confrimF.setText("");
            oldpassF.requestFocus();
        });

        update_2.add(updatepassword,gbc_12);
        //------------------------------------------------------------------------------------------------------------------
        rightmid_2.add(mainPanel,"WELCOME");
        rightmid_2.add(check,"CHECK BALANCE");
        rightmid_2.add(sendMoneypanel, "SEND MONEY");
        rightmid_2.add(transactionpanel,"TRANSACTION");
        rightmid_2.add(update_2,"UPDATE");

        gbc.gridx = 1;
        gbc.weightx = 0.9;
        customerPanel.add(rightmid_2, BorderLayout.CENTER);
        //-----------------------------------------------------------BOTTOM-PANEL-------------------------------------------
        JPanel bottoJPanel = new JPanel();
        bottoJPanel.setBackground(new Color(13,27,42));
        JLabel footer = new JLabel("© 2026 Secure Bank. All Right Reserved.");
        footer.setForeground(Color.white);
        bottoJPanel.add(footer);
        bottoJPanel.setFont(new Font("Monospaced UI",Font.PLAIN,18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        customerPanel.add(bottoJPanel,BorderLayout.SOUTH);
        add(customerPanel, BorderLayout.CENTER);
    }
}
