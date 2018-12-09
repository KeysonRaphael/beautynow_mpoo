package kn.beautynow.negocio.usuario;

import android.content.Context;

import kn.beautynow.persistencia.UsuarioDao;

public class CadastrarUsuario {
    private Context contexto;
    public CadastrarUsuario(Context contextot){
        contexto = contextot;
    }
    public void cadastroUser(String nome,String cpf,String email,String senha,String tipo, String sexo){
        UsuarioDao usuariodao = new UsuarioDao(contexto);
        usuariodao.insereDado(nome, cpf, email, senha, tipo, sexo);
    }
}
