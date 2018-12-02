package kn.beautynow.dominio;

public class Fornecedor {
    private String id;
    private Usuario usuario;
    private Servicos servicos;
    private Galeria galeria;
    private Avaliacao avaliacao;
    private Agenda agenda;
    private Promocao promocao;

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private Galeria getGaleria() {
        return galeria;
    }

    private void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }

    private Avaliacao getAvaliacao() {
        return avaliacao;
    }

    private void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    private Agenda getAgenda() {
        return agenda;
    }

    private void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    private Servicos getServicos() {
        return servicos;
    }

    private void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    private Promocao getPromocao() {
        return promocao;
    }

    private void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }
}
