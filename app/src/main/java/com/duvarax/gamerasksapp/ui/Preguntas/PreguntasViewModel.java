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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreguntasViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Pregunta>> listaPreguntasMutable;

    public PreguntasViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pregunta>> getListaPreguntas(){
        if(listaPreguntasMutable == null){
            listaPreguntasMutable = new MutableLiveData<>();
        }
        return  listaPreguntasMutable;
    }

    public void obtenerPreguntas(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Pregunta>> obtenerPreguntasCall = end.obtenerPreguntasDeUsuarioLogeado(token);
        obtenerPreguntasCall.enqueue(new Callback<ArrayList<Pregunta>>() {
            @Override
            public void onResponse(Call<ArrayList<Pregunta>> call, Response<ArrayList<Pregunta>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listaPreguntasMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, "Error al obtener preguntas del usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pregunta>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}