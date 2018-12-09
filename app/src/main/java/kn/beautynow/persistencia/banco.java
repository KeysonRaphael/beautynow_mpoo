package kn.beautynow.persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "Banco.db";
    private static final int VERSAO;
    static {
        VERSAO = 4;
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
    //Tabela Endereco
    public static final String TABLE_ENDERECO = "endereco";
    public static final String COLUMN_ENDERECO_ID = "Id";
    public static final String COLUMN_ENDERECO_CEP = "cep";
    public static final String COLUMN_ENDERECO_RUA = "rua";
    public static final String COLUMN_ENDERECO_NUMERO = "numero";
    public static final String COLUMN_ENDERECO_BAIRRO = "bairro";
    public static final String COLUMN_ENDERECO_CIDADE = "cidade";
    public static final String COLUMN_ENDERECO_ESTADO = "estado";
    public static final String COLUMN_ENDERECO_PAIS = "pais";
    public static final String COLUMN_ENDERECO_ID_USER = "id_user";
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
        //create table usuario
        String sqlEndereco = "CREATE TABLE " + this.TABLE_ENDERECO + " (" +
                this.COLUMN_ENDERECO_ID + " INTEGER PRIMARY KEY," +
                this.COLUMN_ENDERECO_CEP + " TEXT," +
                this.COLUMN_ENDERECO_RUA + " TEXT," +
                this.COLUMN_ENDERECO_NUMERO + " TEXT," +
                this.COLUMN_ENDERECO_BAIRRO + " TEXT," +
                this.COLUMN_ENDERECO_CIDADE + " TEXT," +
                this.COLUMN_ENDERECO_ESTADO + " TEXT," +
                this.COLUMN_ENDERECO_PAIS + " TEXT," +
                this.COLUMN_ENDERECO_ID_USER + " TEXT" + " )";
        db.execSQL(sqlEndereco);
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
        db.execSQL("DROP TABLE IF EXISTS endereco");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS fornecedor");
        onCreate(db);
    }

}
