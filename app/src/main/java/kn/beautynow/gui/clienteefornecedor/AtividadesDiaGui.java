package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.controller.ExceptionCases;

public class AtividadesDiaGui extends Fragment {
    private static Agenda agenda;

    public AtividadesDiaGui() {
        // Required empty public constructor
    }

    public static AtividadesDiaGui newInstance() {
        return new AtividadesDiaGui();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_atividades_dia_gui, container, false);
        final RecyclerView reciclerview = inf.findViewById(R.id.recyclerAtividade);
        RecyclerView.Adapter adapter = new AdapterAtividades(agenda);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerview.setLayoutManager(llm);
        reciclerview.setAdapter(adapter);
        return inf;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new ExceptionCases(" must implement OnFragmentInteractionListener");
        }
    }
}
