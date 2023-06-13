package com.duvarax.gamerasksapp.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.gamerasksapp.Models.UsuarioLogin;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Boolean> loginMutable;
    private MutableLiveData<ArrayList<String>> juegosRecientesMutable;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Boolean> getLogin(){
        if(loginMutable == null){
            loginMutable = new MutableLiveData<>();
        }
        return loginMutable;
    }




    public void logear(String email, String clave){
        UsuarioLogin login = new UsuarioLogin(email, clave);
        ApiClient.EndPointGamerAsk end =ApiClient.getEndPointGamerAsk();
        Call<String> callLogin = end.login(login);
        callLogin.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", "Bearer " +  response.body());
                        editor.commit();
                        loginMutable.postValue(true);
                    }
                }else{
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
