package kn.beautynow.negocio.clienteefornecedor;

import android.content.Context;

import kn.beautynow.persistencia.NotaDao;

public class NotaNegocio {
    private Context contexto;

    public NotaNegocio(Context context) {
        this.contexto = context;
    }

    public void inserirNotaNegocio(String nota, String cliente, String fornecedor) {
        NotaDao atividadedao = new NotaDao(contexto);
        atividadedao.InserirNota(nota, cliente, fornecedor);
    }
}
