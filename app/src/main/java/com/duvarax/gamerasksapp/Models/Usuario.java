package com.duvarax.gamerasksapp.Models;

public class Usuario {

    public int Id;
    public String Nombre;
    public String Apellido;
    public String NombreUsuario;
    public String Email;
    public String Clave;
    public String Imagen;
    public String Portada;

    public Usuario(int id, String nombre, String apellido, String nombreUsuario, String email, String clave, String imagen, String portada) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        NombreUsuario = nombreUsuario;
        Email = email;
        Clave = clave;
        Imagen = imagen;
        Portada = portada;
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

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String portada) {
        Portada = portada;
    }
}
