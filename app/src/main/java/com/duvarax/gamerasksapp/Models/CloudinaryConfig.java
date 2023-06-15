package com.duvarax.gamerasksapp.Models;

import android.app.Application;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;

public class CloudinaryConfig extends Application {
    private Cloudinary cloudinary;

    public CloudinaryConfig() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Configura tus credenciales de CloudinaryConfig
        MediaManager.init(this);
        cloudinary = new Cloudinary("cloudinary://585953183546733:FckXkGY2UOfIPezxwuZZGGJ7HEA@dhg4fafod");
    }

    public Cloudinary getCloudinary() {
        return cloudinary;
    }
}
