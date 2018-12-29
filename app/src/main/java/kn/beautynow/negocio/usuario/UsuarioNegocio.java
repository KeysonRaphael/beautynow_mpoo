package kn.beautynow.negocio.usuario;

import android.content.Context;

import java.util.ArrayList;

import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.persistencia.EnderecoDao;
import kn.beautynow.persistencia.UsuarioDao;

public class UsuarioNegocio {
    private Context contexto;

    public UsuarioNegocio(Context contextot){
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

    public Usuario existeBanco(String email, String senha, String tipo){
        UsuarioDao usuariodao = new UsuarioDao(this.contexto);
        ArrayList result = usuariodao.selectUsuario(email, senha, tipo);
        Usuario user = new Usuario();
        if(result.isEmpty())
        {
            return user;
        }
        user.setId(result.get(0).toString());
        user.setNome(result.get(1).toString());
        user.setEmail(result.get(2).toString());
        user.setCpf(result.get(3).toString());
        user.setTipoUsuario(result.get(5).toString());
        user.setSexo((String) result.get(7));
        if(8 >= result.size()){
            return user;
        }
        Endereco endereco = new Endereco();
        endereco.setCep(result.get(8).toString());
        endereco.setRua(result.get(9).toString());
        endereco.setNumero(result.get(10).toString());
        endereco.setComplemento(result.get(11).toString());
        endereco.setBairro(result.get(12).toString());
        endereco.setCidade(result.get(13).toString());
        endereco.setEstado(result.get(14).toString());
        user.setEndereco(endereco);
        return user;
    }

    public void cadastrarEndereco(String iduser,String rua, String numero, String complemento, String bairro,String cidade,String estado, String cep){
        EnderecoDao enderecodao = new EnderecoDao(contexto);
        boolean resultado = enderecodao.existeEndereco(iduser);
        if (resultado){
            enderecodao.updateEndereco(iduser, rua, numero, complemento, bairro, cidade, estado, cep);
        }else{
            enderecodao.inserirEndereco(iduser, rua, numero, complemento, bairro, cidade, estado, cep);
        }
        ArrayList endereco = enderecodao.buscarEndereco(iduser);
        Session.getSession(contexto).getEndereco().setCep(endereco.get(1).toString());
        Session.getSession(contexto).getEndereco().setRua(endereco.get(2).toString());
        Session.getSession(contexto).getEndereco().setNumero(endereco.get(3).toString());
        Session.getSession(contexto).getEndereco().setComplemento(endereco.get(4).toString());
        Session.getSession(contexto).getEndereco().setBairro(endereco.get(5).toString());
        Session.getSession(contexto).getEndereco().setCidade(endereco.get(6).toString());
        Session.getSession(contexto).getEndereco().setEstado(endereco.get(7).toString());
    }
}
