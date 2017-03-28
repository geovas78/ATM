/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 *
 * @author GOGO
 */
public class TransactionListener {

    private boolean successful = false;
    private double balanceAfterTransaction;
    private String resultMessage;

    // constructor
    public TransactionListener() {

    }

    public void transactionPerformed(int amount) {

        String date = getDate();
        try {

            String infoToSend = DataFromDB.getAccountNumber() + "," + amount + "," + date;

            String infoCodedString = URLEncoder.encode(infoToSend, "UTF-8");

            URL url = new URL("https://webbank-gvasilski.rhcloud.com/MakeWithdrawATM");
            java.net.URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + infoCodedString);
            out.close();

            //receive the feedback
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String feedback;
            String ok = "OK";
            while ((feedback = in.readLine()) != null) {
                String[] infoBack = feedback.split("[,]");
                String firstParam = infoBack[0];
                String secondParam = infoBack[1];
                if (firstParam.equals(ok)) {
                    successful = true;
                    balanceAfterTransaction = Double.parseDouble(secondParam);
                    System.out.println(balanceAfterTransaction);
                    Money moneyOut = new Money(amount);
                } else {
                    successful = false;
                }
            }
            in.close();
        } catch (Exception ex) {
            resultMessage = "ERROR: " + ex.getMessage();
        }
    }

    public String getDate() {
        Calendar c = Calendar.getInstance();
        String d = new String();
        d = d.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        return d;
    }

    public boolean isOK() {
        return successful;
    }

    public double getBalanceAfter() {
        return balanceAfterTransaction;
    }
}
