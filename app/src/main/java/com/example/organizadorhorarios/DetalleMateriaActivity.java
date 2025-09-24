package com.example.organizadorhorarios;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleMateriaActivity extends AppCompatActivity {
    private TextView tvNombre, tvProfesor, tvSalon, tvDia, tvHorario, tvNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_materia);

        inicializarVistas();
        mostrarDatos();
    }

    private void inicializarVistas() {
        tvNombre = findViewById(R.id.tvDetalleNombre);
        tvProfesor = findViewById(R.id.tvDetalleProfesor);
        tvSalon = findViewById(R.id.tvDetalleSalon);
        tvDia = findViewById(R.id.tvDetalleDia);
        tvHorario = findViewById(R.id.tvDetalleHorario);
        tvNotas = findViewById(R.id.tvDetalleNotas);
    }

    private void mostrarDatos() {
        String nombre = getIntent().getStringExtra("nombre");
        String profesor = getIntent().getStringExtra("profesor");
        String salon = getIntent().getStringExtra("salon");
        int dia = getIntent().getIntExtra("dia", 0);
        String horaInicio = getIntent().getStringExtra("horaInicio");
        String horaFin = getIntent().getStringExtra("horaFin");
        String notas = getIntent().getStringExtra("notas");

        tvNombre.setText(nombre);
        tvProfesor.setText("Profesor: " + profesor);
        tvSalon.setText("Salón: " + salon);
        tvDia.setText("Día: " + obtenerNombreDia(dia));
        tvHorario.setText("Horario: " + horaInicio + " - " + horaFin);
        tvNotas.setText("Notas: " + (notas.isEmpty() ? "Sin notas" : notas));
    }

    private String obtenerNombreDia(int dia) {
        switch (dia) {
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miércoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            default: return "N/A";
        }
    }
}