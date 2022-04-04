package cryptotrader.gui;

import cryptotrader.authentication.Database;
import cryptotrader.gui.MainUI;
import cryptotrader.trade.TraderList;
import cryptotrader.user.User;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class LoginUI extends JFrame implements ActionListener {

    // Components
    private JLabel status;
    private JTextField userField;
    private JTextField passField;
    private JButton loginButton;

    private JFrame loginFrame;
    private JPanel loginPanel;

    Database database = Database.getInstance();

    public LoginUI()
    {
        super("Cryptotrader 3000 - Login");
        loginFrame = new JFrame();
        
        // Create components
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setPreferredSize(new DimensionUIResource(100, 20));
        
        userField = new JTextField();
        userField.setPreferredSize(new DimensionUIResource(100, 20));
        passField = new JTextField();
        passField.setPreferredSize(new DimensionUIResource(100, 20));

        status = new JLabel(" ");

        // Create panel
        loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        loginPanel.setLayout(new GridBagLayout());

        // Add components to panel
        JLabel userLabel = new JLabel("Username: ");
        GridBagConstraints userCon = new GridBagConstraints();
        userCon.weightx = 0.5;
        userCon.gridy = 0;
        loginPanel.add(userLabel, userCon);
        loginPanel.add(userField, userCon);

        JLabel passLabel = new JLabel("Password: ");
        GridBagConstraints passCon = new GridBagConstraints();
        passCon.weightx = 0.5;
        passCon.gridy = 1;
        loginPanel.add(passLabel, passCon);
        loginPanel.add(passField, passCon);

        GridBagConstraints buttonCon = new GridBagConstraints();
        buttonCon.weightx = 1;
        buttonCon.gridy = 2;
        buttonCon.gridwidth = 2;
        loginPanel.add(loginButton, buttonCon);
        
        GridBagConstraints statusCon = new GridBagConstraints();
        statusCon.weightx = 1;
        statusCon.gridy = 3;
        statusCon.gridwidth = 2;
        loginPanel.add(status, statusCon);

        // Add panel to frame
        loginFrame.add(loginPanel, BorderLayout.CENTER);

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setTitle("Cryptotrader 3000 - Login");
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(database.authenticate(userField.getText(), passField.getText()))
        {
            initUser();
            loginFrame.setVisible(false);
        }
        else
        {
            status.setText("Incorrect User Credentials");
        }   
    }

    private void initUser()
    {
        User user = User.getInstance();
        user.setUsername(userField.getText());
        user.setTraderList(database.getTraders());
        user.setTradeLog(database.getTradeLog(user.getTraderList()));

        initMain();
    }

    private void initMain()
    {
        JFrame mainFrame = MainUI.getInstance();
		mainFrame.setSize(900, 600);
		mainFrame.pack();
		mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            // When closing MainUI, save TraderList and TradeLog instances to db
            @Override
            public void windowClosing(WindowEvent e) {
                database.addTraders(User.getInstance().getTraderList());
                database.addTradeLog(User.getInstance().getTradeLog());
            }
            
            // When MainUI is closed, close the loginFrame as well
            @Override
            public void windowClosed(WindowEvent e) {
            loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public static void main(String[] args)
    {
        new LoginUI();
    }
}
