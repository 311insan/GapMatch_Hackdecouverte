package com.tripledigitaldaggers;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class UserInterface {
    public JFrame frame;
    public JButton button;
    private JTextField inputField;
    private JButton submitButton;
    private String userText;
    private CompletableFuture<String> pendingInput;


    public UserInterface() {
        frame = new JFrame("Test!");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        addButtons();
    }

    public void addButtons() {
        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            userText = inputField.getText();
            inputField.setText("");
            if (pendingInput != null && !pendingInput.isDone()) {
                pendingInput.complete(userText);
            }
        });
        frame.add(inputField);
        frame.add(submitButton);
    }

    public void display() {
        frame.getContentPane().setBackground(new Color(174, 204, 228));
        frame.setVisible(true);
    }

    public void displayHeader(String arg) {
        JLabel label = new JLabel(arg);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        frame.add(label);
    }

    public String waitForInput() {
        pendingInput = new CompletableFuture<>();
        try {
            return pendingInput.get();
        } catch (Exception e) {
            return "";
        }
    }

}
