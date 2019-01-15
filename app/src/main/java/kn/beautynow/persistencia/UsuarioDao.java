package kn.beautynow.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;

public class UsuarioDao {
    private SQLiteDatabase db;
    private Banco banco;
    private Context contexto;

    public UsuarioDao(Context context){
        this.contexto = context;
        banco = new Banco(context);
    }

    public void insereDado(String nome, String cpf, String email, String senha, String tipo, String sexo){
        ContentValues valores;
        String resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.COLUMN_USUARIO_NOME, nome);
        valores.put(Banco.COLUMN_USUARIO_CPF, cpf);
        valores.put(Banco.COLUMN_USUARIO_EMAIL, email);
        valores.put(Banco.COLUMN_USUARIO_SENHA, senha);
        valores.put(Banco.COLUMN_USUARIO_TIPO, tipo);
        valores.put(Banco.COLUMN_USUARIO_SEXO, sexo);

        resultado = String.valueOf(db.insert(Banco.TABLE_USUARIO, null, valores));
        String idTipo;
        db.close();
        if (tipo.equals("Cliente")){
            ClienteDao cliente = new ClienteDao(contexto);
            idTipo = cliente.insereCliente(resultado);
        }else{
            FornecedorDao fornecedor = new FornecedorDao(contexto);
            idTipo = fornecedor.insereFornecedor(resultado);
        }
        db = banco.getWritableDatabase();
        ContentValues valor = new ContentValues();
        valor.put(Banco.COLUMN_USUARIO_ID_USER_TIPO,idTipo);
        Log.d("resultado1", resultado);
        db.update(Banco.TABLE_USUARIO,valor ,"Id =?" ,new String[]{resultado});
        db.close();
    }

    public ArrayList<String> selectUsuario(String vemail, String vsenha, String vtipo){
        ArrayList<String> retorno = new ArrayList<>();
        String selectUser = "SELECT * FROM "+ Banco.TABLE_USUARIO +" WHERE email = '"+ vemail
                + "' AND senha = '"+ vsenha +"' AND tipo = '"+ vtipo +"' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 1){
            while(cursor.moveToNext()){
                if(cursor.getCount() > 0){
                    retorno.add(0, cursor.getString(0));
                    retorno.add(1, cursor.getString(1));
                    retorno.add(2, cursor.getString(2));
                    retorno.add(3, cursor.getString(3));
                    retorno.add(4, cursor.getString(4));
                    retorno.add(5, cursor.getString(5));
                    retorno.add(6, cursor.getString(6));
                    retorno.add(7, cursor.getString(7));
                    retorno.add(8, cursor.getString(8));
                    String selectEndereco = "SELECT * FROM "+ Banco.TABLE_ENDERECO +
                            " WHERE id_user = '"+ cursor.getString(0) + "' limit 1";
                    db = banco.getReadableDatabase();
                    Cursor cursorend = db.rawQuery(selectEndereco,new String[]{});
                    if(cursorend.getCount() >= 1) {
                        while (cursorend.moveToNext()) {
                            if (cursorend.getCount() > 0) {
                                retorno.add(9,cursorend.getString(1));
                                retorno.add(10,cursorend.getString(2));
                                retorno.add(11,cursorend.getString(3));
                                retorno.add(12,cursorend.getString(4));
                                retorno.add(13,cursorend.getString(5));
                                retorno.add(14,cursorend.getString(6));
                                retorno.add(15,cursorend.getString(7));
                                cursorend.close();
                                return retorno;
                            }
                        }
                    }
                    cursor.close();
                    db.close();
                    return retorno;
                }
            }
        }
        return retorno;
    }
    public ArrayList<String> selectUsuarioPorTipo(String idfornecedor, String Tipo){
        ArrayList<String> retorno = new ArrayList<>();
        String selectUser = "SELECT * FROM "+ Banco.TABLE_USUARIO +" WHERE id_user_tipo = '"+ idfornecedor
                + "' AND tipo = '"+ Tipo +"' limit 1";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUser,new String[]{});
        if(cursor.getCount() >= 1){
            while(cursor.moveToNext()){
                if(cursor.getCount() > 0){
                    retorno.add(0, cursor.getString(0));
                    retorno.add(1, cursor.getString(1));
                    retorno.add(2, cursor.getString(2));
                    retorno.add(3, cursor.getString(3));
                    retorno.add(4, cursor.getString(4));
                    retorno.add(5, cursor.getString(5));
                    retorno.add(6, cursor.getString(6));
                    retorno.add(7, cursor.getString(7));
                    retorno.add(8, cursor.getString(8));
                    String selectEndereco = "SELECT * FROM "+ Banco.TABLE_ENDERECO +
                            " WHERE id_user = '"+ cursor.getString(0) + "' limit 1";
                    db = banco.getReadableDatabase();
                    Cursor cursorend = db.rawQuery(selectEndereco,new String[]{});
                    if(cursorend.getCount() >= 1) {
                        while (cursorend.moveToNext()) {
                            if (cursorend.getCount() > 0) {
                                retorno.add(9,cursorend.getString(1));
                                retorno.add(10,cursorend.getString(2));
                                retorno.add(11,cursorend.getString(3));
                                retorno.add(12,cursorend.getString(4));
                                retorno.add(13,cursorend.getString(5));
                                retorno.add(14,cursorend.getString(6));
                                retorno.add(15,cursorend.getString(7));
                                cursorend.close();
                                return retorno;
                            }
                        }
                    }
                    cursor.close();
                    db.close();
                    return retorno;
                }
            }
        }
        return retorno;
    }

    public Boolean buscaUsuario(String cpfv, String tipov){
        String querySql = "SELECT cpf FROM usuario WHERE cpf = ? AND tipo = ?";
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(querySql, new String[] {cpfv, tipov});
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

}
