package kn.beautynow.dominio.fornecedor;

import org.w3c.dom.Text;

public class Imagem {
    private int id;
    private String descricao;
    private int altura;
    private int largura;
    private Text imagem;

    private String getDescricao() {
        return descricao;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private int getAltura() {
        return altura;
    }

    private void setAltura(int altura) {
        this.altura = altura;
    }

    private int getLargura() {
        return largura;
    }

    private void setLargura(int largura) {
        this.largura = largura;
    }

    private Text getImagem() {
        return imagem;
    }

    private void setImagem(Text imagem) {
        this.imagem = imagem;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}

