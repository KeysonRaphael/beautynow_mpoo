package kn.beautynow.dominio.clienteefornecedor;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private int id;
    private ArrayList<Atividade> calendario =  new ArrayList<Atividade>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Atividade> getCalendario() {
        return calendario;
    }

    public void setCalendario(ArrayList<Atividade> calendario) {
        this.calendario = calendario;
    }

    public void inserirAtividade(Atividade atividade){
        this.calendario.add(atividade);
    }
}
