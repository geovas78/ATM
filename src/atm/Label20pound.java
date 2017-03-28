/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Joe-Joe
 */
public class Label20pound extends JFrame
{
    public Label20pound(int x,int y)
    {
        URL pound20URL = Label10pound.class.getResource("20pound.jpg");
        ImageIcon img = new ImageIcon(pound20URL);
        JLabel label = new JLabel(img);
        add(label);
        setLocation(x, y);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(265,175);
        setVisible(true);
    }
}
