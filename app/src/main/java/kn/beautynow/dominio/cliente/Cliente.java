package kn.beautynow.dominio.cliente;

import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.usuario.Usuario;

public class Cliente {
    private String id;
    private Usuario usuario;
    private Agenda agenda;

    public Cliente() {
        usuario = new Usuario();
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

}
