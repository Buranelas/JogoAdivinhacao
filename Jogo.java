import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jogo extends JFrame {
    private JTextField guessField;
    private JButton checkButton;
    private JLabel resultLabel;

    private int randomNumber;

    public Jogo() {
        setTitle("Jogo de Adivinhação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        JLabel guessLabel = new JLabel("Tente adivinhar um número entre 1 e 1000:");
        guessField = new JTextField(10);
        checkButton = new JButton("Verificar");
        resultLabel = new JLabel();

        add(guessLabel);
        add(guessField);
        add(checkButton);
        add(resultLabel);

        generateRandomNumber();

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        guessField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void generateRandomNumber() {
        randomNumber = (int) (Math.random() * 1000) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            if (userGuess < randomNumber) {
                resultLabel.setText("O número é maior.");
            } else if (userGuess > randomNumber) {
                resultLabel.setText("O número é menor.");
            } else {
                resultLabel.setText("Parabéns! Você adivinhou o número correto!");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Por favor, insira um número válido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }
}
