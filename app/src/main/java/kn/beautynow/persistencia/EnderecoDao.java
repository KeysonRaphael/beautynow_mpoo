package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EnderecoDao {
    private Banco banco;
    private SQLiteDatabase db;


    public EnderecoDao(Context context) {
        banco = new Banco(context);
    }

    public void inserirEndereco(ArrayList<String> array) {
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_ENDERECO_ID_USER, array.get(0));
        valores.put(Banco.COLUMN_ENDERECO_CEP, array.get(7));
        valores.put(Banco.COLUMN_ENDERECO_RUA, array.get(1));
        valores.put(Banco.COLUMN_ENDERECO_NUMERO, array.get(2));
        valores.put(Banco.COLUMN_ENDERECO_COMPLEMENTO, array.get(3));
        valores.put(Banco.COLUMN_ENDERECO_BAIRRO, array.get(4));
        valores.put(Banco.COLUMN_ENDERECO_CIDADE, array.get(5));
        valores.put(Banco.COLUMN_ENDERECO_ESTADO, array.get(6));
        db.insert(Banco.TABLE_ENDERECO, null, valores);
        db.close();
    }

    public void updateEndereco(ArrayList<String> array) {
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Banco.COLUMN_ENDERECO_RUA, array.get(1));
        cv.put(Banco.COLUMN_ENDERECO_NUMERO, array.get(2));
        cv.put(Banco.COLUMN_ENDERECO_COMPLEMENTO, array.get(4));
        cv.put(Banco.COLUMN_ENDERECO_BAIRRO, array.get(4));
        cv.put(Banco.COLUMN_ENDERECO_CIDADE, array.get(5));
        cv.put(Banco.COLUMN_ENDERECO_ESTADO, array.get(6));
        cv.put(Banco.COLUMN_ENDERECO_CEP, array.get(7));
        db.update(Banco.TABLE_ENDERECO, cv, "id_user=?", new String[]{array.get(0)});
    }

    public boolean existeEndereco(String iduser) {
        String querySql = "SELECT * FROM endereco WHERE id_user = ?";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(querySql, new String[]{iduser});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public ArrayList<String> buscarEndereco(String iduser) {
        ArrayList<String> retorno = new ArrayList<>();
        String selectEndereco = "SELECT * FROM " + Banco.TABLE_ENDERECO + " WHERE id_user = '" + iduser
                + "' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectEndereco, new String[]{});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            retorno.add(0, cursor.getString(0));
            retorno.add(1, cursor.getString(1));
            retorno.add(2, cursor.getString(2));
            retorno.add(3, cursor.getString(3));
            retorno.add(4, cursor.getString(4));
            retorno.add(5, cursor.getString(5));
            retorno.add(6, cursor.getString(6));
            retorno.add(7, cursor.getString(7));
            cursor.close();
            db.close();
            return retorno;
        } else {
            cursor.close();
            db.close();
            return retorno;
        }
    }
}
