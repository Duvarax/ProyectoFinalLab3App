package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Comentario;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComentariosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Comentario>> listaComentarioMutable;
    private MutableLiveData<Respuesta> respuestaMutableLiveData;

    public ComentariosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Respuesta> respuestaLiveData(){
        if(respuestaMutableLiveData == null){
            respuestaMutableLiveData = new MutableLiveData<>();
        }
        return respuestaMutableLiveData;
    }
    public LiveData<ArrayList<Comentario>> comentariosLiveData(){
        if(listaComentarioMutable == null){
            listaComentarioMutable = new MutableLiveData<>();
        }
        return listaComentarioMutable;
    }

    public void setRespuesta(Respuesta respuesta){
        respuestaMutableLiveData.setValue(respuesta);
    }

    public void setComentarios(Respuesta respuesta){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Comentario>> callComentarios = end.obtenerComentarios(token, respuesta);
        callComentarios.enqueue(new Callback<ArrayList<Comentario>>() {
            @Override
            public void onResponse(Call<ArrayList<Comentario>> call, Response<ArrayList<Comentario>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listaComentarioMutable.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comentario>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void altaComentario(String texto){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Comentario comentario = new Comentario(0, texto, null, respuestaMutableLiveData.getValue().getUsuario(), respuestaMutableLiveData.getValue());
        Call<Comentario> callAltaComentario = end.altaComentario(token, comentario);
        callAltaComentario.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Comentario coment = response.body();
                        ArrayList<Comentario> listaComentarios = listaComentarioMutable.getValue();
                        listaComentarios.add(coment);
                        listaComentarioMutable.postValue(listaComentarios);
                    }
                }
            }

            @Override
            public void onFailure(Call<Comentario> call, Throwable t) {

            }
        });
    }


}