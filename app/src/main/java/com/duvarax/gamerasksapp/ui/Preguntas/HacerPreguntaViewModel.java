package com.duvarax.gamerasksapp.ui.Preguntas;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HacerPreguntaViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<Juego> juegoMutable;
    private MutableLiveData<Integer> envioSatisfactorioMutable;
    public HacerPreguntaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Juego> getJuego(){
        if(juegoMutable == null){
            juegoMutable = new MutableLiveData<>();
        }
        return juegoMutable;
    }
    public LiveData<Integer> getEnvioSatisfactorio(){
        if(envioSatisfactorioMutable == null){
            envioSatisfactorioMutable = new MutableLiveData<>();
        }
        return envioSatisfactorioMutable;
    }

    public void obtenerJuego(Juego juego){
        juegoMutable.setValue(juego);
    }

    public void enviarPregunta(String texto, String captura){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Pregunta pregunta = new Pregunta(0, texto, "", null, juegoMutable.getValue(), captura);
        Call<Integer> callAltaPregunta = end.altaPregunta(token, pregunta);
        callAltaPregunta.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        if(response.body() == 1){
                            Toast.makeText(context, "Pregunta creada", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCaptura(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri imagenUri = data.getData();
                if (imagenUri != null) {
                    try {
                        InputStream inputStream = context.getContentResolver().openInputStream(imagenUri);
                        byte[] imagenBytes = getBytesFromInputStream(inputStream);
                        // Enviar la imagen al servidor
                        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
                        String token = sp.getString("token", "");
                        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imagenBytes);
                        MultipartBody.Part imagenParte = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestBody);
                        Call<String> cambiarFotoCall = end.cambiarFoto(token, imagenParte);
                        cambiarFotoCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        Toast.makeText(context, "Foto cambiada", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Log.d("salida", response.toString());
                                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    } catch (IOException e) {
                        Log.d("salida", e.toString());
                    }
                }
            }
        }
    }


    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        return output.toByteArray();
    }
}