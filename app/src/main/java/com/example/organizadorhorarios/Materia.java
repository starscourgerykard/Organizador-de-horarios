package com.example.organizadorhorarios;


public class Materia {
    private String nombre;
    private String profesor;
    private String salon;
    private int diaSemana; // 1=Lunes, 2=Martes, etc.
    private String horaInicio;
    private String horaFin;
    private String notas;

    public Materia(String nombre, String profesor, String salon, int diaSemana,
                   String horaInicio, String horaFin, String notas) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.salon = salon;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.notas = notas;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getProfesor() { return profesor; }
    public String getSalon() { return salon; }
    public int getDiaSemana() { return diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public String getNotas() { return notas; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setProfesor(String profesor) { this.profesor = profesor; }
    public void setSalon(String salon) { this.salon = salon; }
    public void setDiaSemana(int diaSemana) { this.diaSemana = diaSemana; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    public void setNotas(String notas) { this.notas = notas; }
}