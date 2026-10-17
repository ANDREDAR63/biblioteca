package com.example.biblioteca;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BibliotecaController {

    private final List<Libro> libros = List.of(
            new Libro("El señor de los anillos", "J.R.R. Tolkien", 1954),
            new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997),
            new Libro("El código da Vinci", "Dan Brown", 2003));

    @GetMapping("/libro")
    public Libro libro() {
        return libros.get(0);
    }

    @GetMapping("/libros")
    public List<Libro> libros() {
        return libros;
    }

    @GetMapping("/libro/resumen")
    public String resumen() {
        return libros.get(0).resumen();
    }

    @GetMapping("/prestamo/{dias}")
    public String prestamo(@PathVariable int dias) {
        if (dias <= 14) {
            return "Préstamo vigente";
        }
        int multa = (dias - 14) * 500;
        return "Préstamo vencido. Multa: $" + multa;
    }

    @GetMapping("/contar/{n}")
    public String contar(@PathVariable int n) {
        StringBuilder cadena = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            cadena.append(i).append(" ");
        }
        return cadena.toString();
    }

}
