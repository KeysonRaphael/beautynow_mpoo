package kn.beautynow.gui.usuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import kn.beautynow.R;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.cliente.ClienteMenu;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = preferences.getString("usuario", "");
        Usuario obj = gson.fromJson(json, Usuario.class);
        if (obj != null){
            if (obj.getTipo_usuario().equals("Cliente")) {
                Intent clienteMenu = new Intent(Splash.this, ClienteMenu.class);
                startActivity(clienteMenu);
                finish();
            }
            if (obj.getTipo_usuario().equals("Fornecedor")) {
                Intent fornecedor = new Intent(Splash.this, Login.class);
                startActivity(fornecedor);
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
