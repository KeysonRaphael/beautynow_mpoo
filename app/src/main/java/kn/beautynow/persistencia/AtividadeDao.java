package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;

public class AtividadeDao {
    private Banco banco;
    private SQLiteDatabase db;

    public AtividadeDao(Context context){
        banco = new Banco(context);
    }

    public void InserirAtividade(ArrayList<String> valor){
            ContentValues valores;
            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put(Banco.COLUMN_AGENDA_SERVICO, valor.get(0));
            valores.put(Banco.COLUMN_AGENDA_VALOR, valor.get(1));
            valores.put(Banco.COLUMN_AGENDA_DATA, valor.get(2));
            valores.put(Banco.COLUMN_AGENDA_HORA, valor.get(3));
            valores.put(Banco.COLUMN_AGENDA_CLIENTE, valor.get(4));
            valores.put(Banco.COLUMN_AGENDA_FORNECEDOR, valor.get(5));
            valores.put(Banco.COLUMN_AGENDA_ATIVO, "N");
            valores.put(Banco.COLUMN_AGENDA_FINALIZADO, "N");
            db.insert(Banco.TABLE_AGENDA, null, valores);
            db.close();
    }

    public Agenda carregarAgendaClienteDao(String cliente){
        Agenda agenda = new Agenda();
        String selectUser = "SELECT * FROM "+ Banco.TABLE_AGENDA +" WHERE cliente = '"+ cliente + "'";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 0){
            ArrayList<Atividade> atividades= new ArrayList<Atividade>();
            int count = 0;
            while(cursor.moveToNext()){
                Atividade atividade = new Atividade();
                atividade.setId(cursor.getString(0));
                atividade.setServico(cursor.getString(1));
                atividade.setValor(cursor.getString(2));
                atividade.setData(cursor.getString(3));
                atividade.setHora(cursor.getString(4));
                atividade.setCliente(cursor.getString(5));
                atividade.setFornecedor(cursor.getString(6));
                atividade.setAtivo(cursor.getString(7));
                atividade.setFinalizado(cursor.getString(8));
                atividades.add(count, atividade);
                count += 1;
            }
            agenda.setCalendario(atividades);
            cursor.close();
            db.close();
            return agenda;
        }
        return agenda;
    }

    public Agenda carregarAgendaFornecedorDao(String idfornecedor) {
        Agenda agenda = new Agenda();
        String selectUser = "SELECT * FROM "+ Banco.TABLE_AGENDA +" WHERE fornecedor = '"+ idfornecedor + "'";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 0){
            ArrayList<Atividade> atividades= new ArrayList<Atividade>();
            int count = 0;
            while(cursor.moveToNext()){
                Atividade atividade = new Atividade();
                if(cursor.getCount() > 0){
                    atividade.setId(cursor.getString(0));
                    atividade.setServico(cursor.getString(1));
                    atividade.setValor(cursor.getString(2));
                    atividade.setData(cursor.getString(3));
                    atividade.setHora(cursor.getString(4));
                    atividade.setCliente(cursor.getString(5));
                    atividade.setFornecedor(cursor.getString(6));
                    atividade.setAtivo(cursor.getString(7));
                    atividade.setFinalizado(cursor.getString(8));
                    atividades.add(count, atividade);
                    count += 1;
                }
            }
            agenda.setCalendario(atividades);
            cursor.close();
            db.close();
            return agenda;
        }
        return agenda;
    }
}
