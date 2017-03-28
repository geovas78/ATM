/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author GOGO
 */
class Standby {

    private JFrame frame;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6;
    private JPanel leftPanel, rightPanel, centerPanel;
    private JPanel standbyPanel;

    private IdleScreen screen;

    private CardInserted2 cardIn = new CardInserted2();

    //private CardDetails details;
    private Font font = new Font("DialogInput", Font.BOLD, 40);

    private String pinInfo = "";

    public Standby() {
        System.out.println("" + CashAvailable.cashINMashine());
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

        screen = new IdleScreen();
        screen.setBackground(Color.green);
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(screen, BorderLayout.CENTER);

        if (CashAvailable.cashINMashine() == 0) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
            p.setBackground(Color.green);
            JLabel emptyATM = new JLabel("CASH RUN OUT");
            emptyATM.setFont(font);
            emptyATM.setForeground(Color.red);
            p.add(emptyATM);
            centerPanel.add(p, BorderLayout.NORTH);
        }

        centerPanel.setBackground(Color.green);

        //btn6.addActionListener(new Button6Listener());
        btn1.addActionListener(new Button1Listener());

        //add all three panel to the major one
        standbyPanel = new JPanel(new BorderLayout());
        standbyPanel.setBackground(Color.green);
        standbyPanel.add(leftPanel, BorderLayout.WEST);
        standbyPanel.add(centerPanel, BorderLayout.CENTER);
        standbyPanel.add(rightPanel, BorderLayout.EAST);

    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        this.frame.setContentPane(standbyPanel);
        this.frame.setVisible(true);
    }

    public void getPinAccessInfo(String pinInfoIn) {
        pinInfo = pinInfoIn;
        JPanel pl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pl.setBackground(Color.green);
        JLabel pinBlocked = new JLabel(pinInfo);
        pinBlocked.setFont(font);
        pinBlocked.setForeground(Color.red);
        pl.add(pinBlocked);
        centerPanel.add(pl, BorderLayout.NORTH);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    private class Button1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SecurityCheckIn check = new SecurityCheckIn();
            check.setFrame(frame);
        }

    }

    /*
     public class Button6Listener implements ActionListener {

     public Button6Listener() {
     }

     @Override
     public void actionPerformed(ActionEvent e) {
     if (DataFromDB.getAccess() == 0) {
     cardIn.setFrame(frame);
     } else {
     JPanel pl = new JPanel(new FlowLayout(FlowLayout.CENTER));
     pl.setBackground(Color.green);
     JLabel pinBlocked = new JLabel(pinInfo);
     pinBlocked.setFont(font);
     pinBlocked.setForeground(Color.red);
     pl.add(pinBlocked);
     centerPanel.add(pl, BorderLayout.NORTH);
     }
     }
     }
     private boolean readDetailsFromCard()
     {
     String okToProcess;
     String account;
     String PINfromCard;
        
     try
     {
     FileReader reader = new FileReader("Card.crd");
     BufferedReader buffer = new BufferedReader(reader);
            
            
     //read the lines of the file
     okToProcess = buffer.readLine();
     account = buffer.readLine();
     PINfromCard = buffer.readLine();
            
     //System.out.println(okToProcess + " - " + account + " - " + PINfromCard);
            
     int acc = Integer.parseInt(account);
     int pin = Integer.parseInt(PINfromCard);
            
     details = new CardDetails(acc,pin);
     cardIn.getDetails(details);
            
     //check if the value are asigned to the variables   
     System.out.println(details.getAccountNumber() + " , " + details.getPIN());
            
     if(Integer.parseInt(okToProcess) == 0)
     {
     return true;
     }
     if(Integer.parseInt(okToProcess) == 1)
     {
     return false;
     }
     buffer.close();
     }
        
     catch(FileNotFoundException fnfe)
     {
            
     }
     catch(IOException e)
     {
            
     }
     return false;
     }*/
}
