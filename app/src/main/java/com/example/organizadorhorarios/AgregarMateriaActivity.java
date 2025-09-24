package com.example.organizadorhorarios;



import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarMateriaActivity extends AppCompatActivity {
    private EditText etNombreMateria, etProfesor, etSalon, etNotas;
    private Spinner spinnerDia;
    private Button btnHoraInicio, btnHoraFin, btnGuardarMateria;
    private String horaInicio = "", horaFin = "";
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materia);

        inicializarVistas();
        configurarSpinner();
        configurarEventos();
    }

    private void inicializarVistas() {
        etNombreMateria = findViewById(R.id.etNombreMateria);
        etProfesor = findViewById(R.id.etProfesor);
        etSalon = findViewById(R.id.etSalon);
        etNotas = findViewById(R.id.etNotas);
        spinnerDia = findViewById(R.id.spinnerDia);
        btnHoraInicio = findViewById(R.id.btnHoraInicio);
        btnHoraFin = findViewById(R.id.btnHoraFin);
        btnGuardarMateria = findViewById(R.id.btnGuardarMateria);
        dbHelper = new DatabaseHelper(this);
    }

    private void configurarSpinner() {
        String[] dias = {"Seleccionar día", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(adapter);
    }

    private void configurarEventos() {
        btnHoraInicio.setOnClickListener(v -> mostrarSelectorHora(true));
        btnHoraFin.setOnClickListener(v -> mostrarSelectorHora(false));
        btnGuardarMateria.setOnClickListener(v -> guardarMateria());
    }

    private void mostrarSelectorHora(boolean esHoraInicio) {
        TimePickerDialog dialog = new TimePickerDialog(this, (view, hora, minuto) -> {
            String horaFormateada = String.format("%02d:%02d", hora, minuto);
            if (esHoraInicio) {
                horaInicio = horaFormateada;
                btnHoraInicio.setText("Inicio: " + horaInicio);
            } else {
                horaFin = horaFormateada;
                btnHoraFin.setText("Fin: " + horaFin);
            }
        }, 8, 0, true);
        dialog.show();
    }

    private void guardarMateria() {
        String nombre = etNombreMateria.getText().toString().trim();
        String profesor = etProfesor.getText().toString().trim();
        String salon = etSalon.getText().toString().trim();
        String notas = etNotas.getText().toString().trim();
        int dia = spinnerDia.getSelectedItemPosition();

        if (nombre.isEmpty() || profesor.isEmpty() || salon.isEmpty() ||
                dia == 0 || horaInicio.isEmpty() || horaFin.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Materia nuevaMateria = new Materia(nombre, profesor, salon, dia, horaInicio, horaFin, notas);
        dbHelper.agregarMateria(nuevaMateria);

        Toast.makeText(this, "Materia guardada exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}