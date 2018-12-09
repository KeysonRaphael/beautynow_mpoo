package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;

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

    public String selectUsuario(String vemail, String vsenha){
        String selectUser = "SELECT * FROM "+ Banco.TABLE_USUARIO +" WHERE email = '"+ vemail
                + "' AND senha = '"+ vsenha +"' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 1){
            while(cursor.moveToNext()){
                if(cursor.getCount() > 0){
                    Usuario user = new Usuario();
                    user.setNome(cursor.getString(1));
                    user.setCpf(cursor.getString(2));
                    user.setEmail(cursor.getString(3));
                    user.setTipo_usuario(cursor.getString(4));
                    user.setSexo(cursor.getString(5));
                    Endereco endereco = new Endereco();
                    String selectEndereco = "SELECT * FROM "+ Banco.TABLE_ENDERECO +
                            " WHERE id_user = '"+ cursor.getString(0) + "' limit 1";
                    db = banco.getReadableDatabase();
                    Cursor cursorend = db.rawQuery(selectEndereco,new String[]{});
                    if(cursorend.getCount() >= 1) {
                        while (cursorend.moveToNext()) {
                            if (cursorend.getCount() > 0) {
                                endereco.setCep(cursorend.getString(1));
                                endereco.setRua(cursorend.getString(2));
                                endereco.setNumero(cursorend.getString(3));
                                endereco.setBairro(cursorend.getString(4));
                                endereco.setCidade(cursorend.getString(5));
                                endereco.setEstado(cursorend.getString(6));
                                endereco.setPais(cursorend.getString(7));
                                user.setEndereco(endereco);
                                cursorend.close();
                                return "";
                            }
                        }
                    }
                    cursor.close();
                    db.close();
                    return "";
                }
            }
        }
        return "";
    }

}
