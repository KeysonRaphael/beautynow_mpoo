package kn.beautynow.dominio.fornecedor;

import android.graphics.Bitmap;

public class Servico {
    private String id;
    private String idFornecedor;
    private String descricao;
    private Bitmap imagem;
    private Bitmap imagemGaleria;
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nome) {
        this.descricao = nome;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public void setImagemGaleria(Bitmap bitmap) {
        this.imagemGaleria = bitmap;
    }

    public Bitmap getImagemGaleria() {
        return imagemGaleria;
    }
}
