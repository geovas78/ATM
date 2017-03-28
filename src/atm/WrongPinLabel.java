/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author GOGO
 */
public class WrongPinLabel extends JLabel implements Runnable {

    private String message;
    private Thread thread1;
    private Thread thread2;
    boolean firstMessage;
    Font font = new Font("Dialog", Font.BOLD, 20);
    Font font1 = new Font("Dialog", Font.BOLD, 25);
    Font font2 = new Font("Dialog", Font.BOLD, 15);

    private boolean go = true;

    public WrongPinLabel() {
        firstMessage = true;
        setHorizontalAlignment(JLabel.CENTER);
        thread1 = new Thread(this);
        setVisible(true);

        thread2 = new Thread();
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        int count = 0;
        while (go) {
            System.out.println("" + count);
            if (firstMessage == true) {
                firstMessage = false;
                setForeground(Color.red);
                message = "WRONG PIN NUMBER !";
                setFont(font);
                setText(message);
            } else {
                firstMessage = true;
                message = "- T R Y  A G A I N -";
                setForeground(Color.black);
                setFont(font1);
                setText(message);
            }

            count++;

            if (count > 3) {
                go = false;
                setVisible(false);
                //System.exit(0);
            }

            try {
                thread1.sleep(100);
                thread2.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }

}
