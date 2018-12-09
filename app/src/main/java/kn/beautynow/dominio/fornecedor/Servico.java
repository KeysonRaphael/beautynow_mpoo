package kn.beautynow.dominio.fornecedor;

public class Servico {
    private String id;
    private String nome;
    private Imagem imagem;

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private Imagem getImagem() {
        return imagem;
    }

    private void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }
}
