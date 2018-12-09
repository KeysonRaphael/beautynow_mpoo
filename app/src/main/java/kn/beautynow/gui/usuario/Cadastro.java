package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import kn.beautynow.R;
import kn.beautynow.negocio.usuario.CadastrarUsuario;

import static kn.beautynow.negocio.ValidaCpf.*;

public class Cadastro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Spinner tipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter<CharSequence> adaptert = ArrayAdapter.createFromResource(this,
                R.array.tipos_usuario, R.layout.spinner_item);
        adaptert.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipo.setAdapter(adaptert);

        Spinner sexo = (Spinner) findViewById(R.id.sexo);
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this,
                R.array.sexos, R.layout.spinner_item);
        adapters.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sexo.setAdapter(adapters);
    }

    public void cadastrarUsuario(View view){
        EditText nome = (EditText) findViewById(R.id.inputNome);
        EditText email = (EditText) findViewById(R.id.inputEmail);
        EditText cpf = (EditText) findViewById(R.id.inputCpf);
        EditText senha = (EditText) findViewById(R.id.inputPassword);
        Spinner tipo = (Spinner) findViewById(R.id.tipo);
        Spinner sexo = (Spinner) findViewById(R.id.sexo);
        String vnome = nome.getText().toString();
        String vemail = email.getText().toString();
        String vcpf = cpf.getText().toString();
        String vsenha = senha.getText().toString();
        String vtipo = tipo.getSelectedItem().toString();
        String vsexo = sexo.getSelectedItem().toString();
        int check = this.validarCampos();
        if (check == 1) {
            CadastrarUsuario cadastro = new CadastrarUsuario(getBaseContext());
            cadastro.cadastroUser(vnome,vcpf,vemail,vsenha,vtipo,vsexo);
            Toast.makeText(getApplicationContext(),"incluido",Toast.LENGTH_LONG).show();
            Intent login = new Intent(cadastro.this, Login.class);
            startActivity(login);
        }
    }
    public int validarCampos(){
        EditText nome = (EditText) findViewById(R.id.inputNome);
        EditText email = (EditText) findViewById(R.id.inputEmail);
        EditText cpf = (EditText) findViewById(R.id.inputCpf);
        EditText senha = (EditText) findViewById(R.id.inputPassword);
        EditText rsenha = (EditText) findViewById(R.id.rsenha);
        String vnome = nome.getText().toString();
        String vemail = email.getText().toString();
        String vcpf = cpf.getText().toString();
        String vsenha = senha.getText().toString();
        String vrsenha = senha.getText().toString();

        int resultado = 0;
        //validar campo nome
        if (vnome.length() == 0) {
            nome.setError("Digite nome!");
            return resultado;
        }
        if (!vnome.matches("^[A-Za-z ]*$")) {
            nome.setError("Nome não deve possuir caracteres especiais");
            return resultado;
        }
        //validar campo email
        if (vemail.length() == 0) {
            email.setError("Digite email!");
            return resultado;
        }
        if (!vemail.matches("^((?!.*?\\.\\.)[A-Za-z0-9\\.\\!\\#\\$\\%\\&\\'*\\+\\-\\/\\=\\?\\^_`\\{\\|\\}\\~]+@[A-Za-z0-9]+[A-Za-z0-9\\-\\.]+\\.[A-Za-z0-9\\-\\.]+[A-Za-z0-9]+)$")){
            email.setError("Email incorreto!");
            return resultado;
        }

        //validar campo cpf
        if (vcpf.length() < 11) {
            cpf.setError("Digite cpf!");
            return resultado;
        }
        if (!isCPF(vcpf)) {
            cpf.setError("cpf incorreto!");
            return resultado;
        }
        //validar campo senha
        if (vsenha.length() == 0) {
            senha.setError("Digite senha!");
            return resultado;
        }
        if (vsenha.length() <= 7) {
            senha.setError("Senha deve conter pelo menos 8 caracteres!");
            return resultado;
        }
        resultado = 1;
        return resultado;
    }
}