package kn.beautynow.gui.usuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import kn.beautynow.R;
import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.controller.Session;

import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.cliente.ClienteMenu;
import kn.beautynow.gui.fornecedor.FornecedorMenu;
import kn.beautynow.negocio.fornecedor.FornecedorNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Spinner tipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter<CharSequence> adaptert = ArrayAdapter.createFromResource(this,
                R.array.tipos_usuario, R.layout.spinner_item);
        adaptert.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipo.setAdapter(adaptert);
    }

    public void Logar(View view) {
        EditText email = findViewById(R.id.inputEmail);
        EditText senha = findViewById(R.id.inputSenha);
        Spinner tipo = findViewById(R.id.tipo);
        String semail = email.getText().toString();
        String ssenha = senha.getText().toString();
        String stipo = tipo.getSelectedItem().toString();
        UsuarioNegocio validar = new UsuarioNegocio(getBaseContext());
        Usuario user = validar.existeBanco(semail, ssenha, stipo);
        if (user.getNome().equals("")) {
            Toast.makeText(getBaseContext(), "E-mail ou senha incorretos!", Toast.LENGTH_LONG).show();
            return;
        } else {
            Session session = new Session();
            session.editSessao(user, getBaseContext());
            if (user.getTipoUsuario().equals("Cliente")) {
                Cliente cliente = new Cliente();
                session.editSessaoCliente(cliente, getBaseContext());
                Intent clienteMenu = new Intent(Login.this, ClienteMenu.class);
                startActivity(clienteMenu);
                finish();
            }
            if (user.getTipoUsuario().equals("Fornecedor")) {
                Fornecedor fornecedor = new Fornecedor();
                Servicos servicos = new Servicos();
                servicos.setListaServicos(new FornecedorNegocio(getBaseContext()).carregarServicos(Session.getSession(getBaseContext()).getIdUser()));
                fornecedor.setServicos(servicos);
                fornecedor.setUsuario(Session.getSession(getBaseContext()));
                session.editSessaoFornecedor(fornecedor, getBaseContext());
                Intent fornecedort = new Intent(Login.this, FornecedorMenu.class);
                startActivity(fornecedort);
                finish();
            }
        }
//
    }

    public void goToCadastro(View view) {
        Intent cadastro = new Intent(Login.this, Cadastro.class);
        startActivity(cadastro);
    }
}
