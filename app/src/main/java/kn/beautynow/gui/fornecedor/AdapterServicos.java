package kn.beautynow.gui.fornecedor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kn.beautynow.R;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;

public class AdapterServicos extends RecyclerView.Adapter<ServicosViewHolder> {
    private Servicos servicos;

    public AdapterServicos(Servicos servicosf){
        this.servicos = servicosf;
    }
    @NonNull
    @Override
    public ServicosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ServicosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicosViewHolder servicosViewHolder, int i) {
        Servico servico = servicos.getListaServicos().get(i);
        servicosViewHolder.descricao.setText(servico.getDescricao());
        servicosViewHolder.valor.setText(servico.getValor());
        servicosViewHolder.imagem.setImageBitmap(servico.getImagem());
    }

    @Override
    public int getItemCount() {
        return servicos.getListaServicos().size();
    }

}
