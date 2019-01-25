package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import kn.beautynow.dominio.fornecedor.Servico;

public class ServicosDao {
    private Banco banco;
    private SQLiteDatabase db;
    private String select = "SELECT * FROM ";

    public ServicosDao(Context context) {
        banco = new Banco(context);
    }

    public ArrayList<Servico> carregarServicos(String selectServicos, boolean recycler){
        ArrayList<Servico> retorno = new ArrayList<>();
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectServicos, new String[]{});
        if (cursor.getCount() > 0) {
            int index = 0;
            cursor.moveToFirst();
            while (cursor.getCount() >= (index + 1)) {
                Servico servico = new Servico();
                servico.setId(cursor.getString(0));
                servico.setIdFornecedor(cursor.getString(1));
                servico.setDescricao(cursor.getString(5));
                servico.setValor(cursor.getString(2));
                if (!recycler){
                    byte[] imagem = cursor.getBlob(3);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    servico.setImagem(bitmap);
                }
                byte[] imagemg = cursor.getBlob(4);
                Bitmap bitmapg = BitmapFactory.decodeByteArray(imagemg, 0, imagemg.length);
                servico.setImagemGaleria(bitmapg);
                retorno.add(index, servico);
                index += 1;
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return retorno;
        }
        cursor.close();
        db.close();
        return retorno;
    }

    public ArrayList selectServicos(String idfornecedor) {
        String selectServicos = select + Banco.TABLE_SERVICOS_FORNECEDOR + " WHERE id_fornecedor = '" + idfornecedor
                + "' ";
        return carregarServicos(selectServicos, true);
    }

    public ArrayList<Servico> selectServicos() {
        String selectServicos = select + Banco.TABLE_SERVICOS_FORNECEDOR + " ";
        return carregarServicos(selectServicos, true);
    }

    public void inserirServico(String descricao) {
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_SERVICOS_DESCRICAO, descricao);
        db.insert(Banco.TABLE_SERVICOS, null, valores);
        db.close();
    }

    public void inserirServicoForncedorDao(String idforncedor, String idservico, String valor, byte[] imagem, byte[] imagemg) {
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_FORNECEDOR, idforncedor);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_SERVICO, idservico);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_VALOR, valor);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM, imagem);
        valores.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM_GALERIA, imagemg);
        db.insert(Banco.TABLE_SERVICOS_FORNECEDOR, null, valores);
        db.close();
    }

    public String buscarServicoDao(String descricao) {
        String querySql = select + "servicos WHERE descricao = ?";
        db = banco.getReadableDatabase();
        String resultado = "0";
        Cursor cursor = db.rawQuery(querySql, new String[]{descricao});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return descricao;
        }
        cursor.close();
        db.close();
        return resultado;
    }

    public Servico buscarServicoFornecedorDao(String id) {
        Servico retorno = new Servico();
        String selectServicos = select + Banco.TABLE_SERVICOS_FORNECEDOR + " " + "WHERE " + Banco.COLUMN_SERVICOS_FORNECEDOR_ID + " = '" + id + "' ";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectServicos, new String[]{});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            retorno.setId(cursor.getString(0));
            retorno.setIdFornecedor(cursor.getString(1));
            retorno.setDescricao(cursor.getString(5));
            retorno.setValor(cursor.getString(2));
            byte[] imagem = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            retorno.setImagem(bitmap);
            byte[] imagemg = cursor.getBlob(4);
            Bitmap bitmapg = BitmapFactory.decodeByteArray(imagemg, 0, imagemg.length);
            retorno.setImagemGaleria(bitmapg);
            cursor.close();
            db.close();
            return retorno;
        }
        cursor.close();
        db.close();
        return retorno;
    }

    public void updateServicoFornecedor(String servico, String valor, byte[] imagem, String idservico, byte[] imagemgaleri) {
        db = banco.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_ID_SERVICO, servico);
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_VALOR, valor);
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM, imagem);
        cv.put(Banco.COLUMN_SERVICOS_FORNECEDOR_IMAGEM_GALERIA, imagemgaleri);
        db.update(Banco.TABLE_SERVICOS_FORNECEDOR, cv, "Id=?", new String[]{idservico});
        db.close();
    }

    public ArrayList<Servico> selectServicosFornecedorDao(String id) {
        String selectServicos = select + Banco.TABLE_SERVICOS_FORNECEDOR + " WHERE " + Banco.COLUMN_SERVICOS_FORNECEDOR_ID_FORNECEDOR + " = " + id;
        return carregarServicos(selectServicos, true);
    }
}
