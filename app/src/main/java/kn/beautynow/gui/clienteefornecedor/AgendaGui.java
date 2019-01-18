package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.clienteefornecedor.AtividadeNegocio;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

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
        final Usuario usuario = Session.getSession(getContext());
        final Agenda agenda;
        if (usuario.getTipoUsuario().equals("Cliente")) {
            agenda = new AtividadeNegocio(getContext()).carregarAgendaClienteNegocio(Session.getSession(getContext()).getIdUser());
        } else {
            agenda = new AtividadeNegocio(getContext()).carregarAgendaFornecedorNegocio(Session.getSession(getContext()).getIdUser());
        }
        final MCalendarView cv = INF.findViewById(R.id.mcalendarView);
        cv.getMarkedDates().getAll().clear();
        for (int i = 0; i < agenda.getCalendario().size(); i++) {
            Atividade atividade = agenda.getCalendario().get(i);
            String[] date = atividade.getData().split("/");
            cv.markDate(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        }
        cv.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                int count = 0;
                Agenda agendan = new Agenda();
                for (int i = 0; i < agenda.getCalendario().size(); i++) {
                    Atividade atividade = agenda.getCalendario().get(i);
                    String[] dates = atividade.getData().split("/");
                    cv.markDate(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
                    if (dates[0].equals(date.getDayString()) && dates[1].equals(date.getMonthString())) {
                        agendan.getCalendario().add(count, atividade);
                        count += 1;
                    }
                }
                if (agendan.getCalendario().size() > 0) {
                    getActivity().setTitle("Marcações");
                    Fragment fragment = new AtividadesDiaGui();
                    AtividadesDiaGui.agenda = agendan;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    if (usuario.getTipoUsuario().equals("Cliente")) {
                        ft.replace(R.id.frame, fragment);
                    } else {
                        ft.replace(R.id.fornecedor_frame, fragment);
                    }
                    ft.commit();
                }
            }
        });
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
