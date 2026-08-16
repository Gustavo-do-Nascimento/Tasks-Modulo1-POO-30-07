import javax.swing.JOptionPane;

public class JogoAdivinhacao {
    public static void main(String[] args) {
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            int numero = (int)(Math.random() * 100) + 1;
            int tentativas = 0;
            boolean acertou = false;

            while (!acertou) {
                try {
                    int palpite = Integer.parseInt(
                        JOptionPane.showInputDialog(
                            null,
                            "Adivinhe o número entre 1 e 100:"
                        )
                    );

                    tentativas++;

                    if (palpite > numero) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Seu palpite está acima!"
                        );

                    } else if (palpite < numero) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Seu palpite está abaixo!"
                        );

                    } else {
                        acertou = true;

                        JOptionPane.showMessageDialog(
                            null,
                            "Acertou!"
                        );

                        JOptionPane.showMessageDialog(
                            null,
                            "Você acertou em " + tentativas + " tentativas!\n\n"
                            + classificar(tentativas)
                        );
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas um número válido!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }

            int resposta = JOptionPane.showConfirmDialog(
                null,
                "Quer jogar novamente?",
                "Novo jogo",
                JOptionPane.YES_NO_OPTION
            );

            jogarNovamente = (resposta == JOptionPane.YES_OPTION);
        }
    }

    static String classificar(int tentativas) {
        if (tentativas <= 3) {
            return "Incrível!";
        } else if (tentativas <= 6) {
            return "Muito bom!";
        } else if (tentativas <= 10) {
            return "Bom!";
        } else {
            return "Continue praticando!";
        }
    }
}