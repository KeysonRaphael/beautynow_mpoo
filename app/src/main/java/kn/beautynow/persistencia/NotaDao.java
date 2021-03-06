package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import kn.beautynow.dominio.clienteefornecedor.Avaliacao;

public class NotaDao {
    private Banco banco;
    private SQLiteDatabase db;

    public NotaDao(Context context) {
        banco = new Banco(context);
    }

    public void InserirNota(String nota, String cliente, String fornecedor) {
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_NOTA_NOTA_FORNECEDOR, nota);
        valores.put(Banco.COLUMN_NOTA_CLIENTE, cliente);
        valores.put(Banco.COLUMN_NOTA_FORNECEDOR, fornecedor);
        db.insert(Banco.TABLE_NOTA, null, valores);
        db.close();
    }

    public ArrayList<Avaliacao> carregarTodasAsNotas() {
        ArrayList<Avaliacao> resultado = new ArrayList<Avaliacao>();
        String selectNotas = "SELECT * FROM " + Banco.TABLE_NOTA;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectNotas, new String[]{});
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setId(cursor.getString(0));
                avaliacao.setCliente(cursor.getString(1));
                avaliacao.setFornecedor(cursor.getString(2));
                avaliacao.setNota(cursor.getDouble(3));
                resultado.add(avaliacao);
            }
        }
        cursor.close();
        db.close();
        return resultado;
    }

}
