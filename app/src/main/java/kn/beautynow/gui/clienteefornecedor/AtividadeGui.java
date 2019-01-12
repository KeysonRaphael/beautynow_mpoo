package kn.beautynow.gui.clienteefornecedor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.MaskEditUtil;

public class AtividadeGui extends Fragment {
    private OnFragmentInteractionListener mListener;
    public String servico = "";
    public String valor = "";
    public String fornecedor = "";


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
        final EditText servico = INF.findViewById(R.id.descricao_atividade);
        servico.setText(this.servico);
        final EditText valor = INF.findViewById(R.id.valor_atividade);
        valor.setText(this.valor);
        final EditText fornecedor = INF.findViewById(R.id.fornecedor_atividade);
        fornecedor.setText(this.fornecedor);
        final EditText data = INF.findViewById(R.id.data_atividade);
        final EditText hora = INF.findViewById(R.id.hora_atividade);
        final Calendar myCalendar = Calendar.getInstance();

        Button marcarAtendimento = INF.findViewById(R.id.marcar_atividade);
        marcarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servicos = servico.getText().toString();
                String fornecedors = fornecedor.getText().toString();
                String valors = valor.getText().toString();
                String datas = data.getText().toString();
                String horas = hora.getText().toString();

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
