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
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JuegosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Juego>> listaJuegosMutable;

    public JuegosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Juego>> getListaJuegos(){
        if(listaJuegosMutable == null){
            listaJuegosMutable = new MutableLiveData<>();
        }
        return listaJuegosMutable;
    }

    public void obtenerListaJuegos(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Juego>> obtenerJuegosCall = end.obtenerJuegos(token);

        obtenerJuegosCall.enqueue(new Callback<ArrayList<Juego>>() {
            @Override
            public void onResponse(Call<ArrayList<Juego>> call, Response<ArrayList<Juego>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listaJuegosMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, "Error al obtener juegos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Juego>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}