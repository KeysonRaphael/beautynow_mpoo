package kn.beautynow.dominio.clienteefornecedor;

import java.util.Date;

import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.usuario.Endereco;

public class Atividade {
    private String id;
    private Date data;
    private Cliente cliente;
    private Fornecedor fornecedor;
    private Endereco endereco;
    private Servico servico;

    private Date getData() {
        return data;
    }

    private void setData(Date data) {
        this.data = data;
    }

    private Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private Fornecedor getFornecedor() {
        return fornecedor;
    }

    private void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    private Endereco getEndereco() {
        return endereco;
    }

    private void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    private Servico getServico() {
        return servico;
    }

    private void setServico(Servico servico) {
        this.servico = servico;
    }

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }
}
