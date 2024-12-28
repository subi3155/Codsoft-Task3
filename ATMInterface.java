import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMInterface extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea resultArea;

    public ATMInterface() {
        account = new BankAccount(1000);

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JLabel label = new JLabel("Enter Amount:");
        amountField = new JTextField(15);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.deposit(amount);
                    resultArea.setText("Deposited: Rs." + amount + "\nCurrent Balance: Rs" + account.getBalance());
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid amount! Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (account.withdraw(amount)) {
                        resultArea.setText("Withdrew: Rs." + amount + "\nCurrent Balance: Rs." + account.getBalance());
                    } else {
                        resultArea.setText("Insufficient balance or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid amount! Please enter a valid number.");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("Current Balance: Rs." + account.getBalance());
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);
        panel.add(new JScrollPane(resultArea)); 
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}
