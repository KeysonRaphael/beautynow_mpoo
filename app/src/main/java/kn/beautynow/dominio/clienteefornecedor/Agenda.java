package kn.beautynow.dominio.clienteefornecedor;

import java.util.ArrayList;

public class Agenda {
    private int id;
    private ArrayList<Atividade> calendario =  new ArrayList<>();

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

}
