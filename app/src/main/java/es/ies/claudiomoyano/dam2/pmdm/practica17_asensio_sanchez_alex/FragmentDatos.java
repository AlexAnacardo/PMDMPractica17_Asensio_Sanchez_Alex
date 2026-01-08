package es.ies.claudiomoyano.dam2.pmdm.practica17_asensio_sanchez_alex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDatos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDatos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvNombre, tvApellidos, tvDni;
    Button botonDelegado;

    public FragmentDatos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDatos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDatos newInstance(String param1, String param2) {
        FragmentDatos fragment = new FragmentDatos();
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
        View view = inflater.inflate(R.layout.fragment_datos, container, false);

        tvNombre = view.findViewById(R.id.tvNombre);
        tvApellidos = view.findViewById(R.id.tvApellidos);
        tvDni = view.findViewById(R.id.tvDni);

        botonDelegado = view.findViewById(R.id.botonDelegado);

        return view;
    }

    public void mostrarDatosAlumno(Alumno alumno) {
        if (alumno != null) {
            tvNombre.setText(alumno.getNombre());
            tvApellidos.setText(alumno.getApellidos());
            tvDni.setText(alumno.getDni());
        }
    }

    public Button getBotonDelegado(){
        return this.botonDelegado;
    }
}