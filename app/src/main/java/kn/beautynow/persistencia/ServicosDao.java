package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

import kn.beautynow.dominio.fornecedor.Servico;

public class ServicosDao {
    private Banco banco;
    private SQLiteDatabase db;

    public ServicosDao(Context context){
        banco = new Banco(context);
    }

    public ArrayList selectServicos(String idfornecedor) {
        ArrayList<Servico> retorno = new ArrayList<>();
        String selectServicos = "SELECT * FROM "+ Banco.TABLE_SERVICOS_FORNECEDOR +" WHERE id_fornecedor = '"+ idfornecedor
                + "' ";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectServicos,new String[]{});
        if (cursor.getCount()>0){
            int index = 0;
            cursor.moveToFirst();
            while (cursor.getCount() >= (index+1)){
                Servico servico = new Servico();
                servico.setId(cursor.getString(0));
                servico.setDescricao(cursor.getString(4));
                servico.setValor(cursor.getString(2));
                byte[]imagem = cursor.getBlob(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                servico.setImagem(bitmap);
                retorno.add(index,servico);
                index += 1;
                cursor.moveToNext();
            }
            cursor.close();
            return retorno;
        }
        return retorno;
    }
    public ArrayList selectServicos() {
        ArrayList<Servico> retorno = new ArrayList<>();
        String selectServicos = "SELECT * FROM "+ Banco.TABLE_SERVICOS_FORNECEDOR +" ";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectServicos,new String[]{});
        if (cursor.getCount()>0){
            int index = 0;
            cursor.moveToFirst();
            while (cursor.getCount() >= (index+1)){
                Servico servico = new Servico();
                servico.setId(cursor.getString(0));
                servico.setIdFornecedor(cursor.getString(1));
                servico.setDescricao(cursor.getString(4));
                servico.setValor(cursor.getString(2));
                byte[]imagem = cursor.getBlob(3);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                servico.setImagem(bitmap);
                retorno.add(index,servico);
                index += 1;
                cursor.moveToNext();
            }
            cursor.close();
            return retorno;
        }
        return retorno;
    }

    public void inserirServico(String descricao){
        ContentValues valores;
        String resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_SERVICOS_DESCRICAO, descricao);
        resultado = String.valueOf(db.insert(Banco.TABLE_SERVICOS, null, valores));
        db.close();
    }
    public void inserirServicoForncedorDao(String idforncedor, String idservico, String valor,  byte[] imagem){
        ContentValues valores;
        String resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_FORNECEDOR, idforncedor);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_SERVICO, idservico);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_VALOR, valor);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM, imagem);
        resultado = String.valueOf(db.insert(Banco.TABLE_SERVICOS_FORNECEDOR, null, valores));
        db.close();
    }
    public String buscarServicoDao(String descricao){
        String querySql = "SELECT * FROM servicos WHERE descricao = ?";
        db = banco.getReadableDatabase();
        String resultado = "0";
        Cursor cursor = db.rawQuery(querySql, new String[] {descricao});
        if(cursor.getCount()>0){
            cursor.close();
            return descricao;
        }
        cursor.close();
        return resultado;
    }

    public Servico buscarServicoFornecedorDao(String id) {
        Servico retorno = new Servico();
        String selectServicos = "SELECT * FROM "+ Banco.TABLE_SERVICOS_FORNECEDOR +" "+ "WHERE " + Banco.COLUMN_SERVICOS_FORNECEDOR_ID + " = '"+ id +"' ";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectServicos,new String[]{});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            retorno.setId(cursor.getString(0));
            retorno.setIdFornecedor(cursor.getString(1));
            retorno.setDescricao(cursor.getString(4));
            retorno.setValor(cursor.getString(2));
            byte[]imagem = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            retorno.setImagem(bitmap);
            cursor.close();
            return retorno;
        }
        return retorno;
    }

    public void updateServicoFornecedor(String servico, String valor, byte[] imagem, String idservico) {
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_SERVICO,servico);
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_VALOR,valor);
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM,imagem);
        db.update(Banco.TABLE_SERVICOS_FORNECEDOR,cv,"Id=?",new String[]{idservico});
    }
}
