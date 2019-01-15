package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;
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
        View INF = inflater.inflate(R.layout.fragment_atividade_info_gui, container, false);
        TextView data = INF.findViewById(R.id.inputdataview);
        data.setText(atividade.getData());
        TextView hora = INF.findViewById(R.id.inputhoraview);
        hora.setText(atividade.getHora());
        TextView cliente = INF.findViewById(R.id.inputclienteview);
        TextView fornecedor = INF.findViewById(R.id.inputfornecedorview);
        fornecedor.setText(atividade.getFornecedor());
        Button confirmarAtendimento = INF.findViewById(R.id.confirmarAtendimento);
        Button rejeitarAtendimento = INF.findViewById(R.id.rejeitarAtendimento);
        if (Session.getSession(getContext()).getTipoUsuario().equals("Cliente")){
            cliente.setVisibility(View.INVISIBLE);
            TextView clientetexto = INF.findViewById(R.id.clienteview);
            clientetexto.setVisibility(View.INVISIBLE);
            confirmarAtendimento.setVisibility(View.INVISIBLE);
            rejeitarAtendimento.setVisibility(View.INVISIBLE);
            fornecedor.setText(new UsuarioNegocio(getContext()).buscarUsarioPorTipo(atividade.getFornecedor(),"Fornecedor").getNome());
        }else{
            fornecedor.setVisibility(View.INVISIBLE);
            TextView fornecedortexto = INF.findViewById(R.id.fornecedorview);
            fornecedortexto.setVisibility(View.INVISIBLE);
            cliente.setText(new UsuarioNegocio(getContext()).buscarUsarioPorTipo(atividade.getCliente(),"Cliente").getNome());
        }
        TextView servico = INF.findViewById(R.id.inputservicoview);
        servico.setText(atividade.getServico());
        TextView valor = INF.findViewById(R.id.inputvalorview);
        valor.setText(atividade.getValor());
        TextView ativo = INF.findViewById(R.id.inputativoview);
        TextView finalizado = INF.findViewById(R.id.inputfinalizadoview);
        if (atividade.getAtivo().equals("N")){
            ativo.setText("Não");
            finalizado.setVisibility(View.INVISIBLE);
            TextView finalizadotext = INF.findViewById(R.id.finalizadoview);
            finalizadotext.setVisibility(View.INVISIBLE);
        }else if(atividade.getAtivo().equals("S")){
            ativo.setText("Sim");
        }else{
            ativo.setText("Não, Atendimento Rejeitado");
        }
        if (atividade.getFinalizado().equals("N")){
            finalizado.setText("Não");
        }else {
            finalizado.setText("Sim");
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
