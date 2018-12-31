package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ServicosDao {
    private Banco banco;
    private SQLiteDatabase db;

    public ServicosDao(Context context){
        banco = new Banco(context);
    }
    public long inserirServico(String descricao){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_CLIENTE_ID_USUARIO, descricao);
        resultado = db.insert(Banco.TABLE_CLIENTE, null, valores);
        db.close();
        return resultado;
    }
}
