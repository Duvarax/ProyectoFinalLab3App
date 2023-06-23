package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Comentario;

public class DetalleComentarioViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Comentario> comentarioMutableLiveData;


    public DetalleComentarioViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Comentario> getComentarioMutable(){
        if(comentarioMutableLiveData == null){
            comentarioMutableLiveData = new MutableLiveData<>();
        }
        return comentarioMutableLiveData;
    }

    public void setComentarioMutableLiveData(Comentario comentario){
        comentarioMutableLiveData.setValue(comentario);
    }
}