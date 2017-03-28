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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author GOGO
 */
public class PINchange implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel label;

    //initialize four password fiels with only one possible input
    private JPasswordField number1 = new JPasswordField(1);
    private JPasswordField number2 = new JPasswordField(1);
    private JPasswordField number3 = new JPasswordField(1);
    private JPasswordField number4 = new JPasswordField(1);

    //the numeric buttons
    private JButton[] buttons = new JButton[10];
    private JButton buttonEmpty1 = new JButton();
    private JButton buttonEmpty2 = new JButton();

    private Font font1 = new Font("DialogInput", Font.BOLD, 20);
    private JPanel centralConsole = new JPanel();
    private JButton btn1, btn2, btn3, btn4, btn5, btn6;
    private JPanel leftPanel, rightPanel;

    private Servises service;

    private JPanel topPanel;

    //some other attributes for the ActionListener
    private int number;
    private int count;

    private String account;

    //the construct
    public PINchange() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //this the top panel of the grid
        topPanel = new JPanel();
        topPanel.setBackground(Color.green);

        //this is the central panel of the grid
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel upper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel down = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String prompt = "<= ABORT    INSERT YOUR PIN BELOW";
        label = new JLabel(prompt);
        upper.add(label);
        label.setFont(font1);

        //here are the password input fields
        down.add(number1);
        number1.setFont(font1);
        number1.setBackground(Color.green);

        down.add(number2);
        number2.setFont(font1);
        number2.setBackground(Color.green);

        down.add(number3);
        number3.setFont(font1);
        number3.setBackground(Color.green);

        down.add(number4);
        number4.setFont(font1);
        number4.setBackground(Color.green);

        //two panels in the middle of the sreen
        upper.setBackground(Color.green);
        down.setBackground(Color.green);

        centerPanel.add(upper, BorderLayout.NORTH);
        centerPanel.add(down, BorderLayout.CENTER);

        //here is the bottom panel of the grid
        JLabel confirm = new JLabel("ENTER =>");
        JLabel cancel = new JLabel("<= CLEAR");
        confirm.setFont(font1);
        cancel.setFont(font1);

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmPanel.add(confirm);
        confirmPanel.setBackground(Color.green);

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cancelPanel.add(cancel);
        cancelPanel.setBackground(Color.green);

        //here the numeric buttons for password input will be added
        JPanel numericButtons = new JPanel(new GridLayout(4, 3));
        numericButtons.setBackground(Color.white);

        //create the buttons and add ActionListener
        for (int i = 0; i < 9; i++) {
            if (i <= 9) {
                buttons[i] = new JButton("" + (i + 1));
                numericButtons.add(buttons[i]);
                buttons[i].addActionListener(this);
            }
        }

        numericButtons.add(buttonEmpty1);

        buttons[9] = new JButton(" 0 ");
        numericButtons.add(buttons[9]);
        buttons[9].addActionListener(this);

        numericButtons.add(buttonEmpty2);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(cancelPanel, BorderLayout.WEST);
        bottomPanel.add(confirmPanel, BorderLayout.EAST);
        bottomPanel.add(numericButtons, BorderLayout.CENTER);

        //add all grid panel to the central console
        centralConsole.setLayout(new GridLayout(3, 1));
        centralConsole.add(topPanel);
        centralConsole.add(centerPanel);
        centralConsole.add(bottomPanel);

        //here is the left, center and right panel of the main one
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));

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

        //add all panels to the main one
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(centralConsole, BorderLayout.CENTER);

        btn6.addActionListener(new EnterListener());
        btn3.addActionListener(new CancelListener());
        btn2.addActionListener(new AbortListener());
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == buttons[i]) {
                count++;
                if (i < 9) {
                    number = i + 1;
                }
                if (i == 9) {
                    number = 0;
                }
            }

            if (count == 1) {
                number1.setText("" + number);
            }
            if (count == 2) {
                number2.setText("" + number);
            }
            if (count == 3) {
                number3.setText("" + number);
            }
            if (count == 4) {
                number4.setText("" + number);
            }
        }
    }

    private class AbortListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service = new Servises();
            service.setFrame(frame);
        }

    }

    private class EnterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String num1 = number1.getText();
            String num2 = number2.getText();
            String num3 = number3.getText();
            String num4 = number4.getText();

            if (num1.length() != 0 && num2.length() != 0
                    && num3.length() != 0 && num4.length() != 0) {
                count = 0;
                String fullPin = num1 + num2 + num3 + num4;
                //int PinChange = Integer.parseInt(fullPin);
                //getAccountNumber();
                writeOnTheCardchip(fullPin);
                service = new Servises();
                service.setFrame(frame);
            }
        }
    }

    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            count = 0;
            number1.setText("");
            number2.setText("");
            number3.setText("");
            number4.setText("");
        }
    }

    private void writeOnTheCardchip(String PINchanged) {

        try {
            
            String accountNumberSend = "" + DataFromDB.getAccountNumber();

            String infoToSend = accountNumberSend + "," + PINchanged ;

            String detailsCodedString = URLEncoder.encode(infoToSend, "UTF-8");

            URL url = new URL("https://webbank-gvasilski.rhcloud.com/ChangePinCard");
            java.net.URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + detailsCodedString);
            out.close();

            //receive the feedback
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String feedback;
            while ((feedback = in.readLine()) != null) {
                label.setText(feedback);
            }
            in.close();

        } catch (Exception ex) {
            label.setText("ERROR: " + ex.getMessage());
        }
    }
}
