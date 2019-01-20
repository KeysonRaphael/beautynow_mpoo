package kn.beautynow.gui.cliente;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.fornecedor.ServicosFornecedor;
import kn.beautynow.negocio.ImagemNegocio;
import kn.beautynow.negocio.recomendacao.SlopeOne;
import kn.beautynow.negocio.usuario.ImagemPerfilNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

public class RecomendacaoGUI extends Fragment {

    private OnFragmentInteractionListener mListener;

    public RecomendacaoGUI() {
        // Required empty public constructor
    }

    public static RecomendacaoGUI newInstance(String param1, String param2) {
        RecomendacaoGUI fragment = new RecomendacaoGUI();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View INF = inflater.inflate(R.layout.fragment_recomendacao_gui, container, false);
        final String fornecedorpredict = SlopeOne.main(getContext(), Session.getSession(getContext()).getIdUser());
        if (!fornecedorpredict.equals("")) {
            Usuario usuario = new UsuarioNegocio(getContext()).buscarUsarioPorTipo(fornecedorpredict, "Fornecedor");
            Bitmap perfil = new ImagemPerfilNegocio(getContext()).getImgPerfil(usuario.getId());
            ImageView perfilfornecedor = INF.findViewById(R.id.inputperfil);
            perfilfornecedor.setImageBitmap(perfil);
            TextView nome = INF.findViewById(R.id.inputfornecedor);
            nome.setText(usuario.getNome());
            TextView endereco = INF.findViewById(R.id.inputendereco);
            endereco.setText(usuario.getEndereco().printEndereco());
            Button servicosfornecedor = INF.findViewById(R.id.servicosfornecedor);
            servicosfornecedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().setTitle("Serviços Fornecedor");
                    FragmentTransaction t = getFragmentManager().beginTransaction();
                    Fragment mFrag = new ServicosFornecedor();
                    ServicosFornecedor.predict = fornecedorpredict;
                    t.replace(R.id.frame, mFrag);
                    t.commit();
                }
            });
        } else {
            GridLayout sem = INF.findViewById(R.id.semrecomendacao);
            sem.setVisibility(View.VISIBLE);
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