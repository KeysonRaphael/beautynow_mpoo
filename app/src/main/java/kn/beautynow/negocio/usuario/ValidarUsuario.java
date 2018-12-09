package kn.beautynow.negocio.usuario;

import android.content.Context;

import java.util.ArrayList;

import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.persistencia.UsuarioDao;

public class ValidarUsuario {
    private Context context;
    public ValidarUsuario(Context context){
        this.context = context;
    }
    public Usuario existeBanco(String email, String senha){
        UsuarioDao usuariodao = new UsuarioDao(this.context);
        ArrayList result = usuariodao.selectUsuario(email, senha);
        Usuario user = new Usuario();
        if(result.isEmpty())
        {
            return user;
        }
        user.setNome(result.get(1).toString());
        user.setEmail(result.get(2).toString());
        user.setCpf(result.get(3).toString());
        user.setTipo_usuario(result.get(5).toString());
        user.setSexo((String) result.get(7));
        if(8 >= result.size()){
            return user;
        }
        Endereco endereco = new Endereco();
        endereco.setCep(result.get(8).toString());
        endereco.setNumero(result.get(9).toString());
        endereco.setRua(result.get(10).toString());
        endereco.setBairro(result.get(11).toString());
        endereco.setCidade(result.get(12).toString());
        endereco.setEstado(result.get(13).toString());
        endereco.setPais(result.get(14).toString());
        user.setEndereco(endereco);
        return user;
    }
}
