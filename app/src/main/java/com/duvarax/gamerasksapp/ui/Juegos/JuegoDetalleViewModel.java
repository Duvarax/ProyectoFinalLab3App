package com.duvarax.gamerasksapp.ui.Juegos;

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
import com.duvarax.gamerasksapp.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JuegoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Juego> juegoMutable;
    private MutableLiveData<Integer> cantidadMutable;

    public JuegoDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Juego> getJuego(){
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


    public void obtenerJuego(Juego juego){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Juego> callJuego = end.añadirJuego(token, juego);
        callJuego.enqueue(new Callback<Juego>() {
            @Override
            public void onResponse(Call<Juego> call, Response<Juego> response) {
                if(response.isSuccessful()){
                    if (response != null){
                        juegoMutable.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Juego> call, Throwable t) {

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