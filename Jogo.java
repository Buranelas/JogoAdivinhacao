import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jogo extends JFrame {
    private JTextField guessField;
    private JButton checkButton;
    private JLabel titleLabel;
    private JTextArea historyArea;

    private int randomNumber;
    private Color originalColor;

    public Jogo() {
        setTitle("Jogo de Adivinhação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Título
        titleLabel = new JLabel("Jogo de Adivinhação");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Painel para entrada e botão
        JPanel inputPanel = new JPanel(new FlowLayout());
        guessField = new JTextField(20);
        checkButton = new JButton("Verificar");
        inputPanel.add(guessField);
        inputPanel.add(checkButton);
        add(inputPanel, BorderLayout.CENTER);

        // Painel para o label e a área de texto
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel instructionLabel = new JLabel("Tente adivinhar o número de 1 a 100");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bottomPanel.add(instructionLabel, BorderLayout.NORTH);

        // TextArea para histórico
        historyArea = new JTextArea(22, 10);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 24));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Alterando o tamanho e a cor do botão
        checkButton.setFont(new Font("Arial", Font.BOLD, 18)); 
        checkButton.setForeground(Color.BLUE); 

        originalColor = historyArea.getForeground(); 

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
        randomNumber = (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            if (userGuess < randomNumber) {
                blinkText("O número " + userGuess + " é menor que o sorteado.\n", Color.RED);
            } else if (userGuess > randomNumber) {
                blinkText("O número " + userGuess + " é maior que o sorteado.\n", Color.RED);
            } else {
                blinkText("Parabéns! O número " + userGuess + " é correto!\n", Color.GREEN);
                guessField.setEditable(false);
                checkButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        guessField.setText(""); 
        guessField.requestFocus(); 
    }

    // Método para fazer o texto piscar em uma cor específica
    private void blinkText(String text, Color color) {
        final Color finalColor = color; 
        final String finalText = text; 
        Timer timer = new Timer(500, new ActionListener() {
            boolean visible = true;
    
            public void actionPerformed(ActionEvent e) {
                if (visible) {
                    historyArea.setForeground(finalColor);
                    historyArea.append(finalText);
                } else {
                    historyArea.setForeground(originalColor);
                    historyArea.setText(historyArea.getText().substring(0, historyArea.getText().length() - finalText.length()));
                }
                visible = !visible;
            }
        });
        timer.setRepeats(false); 
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }
}
