package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    public ArrayList<String> carregarTodosFornecedore(){
        SQLiteDatabase db;
        ArrayList<String> resultado = new ArrayList<String>();
        String selectNotas = "SELECT * FROM "+ Banco.TABLE_FORNECEDOR;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectNotas,new String[]{});
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                resultado.add(cursor.getString(0));
            }
        }
        cursor.close();
        db.close();
        return resultado;
    }
}
