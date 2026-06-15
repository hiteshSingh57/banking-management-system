package com.bank.ui.main;
import com.bank.ui.account.CurrentAccountPanel;
import com.bank.ui.account.JointAccountPanel;
import com.bank.ui.account.OpenAccountPanel;
import com.bank.ui.account.SavingAccountPanel;
import com.bank.ui.login.*;
import com.bank.ui.signup.SignUp;

import javax.swing.*;
import java.awt.*;

public class BankApp extends JFrame {
        private  JPanel cardPanel;
        private CardLayout cardLayout;

    public void MAINFRAME() {

        setTitle("yourBANK App");
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //add all panel
        cardPanel.add(new LoginFrame(cardLayout, cardPanel), "LOGIN");
        cardPanel.add(new SignUp(cardLayout, cardPanel),"SIGN_UP");
        cardPanel.add(new MainFrame(cardLayout, cardPanel), "MAIN FRAME");
        cardPanel.add(new CustomerLoginPanel(cardLayout, cardPanel), "CUSTOMER LOGIN");
        cardPanel.add(new SavingAccountPanel(cardLayout, cardPanel), "Saving ACCOUNT");
        cardPanel.add(new CurrentAccountPanel(cardLayout, cardPanel), "Current ACCOUNT");
        cardPanel.add(new JointAccountPanel(cardLayout, cardPanel), "Joint ACCOUNT");
        cardPanel.add(new EmployeeLoginPanel(cardLayout, cardPanel), "EMPLOYEE LOGIN");
        cardPanel.add(new AdminLogin(cardLayout, cardPanel), "ADMIN LOGIN");
        cardPanel.add(new Customer(cardLayout,cardPanel),"CUSTOMER");
        cardPanel.add(new OpenAccountPanel(cardLayout, cardPanel), "OPEN ACCOUNT");
        cardLayout.show(cardPanel, "LOGIN");
        add(cardPanel);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
