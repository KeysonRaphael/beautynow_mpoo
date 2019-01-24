package kn.beautynow.gui.usuario;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.ExceptionCases;
import kn.beautynow.dominio.controller.FixCursorWindow;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.usuario.ImagemPerfilNegocio;

public class Perfil extends Fragment {
    public final int IMAGEM_SELECIONADA = 1000;
    public Perfil() {
        // Required empty public constructor
    }

    public static Perfil newInstance() {
        return new Perfil();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        FixCursorWindow.fix();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_perfil, container, false);
        final int PICK_FROM_GALLERY = 1;
        try {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
            }
        } catch (Exception e) {
            Log.d("Error. ", e.toString());
        }
        TextView tv = (TextView) inf.findViewById(R.id.nomeUsuario);
        Session session = new Session();
        final Usuario obj = session.getSession(this.getActivity().getBaseContext());
        tv.setText(obj.getNome());
        ImagemPerfilNegocio imagem = new ImagemPerfilNegocio(getContext());
        if (!obj.getEndereco().getRua().equals("")) {
            TextView enderecot = inf.findViewById(R.id.enderecoUsuario);
            enderecot.setText(obj.getEndereco().printEndereco());
        } else if (obj.getEndereco().getRua().equals("") && obj.getTipoUsuario().equals("Fornecedor")) {
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new EditarEndereco();
            getActivity().setTitle("Endereço Estabelecimento");
            t.replace(R.id.fornecedor_frame, mFrag);
            t.commit();
        }
        Bitmap img = imagem.getImgPerfil(obj.getId());
        if (img != null) {
            ImageView perfil = (ImageView) inf.findViewById(R.id.imagePerfil);
            perfil.setImageBitmap(img);
        } else {
            ImageView perfil = (ImageView) inf.findViewById(R.id.imagePerfil);
            perfil.setImageResource(R.drawable.new_icon);
        }
        Button editarPerfil = (Button) inf.findViewById(R.id.editFoto);
        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, IMAGEM_SELECIONADA);
            }
        });
        Button editarEndereco = inf.findViewById(R.id.editEndereco);
        editarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Editar Endereço");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new EditarEndereco();
                if (obj.getTipoUsuario().equals("Cliente")) {
                    t.replace(R.id.frame, mFrag);
                } else {
                    t.replace(R.id.fornecedor_frame, mFrag);
                }
                t.commit();
            }
        });
        return inf;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGEM_SELECIONADA && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView perfil = (ImageView) getView().findViewById(R.id.imagePerfil);
            Bitmap entrada = BitmapFactory.decodeFile(picturePath);
            Bitmap img = Bitmap.createScaledBitmap(entrada, 250,250,false);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            Gson gson = new Gson();
            String json = preferences.getString("usuario", "");
            Usuario obj = gson.fromJson(json, Usuario.class);
            ImagemPerfilNegocio insereImg = new ImagemPerfilNegocio(getContext());
            if (insereImg.getImgPerfil(obj.getId()) != null) {
                insereImg.updateImgPerfil(obj.getId(), img);
                img = insereImg.getImgPerfil(obj.getId());
                perfil.setImageBitmap(img);
                perfil.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                insereImg.insertImgPerfil(obj.getId(), img);
                perfil.setImageBitmap(img);
                perfil.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

    public void setUsuario(String text) {
        TextView nomeusuario = (TextView) getView().findViewById(R.id.nomeUsuario);
        nomeusuario.setText(text);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
