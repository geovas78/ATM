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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author GOGO
 */
public class FillUp implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    
    private JPanel topPanel, centerPanel, bottomPanel;
    private JLabel _1000,_10, _10000, _50000,_100;
    private Font font1 = new Font("DialogInput",Font.BOLD,20);
    private JButton btn1,btn2,btn3,btn4,btn5,btn6;
    private JPanel leftPanel,rightPanel,centralConsole;
    
    private String message;
    
    //constructor method
    public FillUp()
    {
        centralConsole = new JPanel();
        centralConsole.setLayout(new GridLayout(3,1));
        
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.green);
        JPanel easttop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        easttop.setBackground(Color.green);
        _10000 = new JLabel("£ 10 000 =>");
        _10000.setFont(font1);
        easttop.add(_10000);
        JPanel westtop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        westtop.setBackground(Color.green);
        _10 = new JLabel("<= £ 10");
        _10.setFont(font1);
        westtop.add(_10);
        topPanel.add(westtop,BorderLayout.WEST);
        topPanel.add(easttop,BorderLayout.EAST);
        
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.green);
        JPanel westcenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel eastcenter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        westcenter.setBackground(Color.green);
        eastcenter.setBackground(Color.green);
        _100 = new JLabel("<= £ 100");
        _100.setFont(font1);
        _50000 = new JLabel("50 000 =>");
        _50000.setFont(font1);
        westcenter.add(_100);
        eastcenter.add(_50000);
        centerPanel.add(westcenter,BorderLayout.WEST);
        centerPanel.add(eastcenter,BorderLayout.EAST);
        
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.green);
        JPanel westbottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        westbottom.setBackground(Color.green);
        _1000 = new JLabel("<= £ 1000");
        _1000.setFont(font1);
        westbottom.add(_1000);
        bottomPanel.add(westbottom,BorderLayout.WEST);
        
        centralConsole.add(topPanel);
        centralConsole.add(centerPanel);
        centralConsole.add(bottomPanel);
        
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3,1));
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3,1));
        
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
        
        panel = new JPanel(new BorderLayout());
        
        panel.add(leftPanel,BorderLayout.WEST);
        panel.add(centralConsole,BorderLayout.CENTER);
        panel.add(rightPanel,BorderLayout.EAST);
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
    }
    
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==btn1)
        {
            int money = CashAvailable.cashINMashine() + 10;
            if(money > 50000)
            {
               message = "ATM now has £" + 50000;
               CashAvailable.fillUp(50000);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
            else
            {
               message = "ATM now has £" + money;
               CashAvailable.fillUp(money);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
            
        }
        if(e.getSource()==btn2)
        {
            int money = CashAvailable.cashINMashine() + 100;
            if(money > 50000)
            {
               message = "ATM now has £" + 50000;
               CashAvailable.fillUp(50000);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
            else
            {
               message = "ATM now has £" + money;
               CashAvailable.fillUp(money);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
        }
        if(e.getSource()==btn3)
        {
            int money = CashAvailable.cashINMashine() + 1000;
            if(money > 50000)
            {
               message = "ATM now has £" + 50000;
               CashAvailable.fillUp(50000);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
            else
            {
               message = "ATM now has £" + money;
               CashAvailable.fillUp(money);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
        }
        if(e.getSource()==btn4)
        {
            int money = CashAvailable.cashINMashine() + 10000;
            if(money > 50000)
            {
               message = "ATM now has £" + 50000;
               CashAvailable.fillUp(50000);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
            else
            {
               message = "ATM now has £" + money;
               CashAvailable.fillUp(money);
               CashAvailable.setNotes();
               ByeDisplay display = new ByeDisplay(message);
               display.setFrame(frame);
            }
        }
        if(e.getSource()==btn5)
        {
           message = "ATM now has £" + 50000;
           CashAvailable.fillUp(50000);
           CashAvailable.setNotes();
           ByeDisplay display = new ByeDisplay(message);
           display.setFrame(frame); 
        }
    }
}
