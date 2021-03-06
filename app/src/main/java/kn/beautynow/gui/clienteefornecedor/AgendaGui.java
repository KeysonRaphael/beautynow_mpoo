package kn.beautynow.gui.clienteefornecedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kn.beautynow.R;
import kn.beautynow.dominio.clienteefornecedor.Agenda;
import kn.beautynow.dominio.clienteefornecedor.Atividade;
import kn.beautynow.dominio.controller.ExceptionCases;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.negocio.clienteefornecedor.AtividadeNegocio;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

public class AgendaGui extends Fragment {

    public AgendaGui() {
        // Required empty public constructor
    }

    public static AgendaGui newInstance() {
        return new AgendaGui();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_agenda_gui, container, false);
        final Usuario usuario = Session.getSession(getContext());
        final Agenda agenda;
        if (usuario.getTipoUsuario().equals("Cliente")) {
            agenda = new AtividadeNegocio(getContext()).carregarAgendaClienteNegocio(Session.getSession(getContext()).getIdUser());
        } else {
            agenda = new AtividadeNegocio(getContext()).carregarAgendaFornecedorNegocio(Session.getSession(getContext()).getIdUser());
        }
        final MCalendarView cv = inf.findViewById(R.id.mcalendarView);
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
                    new AtividadesDiaGui().setAgenda(agendan);
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
        return inf;
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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
