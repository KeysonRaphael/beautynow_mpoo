package kn.beautynow.gui.fornecedor;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.cliente.ClienteServico;
import kn.beautynow.gui.clienteefornecedor.AtividadeGui;
import kn.beautynow.negocio.fornecedor.ServicoNegocio;

public class ServicosFornecedor extends Fragment
        implements
        NovoServico.OnFragmentInteractionListener,
        AtividadeGui.OnFragmentInteractionListener,
        ClienteServico.OnFragmentInteractionListener {

    private OnFragmentInteractionListener mListener;
    public static String predict = "";

    public ServicosFornecedor() {
        // Required empty public constructor
    }

    public static ServicosFornecedor newInstance(String param1, String param2) {
        ServicosFornecedor fragment = new ServicosFornecedor();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_servicos_fornecedor, container, false);
        final Fornecedor obj = Session.getSessionFornecedor(getContext());
        final Usuario user = Session.getSession(getContext());
        if (user.getTipoUsuario().equals("Fornecedor") && obj.getServicos().getListaServicos().size() == 0) {
            getActivity().setTitle("Novo Serviço");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new NovoServico();
            t.replace(R.id.fornecedor_frame, mFrag);
            t.commit();
        } else {
            final RecyclerView reciclerview = inf.findViewById(R.id.recycler);
            final EditText buscaServico = inf.findViewById(R.id.findServico);
            final Servicos servicos;
            if (!ServicosFornecedor.predict.equals("")) {
                servicos = new ServicoNegocio(getContext()).listarServicosFornecedor(predict);
                predict = "";
            } else if (user.getTipoUsuario().equals("Cliente")) {
                servicos = new ServicoNegocio(getContext()).listarServicos();
            } else {
                servicos = obj.getServicos().clone();
            }
//            for (int i = 0;i<servicos.getListaServicos().size();i++){
//                Bitmap imagemn = Bitmap.createScaledBitmap(servicos.getListaServicos().get(i).getImagem(),75, 75, true);
//                servicos.getListaServicos().get(i).setImagem(imagemn);
//            }
            RecyclerView.Adapter adapter = new AdapterServicos(servicos);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            reciclerview.setLayoutManager(llm);
            reciclerview.setAdapter(adapter);
            buscaServico.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Servicos servicosad = new Servicos();
                    int indice = 0;
                    for (int i = 0; i < servicos.getListaServicos().size(); i++) {
                        if (servicos.getListaServicos().get(i).getDescricao().indexOf(buscaServico.getText().toString()) != -1) {
                            servicosad.getListaServicos().add(indice, servicos.getListaServicos().get(i));
                            indice += 1;
                        }
                    }
                    RecyclerView.Adapter adapter = new AdapterServicos(servicosad);
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    reciclerview.setLayoutManager(llm);
                    reciclerview.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        return inf;
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
