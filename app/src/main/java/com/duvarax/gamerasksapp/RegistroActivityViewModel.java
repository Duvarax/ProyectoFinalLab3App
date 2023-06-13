package com.duvarax.gamerasksapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Boolean> registroMutable;


    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Boolean> getRegistro(){
        if(registroMutable == null){
            registroMutable = new MutableLiveData<>();
        }
        return registroMutable;
    }

    public void registrarse(Usuario usuario){
        if(!validarCorreoElectronico(usuario.getEmail())){
            Toast.makeText(context, "Email no valido", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiClient.EndPointGamerAsk end =ApiClient.getEndPointGamerAsk();
        Call<Integer> altaUsuario = end.registro(usuario);
        altaUsuario.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        if(response.body() != 0){
                            registroMutable.postValue(true);
                        }else{
                            Toast.makeText(context, "No pudo completarse el registro", Toast.LENGTH_SHORT).show();
                        }
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
    public boolean validarCorreoElectronico(String correo) {
        // Expresión regular para validar una dirección de correo electrónico
        String patronCorreo = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(patronCorreo);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }

}
