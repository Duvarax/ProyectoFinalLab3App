package com.duvarax.gamerasksapp.Models;

import java.io.Serializable;

public class Pregunta implements Serializable {
    private int id;
    private String texto;
    private String fechaCreacion;
    private Usuario usuario;
    private Juego juego;
    private String captura;

    public Pregunta(int id, String texto, String fechaCreacion, Usuario usuario, Juego juego, String captura) {
        this.id = id;
        this.texto = texto;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.juego = juego;
        this.captura = captura;
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

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public String getCaptura() {
        return captura;
    }

    public void setCaptura(String captura) {
        this.captura = captura;
    }
}
