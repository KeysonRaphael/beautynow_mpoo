package kn.beautynow.gui.fornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;

public class ServicosFornecedor extends Fragment implements NovoServico.OnFragmentInteractionListener{
    private OnFragmentInteractionListener mListener;

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
        Fornecedor obj = Session.getSessionFornecedor(getContext());
        if (obj.getServicos().getListaServicos().size() == 0){
            getActivity().setTitle("Novo Serviço");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new NovoServico();
            t.replace(R.id.fornecedor_frame, mFrag);
            t.commit();
        }else{
        RecyclerView reciclerview = inf.findViewById(R.id.recycler);
        RecyclerView.Adapter adapter = new AdapterServicos(obj.getServicos());
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerview.setLayoutManager(llm);
        reciclerview.setAdapter(adapter);}
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
