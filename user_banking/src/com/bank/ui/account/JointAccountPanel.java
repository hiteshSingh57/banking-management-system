package com.bank.ui.account;
import com.bank.ui.components.FileChooser;
import com.bank.util.CRNGenerator;
import com.bank.config.DBConnection;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;

public class JointAccountPanel extends JPanel {
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

    public JointAccountPanel(CardLayout cardLayout,JPanel cardPanel) {
        setLayout(new BorderLayout());

        JPanel jointAccount = new JPanel(new GridBagLayout());
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
                new ImageIcon(getClass().getResource("/image/joint.png"))
                        .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
        );
        JLabel logo_2 = new JLabel(logIcon2);
        logo_2.setBorder(BorderFactory.createEmptyBorder(10,20,5,10));
        rightPanel.add(logo_2,BorderLayout.EAST);

        JLabel label_2 = new JLabel("Joint Account");
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
        jointAccount.add(topJPanel,gbc);

        //-------------------------------------------------------------------------------------------------------------------------------
        JPanel middlePanel = new JPanel(new BorderLayout());

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setBackground(new Color(210, 229, 244));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));

        JPanel cardJoint_0 = new JPanel(new GridBagLayout());
        cardJoint_0.setOpaque(false);
        TitledBorder border_das = BorderFactory.createTitledBorder("First Person Details");
        border_das.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_0.setBorder(BorderFactory.createCompoundBorder(
                border_das,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_0 = new GridBagConstraints();
        gbcJoint_0.insets = new Insets(10,20,10,20);
        gbcJoint_0.fill = GridBagConstraints.BOTH;
        gbcJoint_0.weighty=0;
        gbcJoint_0.weightx = 1;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);

    // Row 0 (Labels)
        gbcJoint_0.gridy = 0;
        gbcJoint_0.gridx = 0;
        cardJoint_0.add(new JLabel("Full Name"), gbcJoint_0);

        gbcJoint_0.gridx = 1;
        cardJoint_0.add(new JLabel("Father Name"), gbcJoint_0);

    // Row 1 (Fields)
        gbcJoint_0.gridy = 1;
        gbcJoint_0.gridx = 0;
        JTextField Fullname_1 = new JTextField(15);
        cardJoint_0.add(Fullname_1, gbcJoint_0);

        gbcJoint_0.gridx = 1;
        JTextField fatherName_1 = new JTextField(15);
        cardJoint_0.add(fatherName_1, gbcJoint_0);

    // Row 2 (Labels)
        gbcJoint_0.gridy = 2;
        gbcJoint_0.gridx = 0;
        cardJoint_0.add(new JLabel("Mother Name"), gbcJoint_0);

        gbcJoint_0.gridx = 1;
        cardJoint_0.add(new JLabel("Gender"), gbcJoint_0);

    // Row 3 (Fields)
        gbcJoint_0.gridy = 3;
        gbcJoint_0.gridx = 0;
        JTextField motherName_1 = new JTextField(15);
        cardJoint_0.add(motherName_1, gbcJoint_0);

        gbcJoint_0.gridx = 1;

        JPanel genderPanel = new JPanel();
        String[] genders = {"Select", "Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setFont(new Font("Monospaced", Font.PLAIN, 18));
        cardJoint_0.add(genderBox, gbcJoint_0);

    // Row 4 (Labels)
        gbcJoint_0.gridy = 4;
        gbcJoint_0.gridx = 0;
        cardJoint_0.add(new JLabel("Date Of Birth(DOB)"), gbcJoint_0);

        gbcJoint_0.gridx = 1;
        cardJoint_0.add(new JLabel("Email"), gbcJoint_0);

    // Row 5 (Fields)
        gbcJoint_0.gridy = 5;
        gbcJoint_0.gridx = 0;
        SpinnerDateModel dateModel_1 = new SpinnerDateModel();
        JSpinner dobSpinner_1 = new JSpinner(dateModel_1);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dobSpinner_1, "yyyy-MM-dd");
        dobSpinner_1.setEditor(editor);
        cardJoint_0.add(dobSpinner_1, gbcJoint_0);

        gbcJoint_0.gridx = 1;
        JTextField Email_1 = new JTextField(15);
        cardJoint_0.add(Email_1, gbcJoint_0);

    // Row 6 (Labels)
        gbcJoint_0.gridy = 6;
        gbcJoint_0.gridx = 0;
        cardJoint_0.add(new JLabel("Mobile Number"), gbcJoint_0);

        gbcJoint_0.gridx = 1;
        cardJoint_0.add(new JLabel(""), gbcJoint_0);

    // Row 7 (Field)
        gbcJoint_0.gridy = 7;
        gbcJoint_0.gridx = 0;
        JTextField MobileNumber_1 = new JTextField(15);
        cardJoint_0.add(MobileNumber_1, gbcJoint_0);
        //==================================================================================================================
        JPanel cardJoint_1 = new JPanel(new GridBagLayout());
        cardJoint_1.setOpaque(false);
        TitledBorder border_das_1 = BorderFactory.createTitledBorder("Address Details");
        border_das_1.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_1.setBorder(BorderFactory.createCompoundBorder(
                border_das_1,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_1 = new GridBagConstraints();
        gbcJoint_1.insets = new Insets(10,20,10,20);
        gbcJoint_1.fill = GridBagConstraints.BOTH;
        gbcJoint_1.weighty=0;
        gbcJoint_1.weightx = 1;

        // Row 0 (Labels)
        gbcJoint_1.gridy = 0;
        gbcJoint_1.gridx = 0;
        cardJoint_1.add(new JLabel("Permanent Address"), gbcJoint_1);

        gbcJoint_1.gridx = 1;
        cardJoint_1.add(new JLabel("Communication Address"), gbcJoint_1);

    // Row 1 (Fields)
        gbcJoint_1.gridy = 1;
        gbcJoint_1.gridx = 0;
        JTextArea permanentAddress_1 = new JTextArea(5,15);
        cardJoint_1.add(permanentAddress_1, gbcJoint_1);

        gbcJoint_1.gridx = 1;
        JTextArea communicationAddress_1 = new JTextArea(5,15);
        cardJoint_1.add(communicationAddress_1, gbcJoint_1);

    // Row 2 (Labels)
        gbcJoint_1.gridy = 2;
        gbcJoint_1.gridx = 0;
        cardJoint_1.add(new JLabel("Select State"), gbcJoint_1);

        gbcJoint_1.gridx = 1;
        cardJoint_1.add(new JLabel("Select District"), gbcJoint_1);

    // Row 3 (Fields)
        gbcJoint_1.gridy = 3;
        gbcJoint_1.gridx = 0;
        String[] states_1 = {"Haryana","Uttar Pradesh","Uttarakhand"};
        JComboBox<String> stateBox_1 = new JComboBox<>(states_1);
        cardJoint_1.add(stateBox_1, gbcJoint_1);

        gbcJoint_1.gridx = 1;
        JComboBox<String> districtBox_1 = new JComboBox<>();
        cardJoint_1.add(districtBox_1, gbcJoint_1);

    // Row 4 (Labels)
        gbcJoint_1.gridy = 4;
        gbcJoint_1.gridx = 0;
        cardJoint_1.add(new JLabel("Enter Pin Code"), gbcJoint_1);

        gbcJoint_1.gridx = 1;
        cardJoint_1.add(new JLabel("Enter Aadhaar Card Number"), gbcJoint_1);

    // Row 5 (Fields)
        gbcJoint_1.gridy = 5;
        gbcJoint_1.gridx = 0;
        JTextField pin_1 = new JTextField(7);
        cardJoint_1.add(pin_1, gbcJoint_1);

        gbcJoint_1.gridx = 1;
        JTextField aadhaarNumber_1 = new JTextField(15);
        cardJoint_1.add(aadhaarNumber_1, gbcJoint_1);

    // Row 6 (Labels)
        gbcJoint_1.gridy = 6;
        gbcJoint_1.gridx = 0;
        cardJoint_1.add(new JLabel("Enter Pan Number"), gbcJoint_1);

        gbcJoint_1.gridx = 1;
        cardJoint_1.add(new JLabel(""), gbcJoint_1);

    // Row 7 (Field)
        gbcJoint_1.gridy = 7;
        gbcJoint_1.gridx = 0;
        JTextField panNumber_1 = new JTextField(15);
        cardJoint_1.add(panNumber_1, gbcJoint_1);

    // Data store
        HashMap<String,String[]> map = new HashMap<>();
        map.put("Haryana", new String[]{"Gurgaon","Faridabad","Palwal","Sonipat"});
        map.put("Uttar Pradesh", new String[]{"Noida","Greater Noida","Meerut","Jewar"});
        map.put("Uttarakhand", new String[]{"Dehradun","Haridwar","Rishikesh","Garhwal"});

    // Event
        stateBox_1.addActionListener(_ -> {
            String selectedState = (String) stateBox_1.getSelectedItem();
            districtBox_1.removeAllItems();

            if (map.containsKey(selectedState)) {
                for (String d : map.get(selectedState)) {
                    districtBox_1.addItem(d);
                }
            }
        });
        //==================================================================================================================
        JPanel cardJoint_2 = new JPanel(new GridBagLayout());
        cardJoint_2.setOpaque(false);
        TitledBorder border_das_2 = BorderFactory.createTitledBorder("Second Person Details");
        border_das_2.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_2.setBorder(BorderFactory.createCompoundBorder(
                border_das_2,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_2 = new GridBagConstraints();
        gbcJoint_2.insets = new Insets(10,20,10,20);
        gbcJoint_2.fill = GridBagConstraints.BOTH;
        gbcJoint_2.weighty=0;
        gbcJoint_2.weightx = 1;

        // Row 0 (Labels)
        gbcJoint_2.gridy = 0;
        gbcJoint_2.gridx = 0;
        cardJoint_2.add(new JLabel("Relation With First Person"), gbcJoint_2);

        gbcJoint_2.gridx = 1;
        cardJoint_2.add(new JLabel("Full Name"), gbcJoint_2);

    // Row 1 (Fields)
        gbcJoint_2.gridy = 1;
        gbcJoint_2.gridx = 0;
        JTextField realtion = new JTextField(15);
        cardJoint_2.add(realtion, gbcJoint_2);

        gbcJoint_2.gridx = 1;
        JTextField Fullname_2 = new JTextField(15);
        cardJoint_2.add(Fullname_2, gbcJoint_2);

    // Row 2 (Labels)
        gbcJoint_2.gridy = 2;
        gbcJoint_2.gridx = 0;
        cardJoint_2.add(new JLabel("Father Name"), gbcJoint_2);

        gbcJoint_2.gridx = 1;
        cardJoint_2.add(new JLabel("Mother Name"), gbcJoint_2);

    // Row 3 (Fields)
        gbcJoint_2.gridy = 3;
        gbcJoint_2.gridx = 0;
        JTextField fatherName_2 = new JTextField(15);
        cardJoint_2.add(fatherName_2, gbcJoint_2);

        gbcJoint_2.gridx = 1;
        JTextField motherName_2 = new JTextField(15);
        cardJoint_2.add(motherName_2, gbcJoint_2);

    // Row 4 (Labels)
        gbcJoint_2.gridy = 4;
        gbcJoint_2.gridx = 0;
        cardJoint_2.add(new JLabel("Gender"), gbcJoint_2);

        gbcJoint_2.gridx = 1;
        cardJoint_2.add(new JLabel("Date Of Birth(DOB)"), gbcJoint_2);

    // Row 5 (Fields)
        gbcJoint_2.gridy = 5;
        gbcJoint_2.gridx = 0;

        JPanel genderPanel_1 = new JPanel();
        JRadioButton gender_1_2 = new JRadioButton("Male");
        JRadioButton gender_2_2 = new JRadioButton("Female");
        JRadioButton gender_3_2 = new JRadioButton("Other");

        ButtonGroup buttonGroup_2 = new ButtonGroup();
        buttonGroup_2.add(gender_1_2);
        buttonGroup_2.add(gender_2_2);
        buttonGroup_2.add(gender_3_2);

        genderPanel_1.add(gender_1_2);
        genderPanel_1.add(gender_2_2);
        genderPanel_1.add(gender_3_2);
        cardJoint_2.add(genderPanel, gbcJoint_2);

        gbcJoint_2.gridx = 1;
        SpinnerDateModel dateModel_2 = new SpinnerDateModel();
        JSpinner dobSpinner_2 = new JSpinner(dateModel_2);
        JSpinner.DateEditor editor_2 = new JSpinner.DateEditor(dobSpinner_2, "yyyy-MM-dd");
        dobSpinner_2.setEditor(editor_2);
        cardJoint_2.add(dobSpinner_2, gbcJoint_2);

    // Row 6 (Labels)
        gbcJoint_2.gridy = 6;
        gbcJoint_2.gridx = 0;
        cardJoint_2.add(new JLabel("Email"), gbcJoint_2);

        gbcJoint_2.gridx = 1;
        cardJoint_2.add(new JLabel("Mobile Number"), gbcJoint_2);

    // Row 7 (Fields)
        gbcJoint_2.gridy = 7;
        gbcJoint_2.gridx = 0;
        JTextField Email_2 = new JTextField(15);
        cardJoint_2.add(Email_2, gbcJoint_2);

        gbcJoint_2.gridx = 1;
        JTextField MobileNumber_2 = new JTextField(15);
        cardJoint_2.add(MobileNumber_2, gbcJoint_2);

        //==================================================================================================================
        JPanel cardJoint_3 = new JPanel(new GridBagLayout());
        cardJoint_3.setOpaque(false);
        TitledBorder border_das_3 = BorderFactory.createTitledBorder("Address Details");
        border_das_3.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_3.setBorder(BorderFactory.createCompoundBorder(
                border_das_3,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_3 = new GridBagConstraints();
        gbcJoint_3.insets = new Insets(10,20,10,20);
        gbcJoint_3.fill = GridBagConstraints.BOTH;
        gbcJoint_3.weighty=0;
        gbcJoint_3.weightx = 1;

        // Row 0 (Labels)
        gbcJoint_3.gridy = 0;
        gbcJoint_3.gridx = 0;
        cardJoint_3.add(new JLabel("Permanent Address"), gbcJoint_3);

        gbcJoint_3.gridx = 1;
        cardJoint_3.add(new JLabel("Communication Address"), gbcJoint_3);

    // Row 1 (Fields)
        gbcJoint_3.gridy = 1;
        gbcJoint_3.gridx = 0;
        JTextArea permanentAddress_2 = new JTextArea(5,15);
        cardJoint_3.add(permanentAddress_2, gbcJoint_3);

        gbcJoint_3.gridx = 1;
        JTextArea communicationAddress_2 = new JTextArea(5,15);
        cardJoint_3.add(communicationAddress_2, gbcJoint_3);

    // Row 2 (Labels)
        gbcJoint_3.gridy = 2;
        gbcJoint_3.gridx = 0;
        cardJoint_3.add(new JLabel("Select State"), gbcJoint_3);

        gbcJoint_3.gridx = 1;
        cardJoint_3.add(new JLabel("Select District"), gbcJoint_3);

    // Row 3 (Fields)
        gbcJoint_3.gridy = 3;
        gbcJoint_3.gridx = 0;
        String[] states_2 = {"Haryana","Uttar Pradesh","Uttarakhand"};
        JComboBox<String> stateBox_2 = new JComboBox<>(states_2);
        cardJoint_3.add(stateBox_2, gbcJoint_3);

        gbcJoint_3.gridx = 1;
        JComboBox<String> districtBox_2 = new JComboBox<>();
        cardJoint_3.add(districtBox_2, gbcJoint_3);


    // Row 4 (Labels)
        gbcJoint_3.gridy = 4;
        gbcJoint_3.gridx = 0;
        cardJoint_3.add(new JLabel("Enter Pin Code"), gbcJoint_3);

        gbcJoint_3.gridx = 1;
        cardJoint_3.add(new JLabel("Enter Aadhaar Card Number"), gbcJoint_3);

    // Row 5 (Fields)
        gbcJoint_3.gridy = 5;
        gbcJoint_3.gridx = 0;
        JTextField pin_2 = new JTextField(7);
        cardJoint_3.add(pin_2, gbcJoint_3);

        gbcJoint_3.gridx = 1;
        JTextField aadhaarNumber_2 = new JTextField(15);
        cardJoint_3.add(aadhaarNumber_2, gbcJoint_3);


    // Row 6 (Labels)
        gbcJoint_3.gridy = 6;
        gbcJoint_3.gridx = 0;
        cardJoint_3.add(new JLabel("Enter Pan Number"), gbcJoint_3);

        gbcJoint_3.gridx = 1;
        cardJoint_3.add(new JLabel(""), gbcJoint_3);

    // Row 7 (Field)
        gbcJoint_3.gridy = 7;
        gbcJoint_3.gridx = 0;
        JTextField panNumber_2 = new JTextField(15);
        cardJoint_3.add(panNumber_2, gbcJoint_3);


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
        //==================================================================================================================
        JPanel cardJoint_4 = new JPanel(new GridBagLayout());
        cardJoint_4.setOpaque(false);
        TitledBorder border_das_4 = BorderFactory.createTitledBorder("Upload Documents ");
        border_das_4.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_4.setBorder(BorderFactory.createCompoundBorder(
                border_das_4,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_4 = new GridBagConstraints();
        gbcJoint_4.insets = new Insets(10,20,10,20);
        gbcJoint_4.fill = GridBagConstraints.BOTH;
        gbcJoint_4.weighty=0;
        gbcJoint_4.weightx = 1;

    // Row 0 (Labels)
        gbcJoint_4.gridy = 0;
        gbcJoint_4.gridx = 0;
        cardJoint_4.add(new JLabel("Aadhaar Card"), gbcJoint_4);

        gbcJoint_4.gridx = 1;
        cardJoint_4.add(new JLabel("PAN Card"), gbcJoint_4);


    // Row 1 (Fields)
        gbcJoint_4.gridy = 1;
        gbcJoint_4.gridx = 0;

        JPanel aadhaarPanel = new JPanel();
        aadhaarPanel.setOpaque(false);

        JButton uploadAadhaar = new JButton("Upload");
        JLabel imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(100,50));
        imagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aadhaarPanel.add(uploadAadhaar);
        aadhaarPanel.add(imagePreview);
        cardJoint_4.add(aadhaarPanel, gbcJoint_4);
        gbcJoint_4.gridx = 1;

        JPanel panPanel = new JPanel();
        panPanel.setOpaque(false);

        JButton uploadPan = new JButton("Upload");
        JLabel imagePreview_1 = new JLabel();
        imagePreview_1.setPreferredSize(new Dimension(100,50));
        imagePreview_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panPanel.add(uploadPan);
        panPanel.add(imagePreview_1);
        cardJoint_4.add(panPanel, gbcJoint_4);

    // Row 2 (Labels)
        gbcJoint_4.gridy = 2;
        gbcJoint_4.gridx = 0;
        cardJoint_4.add(new JLabel("Photo"), gbcJoint_4);

        gbcJoint_4.gridx = 1;
        cardJoint_4.add(new JLabel("Signature"), gbcJoint_4);

    // Row 3 (Fields)
        gbcJoint_4.gridy = 3;
        gbcJoint_4.gridx = 0;

        JPanel photoPanel = new JPanel();
        photoPanel.setOpaque(false);

        JButton uploadPhoto = new JButton("Upload");
        uploadPhoto.setOpaque(false);

        JLabel imagePreview_3 = new JLabel();
        imagePreview_3.setPreferredSize(new Dimension(100,50));
        imagePreview_3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        photoPanel.add(uploadPhoto);
        photoPanel.add(imagePreview_3);

        cardJoint_4.add(photoPanel, gbcJoint_4);

        gbcJoint_4.gridx = 1;

        JPanel signPanel = new JPanel();
        signPanel.setOpaque(false);

        JButton uploadSignature = new JButton("Upload");
        JLabel imagePreview_4 = new JLabel();
        imagePreview_4.setPreferredSize(new Dimension(100,50));
        imagePreview_4.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        signPanel.add(uploadSignature);
        signPanel.add(imagePreview_4);

        cardJoint_4.add(signPanel, gbcJoint_4);

    // Actions (same logic)
        uploadAadhaar.addActionListener(_ -> aadhaarcardFile = fileChooser.ChooseImage(imagePreview));
        uploadPan.addActionListener(_ -> pancardFile = fileChooser.ChooseImage(imagePreview_1));
        uploadPhoto.addActionListener(_ -> photoFile = fileChooser.ChooseImage(imagePreview_3));
        uploadSignature.addActionListener(_ -> signFile = fileChooser.ChooseImage(imagePreview_4));
        //==================================================================================================================
        JPanel cardJoint_5 = new JPanel(new GridBagLayout());
        cardJoint_5.setOpaque(false);
        TitledBorder border_das_5 = BorderFactory.createTitledBorder("Final Submission");
        border_das_5.setTitleFont(new Font("Monospaced",Font.PLAIN,24));
        cardJoint_5.setBorder(BorderFactory.createCompoundBorder(
                border_das_5,
                BorderFactory.createEmptyBorder(20,30,20,30)));

        GridBagConstraints gbcJoint_5 = new GridBagConstraints();
        gbcJoint_5.insets = new Insets(10,20,10,20);
        gbcJoint_5.fill = GridBagConstraints.BOTH;
        gbcJoint_5.weighty=0;
        gbcJoint_5.weightx = 1;

        // Row 1 (Labels)
        gbcJoint_5.gridy = 1;
        gbcJoint_5.gridx = 0;
        cardJoint_5.add(new JLabel("Place"), gbcJoint_5);

        gbcJoint_5.gridx = 1;
        cardJoint_5.add(new JLabel(""), gbcJoint_5);


    // Row 2 (Field)
        gbcJoint_5.gridy = 2;
        gbcJoint_5.gridx = 0;
        JTextField Place = new JTextField(15);
        cardJoint_5.add(Place, gbcJoint_5);

    // Row 3 (Buttons)
        gbcJoint_5.gridy = 3;
        gbcJoint_5.gridx = 0;
        gbcJoint_5.gridwidth = 2;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton backButton_6 = new JButton("Previous");
        backButton_6.setPreferredSize(new Dimension(240,40));
        backButton_6.addActionListener(_ -> cardLayout.show(cardPanel, "OPEN ACCOUNT"));

        JButton submitJButton = new JButton("SUBMIT");
        submitJButton.setPreferredSize(new Dimension(240,40));
        submitJButton.setFocusable(false);
        submitJButton.addActionListener(_ -> {
                    //----------------------------------------------------------First person------------------------------------------
                    String fullName = Fullname_1.getText().trim();
                    String FatherName = fatherName_1.getText().trim();
                    String MotherName = motherName_1.getText().trim();
                    String gender = (String) genderBox.getSelectedItem();
                    Date utilDate_1 = (Date) dobSpinner_1.getValue();
                    java.sql.Date sqlDob_1 = new java.sql.Date(utilDate_1.getTime());
                    String email = Email_1.getText().trim();
                    String mobile = MobileNumber_1.getText();
                    String permanent = permanentAddress_1.getText().trim();
                    String communication = communicationAddress_1.getText().trim();
                    String state_1 = (String) stateBox_1.getSelectedItem();
                    String district_1 = (String) districtBox_1.getSelectedItem();
                    String pinCode_1 = pin_1.getText().trim();
                    String aadhaar = aadhaarNumber_1.getText().trim();
                    String pan = panNumber_1.getText().trim();
                    //------------------------------------------------------------Second Person----------------------------
                    String Relation = realtion.getText().trim();
                    String fullName_2 = Fullname_2.getText().trim();
                    String FatherName_2 = fatherName_2.getText().trim();
                    String MotherName_2 = motherName_2.getText().trim();
                    String Gender= "";
                    if (gender_1_2.isSelected()) Gender = "Male";
                    else if (gender_2_2.isSelected()) Gender = "Female";
                    else if (gender_3_2.isSelected()) Gender = "Other";

                    Date utilDate_2 = (Date) dobSpinner_2.getValue();
                    java.sql.Date sqlDob_2 = new java.sql.Date(utilDate_2.getTime());
                    String email_2 = Email_2.getText().trim();
                    String mobile_2 = MobileNumber_2.getText();
                    String permanent_2 = permanentAddress_2.getText().trim();
                    String communication_2 = communicationAddress_2.getText().trim();
                    String state_2 = (String) stateBox_2.getSelectedItem();
                    String district_2 = (String) districtBox_2.getSelectedItem();
                    String pinCode_2 = pin_2.getText().trim();
                    String aadhaar_2 = aadhaarNumber_2.getText().trim();
                    String pan_2 = panNumber_2.getText().trim();

                    String place = Place.getText().trim();

                    //--------------------------------------------------------VALIDATION-------------------------------------------------
                    if (Fullname_1.getText().trim().isEmpty() ||
                            fatherName_1.getText().trim().isEmpty() ||
                            motherName_1.getText().trim().isEmpty() ||
                            Email_1.getText().trim().isEmpty() ||
                            MobileNumber_1.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(cardJoint_5, "Please Fill all Fields");
                        return;
                    }
                    if (!Email_1.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please Enter Valid Email");
                        return;
                    }
                    if (!MobileNumber_1.getText().matches("\\d{10}")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Enter valid 10 digit Mobile Number");
                        return;
                    }
                    if (permanentAddress_1.getText().trim().isEmpty() ||
                            communicationAddress_1.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(cardJoint_5, "Please Fill address Fields");
                        return;
                    }
                    if (stateBox_1.getSelectedItem() == null ||
                            districtBox_1.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please select state and District");
                        return;
                    }
                    if (!pin_1.getText().matches("\\d{6}")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please Enter Valid Pin Code ");
                        return;
                    }
                    if (!aadhaarNumber_1.getText().matches("\\d{12}")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please Enter Valid Aadhaar Number");
                        return;
                    }
                    if (!panNumber_1.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please Enter Valid Pan Number");
                        return;
                    }
                    if (Fullname_2.getText().trim().isEmpty() ||
                            fatherName_2.getText().trim().isEmpty() ||
                            motherName_2.getText().trim().isEmpty() ||
                            Email_2.getText().trim().isEmpty() ||
                            MobileNumber_2.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(cardJoint_5, "Please Fill all Fields");
                        return;
                    }
                    if (!gender_1_2.isSelected() && !gender_2_2.isSelected() && !gender_3_2.isSelected()) {
                        JOptionPane.showMessageDialog(cardJoint_5, "Please select Gender");
                        return;
                    }
                    if (!Email_2.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Please Enter Valid Email");
                        return;
                    }
                    if (!MobileNumber_2.getText().matches("\\d{10}")) {
                        JOptionPane.showMessageDialog(cardJoint_5,"Enter valid 10 digit Mobile Number");
                        return;
                    }
                    if (aadhaarcardFile == null || pancardFile == null || photoFile == null || signFile == null) {
                        JOptionPane.showMessageDialog(cardJoint_5, "Please upload all Required Documents");
                        return;
                    }
                    //-----------------------------------------------------------------------------------------------------------
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


                        String sql_1 = "INSERT INTO users(full_name,father_name,mother_name,gender,dob,email" +
                                ",mobile,place) VALUES(?,?,?,?,?,?,?,?)";

                        PreparedStatement userStatement_1  = conn.prepareStatement(sql_1, Statement.RETURN_GENERATED_KEYS );

                        userStatement_1.setString(1,fullName);
                        userStatement_1.setString(2,FatherName);
                        userStatement_1.setString(3,MotherName);
                        userStatement_1.setString(4,gender);
                        userStatement_1.setDate(5,sqlDob_1);
                        userStatement_1.setString(6,email);
                        userStatement_1.setString(7,mobile);
                        userStatement_1.setString(9,place);

                        userStatement_1.executeUpdate();
                        ResultSet result = userStatement_1.getGeneratedKeys();
                        int userID_1 = 0;
                        if (result.next()) {
                            userID_1 = result.getInt(1);
                        }

                        String sql_2 = "INSERT INTO users(full_name,father_name,mother_name,gender,dob,email," +
                                "mobile,place) VALUES(?,?,?,?,?,?,?,?)";

                        PreparedStatement userStatement_2  = conn.prepareStatement(sql_2, Statement.RETURN_GENERATED_KEYS);

                        userStatement_2.setString(1,fullName_2);
                        userStatement_2.setString(2,FatherName_2);
                        userStatement_2.setString(3,MotherName_2);
                        userStatement_2.setString(4,Gender);
                        userStatement_2.setDate(5,sqlDob_2);
                        userStatement_2.setString(6,email_2);
                        userStatement_2.setString(7,mobile_2);
                        userStatement_2.setString(8,place);

                        userStatement_2.executeUpdate();
                        ResultSet result_2 = userStatement_2.getGeneratedKeys();
                        int userID_2 = 0;
                        if (result_2.next()) {
                            userID_2 = result_2.getInt(1);
                        }

                        String SQL = "INSERT INTO accounts(user_id,account_type,branch_name,account_number,status) VALUES (?,?,?,?,?)";
                        PreparedStatement accountStatement = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

                        accountStatement.setInt(1,userID_1);
                        accountStatement.setString(2,"JOINT");
                        accountStatement.setString(3,"Main Branch");
                        accountStatement.setString(4,newAcc);
                        accountStatement.setString(5,"ACTIVE");

                        accountStatement.executeUpdate();

                        ResultSet resultAccount = accountStatement.getGeneratedKeys();
                        int account_id = 0;
                        if (resultAccount.next()) {
                            account_id = resultAccount.getInt(1);
                        }

                        String addSQL = "INSERT INTO addresses(user_id,type,address,state,district,pin_code) VALUES(?,?,?,?,?,?)";
                        PreparedStatement address = conn.prepareStatement(addSQL);

                        address.setInt(1,userID_1);
                        address.setString(2,"PERMANENT");
                        address.setString(3,permanent);
                        address.setString(4,state_1);
                        address.setString(5,district_1);
                        address.setString(6,pinCode_1);
                        address.executeUpdate();

                        address.setInt(1,userID_1);
                        address.setString(2,"COMMUNICATION");
                        address.setString(3,communication);
                        address.setString(4,state_1);
                        address.setString(5,district_1);
                        address.setString(6,pinCode_1);
                        address.executeUpdate();

                        address.setInt(1,userID_2);
                        address.setString(2,"PERMANENT");
                        address.setString(3,permanent_2);
                        address.setString(4,state_2);
                        address.setString(5,district_2);
                        address.setString(6,pinCode_2);
                        address.executeUpdate();

                        address.setInt(1,userID_2);
                        address.setString(2,"COMMUNICATION");
                        address.setString(3,communication_2);
                        address.setString(4,state_2);
                        address.setString(5,district_2);
                        address.setString(6,pinCode_2);
                        address.executeUpdate();

                        String docSQL = "INSERT INTO documents(account_id,user_id,document_type,file_path) VALUES(?,?,?,?)";
                        PreparedStatement documents = conn.prepareStatement(docSQL);
                        documents.setInt(1,account_id);
                        documents.setInt(2,userID_1);
                        documents.setString(3,"AADHAAR");
                        documents.setString(4,aadhaarcardFile.getAbsolutePath());
                        documents.executeUpdate();

                        documents.setString(3, "PAN");
                        documents.setString(4, pancardFile.getAbsolutePath());
                        documents.executeUpdate();

                        documents.setString(3, "PHOTO");
                        documents.setString(4, photoFile.getAbsolutePath());
                        documents.executeUpdate();

                        documents.setString(3, "SIGNATURE");
                        documents.setString(4, signFile.getAbsolutePath());
                        documents.executeUpdate();

                        String docSQL_2 = "INSERT INTO documents(account_id,user_id,document_type,file_path) VALUES(?,?,?,?)";
                        PreparedStatement documents_2 = conn.prepareStatement(docSQL_2);
                        documents_2.setInt(1,account_id);
                        documents_2.setInt(2,userID_2);
                        documents_2.setString(3,"AADHAAR");
                        documents_2.setString(4,aadhaarcardFile.getAbsolutePath());
                        documents_2.executeUpdate();

                        documents_2.setString(3, "PAN");
                        documents_2.setString(4, pancardFile.getAbsolutePath());
                        documents_2.executeUpdate();

                        documents_2.setString(3, "PHOTO");
                        documents_2.setString(4, photoFile.getAbsolutePath());
                        documents_2.executeUpdate();

                        documents_2.setString(3, "SIGNATURE");
                        documents_2.setString(4, signFile.getAbsolutePath());
                        documents_2.executeUpdate();

                        String sql = "INSERT INTO joint_account_details(account_id, first_person_user_id, " +
                                "second_person_user_id, relation) VALUES(?,?,?,?)";
                        PreparedStatement joint = conn.prepareStatement(sql);

                        joint.setInt(1,account_id);
                        joint.setInt(2,userID_1);
                        joint.setInt(3,userID_2);
                        joint.setString(4,Relation);

                        joint.executeUpdate();

                        conn.commit();
                        JOptionPane.showMessageDialog(cardJoint_5, "Account Created Successfully.");
                        JOptionPane.showMessageDialog(null,"your account number is " + newAcc);

                        String receipt =
                                "======== ACCOUNT OPENING RECEIPT ========\n\n" +
                                        "Name        : " + fullName + "\n" +
                                        "Account No  : " + newAcc + "\n" +
                                        "CRN         : " + crn + "\n" +
                                        "Email       : " + email + "\n" +
                                        "Mobile      : " + mobile + "\n" +
                                        "Account Type: JOINT\n" +
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
                        conn.rollback();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(cardJoint_5, "ERROR" + ex.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    Fullname_1.requestFocus();
        });

        buttonPanel.add(backButton_6);
        buttonPanel.add(submitJButton);

        cardJoint_5.add(buttonPanel, gbcJoint_5);

        applyFont(cardJoint_0, labelFont, fieldFont);
        applyFont(cardJoint_1, labelFont, fieldFont);
        applyFont(cardJoint_2, labelFont, fieldFont);
        applyFont(cardJoint_3, labelFont, fieldFont);
        applyFont(cardJoint_4, labelFont, fieldFont);
        applyFont(cardJoint_5, labelFont, fieldFont);

        scrollPanel.add(cardJoint_0);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardJoint_1);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardJoint_2);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardJoint_3);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardJoint_4);
        scrollPanel.add(Box.createVerticalStrut(15));
        scrollPanel.add(cardJoint_5);

        JScrollPane scroll = new JScrollPane(scrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scroll,BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 0.9;
        jointAccount.add(middlePanel,gbc);
        //------------------------------------------------------------------------------------------------------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(12, 12, 69));

        JLabel footer = new JLabel("© 2026 Secure Bank. All Right Reserved.");
        footer.setFont(new Font("Monospaced",Font.PLAIN,18));
        footer.setForeground(Color.white);
        bottomPanel.add(footer);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 0.05;
        gbc.weightx = 1;
        jointAccount.add(bottomPanel,gbc);
        add(jointAccount, BorderLayout.CENTER);
    }
}
