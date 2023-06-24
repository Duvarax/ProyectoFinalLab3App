package com.duvarax.gamerasksapp.ui.Preguntas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePreguntaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Pregunta> preguntaMutableLiveData;
    private MutableLiveData<String> urlCapturaMutable;
    private MutableLiveData<Boolean> preguntaEliminadaMutable;

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

    public LiveData<String> getUrl(){
        if(urlCapturaMutable == null){
            urlCapturaMutable = new MutableLiveData<>();
        }
        return urlCapturaMutable;
    }
    public LiveData<Boolean> getEliminarPreguntaMutable(){
        if(preguntaEliminadaMutable == null){
            preguntaEliminadaMutable = new MutableLiveData<>();
        }
        return preguntaEliminadaMutable;
    }

    public void setUrlCapturaMutable(String url){
        urlCapturaMutable.setValue(url);
    }

    public void setPregunta(Pregunta pregunta){
        preguntaMutableLiveData.setValue(pregunta);
    }

    public void bajaPregunta(Pregunta pregunta){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> bajaPreguntaCall = end.bajaPregunta(token, pregunta.getId());
        bajaPreguntaCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if (response.body() != 0){
                        Toast.makeText(context, "Pregunta eliminada", Toast.LENGTH_SHORT).show();
                        preguntaEliminadaMutable.setValue(true);
                    }
                }else{
                    Toast.makeText(context, "No puedes eliminar la pregunta de otro usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, t.getMessage() + " failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}