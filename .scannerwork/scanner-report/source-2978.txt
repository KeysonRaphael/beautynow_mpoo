package kn.beautynow.dominio.clienteefornecedor;

import java.util.List;

public class Agenda {
    private int id;
    private List<Atividade> calendario;

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private List<Atividade> getCalendario() {
        return calendario;
    }

    private void setCalendario(List<Atividade> calendario) {
        this.calendario = calendario;
    }

    private void inserirAtividade(Atividade atividade){
        this.calendario.add(atividade);
    }
}
