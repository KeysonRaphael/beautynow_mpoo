package kn.beautynow.gui.fornecedor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kn.beautynow.R;

public class ServicosViewHolder extends RecyclerView.ViewHolder {
    protected ImageView imagem;
    protected TextView descricao;
    protected TextView valor;

    public ServicosViewHolder(View v) {
        super(v);
        imagem = v.findViewById(R.id.image_servico);
        descricao = v.findViewById(R.id.servico_name);
        valor = v.findViewById(R.id.servico_valor);
    }
}
