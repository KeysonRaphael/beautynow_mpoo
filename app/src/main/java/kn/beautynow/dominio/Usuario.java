package kn.beautynow.dominio;

public class Usuario {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String celular;
    private Endereco endereco;

    private String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String getTelefone() {
        return telefone;
    }

    private void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    private String getCelular() {
        return celular;
    }

    private void setCelular(String celular) {
        this.celular = celular;
    }

    private Endereco getEndereco() {
        return endereco;
    }

    private void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
