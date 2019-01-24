package kn.beautynow.gui.usuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.ExceptionCases;
import kn.beautynow.dominio.controller.MaskEditUtil;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Endereco;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

public class EditarEndereco extends Fragment implements Perfil.OnFragmentInteractionListener {
    private EditText cep;
    private EditText rua;
    private EditText numero;
    private EditText complemento;
    private EditText bairro;
    private EditText cidade;
    private EditText estado;

    public EditarEndereco() {
        // Required empty public constructor
    }

    public static EditarEndereco newInstance() {
        return new EditarEndereco();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_editar_endereco, container, false);
        this.cep = inf.findViewById(R.id.editCep);
        this.rua = inf.findViewById(R.id.editRua);
        this.numero = inf.findViewById(R.id.editNumero);
        this.complemento = inf.findViewById(R.id.editComplemento);
        this.bairro = inf.findViewById(R.id.editBairro);
        this.cidade = inf.findViewById(R.id.editCidade);
        this.estado = inf.findViewById(R.id.editEstado);
        cep.addTextChangedListener(MaskEditUtil.mask(cep, MaskEditUtil.FORMAT_CEP));
        final String id = Session.getSession(inf.getContext()).getId();
        if (!Session.getSession(getContext()).getEndereco().getRua().equals("")) {
            Endereco enderecoAnt = new UsuarioNegocio(getContext()).buscarEndereco(id);
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
                boolean camposCorretos = validarcampos();
                if (camposCorretos) {
                    UsuarioNegocio usuarioneg = new UsuarioNegocio(getContext());
                    ArrayList<String> array = new ArrayList<String>();
                    array.add(0,id);
                    array.add(1,rua.getText().toString());
                    array.add(2,numero.getText().toString());
                    array.add(3,complemento.getText().toString());
                    array.add(4,bairro.getText().toString());
                    array.add(5,cidade.getText().toString());
                    array.add(6,estado.getText().toString());
                    array.add(7,cep.getText().toString());
                    usuarioneg.cadastrarEndereco(array);
                    irPerfil();
                }
            }
        });
        Button voltar = inf.findViewById(R.id.voltarPerfil);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPerfil();
            }
        });
        return inf;
    }

    public void irPerfil(){
        getActivity().setTitle("Perfil");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new Perfil();
        Usuario obj = Session.getSession(getContext());
        if (obj.getTipoUsuario().equals("Cliente")) {
            t.replace(R.id.frame, mFrag);
        } else {
            t.replace(R.id.fornecedor_frame, mFrag);
        }
        t.commit();
    }

    public boolean validarcampos() {
        String ruat = rua.getText().toString();
        String numerot = numero.getText().toString();
        String bairrot = bairro.getText().toString();
        String cidadet = cidade.getText().toString();
        String estadot = estado.getText().toString();
        String cept = cep.getText().toString();
        boolean resultadoEndereco = false;
        if (ruat.equals("")) {
            rua.setError("Digite a rua!");
            return resultadoEndereco;
        }
        if (numerot.equals("")) {
            numero.setError("Digite o numero!");
            return resultadoEndereco;
        }
        if (bairrot.equals("")) {
            bairro.setError("Digite o bairro!");
            return resultadoEndereco;
        }
        if (cidadet.equals("")) {
            cidade.setError("Digite a cidade!");
            return resultadoEndereco;
        }
        if (estadot.equals("")) {
            estado.setError("Digite o estado!");
            return resultadoEndereco;
        }
        if (cept.equals("")) {
            cep.setError("Digite o cep!");
            return resultadoEndereco;
        }
        resultadoEndereco = true;
        return resultadoEndereco;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new ExceptionCases(" must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //nothing
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
