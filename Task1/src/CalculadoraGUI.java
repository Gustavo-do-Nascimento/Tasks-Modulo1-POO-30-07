import javax.swing.JOptionPane;

public class CalculadoraGUI {
    public static void main(String[] args) {
        // Cada texto vira um botão; o índice do botão é o retorno do diálogo.
        String[] operacoes = {"Somar", "Subtrair", "Multiplicar", "Dividir", "Sair"};

        while (true) {
            int op = JOptionPane.showOptionDialog(
                null,                          // sem janela pai
                "Escolha a operação:",         // mensagem
                "Calculadora",                 // título
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,  // ícone de pergunta
                null,
                operacoes,                     // os botões
                operacoes[0]                   // botão em foco
            );

            // Índice 4 = "Sair"; CLOSED_OPTION = usuário clicou no X.
            if (op == 4 || op == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Até a próxima! 👋");
                break;
            }

            try {
                // parseDouble converte o texto digitado em número.
                double a = Double.parseDouble(JOptionPane.showInputDialog(null, "Primeiro número:"));
                double b = Double.parseDouble(JOptionPane.showInputDialog(null, "Segundo número:"));

                double resultado = calcular(a, b, op);

                String simbolo;
                switch (op) {
                    case 0:  simbolo = "+"; break;
                    case 1:  simbolo = "-"; break;
                    case 2:  simbolo = "×"; break;
                    default: simbolo = "÷";
                }

                // HTML: número do resultado em azul e negrito.
                String html = String.format(
                    "<html>Resultado de <b>%.2f %s %.2f</b> = " +
                    "<font color='#2563eb'><b>%.2f</b></font></html>",
                    a, simbolo, b, resultado
                );
                JOptionPane.showMessageDialog(null, html, "Resultado", JOptionPane.INFORMATION_MESSAGE);

            } catch (ArithmeticException e) {
                // Divisão por zero → janela de erro (requisito da task).
                JOptionPane.showMessageDialog(null, "Não é possível dividir por zero!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                // Usuário digitou algo que não é número (ou cancelou o input).
                JOptionPane.showMessageDialog(null, "Digite apenas números válidos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método estático que concentra a lógica das 4 operações.
    static double calcular(double a, double b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3:
                // Com double, a/0 daria "Infinity" — então checamos na mão.
                if (b == 0) throw new ArithmeticException("divisão por zero");
                return a / b;
            default: throw new IllegalArgumentException("operação inválida");
        }
    }
}
