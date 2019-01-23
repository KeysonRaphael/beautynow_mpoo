package kn.beautynow.dominio.fornecedor;

import java.util.ArrayList;

public class Servicos{
    private String id;
    private ArrayList<Servico> listaServicos = new ArrayList<>();

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
}
