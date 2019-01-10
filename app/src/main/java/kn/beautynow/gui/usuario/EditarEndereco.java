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
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

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
        final View inf = inflater.inflate(R.layout.fragment_editar_endereco, container, false);
        EditText cep = inf.findViewById(R.id.editCep);
        cep.addTextChangedListener(MaskEditUtil.mask(cep,MaskEditUtil.FORMAT_CEP));
        final String id = Session.getSession(inf.getContext()).getId();
        if (!Session.getSession(getContext()).getEndereco().getRua().equals("")){
            Endereco enderecoAnt = new UsuarioNegocio(getContext()).buscarEndereco(Session.getSession(getContext()).getId());
            EditText rua = inf.findViewById(R.id.editRua);
            EditText numero = inf.findViewById(R.id.editNumero);
            EditText complemento = inf.findViewById(R.id.editComplemento);
            EditText bairro = inf.findViewById(R.id.editBairro);
            EditText cidade = inf.findViewById(R.id.editCidade);
            EditText estado = inf.findViewById(R.id.editEstado);
            cep.setText(enderecoAnt.getCep());
            rua.setText(enderecoAnt.getRua());
            numero.setText(enderecoAnt.getNumero());
            complemento.setText(enderecoAnt.getComplemento());
            bairro.setText(enderecoAnt.getBairro());
            cidade.setText(enderecoAnt.getCidade());
            estado.setText(enderecoAnt.getEstado());
        }
        Button cadastrarEndereco = inf.findViewById(R.id.cadastrarEndereco);
        cadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean camposCorretos = EditarEndereco.validarcampos(inf);
                if(camposCorretos){
                EditText rua = inf.findViewById(R.id.editRua);
                EditText numero = inf.findViewById(R.id.editNumero);
                EditText complemento = inf.findViewById(R.id.editComplemento);
                EditText bairro = inf.findViewById(R.id.editBairro);
                EditText cidade = inf.findViewById(R.id.editCidade);
                EditText estado = inf.findViewById(R.id.editEstado);
                EditText cep = inf.findViewById(R.id.editCep);
                String ruat = rua.getText().toString();
                String numerot = numero.getText().toString();
                String complementot = complemento.getText().toString();
                String bairrot = bairro.getText().toString();
                String cidadet = cidade.getText().toString();
                String estadot = estado.getText().toString();
                String cept = cep.getText().toString();
                UsuarioNegocio usuarioneg = new UsuarioNegocio(getContext());
                usuarioneg.cadastrarEndereco(id, ruat, numerot, complementot, bairrot, cidadet, estadot, cept);
                getActivity().setTitle("Perfil");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Perfil();
                Usuario obj = Session.getSession(getContext());
                if(obj.getTipoUsuario().equals("Cliente")){
                    t.replace(R.id.frame, mFrag);
                }else{
                    t.replace(R.id.fornecedor_frame, mFrag);
                }
                t.commit();}
            }
        });
        Button voltar = inf.findViewById(R.id.voltarPerfil);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Perfil");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new Perfil();
                Usuario obj = Session.getSession(getContext());
                if(obj.getTipoUsuario().equals("Cliente")){
                    t.replace(R.id.frame, mFrag);
                }else{
                    t.replace(R.id.fornecedor_frame, mFrag);
                }
                t.commit();
            }
        });

        return inf;
    }

    public static boolean validarcampos(View inf){
        EditText rua = inf.findViewById(R.id.editRua);
        EditText numero = inf.findViewById(R.id.editNumero);
        EditText complemento = inf.findViewById(R.id.editComplemento);
        EditText bairro = inf.findViewById(R.id.editBairro);
        EditText cidade = inf.findViewById(R.id.editCidade);
        EditText estado = inf.findViewById(R.id.editEstado);
        EditText cep = inf.findViewById(R.id.editCep);
        String ruat = rua.getText().toString();
        String numerot = numero.getText().toString();
        String complementot = complemento.getText().toString();
        String bairrot = bairro.getText().toString();
        String cidadet = cidade.getText().toString();
        String estadot = estado.getText().toString();
        String cept = cep.getText().toString();
        boolean resultado = false;
        if (ruat.equals("")){
            rua.setError("Digite a rua!");
            return resultado;
        }
        if (numerot.equals("")){
            numero.setError("Digite o numero!");
            return resultado;
        }
        if (bairrot.equals("")){
            bairro.setError("Digite o bairro!");
            return resultado;
        }
        if (cidadet.equals("")){
            cidade.setError("Digite a cidade!");
            return resultado;
        }
        if (estadot.equals("")){
            estado.setError("Digite o estado!");
            return resultado;
        }
        if (cept.equals("")){
            cep.setError("Digite o cep!");
            return resultado;
        }
        resultado = true;
        return resultado;
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
