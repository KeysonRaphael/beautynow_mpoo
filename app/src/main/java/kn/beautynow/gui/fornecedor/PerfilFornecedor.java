package kn.beautynow.gui.fornecedor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import kn.beautynow.R;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.usuario.ImagemPerfilNegocio;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFornecedor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFornecedor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFornecedor extends Fragment {
    private OnFragmentInteractionListener mListener;

    public PerfilFornecedor() {
        // Required empty public constructor
    }

    public static PerfilFornecedor newInstance() {
        return new PerfilFornecedor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_perfil_cliente, container, false);
        TextView tv = (TextView) inf.findViewById(R.id.nomeUsuario);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String json = preferences.getString("usuario", "");
        Usuario obj = gson.fromJson(json, Usuario.class);
        tv.setText(obj.getNome());

        ImagemPerfilNegocio imagem = new ImagemPerfilNegocio(getContext());
        Bitmap img = imagem.getImgPerfil(obj.getId());
        if (img != null) {
            ImageView perfil = (ImageView) inf.findViewById(R.id.imagePerfil);
            perfil.setImageBitmap(img);
        }

        Button editarPerfil = (Button)inf.findViewById(R.id.editFoto);
        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1000);
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

            ImageView perfil = (ImageView)getView().findViewById(R.id.imagePerfil);
            Bitmap img = BitmapFactory.decodeFile(picturePath);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Gson gson = new Gson();
            String json = preferences.getString("usuario", "");
            Usuario obj = gson.fromJson(json, Usuario.class);
            ImagemPerfilNegocio insereImg = new ImagemPerfilNegocio(getContext());
            if(insereImg.getImgPerfil(obj.getId()) != null){
                insereImg.updateImgPerfil(obj.getId(),img);
                img = insereImg.getImgPerfil(obj.getId());
                perfil.setImageBitmap(img);
                perfil.setBackgroundColor(getResources().getColor(R.color.white));
            }else{
                insereImg.insertImgPerfil(obj.getId(),img);
                perfil.setImageBitmap(img);
                perfil.setBackgroundColor(getResources().getColor(R.color.white));}
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
