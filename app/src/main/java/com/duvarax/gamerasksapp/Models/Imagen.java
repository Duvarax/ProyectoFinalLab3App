package com.duvarax.gamerasksapp.Models;

public class Imagen {
    public int id;
    public String url;
    public String publicId;

    public Imagen(int id, String url, String publicId) {
        this.id = id;
        this.url = url;
        this.publicId = publicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
