package kn.beautynow.gui.cliente;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import kn.beautynow.R;
import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.gui.fornecedor.NovoServico;

public class ClienteServico extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static String idfornecedor = "";
    public static String descricao = "";
    public static String valor = "";
    public static Bitmap imagen;

    public ClienteServico() {
        // Required empty public constructor
    }

    public static ClienteServico newInstance(String param1, String param2) {
        ClienteServico fragment = new ClienteServico();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_cliente_servico, container, false);
        TextView inputServicoNome = inf.findViewById(R.id.textoServico);
        String servicoNome = ClienteServico.descricao;
        inputServicoNome.setText(servicoNome);
        TextView inputServicoValor = inf.findViewById(R.id.valorServico);
        String servicoValor = ClienteServico.valor;
        inputServicoValor.setText(servicoValor);
        ImageView inputImage = inf.findViewById(R.id.imageServico);
        inputImage.setImageBitmap(ClienteServico.imagen);
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
