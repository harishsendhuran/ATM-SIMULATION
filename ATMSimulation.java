import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMSimulation {

    private double balance = 1000.0; // Initial balance

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMSimulation::new);
    }

    public ATMSimulation() {
        // Create the main frame
        JFrame frame = new JFrame("ATM Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create and add the title label
        JLabel title = new JLabel("ATM Simulation", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        // Create buttons for ATM options
        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit Money");
        JButton withdrawBtn = new JButton("Withdraw Money");
        JButton exitBtn = new JButton("Exit");

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(checkBalanceBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(exitBtn);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Action listeners for buttons
        checkBalanceBtn.addActionListener(e -> showMessage("Your current balance is: $" + String.format("%.2f", balance)));

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        balance += amount;
                        showMessage("Successfully deposited: $" + String.format("%.2f", amount));
                    } else {
                        showMessage("Please enter a positive amount!");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid input! Please enter a valid number.");
                }
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        if (amount <= balance) {
                            balance -= amount;
                            showMessage("Successfully withdrawn: $" + String.format("%.2f", amount));
                        } else {
                            showMessage("Insufficient balance!");
                        }
                    } else {
                        showMessage("Please enter a positive amount!");
                    }
                } catch (NumberFormatException ex) {
                    showMessage("Invalid input! Please enter a valid number.");
                }
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using the ATM. Goodbye!");
            frame.dispose();
        });

        // Show the frame
        frame.setVisible(true);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
