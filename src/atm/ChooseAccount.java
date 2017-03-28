/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Joe-Joe
 */
public class ChooseAccount extends JFrame {

    private JComboBox list;
    private String[] accountList;

    private String selectedAccountNumber = null;

    private JFrame frameToSend;

    private JTextArea infoLabel;

    public ChooseAccount(String[] listIn, JFrame frameIn) {
        accountList = listIn;
        frameToSend = frameIn;
        setTitle(" -- CHOOSE ACCOUNT -- ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(605, 50);
        //setLocationRelativeTo(null);
        setSize(300, 200);
        setResizable(false);

        //set up the containers for the components
        JPanel container = new JPanel(new BorderLayout());
        JPanel topInfo = new JPanel();
        JPanel choicePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        //labels
        Font font1 = new Font("Dialog", Font.BOLD, 16);
        String information = "Please choose your account number\n in order to get your credit card and\n PIN number."
                + " Remenber the PIN from\n the bottom left corner on your card\n and type it to get access to the ATM.";
        infoLabel = new JTextArea(information);
        infoLabel.setFont(font1);
        infoLabel.setForeground(Color.RED);

        //middle combobox put in scrollbar which we will create later when account are avaiable
        list = new JComboBox(accountList);

        //create a button for action
        JButton getCard = new JButton("G E T   Y O U R    C A R D");

        //ADD COMPONENT TO THE PANELS
        topInfo.add(infoLabel);
        choicePanel.add(list);
        buttonPanel.add(getCard);

        // add all panels to the container
        container.add(list, BorderLayout.NORTH);
        container.add(topInfo, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        add(container);

        getCard.addActionListener(new FrowardAccountNumberListener());
        list.addActionListener(new SelectedItemListener());

        setVisible(true);
    }

    private class FrowardAccountNumberListener implements ActionListener {

        public FrowardAccountNumberListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                    
                    String account = "" + DataFromDB.getAccountNumber();
                    
                    String accountCodedSent = URLEncoder.encode(account, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/GetPinAccessCard");
                    java.net.URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("string=" + accountCodedSent);
                    out.close();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String decodedString;
                    while ((decodedString = in.readLine()) != null) {

                        String[] value = decodedString.split("[,]");

                        DataFromDB.setName(value[0]);
                        DataFromDB.setPIN(value[1]);
                        int access = Integer.parseInt(value[2]);
                        DataFromDB.setAccess(access);
                        DataFromDB.setCardNumber(value[3]);
                        DataFromDB.setCVC(value[4]);

                    }
                    in.close();
                } catch (Exception ex) {
                    infoLabel.setText("ERROR: " + ex.getMessage());
                }
            CreditCard cardInvoke = new CreditCard(frameToSend);
            dispose();
        }
    }

    public void getMessageForSelection(String messageSent)
    {
        infoLabel.setText(messageSent);
    }
    private class SelectedItemListener implements ActionListener {

        public SelectedItemListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            selectedAccountNumber = (String) list.getSelectedItem();
            DataFromDB.setAccountNumber(Integer.parseInt(selectedAccountNumber));
        }
    }
}
