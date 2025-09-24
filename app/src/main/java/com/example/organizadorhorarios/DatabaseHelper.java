package com.example.organizadorhorarios;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String PREFS_NAME = "horarios_prefs";
    private static final String MATERIAS_KEY = "materias";
    private SharedPreferences prefs;
    private Gson gson;

    public DatabaseHelper(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void guardarMaterias(List<Materia> materias) {
        String json = gson.toJson(materias);
        prefs.edit().putString(MATERIAS_KEY, json).apply();
    }

    public List<Materia> obtenerMaterias() {
        String json = prefs.getString(MATERIAS_KEY, "");
        if (json.isEmpty()) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Materia>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void agregarMateria(Materia materia) {
        List<Materia> materias = obtenerMaterias();
        materias.add(materia);
        guardarMaterias(materias);
    }
}