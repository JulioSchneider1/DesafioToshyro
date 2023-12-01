import java.util.Arrays;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        MaquinaDeVenda maquinaDeVenda = new MaquinaDeVenda();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira moedas e solicite um produto: ");
        String[] entradaUsuario = scanner.nextLine().split(" ");

        double[] valoresMoedas = new double[entradaUsuario.length];
        int indiceValoresMoedas = 0;

        for (String entrada : entradaUsuario) {
            try {
                double valor = Double.parseDouble(entrada);
                valoresMoedas[indiceValoresMoedas++] = valor;
            } catch (NumberFormatException ignored) {
                // Ignorar strings que não são valores numéricos
            }
        }

        valoresMoedas = Arrays.copyOf(valoresMoedas, indiceValoresMoedas);

        double totalInserido = maquinaDeVenda.inserirMoedas(valoresMoedas);

        // Verificar se há uma solicitação de produto e CHANGE no final
        if (entradaUsuario.length > indiceValoresMoedas) {
            String operacao = entradaUsuario[indiceValoresMoedas];
            if (operacao.equalsIgnoreCase("CHANGE")) {
                maquinaDeVenda.obterTroco(totalInserido);
            } else {
                maquinaDeVenda.dispensarProduto(operacao, totalInserido);
            }
        }
    }
}
