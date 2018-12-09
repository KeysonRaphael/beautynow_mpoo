package kn.beautynow.dominio.cliente;

import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Avaliacao;
import kn.beautynow.dominio.usuario.Usuario;

public class Cliente {
    private String id;
    private Usuario usuario;
    private Agenda agenda;
    private Avaliacao avaliacao;

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

    private Agenda getAgenda() {
        return agenda;
    }

    private void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    private Avaliacao getAvaliacao() {
        return avaliacao;
    }

    private void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
}
