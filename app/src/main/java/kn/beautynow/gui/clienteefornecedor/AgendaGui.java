package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.usuario.Usuario;

public class AgendaGui extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AgendaGui() {
        // Required empty public constructor
    }

    public static AgendaGui newInstance(String param1, String param2) {
        AgendaGui fragment = new AgendaGui();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View INF = inflater.inflate(R.layout.fragment_agenda_gui, container, false);
        Usuario usuario = Session.getSession(getContext());
        Agenda agenda;
        if(usuario.getTipoUsuario().equals("Cliente")){
            agenda = Session.getSessionCliente(getContext()).getAgenda();
        }else{
            agenda = Session.getSessionFornecedor(getContext()).getAgenda();
        }
        CalendarView cv = (CalendarView) INF.findViewById(R.id.calendarView);
        for (int i = 0; i < agenda.getCalendario().size(); i++) {
            Atividade atividade = agenda.getCalendario().get(i);
            long date = atividade.getData().getTime();
            cv.setDate(date,true,true);
        }

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
