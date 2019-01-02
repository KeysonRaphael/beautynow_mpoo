package kn.beautynow.gui.fornecedor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.fornecedor.ServicoNegocio;
import kn.beautynow.negocio.usuario.ImagemPerfilNegocio;

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
        final View inf = inflater.inflate(R.layout.fragment_novo_servico, container, false);
        final Usuario obj = Session.getSession(this.getActivity().getBaseContext());
        final ImageView inputImage = inf.findViewById(R.id.inputServicoImage);
        Button incluirServicoImagem = inf.findViewById(R.id.inserirImagemServico);
        incluirServicoImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1000);
            }
        });
        Button salvarServico = inf.findViewById(R.id.salvarServico);
        salvarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputServicoNome = inf.findViewById(R.id.inputServicoNome);
                String servicoNome = inputServicoNome.getText().toString();
                EditText inputServicoValor = inf.findViewById(R.id.inputServicoValor);
                String servicoValor = inputServicoValor.getText().toString();
                BitmapDrawable servicoImage = (BitmapDrawable) inputImage.getDrawable();
                Bitmap servicoImagem = servicoImage.getBitmap();
                ServicoNegocio servicoNegocio = new ServicoNegocio(getContext());
                servicoNegocio.inserirServicoFornecedor(servicoNome,servicoValor,obj.getIdUser(),servicoImagem);
            }
        });
        return inf;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1000  && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView inputImage = getView().findViewById(R.id.inputServicoImage);
            Bitmap img = BitmapFactory.decodeFile(picturePath);
            inputImage.setImageBitmap(img);
        }
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
