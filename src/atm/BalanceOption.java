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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
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
public class BalanceOption implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JPanel top, central, bottom;
    private JLabel balanceLabel, displayBalance, receipt, exitLabel;

    private Font font = new Font("DialogInput", Font.BOLD, 40);
    private Font font1 = new Font("DialogInput", Font.BOLD, 20);

    private double balance;

    private JButton btn1, btn2, btn3, btn4, btn5, btn6;
    private JPanel leftPanel, rightPanel, centralConsole;
    
    DecimalFormat df = new DecimalFormat("######0.00");

    //the constructor
    public BalanceOption() {
        top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBackground(Color.green);
        balanceLabel = new JLabel("YOUR BALANCE IS");
        balanceLabel.setFont(font);
        top.add(balanceLabel);

        central = new JPanel(new FlowLayout(FlowLayout.CENTER));
        central.setBackground(Color.green);
        //read the details from card chip and asign value to balance
        getBalance();
        displayBalance = new JLabel("£" + df.format(balance));
        displayBalance.setFont(font);
        central.add(displayBalance);

        JPanel leftBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBottom.setBackground(Color.green);
        receipt = new JLabel("<= RECEIPT");
        receipt.setFont(font1);
        leftBottom.add(receipt);

        JPanel rightBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottom.setBackground(Color.green);
        exitLabel = new JLabel("EXIT =>");
        exitLabel.setFont(font1);
        rightBottom.add(exitLabel);

        bottom = new JPanel(new BorderLayout());
        bottom.setBackground(Color.green);
        bottom.add(leftBottom, BorderLayout.WEST);
        bottom.add(rightBottom, BorderLayout.EAST);

        centralConsole = new JPanel(new GridLayout(3, 1));
        centralConsole.add(top);
        centralConsole.add(central);
        centralConsole.add(bottom);

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

        btn3.addActionListener(this);
        btn6.addActionListener(this);

    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    public void getBalance() {
        try {
            
            String accountNumberSend = "" + DataFromDB.getAccountNumber();

            String codedSent = URLEncoder.encode(accountNumberSend, "UTF-8");

            URL url = new URL("https://webbank-gvasilski.rhcloud.com/GetBalanceATM");
            java.net.URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + codedSent);
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String decodedString;

            while ((decodedString = in.readLine()) != null) {

                balance = Double.parseDouble(decodedString);
            }
            in.close();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    public String getDate() {
        Calendar c = Calendar.getInstance();
        String d = new String();
        d = d.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        return d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (e.getSource() == btn3) {
                //DecimalFormat df = new DecimalFormat("######0.00");
                JPanel generalP = new JPanel(new BorderLayout());
                JPanel banner = new JPanel();
                java.net.URL imgURL = BalanceOption.class.getResource("images.gif");
                ImageIcon icon = new ImageIcon(imgURL);
                //create new font for the footer of the receipt
                java.net.URL fontURL = BalanceOption.class.getResource("Playball-Regular.ttf");
                Font fontG = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
                fontG = fontG.deriveFont(Font.PLAIN, 26);
                GraphicsEnvironment ge
                        = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(fontG);

                //create new font for the title
                java.net.URL fontURL2 = BalanceOption.class.getResource("Oswald-Bold.ttf");
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

                String s1 = "<head>\n"
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
                addString = s1 + acc;
                
                String s2 = "</p>\n";
                addString = addString + s2;
                String s3 = "<p>Actual balance : £";
                addString = addString + s3;
                String balanceShow = ""+ df.format(balance);
                addString = addString + balanceShow;
                                
                String s4 = "</p>\n"
                        + "<p></p>\n"
                        + "<p>date : ";
                addString = addString + s4;
                String dateA = getDate();
                addString = addString + dateA;
                        
                String endString = "</p>\n"
                        + "<p></p>\n"
                        + "<hr />\n"
                        + "</body>";
                
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

                String message = "FOR USING OUR SERVICES";
                ByeDisplay bye = new ByeDisplay(message);
                bye.setFrame(frame);
                DataFromDB.setAccountNumber(0);
                GetDataFromDB getTheAccountList = new GetDataFromDB();
                ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(),frame);
            }
        } catch (IOException | FontFormatException | HeadlessException ex) {
            ex.getMessage();
        }
        if (e.getSource() == btn6) {
            Servises service = new Servises();
            service.setFrame(frame);

        }
    }

}
