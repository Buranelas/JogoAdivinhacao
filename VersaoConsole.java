public class VersaoConsole {
    private int randomNumber;

    public VersaoConsole() {
        System.out.println("Jogo de Adivinhação");
        generateRandomNumber();
        playGame();
    }

    private void generateRandomNumber() {
        randomNumber = (int) (Math.random() * 1000) + 1;
    }

    private void playGame() {
        int userGuess;
        System.out.println("Tente adivinhar um número entre 1 e 1000:");

        while (true) {
            try {
                userGuess = Integer.parseInt(System.console().readLine());
                if (userGuess < randomNumber) {
                    System.out.println("O número é maior.");
                } else if (userGuess > randomNumber) {
                    System.out.println("O número é menor.");
                } else {
                    System.out.println("Parabéns! Você adivinhou o número correto!");
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Por favor, insira um número válido.");
            }
        }
    }
    public static void main(String[] args) {
        new VersaoConsole();
    }
}
