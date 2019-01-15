package kn.beautynow.gui.cliente;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import kn.beautynow.R;
import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.clienteefornecedor.AtividadeGui;
import kn.beautynow.gui.fornecedor.NovoServico;
import kn.beautynow.negocio.fornecedor.ServicoNegocio;
import kn.beautynow.negocio.usuario.ImagemPerfilNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

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
        final String servicoNome = ClienteServico.descricao;
        inputServicoNome.setText(servicoNome);
        TextView inputServicoValor = inf.findViewById(R.id.valorServico);
        final String servicoValor = ClienteServico.valor;
        inputServicoValor.setText(servicoValor);
        ImageView inputImage = inf.findViewById(R.id.imageServico);
        inputImage.setImageBitmap(ClienteServico.imagen);
        Usuario user = new UsuarioNegocio(getContext()).buscarUsarioPorTipo(idfornecedor, "Fornecedor");
        String idUser = user.getId();
        Bitmap fornecedorimagem = new ImagemPerfilNegocio(getContext()).getImgPerfil(idUser);
        ImageView imageFornecedor = inf.findViewById(R.id.imageFornecedor);
        imageFornecedor.setImageBitmap(fornecedorimagem);
        TextView nomeFornecedor = inf.findViewById(R.id.nomeFornecedor);
        nomeFornecedor.setText(user.getNome());
        TextView enderecoFornecedor = inf.findViewById(R.id.enderecoFornecedor);
        enderecoFornecedor.setText(user.getEndereco().printEndereco());
        Button marcarAtividade = inf.findViewById(R.id.marcarAtendimento);
        marcarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Marcar Atendimento");
                Fragment fragment = new AtividadeGui();
                AtividadeGui.servico = servicoNome;
                AtividadeGui.valor = servicoValor;
                AtividadeGui.fornecedor = idfornecedor;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                ft.commit();
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
