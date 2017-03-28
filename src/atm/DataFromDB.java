/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Joe-Joe
 */
public class DataFromDB 
{
    private static String nameOfAccountHolder;
    private static int accessNumber;
    private static int accountNumber;
    private static String PINnumber;
    private static String cardNumber;
    private static String cvc;
    
    public static void setName(String nameIn)
    {
        nameOfAccountHolder = nameIn;
    }
    
    public static String getName()
    {
        return nameOfAccountHolder;
    }
    
    public static void setAccountNumber(int number)
    {
        accountNumber = number;
    }
    
    public static int getAccountNumber()
    {
        return accountNumber;
    }
    
    public static void setAccess(int access)
    {
        accessNumber = access;
    }
    
    public static int getAccess()
    {
        return accessNumber;
    }
    
    public static void setPIN(String pin)
    {
        PINnumber = pin;
    }
    
    public static String getPIN()
    {
        return PINnumber;
    }
    
    public static void setCardNumber(String cardNumberSent)
    {
        cardNumber = cardNumberSent;
    }
    
    public static String getCardNumber()
    {
        String [] parts = cardNumber.split("[-]");
        String cardNum = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3];
        return cardNum;
    }
    
    public static void setCVC(String cvcSent)
    {
        cvc = cvcSent;
    }
    
    public static String getCVC()
    {
        return cvc;
    }
}
