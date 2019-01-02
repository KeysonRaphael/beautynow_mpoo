package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FornecedorDao {
    private Banco banco;
    public FornecedorDao(Context context){
        banco = new Banco(context);
    }
    public String insereFornecedor(String iduser){
        ContentValues valores;
        SQLiteDatabase db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_FORNECEDOR_ID_USUARIO, iduser);

        String resultado = String.valueOf(db.insert(Banco.TABLE_FORNECEDOR, null, valores));
        db.close();
        return resultado;
    }
}
