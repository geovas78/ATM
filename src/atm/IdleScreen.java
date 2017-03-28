/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author GOGO
 */
public class IdleScreen extends JPanel implements Runnable
{
    	// declare some constants
	public static final String fileName = "atm-machine";
	public static final int numberOfImages = 9;
	public static final int SLEEP_TIME = 250;

	private JLabel label = new JLabel();
	private ImageIcon image;
	private Thread animationThread;
    
        public IdleScreen()
        {
            	// create a new thread
	animationThread = new Thread(this);

	// add the label to the frame and centre the image
	add("Center",label);
	label.setHorizontalAlignment(JLabel.CENTER);

	// start the thread
	animationThread.start();
        }
        
        @Override
	public void run()
	{
            int currentImage = 0;
            String strImage;
            while(true)
                {
                    strImage = fileName + (currentImage+1) + ".gif";
                    URL imgURL = IdleScreen.class.getResource(strImage);
                    image = new ImageIcon(imgURL);

                    label.setIcon(image);

                try
                    {
                        animationThread.sleep(SLEEP_TIME);
                    }
                catch(InterruptedException e)
                    {
                    }
            currentImage++;
            if(currentImage == numberOfImages)
                {
                    currentImage = 0;
                }
                }
	}
}
