package kn.beautynow.dominio.usuario;

public class Endereco {
    private String rua = "";
    private String numero = "";
    private String bairro = "";
    private String cidade = "";
    private String estado = "";
    private String complemento = "";
    private String cep = "";

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String printEndereco() {
        return "Rua " + rua + " nº " + numero + " - " + bairro + ", " + cidade + " - " + estado + " - " + cep;
    }

    public String printEnderecoMaps() {
        return "Rua " + rua + ", nº " + numero + ", " + bairro + ", " + cidade + " - " + estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
