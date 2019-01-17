package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;

public class AtividadesDiaGui extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static Agenda agenda;

    public AtividadesDiaGui() {
        // Required empty public constructor
    }

    public static AtividadesDiaGui newInstance(String param1, String param2) {
        AtividadesDiaGui fragment = new AtividadesDiaGui();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View INF = inflater.inflate(R.layout.fragment_atividades_dia_gui, container, false);
        final RecyclerView reciclerview = INF.findViewById(R.id.recyclerAtividade);
        RecyclerView.Adapter adapter = new AdapterAtividades(agenda);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerview.setLayoutManager(llm);
        reciclerview.setAdapter(adapter);
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
