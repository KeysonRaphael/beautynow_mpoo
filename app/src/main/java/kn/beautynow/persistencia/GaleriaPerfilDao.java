package kn.beautynow.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;


public class GaleriaPerfilDao {
    private SQLiteDatabase db;
    private Banco banco;

    public GaleriaPerfilDao(Context context){
        banco = new Banco(context);
    }

    public String insereImagemGaleria(String iduser, byte[] img){
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_GALERIA_PERFIL_USUARIO_ID, iduser);
        valores.put(Banco.COLUMN_GALERIA_PERFIL_IMAGE, img);

        resultado = db.insert(Banco.TABLE_GALERIA_PERFIL, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public byte[] getImagem(String id){
        db = banco.getWritableDatabase();

        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM " + Banco.TABLE_GALERIA_PERFIL + " WHERE id_usuario = '" + id +"'";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    byte[] retorno =  cursor.getBlob(cursor.getColumnIndex(Banco.COLUMN_GALERIA_PERFIL_IMAGE));
                    cursor.close();
                    return retorno;
                }
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close data
    }
    return null;
    }
    public boolean updateimgPerfil(String id, byte[] img){
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Banco.COLUMN_GALERIA_PERFIL_IMAGE,img);
        db.update(Banco.TABLE_GALERIA_PERFIL,cv,"id_usuario=?",new String[]{id});
        return true;
    }
}
