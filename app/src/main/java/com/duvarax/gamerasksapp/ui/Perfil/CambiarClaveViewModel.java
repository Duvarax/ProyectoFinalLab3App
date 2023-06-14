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

import com.duvarax.gamerasksapp.Models.EditContraseña;
import com.duvarax.gamerasksapp.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarClaveViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Integer> edicionMutable;


    public CambiarClaveViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Integer> edicionSatisfactoria(){
        if(edicionMutable == null){
            edicionMutable = new MutableLiveData<>();
        }
        return edicionMutable;
    }
    public void editarContraseña(EditContraseña edit){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Call<Integer> editarCall = end.editarContraseña(token, edit);
        editarCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
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

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}