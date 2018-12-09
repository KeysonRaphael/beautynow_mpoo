package kn.beautynow.dominio.fornecedor;

import java.util.List;

public class Servicos {
    private String id;
    private List<Servico> servicos;

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private List<Servico> getServicos() {
        return servicos;
    }

    private void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    private void adicionarServico(Servico servico){
        this.servicos.add(servico);
    }
    private void removerServico(int id){
        this.servicos.remove(id);
    }
}
