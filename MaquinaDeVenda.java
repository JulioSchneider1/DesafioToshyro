import java.util.Arrays;

public class MaquinaDeVenda {
    private static final double[] VALORES_MOEDAS = { 0.01, 0.05, 0.10, 0.25, 0.50, 1.00 };
    private double[] quantidadeMoedasDisponiveis;
    private double[] totalMoedasInseridas;

    private static final String[] NOMES_PRODUTOS = { "Coca-Cola", "Agua", "Pastelina" };
    private static final double[] PRECOS_PRODUTOS = { 1.50, 1.00, 0.30 };
    private int[] quantidadeProdutosDisponiveis;

    public MaquinaDeVenda() {
        this.quantidadeMoedasDisponiveis = new double[VALORES_MOEDAS.length];
        this.totalMoedasInseridas = new double[VALORES_MOEDAS.length];

        this.quantidadeProdutosDisponiveis = new int[NOMES_PRODUTOS.length];

        Arrays.fill(quantidadeMoedasDisponiveis, 10);
        Arrays.fill(quantidadeProdutosDisponiveis, 10);
    }

    public double inserirMoedas(double[] moedas) {
        double totalInserido = 0;

        for (int i = 0; i < moedas.length; i++) {
            int indiceMoeda = obterIndiceMoeda(moedas[i]);

            if (indiceMoeda != -1 && quantidadeMoedasDisponiveis[indiceMoeda] > 0) {
                quantidadeMoedasDisponiveis[indiceMoeda]--;
                totalMoedasInseridas[indiceMoeda] += moedas[i];
                totalInserido += moedas[i];
            } else {
                System.out.println("Moeda não aceita ou sem estoque: " + moedas[i]);
            }
        }

        return totalInserido;
    }

    public void dispensarProduto(String nomeProduto, double totalInserido) {
        int indiceProduto = obterIndiceProduto(nomeProduto);

        if (indiceProduto != -1 && quantidadeProdutosDisponiveis[indiceProduto] > 0) {
            double precoProduto = PRECOS_PRODUTOS[indiceProduto];

            if (totalInserido >= precoProduto) {
                quantidadeProdutosDisponiveis[indiceProduto]--;
                double troco = totalInserido - precoProduto;

                System.out.printf("%s =%.2f%n", nomeProduto, troco);
            } else {
                System.out.println("Dinheiro insuficiente para comprar " + nomeProduto);
            }
        } else {
            System.out.println("Produto não disponível: " + nomeProduto);
        }
    }

    public void obterTroco(double totalInserido) {
        double troco = totalInserido;
    
        System.out.print("= ");
    
        boolean trocoEntregue = false;
        for (int i = 0; i < VALORES_MOEDAS.length; i++) {
            while (troco >= VALORES_MOEDAS[i] && quantidadeMoedasDisponiveis[i] > 0) {
                troco -= VALORES_MOEDAS[i];
                quantidadeMoedasDisponiveis[i]--;
                System.out.printf("%.2f ", VALORES_MOEDAS[i]);
                trocoEntregue = true;
            }
        }
    
        if (!trocoEntregue) {
            System.out.println("NO_CHANGE");
        } else {
            System.out.println();
        }
    }
    
    
    

    private int obterIndiceMoeda(double valor) {
        for (int i = 0; i < VALORES_MOEDAS.length; i++) {
            if (VALORES_MOEDAS[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    private int obterIndiceProduto(String nomeProduto) {
        for (int i = 0; i < NOMES_PRODUTOS.length; i++) {
            if (NOMES_PRODUTOS[i].equalsIgnoreCase(nomeProduto)) {
                return i;
            }
        }
        return -1;
    }
}
