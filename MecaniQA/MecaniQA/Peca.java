/** Classe Peca */

public class Peca {

    private int codigo;
    private String nome;
    private String fabricante;
    private int quantidadeEstoque;
    private double precoCusto;
    private double precoVenda;

    public Peca(int codigo, String nome, String fabricante,
                int quantidadeEstoque, double precoCusto, double precoVenda) {
        this.codigo = codigo;
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return String.format(
            "Código: %d | Nome: %s | Fabricante: %s | Qtd. Estoque: %d | " +
            "Preço Custo: R$ %.2f | Preço Venda: R$ %.2f",
            codigo, nome, fabricante, quantidadeEstoque, precoCusto, precoVenda
        );
    }
}