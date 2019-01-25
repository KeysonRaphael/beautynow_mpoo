package kn.beautynow.gui.usuario;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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
import kn.beautynow.dominio.controller.MaskEditUtil;
import kn.beautynow.dominio.controller.Session;

import kn.beautynow.dominio.fornecedor.Fornecedor;
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
        EditText cpf = findViewById(R.id.inputCPF);
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
        final int PICK_FROM_GALLERY = 1;
        try {
            if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            }
        } catch (Exception e) {
            Log.d(e.toString(),"context");
        }
    }

    public void Logar(View view) {
        EditText cpf = findViewById(R.id.inputCPF);
        EditText senha = findViewById(R.id.inputSenha);
        Spinner tipo = findViewById(R.id.tipo);
        String scpf = MaskEditUtil.unmask(cpf.getText().toString());
        String ssenha = senha.getText().toString();
        String stipo = tipo.getSelectedItem().toString();
        UsuarioNegocio validar = new UsuarioNegocio(getBaseContext());
        Usuario user = validar.existeBanco(scpf, ssenha, stipo);
        if (user.getNome().equals("")) {
            Toast.makeText(getBaseContext(), "CPF ou senha incorretos!", Toast.LENGTH_LONG).show();
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
