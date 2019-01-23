package kn.beautynow.dominio.fornecedor;

import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.usuario.Usuario;

public class Fornecedor {
    private String id;
    private Usuario usuario;
    private Servicos servicos;
    private Agenda agenda;

    public Fornecedor() {
        usuario = new Usuario();
        servicos = new Servicos();
        agenda = new Agenda();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

}
