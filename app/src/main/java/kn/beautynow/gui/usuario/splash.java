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
import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.cliente.ClienteMenu;
import kn.beautynow.gui.fornecedor.FornecedorMenu;
import kn.beautynow.negocio.fornecedor.FornecedorNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;
import kn.beautynow.persistencia.Banco;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            new UsuarioNegocio(getBaseContext()).cadastroUser("keyson","10838811400","cliente@gmail.com",
                    "12345678","Cliente", "Masculino");
            new UsuarioNegocio(getBaseContext()).cadastroUser("keyson","10838811400","fornecedor@gmail.com",
                    "12345678","Fornecedor", "Masculino");
        }catch(Exception e){}
        setContentView(R.layout.activity_splash);
        Banco banco = new Banco(getBaseContext());
        Session session = new Session();
        Usuario obj = Session.getSession(getBaseContext());
        if (obj != null){
            if (obj.getTipoUsuario().equals("Cliente")) {
                Cliente cliente = new Cliente();
                session.editSessaoCliente(cliente,getBaseContext());
                Intent clienteMenu = new Intent(Splash.this, ClienteMenu.class);
                startActivity(clienteMenu);
                finish();
            }
            else {
                Fornecedor fornecedor = new Fornecedor();
                Servicos servicos = new Servicos();
                servicos.setListaServicos(new FornecedorNegocio(getBaseContext()).carregarServicos(Session.getSession(getBaseContext()).getIdUser()));
                fornecedor.setServicos(servicos);
                fornecedor.setUsuario(Session.getSession(getBaseContext()));
                session.editSessaoFornecedor(fornecedor,getBaseContext());
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
