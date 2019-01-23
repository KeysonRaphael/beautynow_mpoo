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

import kn.beautynow.R;
import kn.beautynow.dominio.controller.ExceptionCases;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.gui.fornecedor.ServicosFornecedor;
import kn.beautynow.negocio.clienteefornecedor.AtividadeNegocio;
import kn.beautynow.negocio.usuario.UsuarioNegocio;

public class AtividadeGui extends Fragment {
    private static String servico;
    private static String valor;
    private static String fornecedor;


    public AtividadeGui() {
        // Required empty public constructor
    }

    public static AtividadeGui newInstance() {
        return new AtividadeGui();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_atividade_gui, container, false);
        final TextView servicotext = inf.findViewById(R.id.descricao_atividade);
        servicotext.setText(servico);
        final TextView valortext = inf.findViewById(R.id.valor_atividade);
        valortext.setText(valor);
        final TextView fornecedortext = inf.findViewById(R.id.fornecedor_atividade);
        String user = new UsuarioNegocio(getContext()).buscarUsarioPorTipo(fornecedor, "Fornecedor").getNome();
        fornecedortext.setText(user);
        final EditText data = inf.findViewById(R.id.data);
        final EditText hora = inf.findViewById(R.id.hora);
        final Calendar myCalendar = Calendar.getInstance();
        Button marcarAtendimento = inf.findViewById(R.id.marcar_atividade);
        marcarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AtividadeGui.validar(inf)) {
                    ArrayList<String> valores = new ArrayList<>();
                    valores.add(0, servicotext.getText().toString());
                    valores.add(1, valortext.getText().toString());
                    valores.add(2, data.getText().toString());
                    valores.add(3, hora.getText().toString());
                    valores.add(4, Session.getSession(getContext()).getIdUser());
                    valores.add(5, fornecedor);
                    new AtividadeNegocio(getContext()).marcarAtividade(valores);
                    inf.findViewById(R.id.marcarAtendimentoLayout).setVisibility(View.INVISIBLE);
                    inf.findViewById(R.id.atendimento_solicitado).setVisibility(View.VISIBLE);
                }
            }
        });
        Button servicos = inf.findViewById(R.id.goToServicos);
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
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
                data.setText(sdf.format(myCalendar.getTime()));
            }

        };
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                hora.setText(myCalendar.getTime().toString().substring(11, 16));
            }
        };
        hora.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new TimePickerDialog(getContext(), time, myCalendar.get(Calendar.HOUR_OF_DAY),
                            myCalendar.get(Calendar.MINUTE), true).show();
                }
            }
        });
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        return inf;
    }

    public static boolean validar(View inf) {
        EditText datav = inf.findViewById(R.id.data);
        EditText horav = inf.findViewById(R.id.hora);
        if (datav.getText().toString().equals("")) {
            datav.setError("Insira a data que deseja ser atendido!");
            return false;
        }
        if (horav.getText().toString().equals("")) {
            horav.setError("Insira a hora que deseja ser atendido!");
            return false;
        }
        return true;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof OnFragmentInteractionListener)) {
            throw new ExceptionCases(" must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
