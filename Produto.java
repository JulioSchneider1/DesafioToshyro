public class Produto {
    private String nome;
    private double custo;

    public Produto(String nome, double custo) {
        this.nome = nome;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public double getCusto() {
        return custo;
    }
}