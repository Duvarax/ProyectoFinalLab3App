package com.duvarax.gamerasksapp.ui.Preguntas;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CapturaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> capturaMutable;

    public CapturaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getCapturaMutable(){
        if(capturaMutable == null){
            capturaMutable = new MutableLiveData<>();
        }
        return capturaMutable;
    }

    public void setCapturaMutable(String captura){
        capturaMutable.setValue(captura);
    }
}