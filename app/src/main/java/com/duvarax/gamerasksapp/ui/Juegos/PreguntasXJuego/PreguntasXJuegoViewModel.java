package com.duvarax.gamerasksapp.ui.Juegos.PreguntasXJuego;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreguntasXJuegoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Pregunta>> listaPreguntasMutable;
    private MutableLiveData<Juego> juegoMutable;
    private MutableLiveData<Integer> cantidadMutable;

    public PreguntasXJuegoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pregunta>> getListaPreguntas(){
        if(listaPreguntasMutable == null){
            listaPreguntasMutable = new MutableLiveData<>();
        }
        return listaPreguntasMutable;
    }
    public LiveData<Juego> getJuegoMutable(){
        if(juegoMutable == null){
            juegoMutable = new MutableLiveData<>();
        }
        return juegoMutable;
    }

    public LiveData<Integer> getCantidadPreguntas(){
        if(cantidadMutable == null){
            cantidadMutable = new MutableLiveData<>();
        }
        return cantidadMutable;
    }

    public void setJuego(Juego juego){
        juegoMutable.setValue(juego);
    }


    public void obtenerPreguntasXJuego(Juego juego){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Pregunta>> listaCall = end.obtenerPreguntasXJuego(token, juego);
        listaCall.enqueue(new Callback<ArrayList<Pregunta>>() {
            @Override
            public void onResponse(Call<ArrayList<Pregunta>> call, Response<ArrayList<Pregunta>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listaPreguntasMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, "No se pudo obtener la lista de preguntas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pregunta>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtenerCantidadPreguntas(Juego juego){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> cantidadCall = end.obtenerCantidadPreguntasXJuego(token, juego);
        cantidadCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if (response != null){
                        cantidadMutable.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}