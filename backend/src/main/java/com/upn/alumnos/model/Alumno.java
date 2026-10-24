package com.upn.alumnos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA que representa a un alumno.
 * Cada atributo se guarda como una columna de la tabla "alumno" en MySQL.
 */
@Entity
@Table(name = "alumno")
public class Alumno {

    /** Nota mínima para que un alumno sea considerado REGULAR. */
    public static final double NOTA_MINIMA_REGULAR = 13.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombres;

    @Column(nullable = false, length = 120)
    private String carrera;

    @Column(nullable = false)
    private int ciclo;

    @Column(nullable = false)
    private double promedio;

    public Alumno() {
    }

    public Alumno(Long id, String nombres, String carrera, int ciclo, double promedio) {
        this.id = id;
        this.nombres = nombres;
        this.carrera = carrera;
        this.ciclo = ciclo;
        this.promedio = promedio;
    }

    /**
     * Atributo calculado: no se guarda en la base de datos,
     * se obtiene a partir del promedio cada vez que se envía el JSON.
     */
    public String getEstadoAcademico() {
        return promedio >= NOTA_MINIMA_REGULAR ? "REGULAR" : "EN RIESGO";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
