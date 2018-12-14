package kn.beautynow.negocio.usuario;

import android.content.Context;

import kn.beautynow.persistencia.UsuarioDao;

public class CadastrarUsuario {
    private Context contexto;
    public CadastrarUsuario(Context contextot){
        contexto = contextot;
    }
    public Boolean cadastroUser(String nome,String cpf,String email,String senha,String tipo, String sexo){
        UsuarioDao usuariodao = new UsuarioDao(contexto);
        if (!usuariodao.buscaUsuario(cpf, tipo)){
            usuariodao.insereDado(nome, cpf, email, senha, tipo, sexo);
            return true;
        }else{
            return false;
        }
    }
}
