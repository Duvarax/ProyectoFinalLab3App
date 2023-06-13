package com.duvarax.gamerasksapp.ui.Perfil;

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
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<ArrayList<Juego>> juegosRecientesMutable;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioLogeado(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }

    public LiveData<ArrayList<Juego>> getListaRecientes(){
        if(juegosRecientesMutable == null){
            juegosRecientesMutable = new MutableLiveData<>();
        }
        return juegosRecientesMutable;
    }

    public void setUsuarioLogeado(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Usuario> callUsuario = end.obtenerPerfilUsuario(token);
        callUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        usuarioMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListaJuegosRecientes(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<ArrayList<Juego>> callList = end.obtenerJuegosRecientes(token);
        callList.enqueue(new Callback<ArrayList<Juego>>() {
            @Override
            public void onResponse(Call<ArrayList<Juego>> call, Response<ArrayList<Juego>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        juegosRecientesMutable.postValue(response.body());
                    }
                }else{
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Juego>> call, Throwable t) {

            }
        });
    }
}