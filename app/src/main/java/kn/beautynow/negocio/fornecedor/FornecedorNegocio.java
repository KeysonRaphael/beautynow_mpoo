package kn.beautynow.negocio.fornecedor;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.persistencia.ServicosDao;

public class FornecedorNegocio {
    private Context contexto;

    public FornecedorNegocio(Context contextot){
        contexto = contextot;
    }

    public Fornecedor montarFornecedor(String idfornecedor){
        Fornecedor fornecedor = new Fornecedor();
        ServicosDao servicosDao = new ServicosDao(contexto);
        ArrayList result = servicosDao.selectServicos(idfornecedor);
        for (int i = 0; i < result.size(); ++i) {
            Servico obj = (Servico) result.get(i);
            fornecedor.getServicos().adicionarServico(obj);
        }
        Log.d("teste03", fornecedor.getServicos().getListaServicos().toString());
        return fornecedor;
    }
}
