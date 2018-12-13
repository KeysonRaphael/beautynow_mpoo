package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kn.beautynow.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
