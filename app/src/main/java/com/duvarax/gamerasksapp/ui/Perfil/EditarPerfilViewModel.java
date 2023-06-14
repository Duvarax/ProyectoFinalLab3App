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

import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Integer> edicionMutable;

    public EditarPerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Integer> edicionSatisfactoria(){
        if(edicionMutable == null){
            edicionMutable = new MutableLiveData<>();
        }
        return edicionMutable;
    }

    public void editarPerfil(Usuario usuarioEditado){
        validarCamposNotNull(usuarioEditado);
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> edicionCall = end.editarPerfil(token, usuarioEditado);
        edicionCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        if(response.body() == 1){
                            Toast.makeText(context, "Edicion Completada con exito", Toast.LENGTH_SHORT).show();
                            edicionMutable.postValue(response.body());
                        }else{
                            Toast.makeText(context, "Ocurrio un error con la edicion", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static boolean validarCamposNotNull(Object instancia) {
        Class<?> clase = instancia.getClass();
        Field[] campos = clase.getFields();

        for (Field campo : campos) {
            try {
                Object valorCampo = campo.get(instancia);
                if (valorCampo == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}