package kn.beautynow.dominio.clienteefornecedor;

import java.util.Date;

import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.usuario.Endereco;

public class Atividade {
    private String id;
    private String data;
    private String hora;
    private String cliente;
    private String fornecedor;
    private String endereco;
    private String servico;
    private String valor;
    private String ativo;
    private String finalizado;
    private String notaAtribuida;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }

    public String getNotaAtribuida() {
        return notaAtribuida;
    }

    public void setNotaAtribuida(String notaAtribuida) {
        this.notaAtribuida = notaAtribuida;
    }
}
