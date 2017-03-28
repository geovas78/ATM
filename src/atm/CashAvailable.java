/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author GOGO
 */
public class CashAvailable 
{
    static int cash;
    private static int notes20;
    private static int notes10;
    
    static void fillUp(int cashIn)
    {
        cash = cashIn;
    }
    
    static void cashOut(int cashDemand)
    {
        cash = cash - cashDemand;
    }
    
    static int cashINMashine()
    {
        return cash;
    }
    
    static void give20notes(int notes20In)
    {
        notes20 = notes20 - notes20In;
    }
    
    static int getNumberOf20notes()
    {
        return notes20;
    }
    
    static void give10notes(int notes10In)
    {
        notes10 = notes10 - notes10In;
    }
    
    static int getNumberOf10notes()
    {
        return notes10;
    }
    
    //setup the first or refill of notes
    static void setNotes()
    {
        notes20 = (cash /2)/20;
        notes10 = (cash/2)/10;
    }
    
    //set cash according to the notes
    
    static void put20notes(int notes20put)
    {
        notes20 = notes20put;
        cash = cash + (notes20 * 20);
    }
    
    static void put10notes(int notes10put)
    {
        notes10 = notes10put;
        cash = cash + (notes10 *10);
    }
}
