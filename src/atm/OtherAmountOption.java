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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author GOGO
 */
public class OtherAmountOption {

    private JFrame frame;
    private JPanel panel;

    private JLabel label;
    private JLabel poundSign;
    private JLabel field = new JLabel();

    private Font font1 = new Font("DialogInput", Font.BOLD, 20);
    private Font font = new Font("DialogInput", Font.BOLD, 40);
    private JButton btn1, btn2, btn3, btn4, btn5, btn6;
    private JPanel leftPanel, rightPanel, centralConsole;

    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private WithdrawConfirm confirmation;

    //the numeric buttons
    private JButton[] buttons = new JButton[10];    //new
    private JButton buttonEmpty1 = new JButton();   //new
    private JButton buttonEmpty2 = new JButton();   //new

    private int number;
    private int count;
    private String amount = "";

    public OtherAmountOption() {
        //JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(Color.green);
        label = new JLabel("PLEASE TYPE THE AMOUNT");
        label.setFont(font1);
        labelPanel.add(label);

        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        amountPanel.setBackground(Color.green);
        poundSign = new JLabel("£");
        poundSign.setFont(font1);
        field.setBackground(Color.green);
        field.setFont(font);
        amountPanel.add(poundSign);
        amountPanel.add(field);

        //here is the bottom panel of the grid
        JLabel confirm = new JLabel("ENTER =>");
        JLabel cancel = new JLabel("<= CANCEL");
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
        ButtonListener bl = new ButtonListener();
        for (int i = 0; i < 9; i++) {
            if (i <= 9) {
                buttons[i] = new JButton("" + (i + 1));
                numericButtons.add(buttons[i]);
                buttons[i].addActionListener(bl);
            }
        }

        numericButtons.add(buttonEmpty1);

        buttons[9] = new JButton(" 0 ");
        numericButtons.add(buttons[9]);
        buttons[9].addActionListener(bl);

        numericButtons.add(buttonEmpty2);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(cancelPanel, BorderLayout.WEST);
        bottomPanel.add(confirmPanel, BorderLayout.EAST);
        bottomPanel.add(numericButtons, BorderLayout.CENTER);

        centralConsole = new JPanel(new GridLayout(3, 1));
        centralConsole.add(labelPanel);
        centralConsole.add(amountPanel);
        centralConsole.add(bottomPanel);

        //buttons panel
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

        //the major panel which id added to the frame
        panel = new JPanel(new BorderLayout());

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centralConsole, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        btn6.addActionListener(new ConfirmAction());
        btn3.addActionListener(new CancelListener());

        count = 0;

    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    private class ConfirmAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String amountEntered = field.getText();
            if (amountEntered.equals("")) {
                label.setText("NO AMOUNT ENTERED");
            } else {
                int amount = Integer.parseInt(amountEntered);
                int cash = CashAvailable.cashINMashine();

                if (amount < cash) {
                    if (amount > 300) {
                        label.setText("MAX IS £300 A DAY");
                    } else {
                        if (amount % 10 == 0) {
                            confirmation = new WithdrawConfirm(frame, amount);
                        } else {
                            label.setText("Only £10 and £20 notes available");
                        }
                    }
                }
                if (cash < amount) {
                    label.setText("NOT ENOUGH CASH IN ATM");
                }
                if (cash == 0) {
                    Standby stand = new Standby();
                    stand.setFrame(frame);
                }
            }
        }
    }

    private class ButtonListener implements ActionListener {

        public ButtonListener() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < 10; i++) {
                if (e.getSource() == buttons[i]) {
                    count++;
                    if (i < 9) {
                        number = i + 1;
                        System.out.println("number is " + number);
                        amount = amount + number;
                        System.out.println("number is " + amount);
                    } else {

                        number = 0;
                        System.out.println("number is " + number);
                        amount = amount + number;
                        System.out.println("number is " + amount);
                    }
                }
            }
            if (count == 1) {

                field.setText("" + amount);
                System.out.println();
                System.out.println("amount : " + amount);
                System.out.println();
            }
            if (count == 2) {

                field.setText("" + amount);
                System.out.println();
                System.out.println("amount : " + amount);
                System.out.println();
            }
            if (count == 3) {

                field.setText("" + amount);
                System.out.println();
                System.out.println("amount : " + amount);
                System.out.println();
            }
            if (count > 3) {
                label.setText("You've reached the possible digit entry");
            }

        }
    }

    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            count = 0;
            field.setText("");
            amount = "";
        }
    }
}
