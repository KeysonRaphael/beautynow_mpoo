package kn.beautynow.gui.clienteefornecedor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.MaskEditUtil;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.fornecedor.ServicosFornecedor;
import kn.beautynow.negocio.clienteefornecedor.AtividadeNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

public class AtividadeGui extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static String servico = "";
    public static String valor = "";
    public static String fornecedor = "";


    public AtividadeGui() {
        // Required empty public constructor
    }

    public static AtividadeGui newInstance(String param1, String param2) {
        AtividadeGui fragment = new AtividadeGui();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View INF = inflater.inflate(R.layout.fragment_atividade_gui, container, false);
        final TextView servico = INF.findViewById(R.id.descricao_atividade);
        servico.setText(this.servico);
        final TextView valor = INF.findViewById(R.id.valor_atividade);
        valor.setText(this.valor);
        final TextView fornecedor = INF.findViewById(R.id.fornecedor_atividade);
        String user = new UsuarioNegocio(getContext()).buscarUsarioForncedor(AtividadeGui.fornecedor).getNome();
        fornecedor.setText(user);
        final EditText data = INF.findViewById(R.id.data);
        final EditText hora = INF.findViewById(R.id.hora);
        final Calendar myCalendar = Calendar.getInstance();

        Button marcarAtendimento = INF.findViewById(R.id.marcar_atividade);
        marcarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AtividadeGui.validar(INF)){
                    ArrayList<String> valores = new ArrayList<String>();
                    valores.add(0,servico.getText().toString());
                    valores.add(1,valor.getText().toString());
                    valores.add(2,data.getText().toString());
                    valores.add(3,hora.getText().toString());
                    valores.add(4,Session.getSession(getContext()).getIdUser());
                    valores.add(5,AtividadeGui.fornecedor);
                    new AtividadeNegocio(getContext()).MarcarAtividade(valores);
                    INF.findViewById(R.id.marcarAtendimentoLayout).setVisibility(View.INVISIBLE);
                    INF.findViewById(R.id.atendimento_solicitado).setVisibility(View.VISIBLE);
                }
            }
        });

        Button servicos = INF.findViewById(R.id.goToServicos);
        servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setTitle("Serviços");
                FragmentTransaction t = getFragmentManager().beginTransaction();
                Fragment mFrag = new ServicosFornecedor();
                t.replace(R.id.frame, mFrag);
                t.commit();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
                data.setText(sdf.format(myCalendar.getTime()));
            }

        };

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                myCalendar.set(Calendar.MINUTE,minute);
                hora.setText(myCalendar.getTime().toString().substring(11,16));
            }
        };

        hora.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                new TimePickerDialog(getContext(),time,myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),true).show();}
            }
        });

        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();}
            }
        });

        return INF;
    }

    public static boolean validar(View INF){
        EditText datav = INF.findViewById(R.id.data);
        EditText horav = INF.findViewById(R.id.hora);
        if (datav.getText().toString().equals("")){
            datav.setError("Insira a data que deseja ser atendido!");
            return false;
        }
        if (horav.getText().toString().equals("")){
            horav.setError("Insira a hora que deseja ser atendido!");
            return false;
        }
        return true;
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
