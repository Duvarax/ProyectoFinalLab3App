package com.duvarax.gamerasksapp.Models;

public class EditContraseña {
    public String contraseñaAntigua;
    public String contraseñaNueva;

    public EditContraseña(String contraseñaAntigua, String contraseñaNueva) {
        this.contraseñaAntigua = contraseñaAntigua;
        this.contraseñaNueva = contraseñaNueva;
    }

    public String getContraseñaAntigua() {
        return contraseñaAntigua;
    }

    public void setContraseñaAntigua(String contraseñaAntigua) {
        this.contraseñaAntigua = contraseñaAntigua;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }
}
