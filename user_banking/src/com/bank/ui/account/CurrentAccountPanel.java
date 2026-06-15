package com.bank.ui.account;
import com.bank.ui.components.FileChooser;
import com.bank.util.CRNGenerator;
import com.bank.config.DBConnection;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.util.HashMap;

public class CurrentAccountPanel extends JPanel{
    private CRNGenerator CrnGenerator;
    private File aadhaarcardFile;
    private File pancardFile;
    private File photoFile;
    private File signFile;
    private FileChooser fileChooser = new FileChooser();

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

    public CurrentAccountPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel currentAccount = new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //--------------------------------
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
                new ImageIcon(getClass().getResource("/image/current.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo_2 = new JLabel(logIcon2);
        logo_2.setBorder(BorderFactory.createEmptyBorder(10,20,5,10));
        rightPanel.add(logo_2,BorderLayout.EAST);

        JLabel label_2 = new JLabel("Current Account");
        label_2.setForeground(Color.white);
        label_2.setFont(new Font("Monospaced", Font.PLAIN,28));
        label_2.setVerticalTextPosition(SwingConstants.CENTER);
        rightPanel.add(label_2);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.05;

        topJPanel.add(leftPanel,BorderLayout.WEST);
        topJPanel.add(rightPanel,BorderLayout.EAST);
        currentAccount.add(topJPanel,gbc);

        //-----------------------------------------------------------------------------------------------------
        JPanel middlePanel = new JPanel(new BorderLayout());

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(210, 229, 244));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));


        JPanel cardCurrent_0 = new JPanel(new GridBagLayout());
        cardCurrent_0.setOpaque(false);
        TitledBorder border_das = BorderFactory.createTitledBorder("Basic Details");
        border_das.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_0.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_0 = new GridBagConstraints();
        gbcCurrent_0.insets = new Insets(10,30,10,30);
        gbcCurrent_0.fill = GridBagConstraints.BOTH;
        gbcCurrent_0.weighty=0;
        gbcCurrent_0.weightx = 1;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

    // Row 0 (Labels)
        gbcCurrent_0.gridy = 0;
        gbcCurrent_0.gridx = 0;
        cardCurrent_0.add(new JLabel("Full Name"), gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        cardCurrent_0.add(new JLabel("Father Name"), gbcCurrent_0);

    // Row 1 (Fields)
        gbcCurrent_0.gridy = 1;
        gbcCurrent_0.gridx = 0;
        JTextField Fullname = new JTextField(15);
        cardCurrent_0.add(Fullname, gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        JTextField fatherName = new JTextField(15);
        cardCurrent_0.add(fatherName, gbcCurrent_0);

    // Row 2 (Labels)
        gbcCurrent_0.gridy = 2;
        gbcCurrent_0.gridx = 0;
        cardCurrent_0.add(new JLabel("Mother Name"), gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        cardCurrent_0.add(new JLabel("Date Of Birth(DOB)"), gbcCurrent_0);

    // Row 3 (Fields)
        gbcCurrent_0.gridy = 3;
        gbcCurrent_0.gridx = 0;
        JTextField motherName = new JTextField(15);
        cardCurrent_0.add(motherName, gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        SpinnerDateModel dateModel_1 = new SpinnerDateModel();
        JSpinner dobSpinner_1 = new JSpinner(dateModel_1);
        JSpinner.DateEditor editor_1 = new JSpinner.DateEditor(dobSpinner_1, "yyyy-MM-dd");
        dobSpinner_1.setEditor(editor_1);
        cardCurrent_0.add(dobSpinner_1, gbcCurrent_0);


    // Row 4 (Labels)
        gbcCurrent_0.gridy = 4;
        gbcCurrent_0.gridx = 0;
        cardCurrent_0.add(new JLabel("Gender"), gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        cardCurrent_0.add(new JLabel("Email"), gbcCurrent_0);

    // Row 5 (Fields)
        gbcCurrent_0.gridy = 5;
        gbcCurrent_0.gridx = 0;
        String[] genders = {"Select", "Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setFont(new Font("Monospaced", Font.PLAIN, 18));
        cardCurrent_0.add(genderBox, gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        JTextField Email = new JTextField(15);
        cardCurrent_0.add(Email, gbcCurrent_0);

    // Row 6 (Labels)
        gbcCurrent_0.gridy = 6;
        gbcCurrent_0.gridx = 0;
        cardCurrent_0.add(new JLabel("Mobile Number"), gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        cardCurrent_0.add(new JLabel("Pan Card Number"), gbcCurrent_0);

    // Row 7 (Fields)
        gbcCurrent_0.gridy = 7;
        gbcCurrent_0.gridx = 0;
        JTextField MobileNumber = new JTextField(15);
        cardCurrent_0.add(MobileNumber, gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        JTextField panCard = new JTextField(15);
        cardCurrent_0.add(panCard, gbcCurrent_0);

    // Row 8 (Labels)
        gbcCurrent_0.gridy = 8;
        gbcCurrent_0.gridx = 0;
        cardCurrent_0.add(new JLabel("Aadhaar Card Number"), gbcCurrent_0);

        gbcCurrent_0.gridx = 1;
        cardCurrent_0.add(new JLabel(""), gbcCurrent_0);

    // Row 9 (Field)
        gbcCurrent_0.gridy = 9;
        gbcCurrent_0.gridx = 0;
        JTextField aadhaarCard = new JTextField(15);
        cardCurrent_0.add(aadhaarCard, gbcCurrent_0);

    //=================================================================================================================
        JPanel cardCurrent_1 = new JPanel(new GridBagLayout());
        cardCurrent_1.setOpaque(false);
        TitledBorder border_das_1 = BorderFactory.createTitledBorder("Address");
        border_das_1.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_1.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_1 = new GridBagConstraints();
        gbcCurrent_1.insets = new Insets(10,30,10,30);
        gbcCurrent_1.fill = GridBagConstraints.BOTH;
        gbcCurrent_1.weighty=0;
        gbcCurrent_1.weightx = 1;

        // Row 0 (Labels)
        gbcCurrent_1.gridy = 0;
        gbcCurrent_1.gridx = 0;
        cardCurrent_1.add(new JLabel("Residential Address"), gbcCurrent_1);

        gbcCurrent_1.gridx = 1;
        cardCurrent_1.add(new JLabel("Select State"), gbcCurrent_1);

    // Row 1 (Fields)
        gbcCurrent_1.gridy = 1;
        gbcCurrent_1.gridx = 0;
        JTextArea residentialAddress = new JTextArea(5,15);
        cardCurrent_1.add(residentialAddress, gbcCurrent_1);

        gbcCurrent_1.gridx = 1;
        String[] states_2 = {"Haryana","Uttar Pradesh","Uttarakhand"};
        JComboBox<String> stateBox_2 = new JComboBox<>(states_2);
        cardCurrent_1.add(stateBox_2, gbcCurrent_1);

    // Row 2 (Labels)
        gbcCurrent_1.gridy = 2;
        gbcCurrent_1.gridx = 0;
        cardCurrent_1.add(new JLabel("Select District"), gbcCurrent_1);

        gbcCurrent_1.gridx = 1;
        cardCurrent_1.add(new JLabel("Enter Pin Code"), gbcCurrent_1);

    // Row 3 (Fields)
        gbcCurrent_1.gridy = 3;
        gbcCurrent_1.gridx = 0;
        JComboBox<String> districtBox_2 = new JComboBox<>();
        cardCurrent_1.add(districtBox_2, gbcCurrent_1);

        gbcCurrent_1.gridx = 1;
        JTextField pin = new JTextField(7);
        cardCurrent_1.add(pin, gbcCurrent_1);

    // Data store
        HashMap<String,String[]> map_2 = new HashMap<>();
        map_2.put("Haryana", new String[]{"Gurgaon","Faridabad","Palwal","Sonipat"});
        map_2.put("Uttar Pradesh", new String[]{"Noida","Greater Noida","Meerut","Jewar"});
        map_2.put("Uttarakhand", new String[]{"Dehradun","Haridwar","Rishikesh","Garhwal"});

    // Event
        stateBox_2.addActionListener(_ -> {
            String selectedState = (String) stateBox_2.getSelectedItem();
            districtBox_2.removeAllItems();

            if (map_2.containsKey(selectedState)) {
                for (String d : map_2.get(selectedState)) {
                    districtBox_2.addItem(d);
                }
            }
        });

    //=================================================================================================================
        JPanel cardCurrent_2 = new JPanel(new GridBagLayout());
        cardCurrent_2.setOpaque(false);
        TitledBorder border_das_2 = BorderFactory.createTitledBorder("Business Details");
        border_das_2.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_2.setBorder(BorderFactory.createCompoundBorder(
                border_das_2,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_2 = new GridBagConstraints();
        gbcCurrent_2.insets = new Insets(10,30,10,30);
        gbcCurrent_2.fill = GridBagConstraints.BOTH;
        gbcCurrent_2.weighty=0;
        gbcCurrent_2.weightx = 1;

        // Row 20 (Labels)
        gbcCurrent_2.gridy = 20;
        gbcCurrent_2.gridx = 0;
        cardCurrent_2.add(new JLabel("Business Name"), gbcCurrent_2);

        gbcCurrent_2.gridx = 1;
        cardCurrent_2.add(new JLabel("Business Type"), gbcCurrent_2);

    // Row 21 (Fields)
        gbcCurrent_2.gridy = 21;
        gbcCurrent_2.gridx = 0;
        JTextField businessName = new JTextField(15);
        cardCurrent_2.add(businessName, gbcCurrent_2);

        gbcCurrent_2.gridx = 1;
        JTextField businessType = new JTextField(15);
        cardCurrent_2.add(businessType, gbcCurrent_2);

    // Row 22 (Labels)
        gbcCurrent_2.gridy = 22;
        gbcCurrent_2.gridx = 0;
        cardCurrent_2.add(new JLabel("Date of Establishment"), gbcCurrent_2);

        gbcCurrent_2.gridx = 1;
        cardCurrent_2.add(new JLabel("Business Pan Card Number"), gbcCurrent_2);

    // Row 23 (Fields)
        gbcCurrent_2.gridy = 23;
        gbcCurrent_2.gridx = 0;
        SpinnerDateModel dateModel_3 = new SpinnerDateModel();
        JSpinner dobSpinner_3 = new JSpinner(dateModel_3);
        JSpinner.DateEditor editor_3 = new JSpinner.DateEditor(dobSpinner_3, "yyyy-MM-dd");
        dobSpinner_3.setEditor(editor_3);
        cardCurrent_2.add(dobSpinner_3, gbcCurrent_2);

        gbcCurrent_2.gridx = 1;
        JTextField panCardNumber = new JTextField(15);
        cardCurrent_2.add(panCardNumber, gbcCurrent_2);

    // Row 24 (Labels)
        gbcCurrent_2.gridy = 24;
        gbcCurrent_2.gridx = 0;
        cardCurrent_2.add(new JLabel("Registration Number"), gbcCurrent_2);

        gbcCurrent_2.gridx = 1;
        cardCurrent_2.add(new JLabel(""), gbcCurrent_2);

    // Row 25 (Field)
        gbcCurrent_2.gridy = 25;
        gbcCurrent_2.gridx = 0;
        JTextField registrationNumber = new JTextField(15);
        cardCurrent_2.add(registrationNumber, gbcCurrent_2);
        //==================================================================================================================
        JPanel cardCurrent_3 = new JPanel(new GridBagLayout());
        cardCurrent_3.setOpaque(false);
        TitledBorder border_das_3 = BorderFactory.createTitledBorder("Business Address Details");
        border_das_3.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_3.setBorder(BorderFactory.createCompoundBorder(
                border_das_3,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_3 = new GridBagConstraints();
        gbcCurrent_3.insets = new Insets(10,30,10,30);
        gbcCurrent_3.fill = GridBagConstraints.BOTH;
        gbcCurrent_3.weighty=0;
        gbcCurrent_3.weightx = 1;

        // Row 26 (Labels)
        gbcCurrent_3.gridy = 26;
        gbcCurrent_3.gridx = 0;
        cardCurrent_3.add(new JLabel("Registered Office Address"), gbcCurrent_3);

        gbcCurrent_3.gridx = 1;
        cardCurrent_3.add(new JLabel("Communication Address"), gbcCurrent_3);

    // Row 27 (Fields)
        gbcCurrent_3.gridy = 27;
        gbcCurrent_3.gridx = 0;
        JTextArea registeredAdd = new JTextArea(5,15);
        cardCurrent_3.add(registeredAdd, gbcCurrent_3);

        gbcCurrent_3.gridx = 1;
        JTextArea communicationAddress = new JTextArea(5,15);
        cardCurrent_3.add(communicationAddress, gbcCurrent_3);

    // Row 28 (Labels)
        gbcCurrent_3.gridy = 28;
        gbcCurrent_3.gridx = 0;
        cardCurrent_3.add(new JLabel("Select State"), gbcCurrent_3);

        gbcCurrent_3.gridx = 1;
        cardCurrent_3.add(new JLabel("Select District"), gbcCurrent_3);

    // Row 29 (Fields)
        gbcCurrent_3.gridy = 29;
        gbcCurrent_3.gridx = 0;
        String[] states_4 = {"Haryana","Uttar Pradesh","Uttarakhand"};
        JComboBox<String> stateBox_4 = new JComboBox<>(states_4);
        cardCurrent_3.add(stateBox_4, gbcCurrent_3);

        gbcCurrent_3.gridx = 1;
        JComboBox<String> districtBox_4 = new JComboBox<>();
        cardCurrent_3.add(districtBox_4, gbcCurrent_3);

    // Row 30 (Labels)
        gbcCurrent_3.gridy = 30;
        gbcCurrent_3.gridx = 0;
        cardCurrent_3.add(new JLabel("Enter Pin Code"), gbcCurrent_3);

        gbcCurrent_3.gridx = 1;
        cardCurrent_3.add(new JLabel(""), gbcCurrent_3);

    // Row 31 (Field)
        gbcCurrent_3.gridy = 31;
        gbcCurrent_3.gridx = 0;
        JTextField pinCode_4 = new JTextField(7);
        cardCurrent_3.add(pinCode_4, gbcCurrent_3);

    // Data store
        HashMap<String,String[]> map_4 = new HashMap<>();
        map_4.put("Haryana", new String[]{"Gurgaon","Faridabad","Palwal","Sonipat"});
        map_4.put("Uttar Pradesh", new String[]{"Noida","Greater Noida","Meerut","Jewar"});
        map_4.put("Uttarakhand", new String[]{"Dehradun","Haridwar","Rishikesh","Garhwal"});

    // Event
        stateBox_4.addActionListener(_ -> {
            String selectedState = (String) stateBox_4.getSelectedItem();
            districtBox_4.removeAllItems();

            if (map_4.containsKey(selectedState)) {
                for (String d : map_4.get(selectedState)) {
                    districtBox_4.addItem(d);
                }
            }
        });
    //=================================================================================================================
        JPanel cardCurrent_4 = new JPanel(new GridBagLayout());
        cardCurrent_4.setOpaque(false);
        TitledBorder border_das_4 = BorderFactory.createTitledBorder("Upload Documents");
        border_das_4.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_4.setBorder(BorderFactory.createCompoundBorder(
                border_das_4,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_4 = new GridBagConstraints();
        gbcCurrent_4.insets = new Insets(10,30,10,30);
        gbcCurrent_4.fill = GridBagConstraints.BOTH;
        gbcCurrent_4.weighty=0;
        gbcCurrent_4.weightx = 1;

    // Row 32 (Labels)
        gbcCurrent_4.gridy = 32;
        gbcCurrent_4.gridx = 0;
        cardCurrent_4.add(new JLabel("Aadhaar Card"), gbcCurrent_4);

        gbcCurrent_4.gridx = 1;
        cardCurrent_4.add(new JLabel("PAN Card"), gbcCurrent_4);

    // Row 33 (Fields)
        gbcCurrent_4.gridy = 33;
        gbcCurrent_4.gridx = 0;

        JPanel aadhaarPanel = new JPanel();
        aadhaarPanel.setOpaque(false);

        JButton uploadAadhaar = new JButton("Upload");
        JLabel imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(100,100));
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        aadhaarPanel.add(uploadAadhaar);
        aadhaarPanel.add(imagePreview);
        cardCurrent_4.add(aadhaarPanel, gbcCurrent_4);
        gbcCurrent_4.gridx = 1;
        JPanel panPanel = new JPanel();
        panPanel.setOpaque(false);

        JButton uploadPan = new JButton("Upload");
        JLabel imagePreview_1 = new JLabel();
        imagePreview_1.setPreferredSize(new Dimension(100,100));
        imagePreview_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panPanel.add(uploadPan);
        panPanel.add(imagePreview_1);

        cardCurrent_4.add(panPanel, gbcCurrent_4);

    // Row 34 (Labels)
        gbcCurrent_4.gridy = 34;
        gbcCurrent_4.gridx = 0;
        cardCurrent_4.add(new JLabel("Photo"), gbcCurrent_4);

        gbcCurrent_4.gridx = 1;
        cardCurrent_4.add(new JLabel("Signature"), gbcCurrent_4);

    // Row 35 (Fields)
        gbcCurrent_4.gridy = 35;
        gbcCurrent_4.gridx = 0;

        JPanel photoPanel = new JPanel();
        photoPanel.setOpaque(false);

        JButton uploadPhoto = new JButton("Upload");
        JLabel imagePreview_3 = new JLabel();
        imagePreview_3.setPreferredSize(new Dimension(100,100));
        imagePreview_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        photoPanel.add(uploadPhoto);
        photoPanel.add(imagePreview_3);

        cardCurrent_4.add(photoPanel, gbcCurrent_4);

        gbcCurrent_4.gridx = 1;

        JPanel signPanel = new JPanel();
        signPanel.setOpaque(false);

        JButton uploadSignature = new JButton("Upload");
        JLabel imagePreview_4 = new JLabel();
        imagePreview_4.setPreferredSize(new Dimension(100,100));
        imagePreview_4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        signPanel.add(uploadSignature);
        signPanel.add(imagePreview_4);

        cardCurrent_4.add(signPanel, gbcCurrent_4);

    // Actions (same logic)
        uploadAadhaar.addActionListener(_ -> aadhaarcardFile = fileChooser.ChooseImage(imagePreview));
        uploadPan.addActionListener(_ -> pancardFile = fileChooser.ChooseImage(imagePreview_1));
        uploadPhoto.addActionListener(_ -> photoFile = fileChooser.ChooseImage(imagePreview_3));
        uploadSignature.addActionListener(_ -> signFile = fileChooser.ChooseImage(imagePreview_4));

    //=================================================================================================================
        JPanel cardCurrent_5 = new JPanel(new GridBagLayout());
        cardCurrent_5.setOpaque(false);
        TitledBorder border_das_5 = BorderFactory.createTitledBorder("Final Submission");
        border_das_5.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardCurrent_5.setBorder(BorderFactory.createCompoundBorder(
                border_das_5,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcCurrent_5 = new GridBagConstraints();
        gbcCurrent_5.insets = new Insets(10,30,10,30);
        gbcCurrent_5.fill = GridBagConstraints.BOTH;
        gbcCurrent_5.weighty=0;
        gbcCurrent_5.weightx = 1;

    // Row 39 (Labels)
        gbcCurrent_5.gridy = 39;
        gbcCurrent_5.gridx = 0;
        cardCurrent_5.add(new JLabel("Place"), gbcCurrent_5);

        gbcCurrent_5.gridx = 1;
        cardCurrent_5.add(new JLabel(""), gbcCurrent_5); // empty

    // Row 40 (Field)
        gbcCurrent_5.gridy = 40;
        gbcCurrent_5.gridx = 0;
        JTextField place_6 = new JTextField(15);
        cardCurrent_5.add(place_6, gbcCurrent_5);

    // Row 41 (Buttons)
        gbcCurrent_5.gridy = 41;
        gbcCurrent_5.gridx = 0;
        gbcCurrent_5.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton backButton_6 = new JButton("Previous");
        backButton_6.setPreferredSize(new Dimension(240,40));
        backButton_6.addActionListener(_ -> cardLayout.show(cardPanel, "OPEN ACCOUNT"));

        JButton submitJButton = new JButton("SUBMIT");
        submitJButton.setPreferredSize(new Dimension(240,40));
        submitJButton.setFocusable(false);
        submitJButton.addActionListener(_ -> {

            //----------------------------------------------------Collect All Fields----------------------------------------
            String fullName = Fullname.getText().trim();
            String FatherName = fatherName.getText().trim();
            String MotherName = motherName.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            java.util.Date utilDate_1 = (java.util.Date) dobSpinner_1.getValue();
            java.sql.Date sqlDob_1 = new java.sql.Date(utilDate_1.getTime());
            String email = Email.getText().trim();
            String mobile = MobileNumber.getText();
            String residential = residentialAddress.getText().trim();
            String state_1 = (String) stateBox_2.getSelectedItem();
            String district_1 = (String) districtBox_2.getSelectedItem();
            String pinCode_1 = pin.getText().trim();
            String aadhaar = aadhaarCard.getText().trim();
            String pan = panCardNumber.getText().trim();
    //----------    --------------------------------------------------------Business Details---------------------------------------------
            String business = businessName.getText().trim();
            String business_type = businessType.getText().trim();

            java.util.Date utilDate_2 = (java.util.Date) dobSpinner_3.getValue();
            java.sql.Date sqlDob_2 = new java.sql.Date(utilDate_2.getTime());

            String business_pan = panCardNumber.getText().trim();
            String registration = registrationNumber.getText().trim();
            String registered = registeredAdd.getText().trim();
            String communication = communicationAddress.getText().trim();
            String state_2 = (String) stateBox_4.getSelectedItem();
            String district_2 = (String) districtBox_4.getSelectedItem();
            String pinCode_2 = pinCode_4.getText().trim();
            String place_4 = place_6.getText().trim();
            //------------------------------------------------------Validation-----------------------------------------------
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
            if (residentialAddress.getText().trim().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Please Fill address Fields");
                return;
            }
            if (stateBox_2.getSelectedItem() == null ||
                    districtBox_2.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,"Please select state and District");
                return;
            }
            if (!pin.getText().matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Pin Code ");
                return;
            }
            if (!aadhaarCard.getText().matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Aadhaar Number");
                return;
            }
            if (!panCard.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Pan Number");
                return;
            }

            if (businessName.getText().isEmpty() || businessType.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please Fill all Fields");
                return;
            }
            if (!panCardNumber.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Pan Number");
                return;
            }
            if (!registrationNumber.getText().matches("[L,U]{1}[0-9]{5}[A-Z]{2}[0-9]{4}[A-Z]{3}[0-9]{6}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Registration Number");
                return;
            }
            if(registeredAdd.getText().isEmpty() ||communicationAddress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Address");
                return;
            }
            if (stateBox_4.getSelectedItem() == null ||
                    districtBox_4.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,"Please select state and District");
                return;
            }
            if (!pinCode_4.getText().matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null,"Please Enter Valid Pin Code ");
                return;
            }
            if (aadhaarcardFile == null || pancardFile == null || photoFile == null || signFile == null) {
                JOptionPane.showMessageDialog(null, "Please upload all Required Documents");
                return;
            }

            //------------------------------------------------------JDBC INSERT----------------------------------------------------------
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

                String sql = "INSERT INTO users(full_name,father_name,mother_name,gender,dob,email,mobile," +
                        "place) VALUES(?,?,?,?,?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,fullName);
                ps.setString(2,FatherName);
                ps.setString(3,MotherName);
                ps.setString(4,gender);
                ps.setDate(5,sqlDob_1);
                ps.setString(6,email);
                ps.setString(7,mobile);
                ps.setString(8,place_4);

                ps.executeUpdate();
                ResultSet result = ps.getGeneratedKeys();
                int userID = 0;
                if (result.next()) {
                    userID = result.getInt(1);
                }

                String addressSql = "INSERT INTO addresses(user_id,type,address,state,district,pin_code) VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedAddress = conn.prepareStatement(addressSql);

                preparedAddress.setInt(1,userID);
                preparedAddress.setString(2,"PERMANENT");
                preparedAddress.setString(3,residential);
                preparedAddress.setString(4,state_1);
                preparedAddress.setString(5,district_1);
                preparedAddress.setString(6,pinCode_1);
                preparedAddress.executeUpdate();

                String accountSql = "INSERT INTO accounts(user_id,account_type,branch_name,account_number,CRN,status) VALUES(?,?,?,?,?,?)";
                PreparedStatement prepared = conn.prepareStatement(accountSql, Statement.RETURN_GENERATED_KEYS);

                prepared.setInt(1,userID);
                prepared.setString(2,"CURRENT");
                prepared.setString(3,"Main Branch");
                prepared.setString(4,newAcc);
                prepared.setString(5,crn);
                prepared.setString(6,"ACTIVE");

                prepared.executeUpdate();

                ResultSet resultAccount = prepared.getGeneratedKeys();
                int account_id = 0;
                if (resultAccount.next()) {
                    account_id = resultAccount.getInt(1);
                }

                String currentSql = "INSERT INTO current_account_details(account_id,permanent_address,communication_address,state," +
                        "district,pin_code,business_name,business_type," +
                        "business_establishment_date,business_pan_number,business_registration_number) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statementCurrent = conn.prepareStatement(currentSql);

                statementCurrent.setInt(1,account_id);
                statementCurrent.setString(2,registered);
                statementCurrent.setString(3,communication);
                statementCurrent.setString(4,state_2);
                statementCurrent.setString(5,district_2);
                statementCurrent.setString(6,pinCode_2);
                statementCurrent.setString(7,business);
                statementCurrent.setString(8,business_type);
                statementCurrent.setDate(9,sqlDob_2);
                statementCurrent.setString(10,business_pan);
                statementCurrent.setString(11,registration);

                statementCurrent.executeUpdate();
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
                                "Account Type: CURRENT\n" +
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR " + ex.getMessage());
                throw new RuntimeException(ex);
            }
            Fullname.requestFocus();
        });
        buttonPanel.add(backButton_6);
        buttonPanel.add(submitJButton);

        cardCurrent_5.add(buttonPanel, gbcCurrent_5);
        applyFont(cardCurrent_0, labelFont, fieldFont);
        applyFont(cardCurrent_1, labelFont, fieldFont);
        applyFont(cardCurrent_2, labelFont, fieldFont);
        applyFont(cardCurrent_3, labelFont, fieldFont);
        applyFont(cardCurrent_4, labelFont, fieldFont);
        applyFont(cardCurrent_5, labelFont, fieldFont);

        scrollPanel.add(cardCurrent_0);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardCurrent_1);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardCurrent_2);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardCurrent_3);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardCurrent_4);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardCurrent_5);

        JScrollPane scroll = new JScrollPane(scrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scroll,BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.9;
        currentAccount.add(middlePanel,gbc);
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
        currentAccount.add(bottomPanel,gbc);
        add(currentAccount, BorderLayout.CENTER);
    }
}
