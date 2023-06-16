package com.duvarax.gamerasksapp.Models;

import java.io.Serializable;

public class Comentario implements Serializable {
    private int id;
    private String texto;
    private String fechaCreacion;
    private Usuario usuario;
    private Respuesta respuesta;


    public Comentario(int id, String texto, String fechaCreacion, Usuario usuario, Respuesta respuesta) {
        this.id = id;
        this.texto = texto;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.respuesta = respuesta;
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

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
}
