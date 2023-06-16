package com.duvarax.gamerasksapp.Models;

import java.io.Serializable;

public class Respuesta implements Serializable {
    private int id;
    private String texto;
    private String fechaCreacion;
    private Usuario usuario;
    private Pregunta pregunta;


    public Respuesta(int id, String texto, String fechaCreacion, Usuario usuario, Pregunta pregunta) {
        this.id = id;
        this.texto = texto;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
}
