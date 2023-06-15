package com.duvarax.gamerasksapp.Models;

public class Imagen {
    public int id;
    public String urlImagen;
    public String publicId;

    public Imagen(int id, String url, String publicId) {
        this.id = id;
        this.urlImagen = url;
        this.publicId = publicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return urlImagen;
    }

    public void setUrl(String url) {
        this.urlImagen = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id=" + id +
                ", urlImagen='" + urlImagen + '\'' +
                ", publicId='" + publicId + '\'' +
                '}';
    }
}
