package kn.beautynow.gui.clienteefornecedor;

import android.app.Activity;
import android.content.Context;
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

import java.text.NumberFormat;
import java.util.Locale;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.AgendaViewHolder> {
    private Agenda agenda;

    public AdapterAgenda(Agenda agendaf){
        this.agenda = agendaf;
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_agenda_layout, viewGroup, false);
        return new AgendaViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder agendaViewHolder, int i) {
        Atividade atividade = agenda.getCalendario().get(i);
        agendaViewHolder.descricao.setText(atividade.getServico().getDescricao());
        agendaViewHolder.data.setText(atividade.getData().toString());
        agendaViewHolder.imagem.setImageBitmap(atividade.getServico().getImagem());
        agendaViewHolder.valor.setText(atividade.getServico().getValor());
        agendaViewHolder.fornecedor = atividade.getServico().getIdFornecedor();
        agendaViewHolder.cliente = atividade.getCliente().getUsuario().getNome();
    }

    @Override
    public int getItemCount() {
        return agenda.getCalendario().size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AgendaViewHolder extends RecyclerView.ViewHolder {
        protected TextView descricao;
        protected TextView valor;
        protected TextView data;
        protected ImageView imagem;
        protected String fornecedor;
        protected String cliente;
        protected String servico;

        public AgendaViewHolder(View v) {
            super(v);
            descricao = v.findViewById(R.id.descricao_atividade);
            valor = v.findViewById(R.id.valor_atividade);
            data = v.findViewById(R.id.data_atividade);
            imagem = v.findViewById(R.id.image_atividade);
            fornecedor = "";
            cliente = "";
            servico = "";
            final Context contexto = v.getContext();
            v.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    FragmentTransaction t = ((AppCompatActivity) contexto).getSupportFragmentManager().beginTransaction();
                    Fragment mFrag = new AtividadeGui();
                    ((Activity) contexto).setTitle("Atividade");
                    if (Session.getSession(contexto).getTipoUsuario().equals("Fornecedor")) {
                        t.replace(R.id.fornecedor_frame, mFrag);
                    } else {
                        t.replace(R.id.frame, mFrag);
                    }
                    t.commit();
                }
            });
        }
    }
}
