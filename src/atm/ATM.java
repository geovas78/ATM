/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import javax.swing.JFrame;

/**
 *
 * @author GOGO
 */
public class ATM 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        frame.setTitle(" -- A T M -- ");
        frame.setLocation(50,50);
        frame.setSize(550,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CashAvailable.fillUp(5000);
        CashAvailable.setNotes();
        Standby standby = new Standby();
        standby.setFrame(frame);
        GetDataFromDB getTheAccountList = new GetDataFromDB();
        ChooseAccount choice = new ChooseAccount(getTheAccountList.getList(),frame);
    }
}
