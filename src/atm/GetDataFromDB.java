/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Joe-Joe
 */
public class GetDataFromDB {

    private String[] list;

    GetDataFromDB() {

        try {

            String codedSent = URLEncoder.encode("info", "UTF-8");

            URL url = new URL("https://webbank-gvasilski.rhcloud.com/GetAllAccountsATM");
            java.net.URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + codedSent);
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String decodedString;

            while ((decodedString = in.readLine()) != null) {

                System.out.println(decodedString);

                list = decodedString.split("[,]");
            }
            in.close();

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public String[] getList() {
        return list;
    }
}
