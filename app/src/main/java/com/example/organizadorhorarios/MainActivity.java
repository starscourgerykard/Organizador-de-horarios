package com.example.organizadorhorarios;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HorarioAdapter adapter;
    private DatabaseHelper dbHelper;
    private Button btnAgregarMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVistas();
        configurarRecyclerView();
        configurarEventos();
        cargarMaterias();
    }

    private void inicializarVistas() {
        recyclerView = findViewById(R.id.recyclerViewHorarios);
        btnAgregarMateria = findViewById(R.id.btnAgregarMateria);
        dbHelper = new DatabaseHelper(this);
    }

    private void configurarRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HorarioAdapter(this::mostrarDetalleMateria);
        recyclerView.setAdapter(adapter);
    }

    private void configurarEventos() {
        btnAgregarMateria.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarMateriaActivity.class);
            startActivity(intent);
        });
    }

    private void cargarMaterias() {
        List<Materia> materias = dbHelper.obtenerMaterias();
        adapter.actualizarMaterias(materias);
    }

    private void mostrarDetalleMateria(Materia materia) {
        Intent intent = new Intent(this, DetalleMateriaActivity.class);
        intent.putExtra("nombre", materia.getNombre());
        intent.putExtra("profesor", materia.getProfesor());
        intent.putExtra("salon", materia.getSalon());
        intent.putExtra("dia", materia.getDiaSemana());
        intent.putExtra("horaInicio", materia.getHoraInicio());
        intent.putExtra("horaFin", materia.getHoraFin());
        intent.putExtra("notas", materia.getNotas());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarMaterias();
    }
}