package com.duvarax.gamerasksapp.ui.Preguntas;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Pregunta;

public class DetallePreguntaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Pregunta> preguntaMutableLiveData;

    public DetallePreguntaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Pregunta> getPregunta(){
        if(preguntaMutableLiveData == null){
            preguntaMutableLiveData = new MutableLiveData<>();
        }
        return preguntaMutableLiveData;
    }

    public void setPregunta(Pregunta pregunta){
        preguntaMutableLiveData.setValue(pregunta);
    }
}