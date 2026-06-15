package com.bank.ui.dashboard;

import com.bank.config.DBConnection;
import com.bank.model.EmployeeModel.Employee;
import com.bank.model.UserModel.Customer;
import com.bank.service.EmployeeService.EmployeeSer;
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
import java.util.List;

public class AdminDashboard extends JPanel {
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private CustomerService service;
    private EmployeeSer service2;
    private JPanel rightmid;
    private CardLayout rightCardLayout;

    public AdminDashboard(CardLayout cardLayout, JPanel cardPanel, DefaultTableModel tableModel ,DefaultTableModel tableModel2) {
        this.model = tableModel;
        service = new CustomerService();
        String[] column = {
                "Created At",
                "A/C no.",
                "Name",
                "Mobile",
                "Status",
                "Balance"
        };
        this.model2 = tableModel2;
        service2 = new EmployeeSer();
        String[] column2 = {
                "Employee ID",
                "Name",
                "Role",
                "Email ID",
                "Mobile",
                "Status"
        };
        setLayout(new BorderLayout());
        JPanel employeePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //-----------------------------------------------------------TOP PANEL----------------------------------------------
        JPanel topJPanel = new JPanel(new BorderLayout());
        topJPanel.setBackground(new Color(13, 27, 42));
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        left.setOpaque(false);
        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo = new JLabel(logIcon2);
        logo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        left.add(logo, BorderLayout.WEST);
        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN, 28));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        left.add(label);
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setOpaque(false);
        JLabel emm_welcome = new JLabel();
        emm_welcome.setForeground(Color.WHITE);
        emm_welcome.setText("Welcome Manager");
        emm_welcome.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 10));
        emm_welcome.setFont(new Font("Monospaced", Font.PLAIN, 26));
        emm_welcome.setVerticalTextPosition(SwingConstants.EAST);
        right.add(emm_welcome);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        topJPanel.add(left, BorderLayout.WEST);
        topJPanel.add(right, BorderLayout.EAST);
        employeePanel.add(topJPanel, gbc);
        //--------------------------------------------------------------MIDDLE LEFT------------------------------------------
        JPanel leftMiddle = new JPanel();
        leftMiddle.setBackground(new Color(26, 52, 117));
        leftMiddle.setPreferredSize(new Dimension(250, 0));
        leftMiddle.setLayout(new BoxLayout(leftMiddle, BoxLayout.Y_AXIS));
        leftMiddle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        JButton dash = new JButton("Dashboard");
        dash.setFont(new Font("Monospaced", Font.PLAIN, 18));
        dash.setBackground(new Color(21, 143, 131));
        dash.setForeground(Color.WHITE);
        dash.setAlignmentX(Component.CENTER_ALIGNMENT);
        dash.setMaximumSize(new Dimension(240, 40));
        dash.setFocusable(false);
        dash.addActionListener(_ -> rightCardLayout.show(rightmid, "DASHBOARD"));
    //-------------------------------------------------------------------------------------------------------
        JButton viewEmm = new JButton("View Employee");
        viewEmm.setFont(new Font("Monospaced", Font.PLAIN, 18));
        viewEmm.setBackground(new Color(21, 143, 131));
        viewEmm.setForeground(Color.WHITE);
        viewEmm.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewEmm.setMaximumSize(new Dimension(240, 40));
        viewEmm.setFocusable(false);
        viewEmm.addActionListener(_ -> rightCardLayout.show(rightmid, "VIEW EMPLOYEE"));
        //----------------------------------------------------------------------------------------------------------
        JButton addEmployee = new JButton("Add Employee");
        addEmployee.setFont(new Font("Monospaced", Font.PLAIN, 18));
        addEmployee.setBackground(new Color(21, 143, 131));
        addEmployee.setForeground(Color.WHITE);
        addEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEmployee.setMaximumSize(new Dimension(240, 40));
        addEmployee.setFocusable(false);
        addEmployee.addActionListener(_ -> rightCardLayout.show(rightmid, "ADD EMPLOYEE"));
        //----------------------------------------------------------------------------------------------------------
        JButton view = new JButton("View All Customers");
        view.setFont(new Font("Monospaced", Font.PLAIN, 18));
        view.setBackground(new Color(21, 143, 131));
        view.setForeground(Color.WHITE);
        view.setAlignmentX(Component.CENTER_ALIGNMENT);
        view.setMaximumSize(new Dimension(240, 40));
        view.addActionListener(_ -> rightCardLayout.show(rightmid, "VIEW CUSTOMER"));
        view.setFocusable(false);
        //----------------------------------------------------------------------------------------------------------
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setFont(new Font("Monospaced", Font.PLAIN, 18));
        deleteAccount.setBackground(new Color(21, 143, 131));
        deleteAccount.setForeground(Color.WHITE);
        deleteAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccount.setMaximumSize(new Dimension(240, 40));
        deleteAccount.addActionListener(e -> {
            rightCardLayout.show(rightmid, "DELETE");
            rightmid.repaint();
            rightmid.revalidate();
        });
        deleteAccount.setFocusable(false);
        //------------------------------------------------------------------------------------------------------------
        JButton logJButton = new JButton("Logout");
        logJButton.setFont(new Font("Monospaced", Font.PLAIN, 18));
        logJButton.setBackground(new Color(21, 143, 131));
        logJButton.setForeground(Color.WHITE);
        logJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logJButton.setMaximumSize(new Dimension(240, 40));
        logJButton.setFocusable(false);
        logJButton.addActionListener(_ -> {
                    JOptionPane.showMessageDialog(null, "Log out Successfully !!");
                    cardLayout.show(cardPanel, "ADMIN LOGIN");
                }
        );
        //----------------------------------------------------------------------------------------------------
        leftMiddle.add(dash);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(viewEmm);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(addEmployee);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(view);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(deleteAccount);
        leftMiddle.add(Box.createVerticalStrut(15));
        leftMiddle.add(logJButton);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.85;
        employeePanel.add(leftMiddle, gbc);
        //------------------------------------------------------------------------MIDDLE RIGHT----------------------------------------
        rightmid = new JPanel();
        rightmid.setBackground(new Color(29, 193, 174));
        rightCardLayout = new CardLayout();
        rightmid.setLayout(rightCardLayout);
        //-----------------------------------------------------------------------------------------------------------------------------
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(12, 147, 207));
        GridBagConstraints gbc_m = new GridBagConstraints();
        gbc_m.insets = new Insets(10, 10, 10, 10);
        gbc_m.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc_m.gridy = 0;
        JLabel wel = new JLabel("DASHBOARD");
        wel.setForeground(Color.WHITE);
        wel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        mainPanel.add(wel, gbc_m);

        //-----------------------------------------------------------ADD EMPLOYEE--------------------------------------
        JPanel addEmployeePanel = new JPanel(new GridBagLayout());
        addEmployeePanel.setBackground(new Color(214, 241, 235));
        GridBagConstraints gbc_12 = new GridBagConstraints();
        gbc_12.insets = new Insets(5, 10, 5, 10);
        gbc_12.gridy = 0;
        JLabel title = new JLabel("ADD EMPLOYEE");
        title.setFont(new Font("Monospaced", Font.PLAIN, 30));
        addEmployeePanel.add(title, gbc_12);
        gbc_12.gridy = 1;
        gbc_12.fill = GridBagConstraints.HORIZONTAL;

        JPanel addEmployeeForm = new JPanel(new GridBagLayout());
        addEmployeeForm.setPreferredSize(new Dimension(900, 520));
        addEmployeeForm.setOpaque(false);
        TitledBorder titleborder = BorderFactory.createTitledBorder("Add Employee");
        titleborder.setTitleFont(new Font("Monospaced", Font.PLAIN, 22));
        addEmployeeForm.setBorder(BorderFactory.createCompoundBorder(
                titleborder,
                BorderFactory.createEmptyBorder(20, 30, 20, 30)));
        GridBagConstraints gbc_44 = new GridBagConstraints();
        gbc_44.insets = new Insets(10, 10, 10, 10);
        gbc_44.anchor = GridBagConstraints.WEST;
        gbc_44.fill = GridBagConstraints.HORIZONTAL;
        gbc_44.weightx = 1;

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        form.setOpaque(false);

        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.weightx = 1;
        gbcForm.insets = new Insets(10,20,10,20);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;
        gbcForm.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Monospaced", Font.PLAIN, 18);
        Font fieldFont = new Font("Monospaced", Font.PLAIN, 18);

        JTextField genderTxt = new JTextField();
        JTextField roleTxt = new JTextField();
        JTextField nameTxt = new JTextField();
        JTextField date = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JPasswordField passTxt = new JPasswordField();
        JPasswordField confirmTxt = new JPasswordField();

        //row 1
        gbcForm.gridy = 0;
        gbcForm.gridx = 0;
        form.add(new JLabel("Name :"), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Role :"), gbcForm);

        gbcForm.gridy = 1;
        gbcForm.gridx = 0;
        form.add(nameTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(roleTxt, gbcForm);

        //row 2
        gbcForm.gridy = 2;
        gbcForm.gridx = 0;
        form.add(new JLabel("Gender :"), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("DOB :"), gbcForm);

        gbcForm.gridy = 3;
        gbcForm.gridx = 0;
        String[] genders = {"Select", "Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setFont(new Font("Monospaced", Font.PLAIN, 18));
        form.add(genderBox, gbcForm);

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
        form.add(new JLabel("Email Id :"), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Phome Number : "), gbcForm);

        gbcForm.gridy = 5;
        gbcForm.gridx = 0;
        form.add(emailTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(phoneTxt, gbcForm);

        //row 4
        gbcForm.gridy = 6;
        gbcForm.gridx = 0;
        form.add(new JLabel("Set Password : "), gbcForm);
        gbcForm.gridx = 1;
        form.add(new JLabel("Confirm Password :"), gbcForm);

        gbcForm.gridy = 7;
        gbcForm.gridx = 0;
        form.add(passTxt, gbcForm);
        gbcForm.gridx = 1;
        form.add(confirmTxt, gbcForm);

        Dimension fieldSize = new Dimension(150,30);
        nameTxt.setPreferredSize(fieldSize);
        roleTxt.setPreferredSize(fieldSize);
        date.setPreferredSize(fieldSize);
        genderBox.setPreferredSize(fieldSize);
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
        addEmployeeForm.add(form, gbc_44);
        gbc_44.gridy = 3;
        gbc_44.gridx = 0;
        gbc_44.gridwidth = 2;

        JButton ADD = new JButton(" ADD ");
        ADD.setFocusable(false);
        ADD.setFont(new Font("Monospaced", Font.PLAIN, 20));
        ADD.setPreferredSize(new Dimension(100, 35));
        addEmployeeForm.add(ADD, gbc_44);
        ADD.addActionListener(_ -> {
            String name = nameTxt.getText().trim();
            String role = roleTxt.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            java.util.Date utilDate = (java.util.Date) dobSpinner.getValue();
            java.sql.Date sqlDob = new java.sql.Date(utilDate.getTime());
            String email = emailTxt.getText().trim();
            String phone = phoneTxt.getText().trim();
            String setpass = new String(passTxt.getPassword());
            String conpass = new String(confirmTxt.getPassword());

            if (nameTxt.getText().trim().isEmpty() ||
                    emailTxt.getText().trim().isEmpty() ||
                    phoneTxt.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill all Fields");
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
            try {
                Connection conn = DBConnection.getConnection();
                String sql = "INSERT INTO employees (name, role, gender, dob, email, phone, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, name);
                stmt.setString(2, role);
                stmt.setString(3, gender);
                stmt.setDate(4, sqlDob);
                stmt.setString(5, email);
                stmt.setString(6, phone);

                String hashed = BCrypt.hashpw(setpass, BCrypt.gensalt());
                stmt.setString(7, hashed);

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
                    nameTxt.setText("");
                    roleTxt.setText("");
                    emailTxt.setText("");
                    phoneTxt.setText("");
                    passTxt.setText("");
                    confirmTxt.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Employee Added Failed!");
                }

                conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        addEmployeePanel.add(addEmployeeForm, gbc_12);
    //-------------------------------------------------------VIEW EMPLOYEE----------------------------------------------
        JPanel emmPanel = new JPanel();
        emmPanel.setLayout(new BorderLayout());
        emmPanel.setBackground(new Color(51, 179, 119));
        //======================================================first panel=============================================
        JPanel searchEmm = new JPanel(new GridBagLayout());
        searchEmm.setOpaque(false);
        GridBagConstraints gbc_8 = new GridBagConstraints();
        gbc_8.insets = new Insets(10, 10, 10, 10);
        gbc_8.gridy = 0;
        JLabel vl1 = new JLabel("VIEW ALL Employee");
        vl1.setForeground(Color.WHITE);
        vl1.setFont(new Font("Monospaced", Font.PLAIN, 30));
        searchEmm.add(vl1, gbc_8);
        gbc_8.gridy = 1;
        JPanel vf1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        vf1.setOpaque(false);
        JLabel vl2 = new JLabel("View All");
        vl2.setForeground(Color.WHITE);
        vl2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        vf1.add(vl2);
        JButton search_2 = new JButton("View");
        search_2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        search_2.addActionListener(e -> {
            model2.setRowCount(0);
            List<Employee> list = service2.getAllEmployees();
            for (Employee emm : list) {
                model2.addRow(new Object[]{
                        emm.getEmployeeId(),
                        emm.getName(),
                        emm.getRole(),
                        emm.getEmail(),
                        emm.getPhone(),
                        emm.getStatus()
                });
            }
        });
        vf1.add(search_2);
        searchEmm.add(vf1, gbc_8);
        emmPanel.add(searchEmm, BorderLayout.NORTH);
        //=====================================================Second panel==============================================
        JPanel f3 = new JPanel();
        f3.setPreferredSize(null);
        f3.setOpaque(false);
        f3.setLayout(new BorderLayout());
        TitledBorder bordertrans = BorderFactory.createTitledBorder("View Customers");
        bordertrans.setTitleFont(new Font("Monospaced", Font.PLAIN, 22));
        bordertrans.setTitleColor(Color.WHITE);
        f3.setBorder(BorderFactory.createCompoundBorder(
                bordertrans,
                BorderFactory.createEmptyBorder(20, 20, 10, 20)));
        model2 = new DefaultTableModel(column2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model2);
        table.setFont(new Font("Monospaced", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        f3.add(scroll);
        emmPanel.add(f3, BorderLayout.CENTER);
    //-----------------------------------------------------VIEW CUSTOMERS-----------------------------------------------

        JPanel viewpanel = new JPanel();
        viewpanel.setLayout(new BorderLayout());
        viewpanel.setBackground(new Color(51, 179, 119));
        //======================================================first panel=============================================
        JPanel f1 = new JPanel(new GridBagLayout());
        f1.setOpaque(false);
        GridBagConstraints gbc_18 = new GridBagConstraints();
        gbc_18.insets = new Insets(10, 10, 10, 10);
        gbc_18.gridy = 0;
        JLabel viewCustomer = new JLabel("VIEW ALL CUSTOMERS");
        viewCustomer.setForeground(Color.WHITE);
        viewCustomer.setFont(new Font("Monospaced", Font.PLAIN, 30));
        f1.add(viewCustomer, gbc_18);
        gbc_18.gridy = 1;
        JPanel viewCus = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        viewCus.setOpaque(false);
        JLabel viewAll = new JLabel("View All");
        viewAll.setForeground(Color.WHITE);
        viewAll.setFont(new Font("Monospaced", Font.PLAIN, 20));
        viewCus.add(viewAll);
        JButton serachAll = new JButton("View");
        serachAll.setFont(new Font("Monospaced", Font.PLAIN, 20));
        serachAll.addActionListener(e -> {
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
        viewCus.add(serachAll);
        f1.add(viewCus, gbc_18);
        gbc_18.gridy = 2;
        JPanel vf2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        vf2.setOpaque(false);
        JLabel vl3 = new JLabel("Search by Account Number :");
        vl3.setForeground(Color.WHITE);
        vl3.setFont(new Font("Monospaced", Font.PLAIN, 20));
        vf2.add(vl3);
        JTextField searchF2 = new JTextField(15);
        searchF2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        vf2.add(searchF2);
        JButton search_3 = new JButton("Search");
        search_3.setFont(new Font("Monospaced", Font.PLAIN, 20));
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
        f1.add(vf2, gbc_18);
        viewpanel.add(f1, BorderLayout.NORTH);
        //=====================================================Second panel==============================================
        JPanel f5 = new JPanel();
        f5.setPreferredSize(null);
        f5.setOpaque(false);
        f5.setLayout(new BorderLayout());
        TitledBorder CusBorder = BorderFactory.createTitledBorder("View Customers");
        CusBorder.setTitleFont(new Font("Monospaced", Font.PLAIN, 22));
        CusBorder.setTitleColor(Color.WHITE);
        f5.setBorder(BorderFactory.createCompoundBorder(
                CusBorder,
                BorderFactory.createEmptyBorder(20, 20, 10, 20)));
        model = new DefaultTableModel(column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable CusTable = new JTable(model);
        CusTable.setFont(new Font("Monospaced", Font.PLAIN, 18));
        CusTable.setRowHeight(30);
        CusTable.setFillsViewportHeight(true);
        CusTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        CusTable.getTableHeader().setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scrollDown = new JScrollPane(CusTable);
        scrollDown.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        f5.add(scrollDown);
        viewpanel.add(f5, BorderLayout.CENTER);
        //-----------------------------------------------------------------DELETE PANEL-------------------------------------
        JPanel deletepanel = new JPanel(new GridBagLayout());
        deletepanel.setBackground(new Color(162, 17, 204));
        GridBagConstraints gbc_10 = new GridBagConstraints();
        gbc_10.insets = new Insets(10, 10, 10, 10);
        gbc_10.gridy = 0;
        JLabel dl1 = new JLabel("DELETE CUSTOMER ACCOUNT");
        dl1.setForeground(Color.WHITE);
        dl1.setFont(new Font("Monospaced", Font.PLAIN, 30));
        deletepanel.add(dl1, gbc_10);
        gbc_10.gridy = 1;
        JPanel center_d = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        center_d.setOpaque(false);
        JLabel dl2 = new JLabel("Search Account Number :");
        dl2.setForeground(Color.WHITE);
        dl2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        center_d.add(dl2);
        JTextField searchF = new JTextField(15);
        searchF.setFont(new Font("Monospaced", Font.PLAIN, 20));
        center_d.add(searchF);
        JButton search_10 = new JButton("Search");
        search_10.setFont(new Font("Monospaced", Font.PLAIN, 20));
        center_d.add(search_10);
        deletepanel.add(center_d, gbc_10);
        gbc_10.gridy = 2;
        gbc_10.fill = GridBagConstraints.HORIZONTAL;
        JPanel result_d = new JPanel(new GridBagLayout());
        result_d.setPreferredSize(new Dimension(500, 350));
        result_d.setOpaque(false);
        TitledBorder border_d = BorderFactory.createTitledBorder("Account Details");
        border_d.setTitleFont(new Font("Monospaced", Font.PLAIN, 22));
        border_d.setTitleColor(Color.WHITE);
        result_d.setBorder(BorderFactory.createCompoundBorder(
                border_d,
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        GridBagConstraints gbc_11 = new GridBagConstraints();
        gbc_11.insets = new Insets(5, 10, 5, 10);
        gbc_11.fill = GridBagConstraints.HORIZONTAL;
        gbc_11.weightx = 1;
        gbc_11.gridy = 0;
        gbc_11.gridx = 0;
        JLabel name_d = new JLabel("Name :");
        name_d.setForeground(Color.WHITE);
        name_d.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(name_d, gbc_11);
        gbc_11.gridy = 0;
        gbc_11.gridx = 1;
        JTextField namedf = new JTextField(15);
        namedf.setFont(new Font("Monospaced", Font.PLAIN, 20));
        namedf.setEditable(false);
        result_d.add(namedf, gbc_11);
        gbc_11.gridy = 1;
        gbc_11.gridx = 0;
        JLabel accountd = new JLabel("A/C Number :");
        accountd.setForeground(Color.WHITE);
        accountd.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(accountd, gbc_11);
        gbc_11.gridy = 1;
        gbc_11.gridx = 1;
        JTextField accdF = new JTextField(15);
        accdF.setFont(new Font("Monospaced", Font.PLAIN, 20));
        accdF.setEditable(false);
        result_d.add(accdF, gbc_11);
        gbc_11.gridy = 2;
        gbc_11.gridx = 0;
        JLabel phoned = new JLabel("Phone :");
        phoned.setForeground(Color.WHITE);
        phoned.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(phoned, gbc_11);
        gbc_11.gridy = 2;
        gbc_11.gridx = 1;
        JTextField phonedF = new JTextField(15);
        phonedF.setEditable(false);
        phonedF.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(phonedF, gbc_11);
        gbc_11.gridy = 3;
        gbc_11.gridx = 0;
        JLabel emaild = new JLabel("Email ID :");
        emaild.setForeground(Color.WHITE);
        emaild.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(emaild, gbc_11);
        gbc_11.gridy = 3;
        gbc_11.gridx = 1;
        JTextField emaildF = new JTextField(15);
        emaildF.setFont(new Font("Monospaced", Font.PLAIN, 20));
        emaildF.setEditable(false);
        result_d.add(emaildF, gbc_11);
        gbc_11.gridy = 4;
        gbc_11.gridx = 0;
        gbc_11.gridwidth = 2;
        JButton deleteButtond = new JButton("Delete Account");
        deleteButtond.setFont(new Font("Monospaced", Font.PLAIN, 20));
        result_d.add(deleteButtond, gbc_11);
        search_10.addActionListener(_ -> {
            String accountNumber = searchF.getText().trim();
            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false);
                String sql2 = "SELECT  a.account_number, u.full_name, u.mobile, u.email " +
                        "FROM accounts a JOIN users u ON a.user_id = u.user_id " +
                        "WHERE a.account_number = ?";
                PreparedStatement statement = conn.prepareStatement(sql2);
                statement.setInt(1, Integer.parseInt(accountNumber));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    namedf.setText(resultSet.getString("full_name"));
                    accdF.setText(resultSet.getString("account_number"));
                    emaildF.setText(resultSet.getString("email"));
                    phonedF.setText(resultSet.getString("mobile"));
                } else {
                    JOptionPane.showMessageDialog(null, "Account Number is not Exist");
                }
                conn.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        deleteButtond.addActionListener(_ ->
        {
            String accountNumber = searchF.getText().trim();
            try (Connection conn = DBConnection.getConnection()) {
                String sql_5 = "SELECT user_id, account_id FROM accounts WHERE account_number = ?";
                PreparedStatement statement = conn.prepareStatement(sql_5);
                statement.setString(1, accountNumber);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("user_id");
                    int accountID = rs.getInt("account_id");
                    int Confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure to delete  this account?");
                    if (Confirm == JOptionPane.YES_OPTION) {
                        PreparedStatement stm_5 = conn.prepareStatement("DELETE FROM saving_account_details WHERE account_id = ?");
                        stm_5.setInt(1, accountID);
                        stm_5.executeUpdate();
                        PreparedStatement stm_6 = conn.prepareStatement("DELETE FROM joint_account_details WHERE account_id = ?");
                        stm_6.setInt(1, accountID);
                        stm_6.executeUpdate();
                        PreparedStatement stm_7 = conn.prepareStatement("DELETE FROM current_account_details WHERE account_id = ?");
                        stm_7.setInt(1, accountID);
                        stm_7.executeUpdate();
                        PreparedStatement stm_1 = conn.prepareStatement("DELETE FROM documents WHERE account_id = ?");
                        stm_1.setInt(1, accountID);
                        stm_1.executeUpdate();
                        PreparedStatement stm_2 = conn.prepareStatement("DELETE FROM addresses WHERE user_id = ?");
                        stm_2.setInt(1, userID);
                        stm_2.executeUpdate();
                        PreparedStatement stm_3 = conn.prepareStatement("DELETE FROM accounts WHERE user_id = ?");
                        stm_3.setInt(1, userID);
                        stm_3.executeUpdate();
                        PreparedStatement stm_4 = conn.prepareStatement("DELETE FROM users WHERE user_id = ?");
                        stm_4.setInt(1, userID);
                        stm_4.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account Deleted Suuccessfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Account NOt Found");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            namedf.setText("");
            accdF.setText("");
            emaildF.setText("");
            phonedF.setText("");
            searchF.setText("");
            searchF.requestFocus();
        });
        deletepanel.add(result_d, gbc_10);
        //------------------------------------------------------------------------------------------------------------------
        rightmid.add(mainPanel, "DASHBOARD");
        rightmid.add(emmPanel, "VIEW EMPLOYEE");
        rightmid.add(addEmployeePanel, "ADD EMPLOYEE");
        rightmid.add(viewpanel, "VIEW CUSTOMER");
        rightmid.add(deletepanel, "DELETE");
        gbc.gridx = 1;
        gbc.weightx = 0.9;
        employeePanel.add(rightmid, gbc);
        //--------------------------------------------------------------------BOTTOM----------------------------------------
        JPanel bottoJPanel = new JPanel();
        bottoJPanel.setBackground(new Color(13, 27, 42));
        JLabel footer = new JLabel("© 2026 Secure Bank. All Right Reserved.");
        footer.setForeground(Color.white);
        bottoJPanel.add(footer);
        bottoJPanel.setFont(new Font("Monospaced UI", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        employeePanel.add(bottoJPanel, gbc);
        add(employeePanel, BorderLayout.CENTER);
    }
}
