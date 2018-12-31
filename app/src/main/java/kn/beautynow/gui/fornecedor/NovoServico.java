package kn.beautynow.gui.fornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;

public class NovoServico extends Fragment {
    private OnFragmentInteractionListener mListener;

    public NovoServico() {
        // Required empty public constructor
    }

    public static NovoServico newInstance() {
        NovoServico fragment = new NovoServico();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_novo_servico, container, false);
        Usuario obj = Session.getSession(this.getActivity().getBaseContext());
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
