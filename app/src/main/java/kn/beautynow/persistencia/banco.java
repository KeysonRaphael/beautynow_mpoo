package kn.beautynow.persistencia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "Banco.db";
    private static final int VERSAO;
    static {
        VERSAO = 6;
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
    public static final String COLUMN_ENDERECO_COMPLEMENTO = "complemento";
    public static final String COLUMN_ENDERECO_BAIRRO = "bairro";
    public static final String COLUMN_ENDERECO_CIDADE = "cidade";
    public static final String COLUMN_ENDERECO_ESTADO = "estado";
    public static final String COLUMN_ENDERECO_ID_USER = "id_user";
    //Tabela Galeria Perfil
    public static final String TABLE_GALERIA_PERFIL = "galeria_perfil";
    public static final String COLUMN_GALERIA_PERFIL_ID = "Id";
    public static final String COLUMN_GALERIA_PERFIL_USUARIO_ID = "id_usuario";
    public static final String COLUMN_GALERIA_PERFIL_IMAGE = "imagem";
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
        String create = "CREATE TABLE ";
        String prim = " INTEGER PRIMARY KEY,";
        String tipotexto = " TEXT,";
        String tipotexton = " TEXT";
        //create table usuario
        String sqlUser = create + Banco.TABLE_USUARIO + " (" +
                Banco.COLUMN_USUARIO_ID + prim +
                Banco.COLUMN_USUARIO_NOME + tipotexto +
                Banco.COLUMN_USUARIO_EMAIL + tipotexto +
                Banco.COLUMN_USUARIO_CPF + tipotexto +
                Banco.COLUMN_USUARIO_SENHA + tipotexto +
                Banco.COLUMN_USUARIO_TIPO + tipotexto +
                Banco.COLUMN_USUARIO_ID_TIPO + tipotexto +
                Banco.COLUMN_USUARIO_SEXO + tipotexton + " )";
        db.execSQL(sqlUser);
        //create table endereço
        String sqlEndereco = create + Banco.TABLE_ENDERECO + " (" +
                Banco.COLUMN_ENDERECO_ID + prim +
                Banco.COLUMN_ENDERECO_CEP + tipotexto +
                Banco.COLUMN_ENDERECO_RUA + tipotexto +
                Banco.COLUMN_ENDERECO_NUMERO + tipotexto +
                Banco.COLUMN_ENDERECO_COMPLEMENTO + tipotexto +
                Banco.COLUMN_ENDERECO_BAIRRO + tipotexto +
                Banco.COLUMN_ENDERECO_CIDADE + tipotexto +
                Banco.COLUMN_ENDERECO_ESTADO + tipotexto +
                Banco.COLUMN_ENDERECO_ID_USER + tipotexton + " )";
        db.execSQL(sqlEndereco);
        //create table galeria perfil
        String sqlGaleriaPerfil = create + Banco.TABLE_GALERIA_PERFIL + " (" +
                Banco.COLUMN_GALERIA_PERFIL_ID + prim +
                Banco.COLUMN_GALERIA_PERFIL_USUARIO_ID + tipotexto +
                Banco.COLUMN_GALERIA_PERFIL_IMAGE + " BLOB " + ")";
        db.execSQL(sqlGaleriaPerfil);
        //create table cliente
        String sqlCliente = create + Banco.TABLE_CLIENTE + " (" +
                Banco.COLUMN_CLIENTE_ID + prim +
                Banco.COLUMN_CLIENTE_ID_USUARIO + tipotexton + " )";
        db.execSQL(sqlCliente);
        //create table fornecedor
        String sqlFornecedor = create + Banco.TABLE_FORNECEDOR + " (" +
                Banco.COLUMN_FORNECEDOR_ID + prim +
                Banco.COLUMN_FORNECEDOR_ID_USUARIO + tipotexton + " )";
        db.execSQL(sqlFornecedor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS endereco");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS fornecedor");
        db.execSQL("DROP TABLE IF EXISTS galeria_perfil");
        onCreate(db);
    }

}
