/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Jack Jones
 */
public class Money {

    private int amount;
    private int yPositionIncrement = 50;
    private int xPos = 50;

    Money(int amountIn) {
        amount = amountIn;

        //check if ATM IS NOT EMPTY
        if (CashAvailable.cashINMashine() == 0) {
            System.out.println("NO CASH IN THE ATM MACHINE");
        } //check if amount is bigger than the cash in the ATM
        else {
            //check if ATM is not empty
            if (CashAvailable.cashINMashine() < amount) {
                System.out.println("NOT ENOUGH CASH IN THE ATM");
            } else {
                //check if the remainder of the amount is 0
                if (amount % 20 == 0) {
                    //check if number of £20 notes is 0
                    if (CashAvailable.getNumberOf20notes() == 0 && CashAvailable.getNumberOf10notes() >= (amount / 10)) {
                        int required10notes = amount / 10;

                        if (required10notes > 10 && required10notes < 20) {

                            for (int i = 0; i < 10; i++) {
                                new Label10pound(50, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            int left = required10notes % 10;
                            for (int j = 0; j < left; j++) {
                                new Label10pound(300, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                        }
                        if (required10notes > 20 && required10notes < 30) {
                            for (int i = 0; i < 10; i++) {
                                new Label10pound(50, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            for (int a = 0; a < 10; a++) {
                                new Label10pound(300, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            for (int j = 0; j < required10notes % 10; j++) {
                                new Label10pound(550, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                        }
                        if (required10notes == 30) {
                            for (int i = 0; i < 10; i++) {
                                new Label10pound(50, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            for (int a = 0; a < 10; a++) {
                                new Label10pound(300, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            for (int j = 0; j < 10; j++) {
                                new Label10pound(550, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                        }
                        if (required10notes == 20) {
                            for (int i = 0; i < 10; i++) {
                                new Label10pound(50, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                            for (int a = 0; a < 10; a++) {
                                new Label10pound(300, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                            yPositionIncrement = 50;
                        }
                        if (required10notes <= 10) {
                            for (int i = 0; i < required10notes; i++) {
                                new Label10pound(50, yPositionIncrement);
                                yPositionIncrement = yPositionIncrement + 70;
                            }
                        }
                        CashAvailable.give10notes(required10notes);
                        CashAvailable.cashOut(amount);
                        yPositionIncrement = 50;
                    } else {
                        int required20notes = amount / 20;
                        int give20n;
                        if (required20notes <= CashAvailable.getNumberOf20notes()) {
                            give20n = required20notes;

                            if (give20n > 10 && give20n < 20) {

                                for (int i = 0; i < 10; i++) {
                                    new Label20pound(50, yPositionIncrement);
                                    yPositionIncrement = yPositionIncrement + 70;
                                }
                                yPositionIncrement = 50;
                                int left = (amount - 200) / 20;
                                for (int j = 0; j < left; j++) {
                                    new Label20pound(300, yPositionIncrement);
                                    yPositionIncrement = yPositionIncrement + 70;
                                }
                            }
                            if (give20n <= 10) {
                                for (int i = 0; i < give20n; i++) {
                                    new Label20pound(50, yPositionIncrement);
                                    yPositionIncrement = yPositionIncrement + 70;
                                }
                            }
                            CashAvailable.give20notes(required20notes);
                            CashAvailable.cashOut(amount);
                            yPositionIncrement = 50;
                        } else {
                            give20n = CashAvailable.getNumberOf20notes();
                            int leftAmount = amount - (give20n * 20);
                            int required10n = leftAmount / 10;

                            if (CashAvailable.getNumberOf10notes() >= required10n) {

                                if (give20n > 10 && give20n < 20) {

                                    for (int i = 0; i < 10; i++) {
                                        new Label20pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    int left = (amount - 200) / 20;
                                    for (int j = 0; j < left; j++) {
                                        new Label20pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (give20n <= 10) {
                                    for (int i = 0; i < give20n; i++) {
                                        new Label20pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                
                                //require £10 to complete the amount
                                if (required10n > 10 && required10n < 20) {

                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    int left = required10n % 10;
                                    for (int j = 0; j < left; j++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n > 20 && required10n < 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < required10n % 10; j++) {
                                        new Label10pound(900, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < 10; j++) {
                                        new Label10pound(900, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 20) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                }
                                if (required10n <= 10) {
                                    for (int i = 0; i < required10n; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                //subtract the number of given notes
                                CashAvailable.give20notes(give20n);
                                CashAvailable.give10notes(required10n);
                                CashAvailable.cashOut(amount);
                                yPositionIncrement = 50;
                            } else {
                                System.out.println("CHOOSE LOWER AMOUNT");
                            }
                        }

                        yPositionIncrement = 50;
                    }
                }

                //check if remainder of amount is 1
                if (amount % 20 != 0) {
                    //check if amount is £10 but no £10 notes
                    if (amount == 10 && CashAvailable.getNumberOf10notes() == 0) {
                        System.out.println("NO £10 NOTES LEFT IN THE ATM");
                    }
                    if (amount == 10 && CashAvailable.getNumberOf10notes() != 0) {
                        new Label10pound(50, 50);
                        CashAvailable.give10notes(1);
                        CashAvailable.cashOut(amount);
                    } else {
                        if (amount >= 20 && CashAvailable.getNumberOf20notes() != 0 && CashAvailable.getNumberOf10notes() == 0) {
                            System.out.println("PLEASE CHOOSE EVEN AMOUNT. NO £10 NOTES");
                        } else {
                            if (CashAvailable.getNumberOf20notes() != 0 && CashAvailable.getNumberOf10notes() != 0) {
                                int required20notes = (amount - 10) / 20;
                                int give20n;

                                if (required20notes <= CashAvailable.getNumberOf20notes()) {
                                    give20n = required20notes;
                                } else {
                                    give20n = CashAvailable.getNumberOf20notes();
                                }

                                //try to give as many £20 as possible
                                if (give20n > 10 && give20n < 20) {

                                    for (int i = 0; i < 10; i++) {
                                        new Label20pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    int left = (amount - 200) / 20;
                                    for (int j = 0; j < left; j++) {
                                        new Label20pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (give20n <= 10) {
                                    for (int i = 0; i < required20notes; i++) {
                                        new Label20pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }

                                //if not enough £20 give £10 to complete the amount
                                int amountLeft = amount - (give20n * 20);
                                int required10n = amountLeft / 10;

                                if (required10n > 10 && required10n < 20) {

                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    int left = required10n % 10;
                                    for (int j = 0; j < left; j++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n > 20 && required10n < 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < required10n % 10; j++) {
                                        new Label10pound(900, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < 10; j++) {
                                        new Label10pound(900, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 20) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(600, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                }
                                if (required10n <= 10) {
                                    for (int i = 0; i < required10n; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                //subtract the number of given notes
                                CashAvailable.give20notes(give20n);
                                CashAvailable.give10notes(required10n);
                                CashAvailable.cashOut(amount);
                                yPositionIncrement = 50;

                            } else {
                                int required10n = amount / 10;

                                if (required10n > 10 && required10n < 20) {

                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    int left = required10n % 10;
                                    for (int j = 0; j < left; j++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n > 20 && required10n < 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < required10n % 10; j++) {
                                        new Label10pound(550, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 30) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int j = 0; j < 10; j++) {
                                        new Label10pound(550, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                if (required10n == 20) {
                                    for (int i = 0; i < 10; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                    for (int a = 0; a < 10; a++) {
                                        new Label10pound(300, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                    yPositionIncrement = 50;
                                }
                                if (required10n <= 10) {
                                    for (int i = 0; i < required10n; i++) {
                                        new Label10pound(50, yPositionIncrement);
                                        yPositionIncrement = yPositionIncrement + 70;
                                    }
                                }
                                CashAvailable.give10notes(required10n);
                                CashAvailable.cashOut(amount);
                                yPositionIncrement = 50;
                            }
                        }
                    }

                }
            }
        }
    }
}
