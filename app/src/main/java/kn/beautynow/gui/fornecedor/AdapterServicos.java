package kn.beautynow.gui.fornecedor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.gui.cliente.ClienteServico;

public class AdapterServicos extends RecyclerView.Adapter<AdapterServicos.ServicosViewHolder> {
    private Servicos servicos;

    public AdapterServicos(Servicos servicosf) {
        this.servicos = servicosf;
    }

    @NonNull
    @Override
    public ServicosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        return new ServicosViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ServicosViewHolder servicosViewHolder, int i) {
        Servico servico = servicos.getListaServicos().get(i);
        servicosViewHolder.descricao.setText(servico.getDescricao());
        servicosViewHolder.valor.setText(servico.getValor());
        servicosViewHolder.fornecedor = servico.getIdFornecedor();
        servicosViewHolder.servico = servico.getId();
        servicosViewHolder.imagem.setImageBitmap(servico.getImagemGaleria());
    }

    @Override
    public int getItemCount() {
        return servicos.getListaServicos().size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ServicosViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imagem;
        protected Bitmap imagemr;
        protected TextView descricao;
        protected TextView valor;
        protected String fornecedor;
        protected String servico;


        public ServicosViewHolder(View v) {
            super(v);
            imagem = v.findViewById(R.id.image_servico);
            descricao = v.findViewById(R.id.servico_name);
            valor = v.findViewById(R.id.servico_valor);
            fornecedor = "";
            servico = "";
            final Context contexto = v.getContext();
            v.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    if (Session.getSession(contexto).getTipoUsuario().equals("Fornecedor")) {
                        ((Activity) contexto).setTitle("Editar Serviço");
                        FragmentTransaction t = ((AppCompatActivity) contexto).getSupportFragmentManager().beginTransaction();
                        Fragment mFrag = new NovoServico();
                        new NovoServico().setIdservico(servico);
                        t.replace(R.id.fornecedor_frame, mFrag);
                        t.commit();
                    } else {
                        ((Activity) contexto).setTitle("Serviço");
                        FragmentTransaction t = ((AppCompatActivity) contexto).getSupportFragmentManager().beginTransaction();
                        Fragment mFrag = new ClienteServico();
                        new ClienteServico().setIdfornecedor(fornecedor);
                        new ClienteServico().setIdservico(servico);
                        t.replace(R.id.frame, mFrag);
                        t.commit();
                    }
                }
            });
        }
    }
}
