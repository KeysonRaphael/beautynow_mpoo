package kn.beautynow.negocio.usuario;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.persistencia.EnderecoDao;
import kn.beautynow.persistencia.UsuarioDao;

public class UsuarioNegocio {
    private Context contexto;

    public UsuarioNegocio(Context contextot) {
        contexto = contextot;
    }

    public Boolean cadastroUser(String nome, String cpf, String email, String senha, String tipo, String sexo) {
        UsuarioDao usuariodao = new UsuarioDao(this.contexto);
        if (!usuariodao.buscaUsuario(cpf, tipo)) {
            usuariodao.insereDado(nome, cpf, email, senha, tipo, sexo);
            return true;
        } else {
            return false;
        }
    }

    public Usuario existeBanco(String cpf, String senha, String tipo) {
        UsuarioDao usuariodao = new UsuarioDao(this.contexto);
        ArrayList result = usuariodao.selectUsuario(cpf, senha, tipo);
        carregarUsuario(result);
        return carregarUsuario(result);
    }

    public void cadastrarEndereco(ArrayList<String> array) {
        EnderecoDao enderecodao = new EnderecoDao(contexto);
        boolean resultado = enderecodao.existeEndereco(array.get(0));
        if (resultado) {
            enderecodao.updateEndereco(array);
        } else {
            enderecodao.inserirEndereco(array);
        }
        ArrayList endereco = enderecodao.buscarEndereco(array.get(0));
        Usuario sessaold = Session.getSession(contexto);
        sessaold.getEndereco().setCep(endereco.get(1).toString());
        sessaold.getEndereco().setRua(endereco.get(2).toString());
        sessaold.getEndereco().setNumero(endereco.get(3).toString());
        sessaold.getEndereco().setComplemento(endereco.get(4).toString());
        sessaold.getEndereco().setBairro(endereco.get(5).toString());
        sessaold.getEndereco().setCidade(endereco.get(6).toString());
        sessaold.getEndereco().setEstado(endereco.get(7).toString());
        Session session = new Session();
        session.editSessao(sessaold, contexto);
    }

    public Endereco buscarEndereco(String iduser) {
        EnderecoDao enderecodao = new EnderecoDao(contexto);
        ArrayList endereco = enderecodao.buscarEndereco(iduser);
        Endereco enderecoold = new Endereco();
        enderecoold.setCep(endereco.get(1).toString());
        enderecoold.setRua(endereco.get(2).toString());
        enderecoold.setNumero(endereco.get(3).toString());
        enderecoold.setComplemento(endereco.get(4).toString());
        enderecoold.setBairro(endereco.get(5).toString());
        enderecoold.setCidade(endereco.get(6).toString());
        enderecoold.setEstado(endereco.get(7).toString());
        return enderecoold;
    }

    public Usuario buscarUsarioPorTipo(String idfornecedor, String tipo) {
        ArrayList result = new UsuarioDao(contexto).selectUsuarioPorTipo(idfornecedor, tipo);
        return carregarUsuario(result);
    }

    private Usuario carregarUsuario(ArrayList result){
        Usuario user = new Usuario();
        user.setId(result.get(0).toString());
        user.setNome(result.get(1).toString());
        user.setEmail(result.get(2).toString());
        user.setCpf(result.get(3).toString());
        user.setTipoUsuario(result.get(5).toString());
        user.setSexo((String) result.get(7));
        user.setIdUser((String) result.get(8));
        if (result.size() <= 9) {
            return user;
        }
        Endereco endereco = new Endereco();
        endereco.setCep(result.get(9).toString());
        endereco.setRua(result.get(10).toString());
        endereco.setNumero(result.get(11).toString());
        endereco.setComplemento(result.get(12).toString());
        endereco.setBairro(result.get(13).toString());
        endereco.setCidade(result.get(14).toString());
        endereco.setEstado(result.get(15).toString());
        user.setEndereco(endereco);
        return user;
    }
}
