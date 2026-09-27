/** Classe Servico  */

public class Servico {

    private int codigo;
    private String nome;
    private String descricao;
    private double tempoEstimado; 
    private double valorMaoDeObra;

    public Servico(int codigo, String nome, String descricao,
            double tempoEstimado, double valorMaoDeObra) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.tempoEstimado = tempoEstimado;
        this.valorMaoDeObra = valorMaoDeObra;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public double getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
    }

    @Override
    public String toString() {
        return String.format(
            "Código: %d | Nome: %s | Descrição: %s | Tempo Estimado: %.1fh | " +
            "Valor Mão de Obra: R$ %.2f",
            codigo, nome, descricao, tempoEstimado, valorMaoDeObra
        );
    }
}