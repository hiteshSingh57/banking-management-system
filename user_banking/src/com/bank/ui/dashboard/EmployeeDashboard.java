package com.bank.ui.dashboard;

import com.bank.config.DBConnection;
import com.bank.model.UserModel.Customer;
import com.bank.service.UserService.CustomerService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmployeeDashboard extends JPanel {
    private DefaultTableModel model;
    private CustomerService service;
    private  JPanel rightmid;
    private  CardLayout rightCardLayout;

    public EmployeeDashboard (CardLayout cardLayout, JPanel cardPanel, int emmployee_id, String employee_name) {
        this.model = model;
        service = new CustomerService();
        String[] column = {
                "Created At",
                "A/C No.",
                "Name",
                "Mobile",
                "Status",
                "Balance"
        };

        setLayout(new BorderLayout());

        JPanel employeePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        //-----------------------------------------------------------TOP PANEL----------------------------------------------
        JPanel topJPanel = new JPanel(new BorderLayout());
        topJPanel.setBackground(new Color(13,27,42));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left.setOpaque(false);
        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo = new JLabel(logIcon2);
        logo.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));
        left.add(logo,BorderLayout.WEST);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,28));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        left.add(label);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setOpaque(false);

        JLabel emm_welcome = new JLabel();
        emm_welcome.setForeground(Color.WHITE);
        emm_welcome.setText("Welcome " + employee_name);
        emm_welcome.setBorder(BorderFactory.createEmptyBorder(5,20,5,10));
        emm_welcome.setFont(new Font("Monospaced",Font.PLAIN,26));
        emm_welcome.setVerticalTextPosition(SwingConstants.EAST);
        right.add(emm_welcome);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.05;

        topJPanel.add(left,BorderLayout.WEST);
        topJPanel.add(right,BorderLayout.EAST);
        employeePanel.add(topJPanel, gbc);

        //--------------------------------------------------------------MIDDLE LEFT------------------------------------------
        JPanel leftMiddle = new JPanel();
        leftMiddle.setBackground(new Color(26, 52, 117));
        leftMiddle.setPreferredSize(new Dimension(250, 0));
        leftMiddle.setLayout(new BoxLayout(leftMiddle, BoxLayout.Y_AXIS));
        leftMiddle.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));

        JButton dash = new JButton("Dashboard");
        dash.setFont(new Font("Monospaced",Font.PLAIN,18));
        dash.setBackground(new Color(21, 143, 131));
        dash.setForeground(Color.WHITE);
        dash.setAlignmentX(Component.CENTER_ALIGNMENT);
        dash.setMaximumSize(new Dimension(240,40));
        dash.setFocusable(false);
        dash.addActionListener(_ -> rightCardLayout.show(rightmid, "DASHBOARD"));

        JButton createAccount = new JButton("Create New Account");
        createAccount.setFont(new Font("Monospaced",Font.PLAIN,18));
        createAccount.setBackground(new Color(21, 143, 131));
        createAccount.setForeground(Color.WHITE);
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccount.setMaximumSize(new Dimension(240,40));
        createAccount.setFocusable(false);
        createAccount.addActionListener(_ -> cardLayout.show(cardPanel, "OPEN ACCOUNT"));

        JButton update = new JButton("Update Customer Account");
        update.setFont(new Font("Monospaced",Font.PLAIN,18));
        update.setBackground(new Color(21, 143, 131));
        update.setForeground(Color.WHITE);
        update.setAlignmentX(Component.CENTER_ALIGNMENT);
        update.setMaximumSize(new Dimension(240,40));
        update.addActionListener( e-> rightCardLayout.show(rightmid,"UPDATE"));
        update.setFocusable(false);

        //--------------------------------------------------------------------------------------------------------------

        JButton view = new JButton("View All Customers");
        view.setFont(new Font("Monospaced",Font.PLAIN,18));
        view.setBackground(new Color(21, 143, 131));
        view.setForeground(Color.WHITE);
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setMaximumSize(new Dimension(240,40));
        view.addActionListener(e->{
            rightCardLayout.show(rightmid,"VIEW");
            rightmid.repaint();
            rightmid.revalidate();
        });
        view.setFocusable(false);

    //------------------------------------------------------------------------------------------------------------

        JButton logJButton = new JButton("Logout");
        logJButton.setFont(new Font("Monospaced",Font.PLAIN,18));
        logJButton.setBackground(new Color(21, 143, 131));
        logJButton.setForeground(Color.WHITE);
        logJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logJButton.setMaximumSize(new Dimension(240,40));
        logJButton.setFocusable(false);

        logJButton.addActionListener(_ -> {
                    JOptionPane.showMessageDialog(null, "Log out Successfully !!");
                    cardLayout.show(cardPanel, "EMPLOYEE LOGIN");
                }
        );

        leftMiddle.add(dash);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(createAccount);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(update);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(view);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(logJButton);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.85;
        employeePanel.add(leftMiddle,gbc);

        //------------------------------------------------------------------------MIDDLE RIGHT----------------------------------------
        rightmid = new JPanel();
        rightmid.setBackground(new Color(29, 193, 174));
        rightCardLayout = new CardLayout();
        rightmid.setLayout(rightCardLayout);
        //--------------------------------------------------------------------------WELCOME PANEL-------------------------------------------
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(12, 147, 207));

        GridBagConstraints gbc_m = new GridBagConstraints();
        gbc_m.insets = new Insets(10,10,10,10);
        gbc_m.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;

        gbc_m.gridy = 0;
        JLabel wel = new JLabel("DASHBOARD");
        wel.setForeground(Color.WHITE);
        wel.setFont(new Font("Monospaced",Font.PLAIN,30));
        mainPanel.add(wel,gbc_m);

        gbc_m.gridy = 1;
        JPanel dasboardright = new JPanel(new GridBagLayout());
        dasboardright.setOpaque(false);
        dasboardright.setPreferredSize(new Dimension(500,300));
        TitledBorder border_das = BorderFactory.createTitledBorder("Employee Detail");
        border_das.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        border_das.setTitleColor(Color.WHITE);
        dasboardright.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(10,20,10,20)));

        GridBagConstraints gbc_13 = new GridBagConstraints();
        gbc_13.insets = new Insets(5,10,5,10);
        gbc_13.anchor = GridBagConstraints.WEST;
        gbc_13.fill = GridBagConstraints.HORIZONTAL;
        gbc_13.weightx = 1;

        gbc_13.gridx = 0;
        gbc_13.gridy = 0;
        JLabel emm_accnum = new JLabel("Employee ID :");
        emm_accnum.setFont(new Font("Monospaced",Font.PLAIN,20));
        emm_accnum.setForeground(Color.WHITE);
        dasboardright.add(emm_accnum, gbc_13);

        gbc_13.gridx = 1;
        gbc_13.gridy = 0;
        JTextField emm_acctext = new JTextField(15);
        emm_acctext.setFont(new Font("Monospaced",Font.PLAIN,20));
        emm_acctext.setText(String.valueOf(emmployee_id));
        emm_acctext.setEditable(false);
        dasboardright.add(emm_acctext,gbc_13);

        gbc_13.gridx = 0;
        gbc_13.gridy = 1;
        JLabel userName = new JLabel("Employee Name :");
        userName.setFont(new Font("Monospaced",Font.PLAIN,20));
        userName.setForeground(Color.WHITE);
        dasboardright.add(userName, gbc_13);

        gbc_13.gridx = 1;
        gbc_13.gridy = 1;
        JTextField emm_userField = new JTextField(15);
        emm_userField.setFont(new Font("Monospaced",Font.PLAIN,20));
        emm_userField.setText(employee_name);
        emm_userField.setEditable(false);
        dasboardright.add(emm_userField,gbc_13);

        mainPanel.add(dasboardright,gbc_m);

        //---------------------------------------------------------------------------UPDATE PANEL-------------------------------------------
        JPanel updatepanel = new JPanel(new GridBagLayout());
        updatepanel.setBackground(new Color(162, 17, 204));

        GridBagConstraints gbc_30= new GridBagConstraints();
        gbc_30.insets = new Insets(10,10,10,10);

        gbc_30.gridy = 0;
        JLabel titleUpdate = new JLabel("UPDATE ACCOUNT DETAILS");
        titleUpdate.setForeground(Color.WHITE);
        titleUpdate.setFont(new Font("Monospaced",Font.PLAIN,26));
        updatepanel.add(titleUpdate,gbc_30);

        gbc_30.gridy = 1;
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        center.setOpaque(false);
        JLabel accSearch = new JLabel("Search Account Number :");
        accSearch.setForeground(Color.WHITE);
        accSearch.setFont(new Font("Monospaced",Font.PLAIN,18));
        center.add(accSearch);

        JTextField searchAcc = new JTextField(15);
        searchAcc.setFont(new Font("Monospaced",Font.PLAIN,18));
        center.add(searchAcc);

        JButton search_1 = new JButton("Search");
        search_1.setFont(new Font("Monospaced",Font.PLAIN,18));
        center.add(search_1);

        updatepanel.add(center, gbc_30);

        gbc_30.gridy = 2;
        gbc_30.fill = GridBagConstraints.HORIZONTAL;
        JPanel updateResult = new JPanel(new GridBagLayout());
        updateResult.setPreferredSize(new Dimension(600,443));
        updateResult.setOpaque(false);
        TitledBorder borderUpdate = BorderFactory.createTitledBorder("Account Details");
        borderUpdate.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        borderUpdate.setTitleColor(Color.WHITE);
        updateResult.setBorder(BorderFactory.createCompoundBorder(
                borderUpdate,
                BorderFactory.createEmptyBorder(20,20,20,20)));

        GridBagConstraints gbc_31 = new GridBagConstraints();
        gbc_31.insets = new Insets(8, 15, 8, 15);
        gbc_31.fill = GridBagConstraints.HORIZONTAL;
        gbc_31.weightx = 1;


        gbc_31.gridy = 0;
        gbc_31.gridx = 0;
        JLabel nametxt = new JLabel("Name :");
        nametxt.setForeground(Color.WHITE);
        nametxt.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(nametxt,gbc_31);

        gbc_31.gridy = 0;
        gbc_31.gridx = 1;
        JTextField nameField = new JTextField(10);
        nameField.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(nameField,gbc_31);

        gbc_31.gridy = 1;
        gbc_31.gridx = 0;
        JLabel accounttxt = new JLabel("A/C Number :");
        accounttxt.setForeground(Color.WHITE);
        accounttxt.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(accounttxt,gbc_31);

        gbc_31.gridy = 1;
        gbc_31.gridx = 1;
        JTextField accF = new JTextField(10);
        accF.setFont(new Font("Monospaced",Font.PLAIN,18));
        accF.setEditable(false);
        updateResult.add(accF,gbc_31);

        gbc_31.gridy = 2;
        gbc_31.gridx = 0;
        JLabel phonetxt = new JLabel("Phone :");
        phonetxt.setForeground(Color.WHITE);
        phonetxt.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(phonetxt,gbc_31);

        gbc_31.gridy = 2;
        gbc_31.gridx = 1;
        JTextField phoneF = new JTextField(10);
        phoneF.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(phoneF,gbc_31);

        gbc_31.gridy = 3;
        gbc_31.gridx = 0;
        JLabel emailtxt = new JLabel("Email ID :");
        emailtxt.setForeground(Color.WHITE);
        emailtxt.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(emailtxt,gbc_31);

        gbc_31.gridy = 3;
        gbc_31.gridx = 1;
        JTextField emailF = new JTextField(10);
        emailF.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(emailF,gbc_31);

        gbc_31.gridy = 4;
        gbc_31.gridx = 0;
        JLabel address = new JLabel("Address :");
        address.setForeground(Color.WHITE);
        address.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(address,gbc_31);

        gbc_31.gridy = 4;
        gbc_31.gridx = 1;
        JTextField addressF = new JTextField(10);
        addressF.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(addressF,gbc_31);

        gbc_31.gridy = 5;
        gbc_31.gridx = 0;
        JLabel state = new JLabel("Select State :");
        state.setForeground(Color.WHITE);
        state.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(state,gbc_31);

        gbc_31.gridy = 5;
        gbc_31.gridx = 1;
        String[] states = {"Haryana","Uttar Pradesh", "Uttarakhand"};
        JComboBox<String> stateBox = new JComboBox<>(states);
        updateResult.add(stateBox,gbc_31);

        gbc_31.gridy = 6;
        gbc_31.gridx = 0;
        JLabel districtLabel = new JLabel("Select District :");
        districtLabel.setForeground(Color.WHITE);
        districtLabel.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(districtLabel,gbc_31);

        gbc_31.gridy = 6;
        gbc_31.gridx= 1;
        JComboBox<String> districtBox = new JComboBox<>();
        updateResult.add(districtBox,gbc_31);

        //Data store in district
        HashMap<String,String[]> map = new HashMap<>();
        map.put("Haryana",new String[]{"Gurgaon","Faridabad","Palwal","Sonipat" });
        map.put("Uttar Pradesh",new String[]{"Noida","Greater Noida", "Meerut", "Jewar"});
        map.put("UttaraKhand",new String[]{"Dehradun","Haridwar", "Rishikesh","Garhwal"});

        //select state event
        stateBox.addActionListener(_ -> {
            String selectedState = (String) stateBox.getSelectedItem();
            districtBox.removeAllItems();
            if (map.containsKey(selectedState)) {
                for (String district : map.get(selectedState)) {
                    districtBox.addItem(district);
                }
            }
        });

        gbc_31.gridy = 7;
        gbc_31.gridx = 0;
        JLabel pinLabel = new JLabel("Enter Pin Code :");
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(new Font("Monospaced",Font.PLAIN,18));
        updateResult.add(pinLabel,gbc_31);

        gbc_31.gridy = 7;
        gbc_31.gridx= 1;
        JTextField pin = new JTextField(10);
        updateResult.add(pin,gbc_31);

        gbc_31.gridy = 8;
        gbc_31.gridx = 0;
        gbc_31.gridwidth = 2;
        JButton updateButton = new JButton("Update Details");
        updateButton.setFont(new Font("Monospaced",Font.PLAIN,16));
        updateButton.setFocusable(false);
        updateResult.add(updateButton,gbc_31);

        search_1.addActionListener( _-> {
            String acc = searchAcc.getText().trim();
            try(Connection conn = DBConnection.getConnection()) {
                String sql = "SELECT a.account_number, u.user_id, u.full_name, u.mobile, u.email, " +
                        "ad.address, ad.state, ad.district, ad.pin_code " +
                        "FROM accounts a " +
                        "JOIN users u ON a.user_id = u.user_id " +
                        "JOIN addresses ad ON u.user_id = ad.user_id " +
                        "WHERE a.account_number = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,acc);

                ResultSet rs = statement.executeQuery();

                if (rs.next()){
                    int userId = rs.getInt("user_id");
                    nameField.setText(rs.getString("full_name"));
                    accF.setText(rs.getString("account_number"));
                    emailF.setText(rs.getString("email"));
                    phoneF.setText(rs.getString("mobile"));
                    addressF.setText(rs.getString("address"));
                    stateBox.setSelectedItem(rs.getString("state"));
                    districtBox.setSelectedItem(rs.getString("district"));
                    pin.setText(rs.getString("pin_code"));

                    accF.putClientProperty("userId",userId);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Account Number is not Exist");
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        updateButton.addActionListener( _-> {

            String name_2 = nameField.getText().trim();
            String email_2 = emailF.getText().trim();
            String mobile = phoneF.getText().trim();
            String addres = addressF.getText().trim();
            String state_1 = (String) stateBox.getSelectedItem();
            String district_1 = (String) districtBox.getSelectedItem();
            String pin_1 = pin.getText().trim();

            if (name_2.isEmpty() || email_2.isEmpty() || mobile.isEmpty() ) {
                JOptionPane.showMessageDialog(null,"All fields required");
                return;
            }
            if (stateBox.getSelectedItem() == null ||
                    districtBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,"Please select state and District");
                return;
            }
            if (!pin.getText().matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Pin Code ");
                return;
            }
            Integer userId = (Integer) accF.getClientProperty("userId");
            if(userId == null){
                JOptionPane.showMessageDialog(null, "No user selected to update!");
                return;
            }
            try(Connection conn = DBConnection.getConnection()) {

                String sql_2 = "UPDATE users SET full_name = ?, email = ?, mobile = ? WHERE user_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql_2);

                statement.setString(1,name_2);
                statement.setString(2,email_2);
                statement.setString(3,mobile);
                statement.setInt(4,userId);
                int rows =statement.executeUpdate();

                String Sql_3 = "UPDATE addresses SET address = ?, state = ?, district = ?, pin_code = ? WHERE user_id = ?";
                PreparedStatement statement1 = conn.prepareStatement(Sql_3);
                statement1.setString(1,addres);
                statement1.setString(2,state_1);
                statement1.setString(3,district_1);
                statement1.setString(4,pin_1);
                statement1.setInt(5,userId);

                int rows2 = statement1.executeUpdate();

                if (rows > 0 && rows2 > 0) {
                    JOptionPane.showMessageDialog(null,"Customer Details Updated Successfully!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Update Failed !!");
                }
            }catch (SQLException e ){
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            searchAcc.setText("");
            nameField.setText("");
            accF.setText("");
            phoneF.setText("");
            emailF.setText("");
            stateBox.setSelectedItem("");
            districtBox.setSelectedItem("");
            pin.setText("");
            searchAcc.requestFocus();
        });
        updatepanel.add(updateResult,gbc_30);

        //-------------------------------------------------------------------------------VIEW PANEL-------------------------------------------
        JPanel viewpanel = new JPanel();
        viewpanel.setLayout(new BorderLayout());
        viewpanel.setBackground(new Color(51, 179, 119));

        //====================================================================first panel===========================================
        JPanel f1 = new JPanel(new GridBagLayout());
        f1.setOpaque(false);

        GridBagConstraints gbc_8 = new GridBagConstraints();
        gbc_8.insets = new Insets(10,10,10,10);

        gbc_8.gridy = 0;
        JLabel vl1 = new JLabel("VIEW ALL CUSTOMERS");
        vl1.setForeground(Color.WHITE);
        vl1.setFont(new Font("Monospaced",Font.PLAIN,30));
        f1.add(vl1, gbc_8);

        gbc_8.gridy = 1;
        JPanel vf1 = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        vf1.setOpaque(false);

        JLabel vl2 = new JLabel("View All");
        vl2.setForeground(Color.WHITE);
        vl2.setFont(new Font("Monospaced",Font.PLAIN,20));
        vf1.add(vl2);

        JButton search_2 = new JButton("View");
        search_2.setFont(new Font("Monospaced",Font.PLAIN,20));
        search_2.addActionListener(e -> {
            model.setRowCount(0); // clear table

            List<Customer> list = service.fetchAllCustomers();

            for (Customer c : list) {
                model.addRow(new Object[]{
                        c.getCreatedAt(),
                        c.getAccountNumber(),
                        c.getName(),
                        c.getMobile(),
                        c.getStatus(),
                        c.getAmount()
                });
            }
        });
        vf1.add(search_2);
        f1.add(vf1,gbc_8);

        gbc_8.gridy = 2;
        JPanel vf2 = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        vf2.setOpaque(false);

        JLabel vl3 = new JLabel("Search by Account Number :");
        vl3.setForeground(Color.WHITE);
        vl3.setFont(new Font("Monospaced",Font.PLAIN,20));
        vf2.add(vl3);

        JTextField searchF2 = new JTextField(15);
        searchF2.setFont(new Font("Monospaced",Font.PLAIN,20));
        vf2.add(searchF2);

        JButton search_3 = new JButton("Search");
        search_3.setFont(new Font("Monospaced",Font.PLAIN,20));
        search_3.addActionListener(e -> {
            model.setRowCount(0);

            String accNo = searchF2.getText();
            Customer c = service.searchCustomer(accNo);

            if (c != null) {
                model.addRow(new Object[]{
                        c.getCreatedAt(),
                        c.getAccountNumber(),
                        c.getName(),
                        c.getMobile(),
                        c.getStatus(),
                        c.getAmount()
                });
            } else {
                JOptionPane.showMessageDialog(null, "Customer not found");
            }
        });
        vf2.add(search_3);
        f1.add(vf2,gbc_8);

        viewpanel.add(f1,BorderLayout.NORTH);
        //=====================================================Second panel==============================================
        JPanel f3 = new JPanel();
        f3.setPreferredSize(null);
        f3.setOpaque(false);
        f3.setLayout(new BorderLayout());

        TitledBorder bordertrans = BorderFactory.createTitledBorder("View Customers");
        bordertrans.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        bordertrans.setTitleColor(Color.WHITE);
        f3.setBorder(BorderFactory.createCompoundBorder(
                bordertrans,
                BorderFactory.createEmptyBorder(20,20,10,20)));

        model = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setFont(new Font("Monospaced",Font.PLAIN,18));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setFont(new Font("Monospaced",Font.PLAIN,20));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        f3.add(scroll);
        viewpanel.add(f3, BorderLayout.CENTER);
        //------------------------------------------------------------------------------------------------------------------
        rightmid.add(mainPanel,"DASHBOARD");
        rightmid.add(updatepanel, "UPDATE");
        rightmid.add(viewpanel,"VIEW");

        gbc.gridx = 1;
        gbc.weightx = 0.9;
        employeePanel.add(rightmid,gbc);
        //--------------------------------------------------------------------BOTTOM----------------------------------------
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
        employeePanel.add(bottoJPanel,gbc);
        add(employeePanel, BorderLayout.CENTER);
    }
}
