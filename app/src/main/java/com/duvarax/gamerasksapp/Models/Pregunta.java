package com.duvarax.gamerasksapp.Models;

import java.io.Serializable;

public class Pregunta implements Serializable {
    private int Id;
    private String Texto;
    private String fechaCreacion;
    private Usuario usuario;
    private Juego juego;
    private String captura;

    public Pregunta(int id, String texto, String fechaCreacion, Usuario usuario, Juego juego, String captura) {
        Id = id;
        Texto = texto;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.juego = juego;
        this.captura = captura;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
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
