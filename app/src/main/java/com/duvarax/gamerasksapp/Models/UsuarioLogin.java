package com.duvarax.gamerasksapp.Models;

public class UsuarioLogin {
    private String Email;
    private String Clave;

    public UsuarioLogin(String email, String clave) {
        Email = email;
        Clave = clave;
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
}
