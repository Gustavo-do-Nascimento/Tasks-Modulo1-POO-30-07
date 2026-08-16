import javax.swing.JOptionPane;

public class ConversorTemperatura {
    public static void main(String[] args) {
        String[] conversoes = {
            "°C → °F",
            "°C → K",
            "°F → °C",
            "°F → K",
            "K → °C",
            "K → °F",
            "Sair"
        };

        while (true) {
            int tipo = JOptionPane.showOptionDialog(
                null,
                "Escolha a conversão:",
                "Conversor de Temperatura",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                conversoes,
                conversoes[0]
            );

            // Sair ou fechar a janela
            if (tipo == 6 || tipo == JOptionPane.CLOSED_OPTION) {
                break;
            }

            try {
                double valor = Double.parseDouble(
                    JOptionPane.showInputDialog(
                        null,
                        "Digite o valor em " + 
                        (tipo == 0 || tipo == 1 ? "°C" :
                         tipo == 2 || tipo == 3 ? "°F" : "K") + ":"
                    )
                );

                // Kelvin não pode ser negativo
                if ((tipo == 4 || tipo == 5) && valor < 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "A temperatura em Kelvin não pode ser negativa!",
                        "Valor inválido",
                        JOptionPane.WARNING_MESSAGE
                    );
                    continue;
                }

                double resultado = converter(valor, tipo);

                String unidadeOrigem;
                String unidadeDestino;

                switch (tipo) {
                    case 0:
                        unidadeOrigem = "°C";
                        unidadeDestino = "°F";
                        break;
                    case 1:
                        unidadeOrigem = "°C";
                        unidadeDestino = "K";
                        break;
                    case 2:
                        unidadeOrigem = "°F";
                        unidadeDestino = "°C";
                        break;
                    case 3:
                        unidadeOrigem = "°F";
                        unidadeDestino = "K";
                        break;
                    case 4:
                        unidadeOrigem = "K";
                        unidadeDestino = "°C";
                        break;
                    default:
                        unidadeOrigem = "K";
                        unidadeDestino = "°F";
                }

                JOptionPane.showMessageDialog(
                    null,
                    String.format(
                        "%.2f %s = %.2f %s",
                        valor,
                        unidadeOrigem,
                        resultado,
                        unidadeDestino
                    ),
                    "Resultado",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Digite um número válido!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    static double converter(double valor, int tipo) {
        switch (tipo) {
            case 0:
                // °C → °F
                return (valor * 9 / 5) + 32;

            case 1:
                // °C → K
                return valor + 273.15;

            case 2:
                // °F → °C
                return (valor - 32) * 5 / 9;

            case 3:
                // °F → K
                return (valor - 32) * 5 / 9 + 273.15;

            case 4:
                // K → °C
                return valor - 273.15;

            case 5:
                // K → °F
                return (valor - 273.15) * 9 / 5 + 32;

            default:
                throw new IllegalArgumentException("Conversão inválida");
        }
    }
}