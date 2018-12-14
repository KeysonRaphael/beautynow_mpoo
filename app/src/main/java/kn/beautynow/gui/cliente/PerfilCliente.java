package kn.beautynow.gui.cliente;

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
 * {@link PerfilCliente.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilCliente extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PerfilCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilCliente.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilCliente newInstance(String param1, String param2) {
        PerfilCliente fragment = new PerfilCliente();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
    public void setUsuario(String text){
        TextView nomeusuario = (TextView)getView().findViewById(R.id.nomeUsuario);
        nomeusuario.setText(text);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
