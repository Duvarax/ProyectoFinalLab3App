package com.duvarax.gamerasksapp.Models;

import java.io.Serializable;

public class Juego implements Serializable {
    private int Id;
    private String Nombre;
    private String Descripcion;
    private String Portada;
    private String Autor;
    private String fechaLanzamiento;

    public Juego(int id, String nombre, String descripcion, String portada, String autor, String fechaLanzamiento) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
        Portada = portada;
        Autor = autor;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String portada) {
        Portada = portada;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
