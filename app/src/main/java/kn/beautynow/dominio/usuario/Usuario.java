package kn.beautynow.dominio.usuario;

public class Usuario {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String celular;
    private Endereco endereco;

    private Usuario{
        id = "";
        nome = "";
        cpf = "";
        telefone = "";
        celular = "";
        endereco = new Endereco();

    }

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
