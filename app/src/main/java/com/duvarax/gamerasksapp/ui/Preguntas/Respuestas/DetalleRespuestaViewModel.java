package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Respuesta;

public class DetalleRespuestaViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Respuesta> respuestaMutableLiveData;

    public DetalleRespuestaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Respuesta> getRespuestaMutable(){
        if (respuestaMutableLiveData == null){
            respuestaMutableLiveData = new MutableLiveData<>();
        }
        return respuestaMutableLiveData;
    }

    public void setRespuestaMutableLiveData(Respuesta respuesta){
        respuestaMutableLiveData.setValue(respuesta);
    }


}