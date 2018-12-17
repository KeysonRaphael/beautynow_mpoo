package kn.beautynow.dominio.fornecedor;

import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;

public class Promocao {
    private String id;
    private String nome;
    private int valor;
    private Servico servico;
    private Fornecedor fornecedor;

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

    private int getValor() {
        return valor;
    }

    private void setValor(int valor) {
        this.valor = valor;
    }

    private Servico getServico() {
        return servico;
    }

    private void setServico(Servico servico) {
        this.servico = servico;
    }

    private Fornecedor getFornecedor() {
        return fornecedor;
    }

    private void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
