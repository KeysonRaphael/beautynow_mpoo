package kn.beautynow.negocio.fornecedor;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.persistencia.ServicosDao;

public class ServicoNegocio {
    private Context contexto;

    public ServicoNegocio(Context context) {
        this.contexto = context;
    }

    public String BuscarServico(String descricao) {
        ServicosDao servicos = new ServicosDao(contexto);
        String resultado = servicos.buscarServicoDao(descricao);
        return resultado;
    }

    public Servico BuscarServicoFornecedor(String id) {
        ServicosDao servicos = new ServicosDao(contexto);
        Servico resultado = servicos.buscarServicoFornecedorDao(id);
        return resultado;
    }

    public String inserirServico(String descricao) {
        ServicosDao servicos = new ServicosDao(contexto);
        String busca = this.BuscarServico(descricao);
        if (busca.equals("0")) {
            servicos.inserirServico(descricao);
        }
        return descricao;
    }

    public void inserirServicoFornecedor(String descricao, String valor, String idfornecedor, Bitmap image) {
        ServicosDao servicos = new ServicosDao(contexto);
        String idservico = this.inserirServico(descricao);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 10, out);
        byte[] imagem = out.toByteArray();
        servicos.inserirServicoForncedorDao(idfornecedor, idservico, valor, imagem);
        Fornecedor fornecedorn = Session.getSessionFornecedor(contexto);
        Servicos servicosn = new Servicos();
        servicosn.setListaServicos(new FornecedorNegocio(contexto).carregarServicos(Session.getSession(contexto).getIdUser()));
        fornecedorn.setServicos(servicosn);
        Session session = new Session();
        session.editSessaoFornecedor(fornecedorn, contexto);
    }

    public Servicos listarServicos() {
        ServicosDao servicosDao = new ServicosDao(contexto);
        Servicos servicos = new Servicos();
        servicos.setListaServicos(servicosDao.selectServicos());
        return servicos;
    }

    public void updateServicoFornecedor(String servicoNome, String servicoValor, Bitmap servicoImagem, String idServico) {
        ServicosDao servicosDao = new ServicosDao(contexto);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        servicoImagem.compress(Bitmap.CompressFormat.PNG, 10, out);
        byte[] imagem = out.toByteArray();
        servicosDao.updateServicoFornecedor(servicoNome, servicoValor, imagem, idServico);
        Fornecedor fornecedorn = Session.getSessionFornecedor(contexto);
        Servicos servicosn = new Servicos();
        servicosn.setListaServicos(new FornecedorNegocio(contexto).carregarServicos(Session.getSession(contexto).getIdUser()));
        fornecedorn.setServicos(servicosn);
        Session session = new Session();
        session.editSessaoFornecedor(fornecedorn, contexto);
    }

    public Servicos listarServicosFornecedor(String predict) {
        ServicosDao servicosDao = new ServicosDao(contexto);
        Servicos servicos = new Servicos();
        servicos.setListaServicos(servicosDao.selectServicosFornecedorDao(predict));
        return servicos;
    }
}
