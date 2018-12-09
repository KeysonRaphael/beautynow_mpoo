package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kn.beautynow.dominio.controller.Session;

import kn.beautynow.R;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.usuario.ValidarUsuario;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Logar(View view) {
        EditText email = findViewById(R.id.inputEmail);
        EditText senha = findViewById(R.id.inputSenha);
        String semail = email.getText().toString();
        String ssenha = senha.getText().toString();
        ValidarUsuario validar = new ValidarUsuario(getBaseContext());
        Usuario user = validar.existeBanco(semail,ssenha);
        if(user.getNome().equals("")){
            Toast.makeText(getBaseContext(),"E-mail ou senha incorretos!",Toast.LENGTH_LONG).show();
            return;
        }
        Session sessao = new Session();
        sessao.editSessao(user);
        if (user.getTipo_usuario().equals("Cliente")) {
            Intent listaFornecedores = new Intent(Login.this, Login.class);
            startActivity(listaFornecedores);
        }
        if (user.getTipo_usuario().equals("Fornecedor")) {
            Intent fornecedor = new Intent(Login.this, Login.class);
            startActivity(fornecedor);
        }
    }
    public void goToCadastro(View view) {
        Intent cadastro = new Intent(Login.this, Cadastro.class);
        startActivity(cadastro);
    }
}
