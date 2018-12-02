package kn.beautynow.dominio;

public class Endereco {
    private String id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

    private String getRua() {
        return rua;
    }

    private void setRua(String rua) {
        this.rua = rua;
    }

    private String getNumero() {
        return numero;
    }

    private void setNumero(String numero) {
        this.numero = numero;
    }

    private String getBairro() {
        return bairro;
    }

    private void setBairro(String bairro) {
        this.bairro = bairro;
    }

    private String getCidade() {
        return cidade;
    }

    private void setCidade(String cidade) {
        this.cidade = cidade;
    }

    private String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }

    private String getPais() {
        return pais;
    }

    private void setPais(String pais) {
        this.pais = pais;
    }

    private String getCep() {
        return cep;
    }

    private void setCep(String cep) {
        this.cep = cep;
    }
}
