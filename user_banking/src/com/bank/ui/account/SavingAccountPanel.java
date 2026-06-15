package com.bank.ui.account;

import com.bank.ui.components.FileChooser;
import com.bank.config.DBConnection;
import com.bank.util.CRNGenerator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.util.HashMap;

public class SavingAccountPanel extends JPanel{
    private File aadhaarcardFile;
    private File pancardFile;
    private File photoFile;
    private File signFile;
    private  FileChooser fileChooser = new FileChooser();
    private void applyFont(Container container, Font labelFont, Font fieldFont) {
        for (Component comp : container.getComponents()) {

            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
            }
            else if (comp instanceof JTextField ||
                    comp instanceof JTextArea ||
                    comp instanceof JSpinner ||
                    comp instanceof JRadioButton ||
                    comp instanceof JComboBox) {
                comp.setFont(fieldFont);
            }

            if (comp instanceof Container) {
                applyFont((Container) comp, labelFont, fieldFont);
            }
        }
    }
    public SavingAccountPanel(CardLayout cardLayout,JPanel cardPanel) {

        JPanel savingAccount = new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //-------------------------------------------------TOP PANEL--------------------------------------------------------
        JPanel topJPanel = new JPanel(new BorderLayout());
        topJPanel.setBackground(new Color(33, 102, 255));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        ImageIcon logIcon = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/logo.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo = new JLabel(logIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(10,20,5,10));
        leftPanel.add(logo,BorderLayout.WEST);

        JLabel label = new JLabel("yourBANK");
        label.setForeground(Color.white);
        label.setFont(new Font("Monospaced", Font.PLAIN,28));
        label.setVerticalTextPosition(SwingConstants.CENTER);
        leftPanel.add(label);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);

        ImageIcon logIcon2 = new ImageIcon(
                new ImageIcon(getClass().getResource("/image/saving.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo_2 = new JLabel(logIcon2);
        logo_2.setBorder(BorderFactory.createEmptyBorder(10,20,5,10));
        rightPanel.add(logo_2,BorderLayout.EAST);

        JLabel label_2 = new JLabel("Saving Account");
        label_2.setForeground(Color.white);
        label_2.setFont(new Font("Monospaced", Font.PLAIN,28));
        label_2.setVerticalTextPosition(SwingConstants.CENTER);
        rightPanel.add(label_2);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.05;

        topJPanel.add(leftPanel,BorderLayout.WEST);
        topJPanel.add(rightPanel,BorderLayout.EAST);
        savingAccount.add(topJPanel,gbc);
        //----------------------------------------------------MIDDLE PANEL--------------------------------------------------
        JPanel middlePanel = new JPanel(new BorderLayout());

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(210, 229, 244));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));

        JPanel cardSaving_0 = new JPanel(new GridBagLayout());
        cardSaving_0.setOpaque(false);
        TitledBorder border_das = BorderFactory.createTitledBorder("Basic Details");
        border_das.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardSaving_0.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcSaving_0 = new GridBagConstraints();
        gbcSaving_0.insets = new Insets(10,30,10,30);
        gbcSaving_0.fill = GridBagConstraints.BOTH;
        gbcSaving_0.gridx = 0;
        gbcSaving_0.weighty=0;
        gbcSaving_0.weightx = 1;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

        // Row 0 (Labels)
        gbcSaving_0.gridy = 0;
        gbcSaving_0.gridx = 0;
        cardSaving_0.add(new JLabel("Full Name"), gbcSaving_0);

        gbcSaving_0.gridx = 1;
        cardSaving_0.add(new JLabel("Father Name"), gbcSaving_0);

    // Row 1 (Fields)
        gbcSaving_0.gridy = 1;
        gbcSaving_0.gridx = 0;
        JTextField Fullname = new JTextField(15);
        cardSaving_0.add(Fullname, gbcSaving_0);

        gbcSaving_0.gridx = 1;
        JTextField fatherName = new JTextField(15);
        cardSaving_0.add(fatherName, gbcSaving_0);

    // Row 2 (Labels)
        gbcSaving_0.gridy = 2;
        gbcSaving_0.gridx = 0;
        cardSaving_0.add(new JLabel("Mother Name"), gbcSaving_0);

        gbcSaving_0.gridx = 1;
        cardSaving_0.add(new JLabel("Gender"), gbcSaving_0);

    // Row 3 (Fields)
        gbcSaving_0.gridy = 3;
        gbcSaving_0.gridx = 0;
        JTextField motherName = new JTextField(15);
        cardSaving_0.add(motherName, gbcSaving_0);

        gbcSaving_0.gridx = 1;
        String[] genders = {"Select", "Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setFont(new Font("Monospaced", Font.PLAIN, 18));
        cardSaving_0.add(genderBox, gbcSaving_0);


    // Row 4 (Labels)
        gbcSaving_0.gridy = 4;
        gbcSaving_0.gridx = 0;
        cardSaving_0.add(new JLabel("Date Of Birth"), gbcSaving_0);

        gbcSaving_0.gridx = 1;
        cardSaving_0.add(new JLabel("Email"), gbcSaving_0);

    // Row 5 (Fields)
        gbcSaving_0.gridy = 5;
        gbcSaving_0.gridx = 0;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(editor);

        cardSaving_0.add(dobSpinner, gbcSaving_0);

        gbcSaving_0.gridx = 1;
        JTextField Email = new JTextField(15);
        cardSaving_0.add(Email, gbcSaving_0);


    // Row 6 (Labels)
        gbcSaving_0.gridy = 6;
        gbcSaving_0.gridx = 0;
        cardSaving_0.add(new JLabel("Mobile Number"), gbcSaving_0);

    // (Second column empty agar kuch nahi hai)
        gbcSaving_0.gridx = 1;
        cardSaving_0.add(new JLabel(""), gbcSaving_0);

    // Row 7 (Field)
        gbcSaving_0.gridy = 7;
        gbcSaving_0.gridx = 0;
        JTextField MobileNumber = new JTextField(15);
        cardSaving_0.add(MobileNumber, gbcSaving_0);


        //======================================================second titeled==================================
        JPanel cardSaving_1 = new JPanel(new GridBagLayout());
        cardSaving_1.setOpaque(false);
        TitledBorder border_das_2 = BorderFactory.createTitledBorder("Address Details");
        border_das_2.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardSaving_1.setBorder(BorderFactory.createCompoundBorder(
                border_das_2,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcSaving_1 = new GridBagConstraints();
        gbcSaving_1.insets = new Insets(5,5,5,5);
        gbcSaving_1.fill = GridBagConstraints.HORIZONTAL;
        gbcSaving_1.gridx = 0;
        gbcSaving_1.weightx = 1;

        // Row 0 (Labels)
        gbcSaving_1.gridy = 0;
        gbcSaving_1.gridx = 0;
        cardSaving_1.add(new JLabel("Permanent Address"), gbcSaving_1);

        gbcSaving_1.gridx = 1;
        cardSaving_1.add(new JLabel("Communication Address"), gbcSaving_1);

    // Row 1 (Fields)
        gbcSaving_1.gridy = 1;
        gbcSaving_1.gridx = 0;
        JTextArea permanentAddress = new JTextArea(5,15);
        cardSaving_1.add(permanentAddress, gbcSaving_1);

        gbcSaving_1.gridx = 1;
        JTextArea communicationAddress = new JTextArea(5,15);
        cardSaving_1.add(communicationAddress, gbcSaving_1);

    // Row 2 (Labels)
        gbcSaving_1.gridy = 2;
        gbcSaving_1.gridx = 0;
        cardSaving_1.add(new JLabel("Select State"), gbcSaving_1);

        gbcSaving_1.gridx = 1;
        cardSaving_1.add(new JLabel("Select District"), gbcSaving_1);

    // Row 3 (Fields)
        gbcSaving_1.gridy = 3;
        gbcSaving_1.gridx = 0;
        String[] states = {"Haryana","Uttar Pradesh","Uttarakhand"};
        JComboBox<String> stateBox = new JComboBox<>(states);
        cardSaving_1.add(stateBox, gbcSaving_1);

        gbcSaving_1.gridx = 1;
        JComboBox<String> districtBox = new JComboBox<>();
        cardSaving_1.add(districtBox, gbcSaving_1);

    // Data map
        HashMap<String,String[]> map = new HashMap<>();
        map.put("Haryana", new String[]{"Gurgaon","Faridabad","Palwal","Sonipat"});
        map.put("Uttar Pradesh", new String[]{"Noida","Greater Noida","Meerut","Jewar"});
        map.put("Uttarakhand", new String[]{"Dehradun","Haridwar","Rishikesh","Garhwal"});

    // Event
        stateBox.addActionListener(_ -> {
            String selectedState = (String) stateBox.getSelectedItem();
            districtBox.removeAllItems();
            if (map.containsKey(selectedState)) {
                for (String d : map.get(selectedState)) {
                    districtBox.addItem(d);
                }
            }
        });

    // Row 4 (Labels)
        gbcSaving_1.gridy = 4;
        gbcSaving_1.gridx = 0;
        cardSaving_1.add(new JLabel("Pin Code"), gbcSaving_1);

        gbcSaving_1.gridx = 1;
        cardSaving_1.add(new JLabel("Aadhaar Number"), gbcSaving_1);

    // Row 5 (Fields)
        gbcSaving_1.gridy = 5;
        gbcSaving_1.gridx = 0;
        JTextField pin = new JTextField(10);
        cardSaving_1.add(pin, gbcSaving_1);

        gbcSaving_1.gridx = 1;
        JTextField aadhaarNumber = new JTextField(15);
        cardSaving_1.add(aadhaarNumber, gbcSaving_1);

    // Row 6 (Labels)
        gbcSaving_1.gridy = 6;
        gbcSaving_1.gridx = 0;
        cardSaving_1.add(new JLabel("PAN Number"), gbcSaving_1);

        gbcSaving_1.gridx = 1;
        cardSaving_1.add(new JLabel(""), gbcSaving_1); // empty

    // Row 7 (Field)
        gbcSaving_1.gridy = 7;
        gbcSaving_1.gridx = 0;
        JTextField panNumber = new JTextField(15);
        cardSaving_1.add(panNumber, gbcSaving_1);

        //==============================================================================================================
        JPanel cardSaving_2 = new JPanel(new GridBagLayout());
        cardSaving_2.setOpaque(false);
        TitledBorder border_das_3 = BorderFactory.createTitledBorder("Upload Documents");
        border_das_3.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardSaving_2.setBorder(BorderFactory.createCompoundBorder(
                border_das_3,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcSaving_2 = new GridBagConstraints();
        gbcSaving_2.insets = new Insets(5,5,5,5);
        gbcSaving_2.fill = GridBagConstraints.HORIZONTAL;
        gbcSaving_2.weightx = 1;

        // Row 0 (Labels)
        gbcSaving_2.gridy = 0;
        gbcSaving_2.gridx = 0;
        cardSaving_2.add(new JLabel("Aadhaar Card"), gbcSaving_2);

        gbcSaving_2.gridx = 1;
        cardSaving_2.add(new JLabel("PAN Card"), gbcSaving_2);

    // Row 1 (Fields)
        gbcSaving_2.gridy = 1;
        gbcSaving_2.gridx = 0;

        JPanel aadhaarPanel = new JPanel();
        aadhaarPanel.setOpaque(false);
        JButton uploadAadhaar = new JButton("Upload");
        JLabel imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(100,100));
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        aadhaarPanel.add(uploadAadhaar);
        aadhaarPanel.add(imagePreview);

        cardSaving_2.add(aadhaarPanel, gbcSaving_2);

        gbcSaving_2.gridx = 1;

        JPanel panPanel = new JPanel();
        panPanel.setOpaque(false);
        JButton uploadPan = new JButton("Upload");
        JLabel imagePreview_1 = new JLabel();
        imagePreview_1.setPreferredSize(new Dimension(100,100));
        imagePreview_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panPanel.add(uploadPan);
        panPanel.add(imagePreview_1);

        cardSaving_2.add(panPanel, gbcSaving_2);

    // Row 2 (Labels)
        gbcSaving_2.gridy = 2;
        gbcSaving_2.gridx = 0;
        cardSaving_2.add(new JLabel("Photo"), gbcSaving_2);

        gbcSaving_2.gridx = 1;
        cardSaving_2.add(new JLabel("Signature"), gbcSaving_2);


    // Row 3 (Fields)
        gbcSaving_2.gridy = 3;
        gbcSaving_2.gridx = 0;

        JPanel photoPanel = new JPanel();
        photoPanel.setOpaque(false);
        JButton uploadPhoto = new JButton("Upload");
        JLabel imagePreview_3 = new JLabel();
        imagePreview_3.setPreferredSize(new Dimension(100,100));
        imagePreview_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        photoPanel.add(uploadPhoto);
        photoPanel.add(imagePreview_3);

        cardSaving_2.add(photoPanel, gbcSaving_2);

        gbcSaving_2.gridx = 1;

        JPanel signPanel = new JPanel();
        signPanel.setOpaque(false);

        JButton uploadSignature = new JButton("Upload");
        JLabel imagePreview_4 = new JLabel();
        imagePreview_4.setPreferredSize(new Dimension(100,100));
        imagePreview_4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        signPanel.add(uploadSignature);
        signPanel.add(imagePreview_4);

        cardSaving_2.add(signPanel, gbcSaving_2);

    // Actions
        uploadAadhaar.addActionListener(_ -> aadhaarcardFile = fileChooser.ChooseImage(imagePreview));
        uploadPan.addActionListener(_ -> pancardFile = fileChooser.ChooseImage(imagePreview_1));
        uploadPhoto.addActionListener(_ -> photoFile = fileChooser.ChooseImage(imagePreview_3));
        uploadSignature.addActionListener(_ -> signFile = fileChooser.ChooseImage(imagePreview_4));
        // ======================================================================================

        JPanel cardSaving_3 = new JPanel(new GridBagLayout());
        cardSaving_3.setOpaque(false);
        TitledBorder border_das_4 = BorderFactory.createTitledBorder("Final Submission");
        border_das_4.setTitleFont(new Font("Monospaced",Font.PLAIN,22));
        cardSaving_3.setBorder(BorderFactory.createCompoundBorder(
                border_das_4,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcSaving_3 = new GridBagConstraints();
        gbcSaving_3.insets = new Insets(5,5,5,5);
        gbcSaving_3.fill = GridBagConstraints.HORIZONTAL;
        gbcSaving_3.weightx = 1;

        // Row 0 (Labels)
        gbcSaving_3.gridy = 0;
        gbcSaving_3.gridx = 0;
        cardSaving_3.add(new JLabel("Place"), gbcSaving_3);

        gbcSaving_3.gridx = 1;
        cardSaving_3.add(new JLabel(""), gbcSaving_3); // empty second column

    // Row 1 (Field)
        gbcSaving_3.gridy = 1;
        gbcSaving_3.gridx = 0;
        JTextField place_1  = new JTextField(15);
        cardSaving_3.add(place_1, gbcSaving_3);

    // Row 2 (Buttons)
        gbcSaving_3.gridy = 2;
        gbcSaving_3.gridx = 0;
        gbcSaving_3.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton backButton = new JButton("Previous");
        backButton.addActionListener(_ -> cardLayout.show(cardPanel, "OPEN ACCOUNT"));

        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFocusable(false);
        submitButton.addActionListener(_ -> {
            //--------------------collect all fields----------------------
            String fullName = Fullname.getText().trim();
            String FatherName = fatherName.getText().trim();
            String MotherName = motherName.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            java.util.Date utilDate = (java.util.Date) dobSpinner.getValue();
            java.sql.Date sqlDob = new java.sql.Date(utilDate.getTime());
            String email = Email.getText().trim();
            String mobile = MobileNumber.getText();
            String permanentAddr = permanentAddress.getText().trim();
            String communicationAddr = communicationAddress.getText().trim();
            String state = (String) stateBox.getSelectedItem();
            String district = (String) districtBox.getSelectedItem();
            String pinCode = pin.getText().trim();
            String aadhaar = aadhaarNumber.getText().trim();
            String pan = panNumber.getText().trim();
            String place = place_1.getText().trim();

            //--------------------Validation----------------------------------
            if (Fullname.getText().trim().isEmpty() ||
                    fatherName.getText().trim().isEmpty() ||
                    motherName.getText().trim().isEmpty() ||
                    Email.getText().trim().isEmpty() ||
                    MobileNumber.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill all Fields");
                return;
            }
            if (!Email.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Email");
                return;
            }
            if (!MobileNumber.getText().matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null,"Enter valid 10 digit Mobile Number");
                return;
            }
            if (permanentAddress.getText().trim().isEmpty() ||
                    communicationAddress.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill address Fields");
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
            if (!aadhaarNumber.getText().matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Aadhaar Number");
                return;
            }
            if (!panNumber.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Pan Number");
                return;
            }
            if (aadhaarcardFile == null || pancardFile == null || photoFile == null || signFile == null) {
                JOptionPane.showMessageDialog(null,"Please upload all Required Documents");
                return;
            }
            if ( place.isEmpty() ) {
                JOptionPane.showMessageDialog(null,"Please Fill All Fields");
                return;
            }
            //--------------------------------JDBC INSERT--------------------------------------

            try (Connection conn = DBConnection.getConnection()){
                conn.setAutoCommit(false);

                String crn = CRNGenerator.generateCRN(conn);

                String lastAcc = null;
                Statement str = conn.createStatement();
                ResultSet rs = str.executeQuery(
                        "SELECT account_number FROM accounts ORDER BY account_number DESC LIMIT 1");
                if(rs.next()) {
                    lastAcc = rs.getString("account_number");
                }

                String newAcc;
                if (lastAcc == null){
                    newAcc = "00261001002";
                }else {
                    int num = Integer.parseInt(lastAcc.substring(3));
                    num++;
                    newAcc = "0026100" + num;
                }

                String sql = "INSERT INTO users(full_name,father_name,mother_name,gender,dob,email," +
                        "mobile,place) VALUES(?,?,?,?,?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,fullName);
                ps.setString(2,FatherName);
                ps.setString(3,MotherName);
                ps.setString(4,gender);
                ps.setDate(5,sqlDob);
                ps.setString(6,email);
                ps.setString(7,mobile);
                ps.setString(8,place);

                ps.executeUpdate();
                ResultSet result = ps.getGeneratedKeys();
                int userID = 0;
                if (result.next()) {
                    userID = result.getInt(1);
                }

                String accountSql = "INSERT INTO accounts(user_id,account_type,branch_name,account_number,status) VALUES(?,?,?,?,?)";
                PreparedStatement prepared = conn.prepareStatement(accountSql, Statement.RETURN_GENERATED_KEYS);

                prepared.setInt(1,userID);
                prepared.setString(2,"SAVING");
                prepared.setString(3,"Main Branch");
                prepared.setString(4, newAcc);
                prepared.setString(5,"ACTIVE");

                prepared.executeUpdate();

                ResultSet resultAccount = prepared.getGeneratedKeys();
                int account_id = 0;
                if (resultAccount.next()) {
                    account_id = resultAccount.getInt(1);
                }

                String addressSql = "INSERT INTO addresses(user_id,type,address,state,district,pin_code) VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedAddress = conn.prepareStatement(addressSql);

                preparedAddress.setInt(1,userID);
                preparedAddress.setString(2,"PERMANENT");
                preparedAddress.setString(3,permanentAddr);
                preparedAddress.setString(4,state);
                preparedAddress.setString(5,district);
                preparedAddress.setString(6,pinCode);
                preparedAddress.executeUpdate();

                String savingSql = "INSERT INTO saving_account_details(account_id,permanent_address,communication_address," +
                        "state,district,pin_code,aadhaar_number,pan_number) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedSaving = conn.prepareStatement(savingSql);

                preparedSaving.setInt(1,account_id);
                preparedSaving.setString(2,permanentAddr);
                preparedSaving.setString(3,communicationAddr);
                preparedSaving.setString(4,state);
                preparedSaving.setString(5,district);
                preparedSaving.setString(6,pinCode);
                preparedSaving.setString(7,aadhaar);
                preparedSaving.setString(8,pan);

                preparedSaving.executeUpdate();

                String socSql = "INSERT INTO documents(account_id,user_id,document_type,file_path) VALUES(?,?,?,?)";
                PreparedStatement statementDoc = conn.prepareStatement(socSql);

                statementDoc.setInt(1,account_id);
                statementDoc.setInt(2,userID);
                statementDoc.setString(3,"AADHAAR");
                statementDoc.setString(4,aadhaarcardFile.getAbsolutePath());
                statementDoc.executeUpdate();

                statementDoc.setString(3, "PAN");
                statementDoc.setString(4, pancardFile.getAbsolutePath());
                statementDoc.executeUpdate();

                statementDoc.setString(3, "PHOTO");
                statementDoc.setString(4, photoFile.getAbsolutePath());
                statementDoc.executeUpdate();

                statementDoc.setString(3, "SIGNATURE");
                statementDoc.setString(4, signFile.getAbsolutePath());
                statementDoc.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(null, "Account Created Successfully.");
                JOptionPane.showMessageDialog(null,"your account number is " + newAcc);

                String receipt =
                        "======== ACCOUNT OPENING RECEIPT ========\n\n" +
                                "Name        : " + fullName + "\n" +
                                "Account No  : " + newAcc + "\n" +
                                "CRN         : " + crn + "\n" +
                                "Email       : " + email + "\n" +
                                "Mobile      : " + mobile + "\n" +
                                "Account Type: SAVING\n" +
                                "Branch      : Main Branch\n\n" +
                                "Status      : ACTIVE\n" +
                                "=========================================\n" +
                                "Thank You for Banking with Us!";

                JTextArea textArea = new JTextArea(receipt);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                textArea.setEditable(false);

                int option = JOptionPane.showOptionDialog(
                        null,
                        new JScrollPane(textArea),
                        "Print Preview",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Print", "Close"},
                        "Print"
                );

                if (option == JOptionPane.YES_OPTION) {
                    try {
                        boolean done = textArea.print();
                        if (done) {
                            JOptionPane.showMessageDialog(null, "Printed Successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Printing Cancelled");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Print Error: " + ex.getMessage());
                    }
                }
                cardLayout.show(cardPanel, "EMPLOYEE DASHBOARD");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR " + ex.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Fullname.requestFocus();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        cardSaving_3.add(buttonPanel, gbcSaving_3);

        applyFont(cardSaving_0, labelFont, fieldFont);
        applyFont(cardSaving_1, labelFont, fieldFont);
        applyFont(cardSaving_2, labelFont, fieldFont);
        applyFont(cardSaving_3, labelFont, fieldFont);

        scrollPanel.add(cardSaving_0);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardSaving_1);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardSaving_2);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardSaving_3);

        JScrollPane scroll = new JScrollPane(scrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        middlePanel.add(scroll,BorderLayout.CENTER);
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.9;
        savingAccount.add(middlePanel,gbc);
        //------------------------------------------------------------------------------------------------------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(12, 12, 69));

        JLabel footer = new JLabel("© 2026 Secure Bank. All Right Reserved.");
        footer.setFont(new Font("Monospaced",Font.PLAIN,18));
        footer.setForeground(Color.white);
        bottomPanel.add(footer);

        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.05;
        savingAccount.add(bottomPanel,gbc);
        add(savingAccount, BorderLayout.CENTER);
    }
}
