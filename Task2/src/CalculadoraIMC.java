import javax.swing.JOptionPane;

public class CalculadoraIMC {
    public static void main(String[] args) {
        try {
            // Capturar nome, peso e altura
            String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");
            double peso = Double.parseDouble(
                JOptionPane.showInputDialog(null, "Digite seu peso em kg:")
            );
            double altura = Double.parseDouble(
                JOptionPane.showInputDialog(null, "Digite sua altura em metros:")
            );

            // Calcular IMC
            double imc = peso / (altura * altura);

            // Determinar categoria e cor
            String[] categoria = getCategoria(imc);

            // Exibir resultado com HTML
            String html = String.format(
                "<html>" +
                "<b>Nome:</b> %s<br>" +
                "<b>IMC:</b> %.2f<br>" +
                "<b>Categoria:</b> <font color='%s'><b>%s</b></font>" +
                "</html>",
                nome, imc, categoria[1], categoria[0]
            );

            JOptionPane.showMessageDialog(
                null,
                html,
                "Resultado do IMC",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {
            // Erro caso o usuário digite algo que não seja número
            JOptionPane.showMessageDialog(
                null,
                "Digite apenas números válidos!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    static String[] getCategoria(double imc) {
        if (imc < 18.5) {
            return new String[]{"Abaixo do peso", "#3b82f6"};
        } else if (imc < 25.0) {
            return new String[]{"Peso normal", "#16a34a"};
        } else if (imc < 30.0) {
            return new String[]{"Sobrepeso", "#f97316"};
        } else {
            return new String[]{"Obesidade", "#dc2626"};
        }
    }
}
