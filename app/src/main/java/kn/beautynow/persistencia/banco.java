package kn.beautynow.persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class banco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO;

    static {
        VERSAO = 1;
    }

    public banco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUser = "CREATE TABLE usuario( id integer primary key autoincrement, nome text, cpf text,"+
                " telefone int,celular  int, cod_endereco int)";
        String sqlEndereco = "CREATE TABLE endereco(id integer primary key autoincrement, rua text,"+
                "numero int, bairro text, cidade text, estado text, pais text, cod_usuario int)";
        String sqlCliente = "CREATE TABLE cliente(id integer primary key autoincrement, cod_usuario int)";
        String sqlFornecedor = "CREATE TABLE fornecedor(id integer primary key autoincrement, cod_usuario int)";
        String sqlServicos = "CREATE TABLE servicos(id integer primary key autoincrement, nome text)";
        String sqlServicosFornecedor = "CREATE TABLE servico_fornecedor(id integer primary key autoincrement,"+
                " cod_fornecedor int, cod_servico int)";
        db.execSQL(sqlUser);
        db.execSQL(sqlEndereco);
        db.execSQL(sqlCliente);
        db.execSQL(sqlFornecedor);
        db.execSQL(sqlServicos);
        db.execSQL(sqlServicosFornecedor);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS endereco");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS fornecedor");
        db.execSQL("DROP TABLE IF EXISTS servicos");
        db.execSQL("DROP TABLE IF EXISTS servico_fornecedor");
        onCreate(db);
    }

}
