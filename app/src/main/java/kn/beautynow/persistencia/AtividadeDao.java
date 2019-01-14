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
        String selectUser = "SELECT * FROM "+ Banco.TABLE_AGENDA +" WHERE email = '"+ vemail
                + "' AND senha = '"+ vsenha +"' AND tipo = '"+ vtipo +"' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 1){
            while(cursor.moveToNext()){
                ArrayList<Atividade> atividades= agenda.getCalendario();
                Atividade atividade = new Atividade();
                if(cursor.getCount() > 0){
                    atividade.setCliente(cursor.getString(0));
                    }
            }
            cursor.close();
            db.close();
            return agenda;
        }
        return agenda;
    }
}
