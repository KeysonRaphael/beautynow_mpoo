package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDao {
    private SQLiteDatabase db;
    private Banco banco;
    private Context context;

    public UsuarioDao(Context context){
        banco = new Banco(context);
    }

    public String insereDado(String nome, String cpf, String email, String senha, String tipo, String sexo){
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_USUARIO_NOME, nome);
        valores.put(Banco.COLUMN_USUARIO_CPF, cpf);
        valores.put(Banco.COLUMN_USUARIO_EMAIL, email);
        valores.put(Banco.COLUMN_USUARIO_SENHA, senha);
        valores.put(Banco.COLUMN_USUARIO_TIPO, tipo);
        valores.put(Banco.COLUMN_USUARIO_SEXO, sexo);

        resultado = db.insert(Banco.TABLE_USUARIO, null, valores);
        db.close();
        if (tipo == "Cliente"){
            ClienteDao cliente = new ClienteDao(context);
            cliente.insereCliente(resultado);
        }

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

}
