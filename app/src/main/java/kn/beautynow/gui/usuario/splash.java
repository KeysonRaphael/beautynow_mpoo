package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.cliente.ClienteMenu;
import kn.beautynow.gui.fornecedor.FornecedorMenu;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Usuario obj = new Usuario();
        Session session = new Session();
        obj = session.getSession(getBaseContext());
        if (obj != null){
            if (obj.getTipoUsuario().equals("Cliente")) {
                Intent clienteMenu = new Intent(Splash.this, ClienteMenu.class);
                startActivity(clienteMenu);
                finish();
            }
            else {
                Intent fornecedorMenu = new Intent(Splash.this, FornecedorMenu.class);
                startActivity(fornecedorMenu);
                finish();
            }
        }
    }

    public void goToLogin(View view) {
        Intent login = new Intent(Splash.this, Login.class);
        startActivity(login);
    }
    public void goToCadastro(View view) {
        Intent cadastro = new Intent(Splash.this, Cadastro.class);
        startActivity(cadastro);
    }
}
