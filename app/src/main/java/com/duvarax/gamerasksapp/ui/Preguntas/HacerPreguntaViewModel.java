package com.duvarax.gamerasksapp.ui.Preguntas;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.gamerasksapp.Models.Imagen;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private MutableLiveData<String> capturaMutable;
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
    public LiveData<String> getCapturaMutable(){
        if(capturaMutable == null){
            capturaMutable = new MutableLiveData<>();
        }
        return capturaMutable;
    }

    public void obtenerJuego(Juego juego){
        juegoMutable.setValue(juego);
    }

    public void enviarPregunta(String texto){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClient.EndPointGamerAsk end = ApiClient.getEndPointGamerAsk();
        Pregunta pregunta;
        if(capturaMutable.getValue() == null || capturaMutable.getValue() == " "){
            pregunta = new Pregunta(0, texto, "", null, juegoMutable.getValue(), "https://res.cloudinary.com/dhg4fafod/image/upload/v1686864008/gamerask_/xnauaq8gcbwjnx4dn6av.jpg");
        }else{
            pregunta = new Pregunta(0, texto, "", null, juegoMutable.getValue(), capturaMutable.getValue());
        }
        Call<Integer> callAltaPregunta = end.altaPregunta(token, pregunta);
        callAltaPregunta.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        if(response.body() == 1){
                            Toast.makeText(context, "Pregunta creada", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
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
                        String realPath = getRealPathFromURI(imagenUri);
                        File imgFile = new File(realPath);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),imgFile);
                        MultipartBody.Part captura = MultipartBody.Part.createFormData("captura", imgFile.getName(), requestBody);
                        Call<Imagen> callCaptura = end.getCaptura(token, captura);
                        callCaptura.enqueue(new Callback<Imagen>() {
                            @Override
                            public void onResponse(Call<Imagen> call, Response<Imagen> response) {
                                if (response.isSuccessful()){
                                    if(response.body() != null){
                                        Toast.makeText(context, "Imagen guardada", Toast.LENGTH_SHORT).show();
                                        capturaMutable.postValue(response.body().getUrl());
                                    }
                                }else{
                                    Log.d("salida", response.toString());
                                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Imagen> call, Throwable t) {
                                Log.d("salida", t.getMessage());
                                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        Log.d("salida", e.toString());
                    }
                }
            }
        }
    }


    private String getRealPathFromURI(Uri uri) {
        String filePath = "";
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(column_index);
            }
            cursor.close();
        }
        return filePath;
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