package kn.beautynow.negocio.clienteefornecedor;

import android.content.Context;

import java.util.ArrayList;

import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.persistencia.AtividadeDao;

public class AtividadeNegocio {
    private Context contexto;

    public AtividadeNegocio(Context context) {
        this.contexto = context;
    }

    public void marcarAtividade(ArrayList<String> valores) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.InserirAtividade(valores);
    }

    public void confirmarAtividade(String idatividade) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.confirmarAtividadeDao(idatividade);
    }

    public void notainseridaAtividade(String idatividade) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.notainseridaAtividadeDao(idatividade);
    }

    public void recusarAtividade(String idatividade) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.recusarAtividadeDao(idatividade);
    }

    public void finalizarAtividade(String idatividade) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.finalizarAtividadeDao(idatividade);
    }

    public Agenda carregarAgendaClienteNegocio(String idcliente) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        return atividadedao.carregarAgendaTipoDao(idcliente, "Cliente");
    }

    public Agenda carregarAgendaFornecedorNegocio(String idfornecedor) {
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        return atividadedao.carregarAgendaTipoDao(idfornecedor,"Fornecedor");
    }
}
