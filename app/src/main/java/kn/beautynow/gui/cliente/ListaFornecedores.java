package kn.beautynow.gui.cliente;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kn.beautynow.R;
import kn.beautynow.dominio.fornecedor.Servicos;
import kn.beautynow.gui.fornecedor.AdapterServicos;
import kn.beautynow.negocio.fornecedor.ServicoNegocio;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaFornecedores.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaFornecedores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFornecedores extends Fragment {
    private OnFragmentInteractionListener mListener;

    public ListaFornecedores() {
        // Required empty public constructor
    }
    public static ListaFornecedores newInstance(String param1, String param2) {
        ListaFornecedores fragment = new ListaFornecedores();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_lista_fornecedores, container, false);
        RecyclerView reciclerview = inf.findViewById(R.id.recycler);
        Servicos servicos = new ServicoNegocio(getContext()).listarServicos();
        RecyclerView.Adapter adapter = new AdapterServicos(servicos);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerview.setLayoutManager(llm);
        reciclerview.setAdapter(adapter);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
