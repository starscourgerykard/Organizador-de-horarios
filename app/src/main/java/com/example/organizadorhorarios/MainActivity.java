package com.example.organizadorhorarios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnAgregarMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontrar el botón
        btnAgregarMateria = findViewById(R.id.btnAgregarMateria);

        // Configurar botón
        btnAgregarMateria.setOnClickListener(v -> {
            Toast.makeText(this, "¡Botón funciona!", Toast.LENGTH_SHORT).show();
        });
    }
}