package kn.beautynow.dominio.clienteefornecedor;

public class Avaliacao {
    private String id;
    private int preco;
    private int qds;
    private int qda;
    private int pontualidade;

    private int getPreco() {
        return preco;
    }

    private void setPreco(int preco) {
        this.preco = preco;
    }

    private int getQds() {
        return qds;
    }

    private void setQds(int qds) {
        this.qds = qds;
    }

    private int getQda() {
        return qda;
    }

    private void setQda(int qda) {
        this.qda = qda;
    }

    private int getPontualidade() {
        return pontualidade;
    }

    private void setPontualidade(int pontualidade) {
        this.pontualidade = pontualidade;
    }

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }
}
