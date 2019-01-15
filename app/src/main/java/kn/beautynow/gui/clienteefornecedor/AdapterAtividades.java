package kn.beautynow.gui.clienteefornecedor;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;
public class AdapterAtividades extends RecyclerView.Adapter<AdapterAtividades.AtividadeViewHolder> {
    private Agenda agenda;

    public AdapterAtividades(Agenda agendaf){
        this.agenda = agendaf;
    }

    @NonNull
    @Override
    public AtividadeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_agenda_layout, viewGroup, false);
        return new AtividadeViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull AtividadeViewHolder atividadeViewHolder, int i) {
        Atividade atividade = agenda.getCalendario().get(i);
        atividadeViewHolder.data.setText(atividade.getData());
        atividadeViewHolder.servico.setText(atividade.getServico());
        atividadeViewHolder.atividade = atividade;
    }

    @Override
    public int getItemCount() {
        return agenda.getCalendario().size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AtividadeViewHolder extends RecyclerView.ViewHolder {
        protected TextView data;
        protected TextView servico;
        protected Atividade atividade;


        public AtividadeViewHolder(View v) {
            super(v);
            data = v.findViewById(R.id.data);
            servico = v.findViewById(R.id.servico);
            atividade = new Atividade();
            final Context contexto = v.getContext();
            v.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    ((Activity) contexto).setTitle("Atendimento");
                    FragmentTransaction t = ((AppCompatActivity) contexto).getSupportFragmentManager().beginTransaction();
                    Fragment mFrag = new AtividadeInfoGUI();
                    AtividadeInfoGUI.atividade = atividade;
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
