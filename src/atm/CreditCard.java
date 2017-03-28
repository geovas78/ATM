/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Joe-Joe
 */
public class CreditCard extends JFrame {

    private JFrame frameToSend;
    private CardInserted2 cardIn = new CardInserted2();
    private String pinBlocked;
    String pinDisplay;
    String nameDisplay;
    String cardNum;
    String cardCVC;

    //private CardDetails details;
    private Font font = new Font("DialogInput", Font.BOLD, 40);

    public CreditCard(JFrame frameIn) {

        frameToSend = frameIn;
        
        java.net.URL fontURL = CreditCard.class.getResource("CREDC___.ttf");
        Font fontG = null;
        try {
            fontG = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
        } catch (FontFormatException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        fontG = fontG.deriveFont(Font.PLAIN, 12);
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fontG);

        setTitle(" -- Credit Card -- ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(605, 50);
        setSize(305, 260);
        setResizable(false);
        pinDisplay = "" + DataFromDB.getPIN();
        nameDisplay = DataFromDB.getName();
        cardNum = DataFromDB.getCardNumber();
        cardCVC = "CVC : " + DataFromDB.getCVC();

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);

        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());

        URL cardURL = CreditCard.class.getResource("card.jpg");
        ImageIcon card = new ImageIcon(cardURL);

        JButton insert = new JButton("INSERT YOUR CARD");

        JLabel cardLabel = new JLabel(card);
        cardLabel.setBounds(0, 0, 290, 180);

        JLabel nameLabel = new JLabel(nameDisplay);
        nameLabel.setBounds(30, 130, 200, 50);
        nameLabel.setForeground(Color.WHITE);
        //cardPanel.add(cardLabel);
        buttonPanel.add(insert);

        JLabel pinLabel = new JLabel(pinDisplay);
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(fontG);
        pinLabel.setBounds(60, 108, 50, 50);
        
        JLabel cardNumberLabel = new JLabel(cardNum);
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setFont(fontG);
        cardNumberLabel.setBounds(30,86,250,50);
        
        JLabel cvcLabel = new JLabel(cardCVC);
        cvcLabel.setForeground(Color.ORANGE);
        cvcLabel.setBounds(170, 140, 100, 50);

        cardPanel.add(nameLabel);
        cardPanel.add(pinLabel);
        cardPanel.add(cardNumberLabel);
        cardPanel.add(cvcLabel);
        cardPanel.add(cardLabel);

        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        insert.addActionListener(new ProcessPIN());

        add(mainPanel);

        setVisible(true);
    }

    private class ProcessPIN implements ActionListener {

        public ProcessPIN() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (DataFromDB.getAccountNumber() == 0) {
                GetDataFromDB getTheAccountList = new GetDataFromDB();
                ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(), frameToSend);
                choice.getMessageForSelection("NO ACCOUNT HAS BEEN SELECTED");
                dispose();
            } else {
                if (DataFromDB.getAccess() == 0) {
                    cardIn.setFrame(frameToSend);
                    dispose();
                } else {
                    pinBlocked = "PIN BLOCKED";
                    Standby goBack = new Standby();
                    goBack.getPinAccessInfo(pinBlocked);
                    goBack.setFrame(frameToSend);
                    GetDataFromDB getTheAccountList = new GetDataFromDB();
                    ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(), frameToSend);
                    dispose();
                }
            }
        }
    }
}
