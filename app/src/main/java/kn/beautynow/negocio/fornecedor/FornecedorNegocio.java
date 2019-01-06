package kn.beautynow.negocio.fornecedor;

import android.content.Context;

import java.util.ArrayList;

import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.persistencia.ServicosDao;

public class FornecedorNegocio {
    private Context contexto;

    public FornecedorNegocio(Context contextot){
        contexto = contextot;
    }

    public ArrayList carregarServicos(String idfornecedor){
        Servicos servicos = new Servicos();
        ServicosDao servicosDao = new ServicosDao(contexto);
        ArrayList result = servicosDao.selectServicos(idfornecedor);
        for (int i = 0; i < result.size(); ++i) {
            Servico obj = (Servico) result.get(i);
            servicos.getListaServicos().add(i,obj);
        }
        return result;
    }
}
