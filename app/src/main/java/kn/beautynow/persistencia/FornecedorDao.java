package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FornecedorDao {
    private SQLiteDatabase db;
    private Banco banco;
    public FornecedorDao(Context context){
        banco = new Banco(context);
    }
    public String insereFornecedor(long iduser){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_FORNECEDOR_ID_USUARIO, iduser);

        resultado = db.insert(Banco.TABLE_FORNECEDOR, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }
}
