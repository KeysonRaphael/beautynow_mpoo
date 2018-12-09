package kn.beautynow.persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "Banco.db";
    private static final int VERSAO;
    static {
        VERSAO = 3;
    }
    //Tabela Usuario
    public static final String TABLE_USUARIO = "usuario";
    public static final String COLUMN_USUARIO_ID = "Id";
    public static final String COLUMN_USUARIO_NOME = "nome";
    public static final String COLUMN_USUARIO_EMAIL = "email";
    public static final String COLUMN_USUARIO_CPF = "cpf";
    public static final String COLUMN_USUARIO_SENHA = "senha";
    public static final String COLUMN_USUARIO_TIPO = "tipo";
    public static final String COLUMN_USUARIO_SEXO = "sexo";
    public static final String COLUMN_USUARIO_ID_TIPO = "id_tipo";
    //Tabela Cliente
    public static final String TABLE_CLIENTE = "cliente";
    public static final String COLUMN_CLIENTE_ID = "Id";
    public static final String COLUMN_CLIENTE_ID_USUARIO = "id_usuario";
    //Tabela Fornecedor
    public static final String TABLE_FORNECEDOR = "fornecedor";
    public static final String COLUMN_FORNECEDOR_ID = "Id";
    public static final String COLUMN_FORNECEDOR_ID_USUARIO = "id_usuario";

    public Banco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table usuario
        String sqlUser = "CREATE TABLE " + this.TABLE_USUARIO + " (" +
                this.COLUMN_USUARIO_ID + " INTEGER PRIMARY KEY," +
                this.COLUMN_USUARIO_NOME + " TEXT," +
                this.COLUMN_USUARIO_EMAIL + " TEXT," +
                this.COLUMN_USUARIO_CPF + " TEXT," +
                this.COLUMN_USUARIO_SENHA + " TEXT," +
                this.COLUMN_USUARIO_TIPO + " TEXT," +
                this.COLUMN_USUARIO_ID_TIPO + " TEXT," +
                this.COLUMN_USUARIO_SEXO + " TEXT" + " )";
        db.execSQL(sqlUser);
        //create table cliente
        String sqlCliente = "CREATE TABLE " + this.TABLE_CLIENTE + " (" +
                this.COLUMN_CLIENTE_ID + " INTEGER PRIMARY KEY," +
                this.COLUMN_CLIENTE_ID_USUARIO + " TEXT" + " )";
        db.execSQL(sqlCliente);
        //create table fornecedor
        String sqlFornecedor = "CREATE TABLE " + this.TABLE_FORNECEDOR + " (" +
                this.COLUMN_FORNECEDOR_ID + " INTEGER PRIMARY KEY," +
                this.COLUMN_FORNECEDOR_ID_USUARIO + " TEXT" + " )";
        db.execSQL(sqlFornecedor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }

}
