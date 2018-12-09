package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDao {
    private SQLiteDatabase db;
    private Banco banco;
    public ClienteDao(Context context){
        banco = new Banco(context);
    }
    public String insereCliente(long iduser){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_CLIENTE_ID_USUARIO, iduser);

        resultado = db.insert(Banco.TABLE_CLIENTE, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }
}