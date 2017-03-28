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
import javax.swing.JTextField;
/**
 *
 * @author GOGO
 */
public class SecurityCheckIn 
{
    private JFrame frame;
    private JPanel panel;
    
    private JPanel top, center, bottom;
    private JTextField field = new JTextField(15);
    private JLabel labelHost;
    private JLabel labelInfoCash;
    private Font font = new Font("DialogInput",Font.BOLD,15);
    private Font font2 = new Font("DialogInput",Font.BOLD,10);
    
    private Font font1 = new Font("DialogInput",Font.BOLD,20);
    private JButton btn1,btn2,btn3,btn4,btn5,btn6;
    private JPanel leftPanel,rightPanel,centralConsole;
    
    private final String ATMID = "7809avg4421";
    
    public SecurityCheckIn()
    {
        top = new JPanel(new FlowLayout(FlowLayout.CENTER));
       top.setBackground(Color.green);
       labelHost = new JLabel("Please type in the ATM ID");
       labelHost.setFont(font1);
       top.add(labelHost);
       
       center = new JPanel(new FlowLayout(FlowLayout.CENTER));
       center.setBackground(Color.green);
       field.setBackground(Color.green);
       field.setFont(font1);
       center.add(field);
       
       bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
       bottom.setBackground(Color.green);
       labelInfoCash = new JLabel("press here to continue =>");
       labelInfoCash.setFont(font);
       bottom.add(labelInfoCash);
       
       centralConsole = new JPanel(new GridLayout(3,1));
       centralConsole.add(top);
       centralConsole.add(center);
       centralConsole.add(bottom);
       
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
        
        btn6.addActionListener(new Forward());
    }
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    private class Forward implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
             String idEntered = field.getText();
             
             if(idEntered.equals(ATMID))
             {
                 FillUp fillup = new FillUp();
                 fillup.setFrame(frame);
             }
             else
             {
                 Standby stand = new Standby();
                 stand.setFrame(frame);
             }
        }
        
    }
}
