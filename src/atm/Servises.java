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
public class Servises 
{
    private JFrame frame;
    private JPanel panel;
    
    private JPanel topPanel, centerPanel, bottomPanel;
    private JLabel majorLabel, withdrawLabel, checkBalanceLabel,
            transactionsLabel, pinChangeLabel;
    private Font font1 = new Font("DialogInput",Font.BOLD,20);
    private JButton btn1,btn2,btn3,btn4,btn5,btn6;
    private JPanel leftPanel,rightPanel,centralConsole;
    private WithdrawalOptions options = new WithdrawalOptions();
    private PINchange change = new PINchange();
    
    
    public Servises()
    {
        centralConsole = new JPanel();
        centralConsole.setLayout(new GridLayout(3,1));
        
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.green);
        JPanel easttop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        easttop.setBackground(Color.green);
        withdrawLabel = new JLabel("WITHDRAW =>");
        withdrawLabel.setFont(font1);
        easttop.add(withdrawLabel);
        topPanel.add(easttop,BorderLayout.EAST);
        
        centerPanel = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel eastcenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        center.setBackground(Color.green);
        eastcenter.setBackground(Color.green);
        majorLabel = new JLabel("");
        majorLabel.setFont(font1);
        checkBalanceLabel = new JLabel("CHECK BALANCE =>");
        checkBalanceLabel.setFont(font1);
        center.add(majorLabel);
        eastcenter.add(checkBalanceLabel);
        centerPanel.add(center,BorderLayout.CENTER);
        centerPanel.add(eastcenter,BorderLayout.EAST);
        
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.green);
        JPanel eastbottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel westbottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        eastbottom.setBackground(Color.green);
        westbottom.setBackground(Color.green);
        transactionsLabel = new JLabel("ABORT =>");
        transactionsLabel.setFont(font1);
        pinChangeLabel = new JLabel("<= CHANGE PIN");
        pinChangeLabel.setFont(font1);
        eastbottom.add(transactionsLabel);
        westbottom.add(pinChangeLabel);
        bottomPanel.add(eastbottom,BorderLayout.EAST);
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
        
        btn4.addActionListener(new WithdrawListener());
        btn3.addActionListener(new PinListener());
        btn6.addActionListener(new AbortListener());
        btn5.addActionListener(new BalanceListener());
        
    }
    
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    private class BalanceListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
             BalanceOption bop = new BalanceOption();
             bop.setFrame(frame);
        }
    }

    private class AbortListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Standby st = new Standby();
            st.setFrame(frame);
            String message = "WELCOME TO COME BACK AGAIN";
            ByeDisplay bye = new ByeDisplay(message);
            bye.setFrame(frame);
            DataFromDB.setAccountNumber(0);
            GetDataFromDB getTheAccountList = new GetDataFromDB();
            ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(),frame);
        }
    }

    private class PinListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
             change.setFrame(frame);
        }
        
    }

    private class WithdrawListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            options.setFrame(frame);
        }
    }
}


