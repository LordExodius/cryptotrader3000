package cryptotrader.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PopupUI extends JFrame implements ActionListener{

    // Components
    JButton okayButton;

    JFrame popupFrame;
    JPanel popupPanel;

    public PopupUI(String message)
    {
        popupFrame = new JFrame();

        // Create Components
        okayButton = new JButton("Okay");
        okayButton.addActionListener(this);

        JLabel messageLabel = new JLabel(message);

        popupPanel = new JPanel();
        popupPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        popupPanel.setLayout(new GridLayout(2, 1));

        popupPanel.add(messageLabel);
        popupPanel.add(okayButton);

        popupFrame.add(popupPanel, BorderLayout.CENTER);

        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.setTitle("Error");
        popupFrame.pack();
        popupFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popupFrame.dispatchEvent(new WindowEvent(popupFrame, WindowEvent.WINDOW_CLOSING));
    }
}
