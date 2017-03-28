/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author GOGO
 */
public class WithdrawConfirm implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JPanel topPanel, centralPanel, bottomPanel;
    private JPanel yesPanel, noPanel;
    private JLabel amountLabel, receiptLabel, yesLabel, noLabel;
    private Font font = new Font("DialogInput", Font.BOLD, 40);

    //major panel attributes
    private Font font1 = new Font("DialogInput", Font.BOLD, 20);
    private JButton btn1, btn2, btn3, btn4, btn5, btn6;
    private JPanel leftPanel, rightPanel, centralConsole;

    private int amount;
    
    String message;

    private ByeDisplay bye;

    public WithdrawConfirm(JFrame frame, int amount) {

        this.amount = amount;

        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.green);
        amountLabel = new JLabel("£ " + amount);
        amountLabel.setFont(font);
        topPanel.add(amountLabel);

        centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centralPanel.setBackground(Color.green);
        receiptLabel = new JLabel("WUOLD YOU LIKE A RECEIPT");
        receiptLabel.setFont(font1);
        centralPanel.add(receiptLabel);

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.green);
        yesLabel = new JLabel("<= YES");
        yesLabel.setFont(font1);
        noLabel = new JLabel("NO =>");
        noLabel.setFont(font1);
        yesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yesPanel.setBackground(Color.green);
        noPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        noPanel.setBackground(Color.green);
        yesPanel.add(yesLabel);
        noPanel.add(noLabel);
        bottomPanel.add(yesPanel, BorderLayout.WEST);
        bottomPanel.add(noPanel, BorderLayout.EAST);

        centralConsole = new JPanel(new GridLayout(3, 1));
        centralConsole.add(topPanel);
        centralConsole.add(centralPanel);
        centralConsole.add(bottomPanel);

        //buttons panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));

        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        btn4 = new JButton();
        btn5 = new JButton();
        btn6 = new JButton();

        leftPanel.add(btn1);
        leftPanel.add(btn2);
        leftPanel.add(btn3);

        rightPanel.add(btn4);
        rightPanel.add(btn5);
        rightPanel.add(btn6);

        //the major panel which id added to the frame
        panel = new JPanel(new BorderLayout());

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centralConsole, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        btn3.addActionListener(new ConfirmListener());
        btn6.addActionListener(this);

        //add the frame and make it visible
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    public void setFrame(JFrame frame, int amountIn) {
        amount = amountIn;
        //System.out.println("£ " + amount);
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn6) {
            
            
            TransactionListener translistener = new TransactionListener();
            translistener.transactionPerformed(amount);
            
            if(translistener.isOK() == true)
            {
               message = "FOR USING OUR SERVICES"; 
            }
            else
            {
                message = "TRANSACTION NOT SUCCESSFUL"; 
            }

            //String message = "FOR USING OUR SERVICES";
            bye = new ByeDisplay(message);
            bye.setFrame(frame);
            DataFromDB.setAccountNumber(0);
            GetDataFromDB getTheAccountList = new GetDataFromDB();
            ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(), frame);
        }
    }

    private class ConfirmListener implements ActionListener {

        DecimalFormat df = new DecimalFormat("######0.00");

        @Override
        public void actionPerformed(ActionEvent e) {
            TransactionListener trans = new TransactionListener();
            trans.transactionPerformed(amount);
            if (trans.isOK() == true) {
                try {

                    JPanel generalP = new JPanel(new BorderLayout());
                    JPanel banner = new JPanel();
                    java.net.URL imgURL = WithdrawConfirm.class.getResource("images.gif");
                    ImageIcon icon = new ImageIcon(imgURL);
                    //create new font for the footer of the receipt
                    java.net.URL fontURL = WithdrawConfirm.class.getResource("Playball-Regular.ttf");
                    Font fontG = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
                    fontG = fontG.deriveFont(Font.PLAIN, 26);
                    GraphicsEnvironment ge
                            = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(fontG);

                    //create new font for the title
                    java.net.URL fontURL2 = WithdrawConfirm.class.getResource("Oswald-Bold.ttf");
                    Font fontG2 = Font.createFont(Font.TRUETYPE_FONT, fontURL2.openStream());
                    fontG2 = fontG2.deriveFont(Font.PLAIN, 36);
                    GraphicsEnvironment ge2
                            = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge2.registerFont(fontG2);

                    String addString = "";

                    JLabel topLabel = new JLabel();
                    topLabel.setIcon(icon);
                    topLabel.setText("Georgi's Bank");
                    //topLabel.setFont(font99);
                    topLabel.setFont(fontG2);

                    banner.add(topLabel);

                    String s = "<head>\n"
                            + "<link href='https://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>\n"
                            + "<title>receipt</title>\n"
                            + "<style>\n"
                            + "h3{\n"
                            + "	text-align:center;\n"
                            + "	font-size:14px;\n"
                            + "	font-style:italic;\n"
                            + "	font-weight:bold;\n"
                            + "	color:#000080;\n"
                            + "	font-family:Impact;\n"
                            + "}\n"
                            + "p{\n"
                            + "	text-align:center;\n"
                            + "	text-justify:distribute;\n"
                            + "	font-size:14px;\n"
                            + "	font-weight:bold;\n"
                            + "	color:#000080;\n"
                            + "	font-family:\"Times New Roman\", Times, serif;\n"
                            + "}\n"
                            + "footer{\n"
                            + "	color:#FF69B4;\n"
                            + "	text-align:center;\n"
                            + "	font-family: 'Playball', cursive;\n"
                            + "}\n"
                            + "</style>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "<h3>--     TRANSACTION RECEIPT     --</h3>\n"
                            + "<hr />\n"
                            + "<p></p>\n"
                            + "<p>Account number : ";
                    String acc = "" + DataFromDB.getAccountNumber();
                    addString = s + acc;
                    
                    String s2 = "</p>\n";
                    addString = addString + s2;
                    String s3 = "<p>Amount withdrawn : £";
                    addString = addString + s3;
                    addString = addString + amount;
                    String s4 =  "</p>\n";
                    addString = addString + s4;
                    String s5 = "<p>Actual balance : ";
                    addString = addString + s5;
                    String balanceA = "£" + df.format(trans.getBalanceAfter());
                    addString = addString + balanceA;
                    String s6 =  "</p>\n" + "<p></p>\n" + "<p>date : ";
                    addString = addString + s6;
                    String dateA = getDate();
                    addString = addString + dateA;
                    String endString = "</p>\n<p></p>\n<hr />\n</body>";
                    addString = addString + endString;

                    JEditorPane ep = new JEditorPane("text/html", addString);
                    ep.setEditable(false);
                    JScrollPane p = new JScrollPane(ep);

                    JLabel label = new JLabel("Created by Georgi Vasilski");
                    label.setFont(fontG);
                    label.setForeground(java.awt.Color.pink);

                    generalP.add("North", banner);
                    generalP.add("Center", p);
                    generalP.add(label, BorderLayout.SOUTH);

                    JOptionPane.showMessageDialog(null, generalP, "RECEIPT", JOptionPane.PLAIN_MESSAGE);

                } catch (IOException | FontFormatException | HeadlessException ex) {
                    ex.getMessage();
                }
                message = "FOR USING OUR SERVICES";
            }
            else
            {
               message = "TRANSACTION NOT SUCCESSFUL"; 
            }
            //message = "FOR USING OUR SERVICES";
            bye = new ByeDisplay(message);
            bye.setFrame(frame);
            DataFromDB.setAccountNumber(0);
            GetDataFromDB getTheAccountList = new GetDataFromDB();
            ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(), frame);
        }
    }

    public String getDate() {
        Calendar c = Calendar.getInstance();
        String d = new String();
        d = d.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        return d;
    }
}
