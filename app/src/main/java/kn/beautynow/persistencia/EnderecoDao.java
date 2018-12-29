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

    public void inserirEndereco(String iduser,String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep){
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_ENDERECO_ID_USER, iduser);
        valores.put(Banco.COLUMN_ENDERECO_CEP, cep);
        valores.put(Banco.COLUMN_ENDERECO_RUA, rua);
        valores.put(Banco.COLUMN_ENDERECO_NUMERO, numero);
        valores.put(Banco.COLUMN_ENDERECO_COMPLEMENTO, complemento);
        valores.put(Banco.COLUMN_ENDERECO_BAIRRO, bairro);
        valores.put(Banco.COLUMN_ENDERECO_CIDADE, cidade);
        valores.put(Banco.COLUMN_ENDERECO_ESTADO, estado);
        db.insert(Banco.TABLE_ENDERECO, null, valores);
        db.close();
    }

    public void updateEndereco(String iduser,String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep){
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Banco.COLUMN_ENDERECO_RUA,rua);
        cv.put(Banco.COLUMN_ENDERECO_NUMERO,numero);
        cv.put(Banco.COLUMN_ENDERECO_COMPLEMENTO,complemento);
        cv.put(Banco.COLUMN_ENDERECO_BAIRRO,bairro);
        cv.put(Banco.COLUMN_ENDERECO_CIDADE,cidade);
        cv.put(Banco.COLUMN_ENDERECO_ESTADO,estado);
        cv.put(Banco.COLUMN_ENDERECO_CEP,cep);
        db.update(Banco.TABLE_ENDERECO,cv,"id_user=?",new String[]{iduser});
    }

    public boolean existeEndereco(String iduser){
        String querySql = "SELECT * FROM endereco WHERE id_user = ?";
        SQLiteDatabase db;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(querySql, new String[] {iduser});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public ArrayList<String> buscarEndereco(String iduser){
        ArrayList<String> retorno = new ArrayList<>();
        String selectEndereco = "SELECT * FROM "+ Banco.TABLE_ENDERECO +" WHERE id_user = '"+ iduser
                + "' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectEndereco,new String[]{});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Log.d("testecursor", cursor.getString(0));
            retorno.add(0, cursor.getString(0));
            retorno.add(1, cursor.getString(1));
            retorno.add(2, cursor.getString(2));
            retorno.add(3, cursor.getString(3));
            retorno.add(4, cursor.getString(4));
            retorno.add(5, cursor.getString(5));
            retorno.add(6, cursor.getString(6));
            retorno.add(7, cursor.getString(7));
            cursor.close();
            return retorno;
        }else{
            return retorno;
        }
    }
}
