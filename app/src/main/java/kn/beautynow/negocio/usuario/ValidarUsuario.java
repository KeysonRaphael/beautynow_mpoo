package kn.beautynow.negocio.usuario;

import android.content.Context;

import kn.beautynow.persistencia.UsuarioDao;

public class ValidarUsuario {
    private Context context;
    public ValidarUsuario(Context context){
        this.context = context;
    }
    public String existeBanco(String email, String senha){
        UsuarioDao usuariodao = new UsuarioDao(this.context);
        String result = usuariodao.selectUsuario(email, senha);
        return result;
    }
}
