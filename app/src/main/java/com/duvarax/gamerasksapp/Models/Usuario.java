package com.duvarax.gamerasksapp.Models;

public class Usuario {

    public int id;
    public String nombre;
    public String apellido;
    public String nombreUsuario;
    public String email;
    public String clave;
    public String imagen;
    public String portada;

    public Usuario(int id, String nombre, String apellido, String nombreusuario, String email, String clave, String imagen, String portada) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreusuario;
        this.email = email;
        this.clave = clave;
        this.imagen = imagen;
        this.portada = portada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreusuario() {
        return nombreUsuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreUsuario = nombreusuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombreusuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                ", portada='" + portada + '\'' +
                '}';
    }
}
