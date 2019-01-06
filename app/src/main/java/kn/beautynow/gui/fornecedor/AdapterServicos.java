package kn.beautynow.gui.fornecedor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import kn.beautynow.R;
import kn.beautynow.dominio.fornecedor.Servico;
import kn.beautynow.dominio.fornecedor.Servicos;

public class AdapterServicos extends RecyclerView.Adapter<AdapterServicos.ServicosViewHolder> {
    private Servicos servicos;

    public AdapterServicos(Servicos servicosf){
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
        servicosViewHolder.imagem.setImageBitmap(servico.getImagem());
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
        protected TextView descricao;
        protected TextView valor;

        public ServicosViewHolder(View v) {
            super(v);
            imagem = v.findViewById(R.id.image_servico);
            descricao = v.findViewById(R.id.servico_name);
            valor = v.findViewById(R.id.servico_valor);
            final Context contexto = v.getContext();
            v.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override public void onClick(View v) {
                    ((Activity)contexto).setTitle("Editar Serviço");
                    FragmentTransaction t = ((AppCompatActivity)contexto).getSupportFragmentManager().beginTransaction();
                    Fragment mFrag = new NovoServico();
                    NovoServico.valor = (valor.getText().toString());
                    NovoServico.descricao = (descricao.getText().toString());
                    NovoServico.imagen = ((BitmapDrawable)imagem.getDrawable()).getBitmap();
                    t.replace(R.id.fornecedor_frame, mFrag);
                    t.commit();
                }
            });
        }
    }
}
