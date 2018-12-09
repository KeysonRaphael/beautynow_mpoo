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
        user.setNome(result.get(0).toString());
        user.setCpf(result.get(1).toString());
        user.setEmail(result.get(2).toString());
        user.setTipo_usuario(result.get(3).toString());
        user.setSexo(result.get(4).toString());
        if(5 >= result.size()){
            return user;
        }
        Endereco endereco = new Endereco();
        endereco.setCep(result.get(5).toString());
        endereco.setNumero(result.get(6).toString());
        endereco.setRua(result.get(7).toString());
        endereco.setBairro(result.get(8).toString());
        endereco.setCidade(result.get(9).toString());
        endereco.setEstado(result.get(10).toString());
        endereco.setPais(result.get(11).toString());
        return user;
    }
}
