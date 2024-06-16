import java.awt.*;
import java.awt.event.*;

public class AwtCalculator extends Frame {
    private TextField textField;
    private double num1, num2, result;
    private String operator;

    public AwtCalculator() {
        operator = "";
        textField = new TextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setEditable(false);

        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            final int num = i;
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + num);
                }
            });
        }

        Button addButton = createOperatorButton("+");
        Button subButton = createOperatorButton("-");
        Button mulButton = createOperatorButton("*");
        Button divButton = createOperatorButton("/");
        Button eqButton = createOperatorButton("=");
        Button clrButton = createOperatorButton("C");

        Panel buttonPanel = new Panel(new GridLayout(4, 4, 10, 10));
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(addButton);
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(subButton);
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(numberButtons[i]);
        }
        buttonPanel.add(mulButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(divButton);
        buttonPanel.add(eqButton);
        buttonPanel.add(clrButton);

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setTitle("AWT Calculator");
        setSize(300, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    private Button createOperatorButton(String label) {
        Button button = new Button(label);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (Character.isDigit(command.charAt(0))) {
                    textField.setText(textField.getText() + command);
                } else if (command.equals("=")) {
                    num2 = Double.parseDouble(textField.getText());
                    performOperation();
                    operator = "";
                } else if (command.equals("C")) {
                    textField.setText("");
                    num1 = num2 = result = 0;
                    operator = "";
                } else {
                    if (!operator.isEmpty()) {
                        num2 = Double.parseDouble(textField.getText());
                        performOperation();
                    }
                    operator = command;
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                }
            }
        });
        return button;
    }

    private void performOperation() {
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
                    textField.setText("Error");
                    return;
                }
                break;
        }
        textField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        new AwtCalculator();
    }
}
