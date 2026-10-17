package com.example.biblioteca;

public class Libro {

    private String titulo;
    private String autor;
    private int anio;
    private boolean disponible;

    public Libro(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public boolean esAntiguo() {
        return anio < 2000;
    }

    public String resumen() {
        return titulo + " de " + autor + " (" + anio + ")";
    }

}
