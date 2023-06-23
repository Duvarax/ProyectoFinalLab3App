package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

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
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespuestasViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Pregunta> preguntaMutable;
    private MutableLiveData<ArrayList<Respuesta>> listaRespuestasMutable;


    public RespuestasViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }
    public LiveData<Pregunta> preguntaLiveData(){
        if(preguntaMutable == null){
            preguntaMutable = new MutableLiveData<>();
        }
        return preguntaMutable;
    }
    public LiveData<ArrayList<Respuesta>> respuestasLiveData(){
        if(listaRespuestasMutable == null){
            listaRespuestasMutable = new MutableLiveData<>();
        }
        return listaRespuestasMutable;
    }

    public void setPreguntaMutable(Pregunta pregunta){
        preguntaMutable.setValue(pregunta);
    }
    public void setListaRespuestasMutable(Pregunta pregunta){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Respuesta>> callRespuestas = end.obtenerRespuestasDePregunta(token, pregunta);
        callRespuestas.enqueue(new Callback<ArrayList<Respuesta>>() {
            @Override
            public void onResponse(Call<ArrayList<Respuesta>> call, Response<ArrayList<Respuesta>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listaRespuestasMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, "Respuesta insatisfactoria", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Respuesta>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void altaRespuesta(String texto){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Respuesta respuesta;
        respuesta = new Respuesta(0, texto, null, preguntaMutable.getValue().getUsuario(), preguntaMutable.getValue(), false);
        Call<Respuesta> callRespuesta = end.altaRespuesta(token, respuesta);
        callRespuesta.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Respuesta res = response.body();
                        ArrayList<Respuesta> listaRespuestas = listaRespuestasMutable.getValue();
                        listaRespuestas.add(res);
                        listaRespuestasMutable.postValue(listaRespuestas);
                        Toast.makeText(context, "Respuesta enviada", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void valorarRespuesta(Respuesta respuesta){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> callAltaValoracion = end.altaValoracion(token, respuesta);
        callAltaValoracion.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() != 0){
                        Toast.makeText(context, "❤", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void quitarValoracion(Respuesta respuesta){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> callBajaValoracion = end.bajaValoracion(token, respuesta.getId());
        callBajaValoracion.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if (response.body() != 0){
                        Toast.makeText(context, "Like eliminado", Toast.LENGTH_SHORT).show();
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