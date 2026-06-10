import javax.swing.*;

public class Payment {

    public static boolean processPayment(double amount) {

        int option = JOptionPane.showConfirmDialog(
                null,
                "Pay ₹" + amount + "?",
                "Payment Gateway",
                JOptionPane.YES_NO_OPTION);

        return option == JOptionPane.YES_OPTION;
    }
}