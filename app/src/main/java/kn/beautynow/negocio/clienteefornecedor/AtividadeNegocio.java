package kn.beautynow.negocio.clienteefornecedor;

import android.content.Context;

import java.util.ArrayList;

import kn.beautynow.persistencia.AtividadeDao;

public class AtividadeNegocio {
    private Context contexto;

    public AtividadeNegocio(Context context){
        this.contexto = context;
    }

    public void MarcarAtividade(ArrayList<String> valores){
        AtividadeDao atividadedao = new AtividadeDao(contexto);
        atividadedao.InserirAtividade(valores);
    }
}
