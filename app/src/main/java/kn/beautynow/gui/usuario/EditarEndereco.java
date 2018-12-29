package kn.beautynow.gui.usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.MaskEditUtil;

public class EditarEndereco extends Fragment implements Perfil.OnFragmentInteractionListener{

    private OnFragmentInteractionListener mListener;

    public EditarEndereco() {
        // Required empty public constructor
    }

    public static EditarEndereco newInstance(String param1, String param2) {
        EditarEndereco fragment = new EditarEndereco();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_editar_endereco, container, false);
        EditText cep = inf.findViewById(R.id.editCep);
        cep.addTextChangedListener(MaskEditUtil.mask(cep,MaskEditUtil.FORMAT_CEP));
        Button cadastrarEndereco = inf.findViewById(R.id.cadastrarEndereco);
        cadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().setTitle("Perfil");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Perfil();
                t.replace(R.id.frame, mFrag);
                t.commit();
            }
        });
        Button voltar = inf.findViewById(R.id.voltarPerfil);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Perfil");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Perfil();
                t.replace(R.id.frame, mFrag);
                t.commit();
            }
        });

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
