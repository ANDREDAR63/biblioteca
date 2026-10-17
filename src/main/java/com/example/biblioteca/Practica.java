package com.example.biblioteca;

public class Practica {

    public static int calcularMulta(int diasRetraso) {
        return diasRetraso * 500; // pesos por día
    }

    public static void main(String[] args) {
        String titulo = "Cien años de soledad";
        int añoPublicacion = 1967;
        boolean disponible = true;

        System.out.println("Título: " + titulo);
        System.out.println("Año de publicación: " + añoPublicacion);
        System.out.println("Disponible: " + disponible);

        if (añoPublicacion < 2000) {
            System.out.println("Clásico");
        } else {
            System.out.println("Contemporáneo");
        }

        int añoPrueba = 2015;
        if (añoPrueba < 2000) {
            System.out.println("Clásico");
        } else {
            System.out.println("Contemporáneo");
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("Ejemplar " + i);
        }

        System.out.println("Multa 3 días: " + calcularMulta(3));
        System.out.println("Multa 7 días: " + calcularMulta(7));
        System.out.println("Multa 30 días: " + calcularMulta(30));

        Libro libro1 = new Libro("El señor de los anillos", "J.R.R. Tolkien", 1954);
        Libro libro2 = new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997);
        Libro libro3 = new Libro("El código da Vinci", "Dan Brown", 2003);

        System.out.println(libro1.resumen());
        System.out.println(libro2.resumen());
        System.out.println(libro3.resumen());

        System.out.println("¿Antiguo? " + libro1.esAntiguo());
        System.out.println("¿Antiguo? " + libro2.esAntiguo());
        System.out.println("¿Antiguo? " + libro3.esAntiguo());
    }

}
