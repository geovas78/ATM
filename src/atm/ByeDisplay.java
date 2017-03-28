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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author GOGO
 */
public class ByeDisplay implements Runnable
{
    private JFrame frame;
    private JPanel panel;
    
    private Font font = new Font("DialogInput",Font.BOLD,40);
    private Font font1 = new Font("DialogInput",Font.BOLD,30);
    private JButton btn1,btn2,btn3,btn4,btn5,btn6;
    private JPanel leftPanel,rightPanel,centralConsole;
    
    private JLabel byeLabel1,byeLabel2,byeLabel3;
    
    private boolean go = true;
    private Thread thread;
    
    private String message;
    
    
    public ByeDisplay(String message)
    {
        this.message = message;
        byeLabel1 = new JLabel("THANK YOU");
        byeLabel1.setFont(font);
        byeLabel2 = new JLabel(this.message);
        byeLabel2.setFont(font1);
        byeLabel3 = new JLabel("BYE !!!");
        byeLabel3.setFont(font);
        
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBackground(Color.green);
        center.setBackground(Color.green);
        bottom.setBackground(Color.green);
        top.add(byeLabel1);
        center.add(byeLabel2);
        bottom.add(byeLabel3);
        
        
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
        
        //the major panel which id added to the frame
        panel = new JPanel(new BorderLayout());
        
        panel.add(leftPanel,BorderLayout.WEST);
        panel.add(centralConsole,BorderLayout.CENTER);
        panel.add(rightPanel,BorderLayout.EAST);
        
        thread = new Thread(this);
        thread.start();
    }
    
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    @Override
    public void run() 
    {
        int count = 1;
        while(go)
        {
            System.out.println("" + count);
            count++;
            
            try
            {
                thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                
            }
            
            if (count == 5)
            {
                go = false;
                Standby standby = new Standby();
                standby.setFrame(frame);
            }
                    
        }
    }
}
