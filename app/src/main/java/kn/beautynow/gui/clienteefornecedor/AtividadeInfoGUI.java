package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.negocio.clienteefornecedor.AtividadeNegocio;
import kn.beautynow.negocio.clienteefornecedor.NotaNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

public class AtividadeInfoGUI extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static Atividade atividade;

    public AtividadeInfoGUI() {
        // Required empty public constructor
    }

    public static AtividadeInfoGUI newInstance(String param1, String param2) {
        AtividadeInfoGUI fragment = new AtividadeInfoGUI();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View INF = inflater.inflate(R.layout.fragment_atividade_info_gui, container, false);
        TextView data = INF.findViewById(R.id.inputdataview);
        data.setText(atividade.getData());
        TextView hora = INF.findViewById(R.id.inputhoraview);
        hora.setText(atividade.getHora());
        final TextView cliente = INF.findViewById(R.id.inputclienteview);
        TextView fornecedor = INF.findViewById(R.id.inputfornecedorview);
        fornecedor.setText(atividade.getFornecedor());
        final Button confirmarAtendimento = INF.findViewById(R.id.confirmarAtendimento);
        final Button rejeitarAtendimento = INF.findViewById(R.id.rejeitarAtendimento);
        final Button finalizarAtendimento = INF.findViewById(R.id.finalizarAtendimento);
        final Button darNotaFornecedor = INF.findViewById(R.id.darNotaFornecedor);
        final TextView ativo = INF.findViewById(R.id.inputativoview);
        final TextView finalizado = INF.findViewById(R.id.inputfinalizadoview);
        final GridLayout grid = INF.findViewById(R.id.darnota);
        darNotaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid.setVisibility(View.VISIBLE);
            }
        });
        Button enviarNota = INF.findViewById(R.id.enviarNota);
        enviarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nota = INF.findViewById(R.id.inputNota);
                new NotaNegocio(getContext()).inserirNotaNegocio(nota.getText().toString(), atividade.getCliente(), atividade.getFornecedor());
                new AtividadeNegocio(getContext()).notainseridaAtividade(atividade.getId());
                darNotaFornecedor.setVisibility(View.INVISIBLE);
                grid.setVisibility(View.INVISIBLE);

            }
        });
        if (atividade.getNotaAtribuida().equals("S")) {
            darNotaFornecedor.setVisibility(View.INVISIBLE);
        }
        finalizarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizado.setText("Sim");
                new AtividadeNegocio(getContext()).finalizarAtividade(atividade.getId());
                finalizarAtendimento.setVisibility(View.INVISIBLE);
            }
        });
        confirmarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AtividadeNegocio(getContext()).confirmarAtividade(atividade.getId());
                ativo.setText("Sim");
                finalizado.setVisibility(View.VISIBLE);
                TextView finalizadotext = INF.findViewById(R.id.finalizadoview);
                finalizadotext.setVisibility(View.VISIBLE);
                confirmarAtendimento.setVisibility(View.GONE);
                rejeitarAtendimento.setVisibility(View.INVISIBLE);
                finalizarAtendimento.setVisibility(View.VISIBLE);
            }
        });
        rejeitarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AtividadeNegocio(getContext()).recusarAtividade(atividade.getId());
                ativo.setText("Atendimento Rejeitado");
                rejeitarAtendimento.setVisibility(View.INVISIBLE);
                confirmarAtendimento.setVisibility(View.GONE);
            }
        });
        if (Session.getSession(getContext()).getTipoUsuario().equals("Cliente")) {
            cliente.setVisibility(View.INVISIBLE);
            TextView clientetexto = INF.findViewById(R.id.clienteview);
            clientetexto.setVisibility(View.INVISIBLE);
            confirmarAtendimento.setVisibility(View.INVISIBLE);
            rejeitarAtendimento.setVisibility(View.INVISIBLE);
            finalizarAtendimento.setVisibility(View.INVISIBLE);
            fornecedor.setText(new UsuarioNegocio(getContext()).buscarUsarioPorTipo(atividade.getFornecedor(), "Fornecedor").getNome());
            TextView enderecotexto = INF.findViewById(R.id.enderecoview);
            enderecotexto.setVisibility(View.VISIBLE);
            TextView inputendereco = INF.findViewById(R.id.inputenderecoview);
            inputendereco.setText(new UsuarioNegocio(getContext()).buscarUsarioPorTipo(atividade.getFornecedor(), "Fornecedor").getEndereco().printEndereco());
            inputendereco.setVisibility(View.VISIBLE);
        } else {
            fornecedor.setVisibility(View.INVISIBLE);
            TextView fornecedortexto = INF.findViewById(R.id.fornecedorview);
            fornecedortexto.setVisibility(View.INVISIBLE);
            cliente.setText(new UsuarioNegocio(getContext()).buscarUsarioPorTipo(atividade.getCliente(), "Cliente").getNome());
        }
        TextView servico = INF.findViewById(R.id.inputservicoview);
        servico.setText(atividade.getServico());
        TextView valor = INF.findViewById(R.id.inputvalorview);
        valor.setText(atividade.getValor());
        if (atividade.getAtivo().equals("N")) {
            if (Session.getSession(getContext()).getTipoUsuario().equals("Cliente")) {
                ativo.setText("Aguardando Fornecedor");
            } else {
                ativo.setText("Confirme ou Rejeite!");
            }
            finalizado.setVisibility(View.INVISIBLE);
            TextView finalizadotext = INF.findViewById(R.id.finalizadoview);
            finalizadotext.setVisibility(View.INVISIBLE);
        } else if (atividade.getAtivo().equals("S")) {
            ativo.setText("Sim");
            rejeitarAtendimento.setVisibility(View.INVISIBLE);
            confirmarAtendimento.setVisibility(View.INVISIBLE);
            finalizarAtendimento.setVisibility(View.VISIBLE);
        } else {
            ativo.setText("Não, Atendimento Rejeitado");
            rejeitarAtendimento.setVisibility(View.INVISIBLE);
            confirmarAtendimento.setVisibility(View.INVISIBLE);
        }
        if (atividade.getFinalizado().equals("N")) {
            finalizado.setText("Não");
        } else {
            finalizado.setText("Sim");
            rejeitarAtendimento.setVisibility(View.INVISIBLE);
            confirmarAtendimento.setVisibility(View.INVISIBLE);
            finalizarAtendimento.setVisibility(View.INVISIBLE);
            if (Session.getSession(getContext()).getTipoUsuario().equals("Cliente")) {
                if (atividade.getNotaAtribuida().equals("N")) {
                    darNotaFornecedor.setVisibility(View.VISIBLE);
                }
            }
        }
        return INF;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
