package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kn.beautynow.dominio.controller.Session;

import kn.beautynow.R;
import kn.beautynow.negocio.usuario.ValidarUsuario;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Logar(View view) {
        EditText email = (EditText) findViewById(R.id.inputEmail);
        EditText senha = (EditText) findViewById(R.id.inputSenha);
        String semail = email.getText().toString();
        String ssenha = senha.getText().toString();
        ValidarUsuario validar = new ValidarUsuario(getBaseContext());
        String teste = validar.existeBanco(semail,ssenha);
        Toast.makeText(getBaseContext(),teste,Toast.LENGTH_LONG);
//        Session sessao = new Session();
//        sessao.editSessao(semail,ssenha);
//        Intent login = new Intent(Login.this, Login.class);
//        startActivity(login);
    }
    public void goToCadastro(View view) {
        Intent cadastro = new Intent(Login.this, Cadastro.class);
        startActivity(cadastro);
    }
}
