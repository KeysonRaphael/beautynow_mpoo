package kn.beautynow.dominio.fornecedor;

import java.util.ArrayList;
import java.util.List;

public class Servicos implements Cloneable {
    private String id;
    private ArrayList<Servico> listaServicos = new ArrayList<Servico>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Servico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(ArrayList<Servico> servicos) {
        this.listaServicos = servicos;
    }

    public Servicos clone() {
        Servicos clone = null;
        try {
            clone = (Servicos) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
