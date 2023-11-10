package org.example.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class CalculatorApp extends JFrame implements ActionListener {
    private final JTextField textField;
    private double num1, num2, result, memory = 0;
    private String operator, input;
    public CalculatorApp() {
        setTitle("计算器");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        textField = new JTextField();
        textField.setFont(new Font("Arial",Font.BOLD,50));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));
        String[] buttonLabels = {
                " ", "CE", " ","<-"," ","C",
                "MC", "7", "8", "9", "/", "√",
                "MR", "4", "5", "6", "*", "%",
                "MS", "1", "2", "3", "-","1/x",
                "M+", "0", "±", ".", "+"
        };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial",Font.BOLD,40));
            buttonPanel.add(button);
        }
        JButton equalButton = new JButton("=");
        equalButton.addActionListener(this);
        equalButton.setBackground(Color.RED);
        equalButton.setFont(new Font("Arial",Font.BOLD,40));
        buttonPanel.add(equalButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789".contains(command) || ".".equals(command)) {
            input += command;
            textField.setText(input);
        } else if ("+-*/".contains(command)) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                operator = command;
                input = "";
            }
        } else if ("=".equals(command)) {
            if (!input.isEmpty()) {
                num2 = Double.parseDouble(input);
                calculateResult();
                input = Double.toString(result);
                textField.setText(input);
            }
        } else if ("CE".equals(command)) {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                textField.setText(input);
            }
        }else if ("C".equals(command)) {
            input = "";
            textField.setText("");
        } else if ("MC".equals(command)) {
            memory = 0;
        } else if ("MR".equals(command)) {
            input = Double.toString(memory);
            textField.setText(input);
        } else if ("MS".equals(command)) {
            memory = Double.parseDouble(input);
        } else if ("M+".equals(command)) {
            memory += Double.parseDouble(input);
        } else if ("√".equals(command)) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                result = Math.sqrt(num1);
                input = Double.toString(result);
                textField.setText(input);
            }
        } else if ("1/x".equals(command)) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                if (num1 != 0) {
                    result = 1 / num1;
                    input = Double.toString(result);
                    textField.setText(input);
                } else {
                    input = "";
                    textField.setText("错误：除数不能为零");
                }
            }
        }else if ("<-".equals(command)) {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                textField.setText(input);
            }
        }else if ("±".equals(command)) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                result = -num1;
                input = Double.toString(result);
                textField.setText(input);
            }
        }else if ("%".equals(command)) {
            if (!input.isEmpty()) {
                num1 = Double.parseDouble(input);
                result = num1 / 100;
                input = Double.toString(result);
                textField.setText(input);
            }
        }
    }

    private void calculateResult() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    input = "";
                    textField.setText("错误：除数不能为零");
                }
                break;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }
}
