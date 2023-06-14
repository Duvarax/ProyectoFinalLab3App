package com.duvarax.gamerasksapp.ui.Perfil;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.Request.ApiClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    private MutableLiveData<ArrayList<Juego>> juegosRecientesMutable;
    private MutableLiveData<String> fotoMutable;
    private MutableLiveData<String> portadaMutable;


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

    public LiveData<String> getFotoMutable(){
        if(fotoMutable == null){
            fotoMutable = new MutableLiveData<>();
        }
        return fotoMutable;
    }
    public LiveData<String> getPortadaMutable(){
        if(portadaMutable == null){
            portadaMutable = new MutableLiveData<>();
        }
        return portadaMutable;
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
                        Usuario usuarioLogeado = response.body();
                        if(usuarioLogeado.getPortada() == null || usuarioLogeado.getPortada() == ""){
                            usuarioLogeado.setPortada("https://res.cloudinary.com/dhg4fafod/image/upload/v1686700055/ktdlkatbk4aknnr2zz36.jpg");
                        }
                        if(usuarioLogeado.getImagen() == null || usuarioLogeado.getImagen() == ""){
                            usuarioLogeado.setImagen("https://res.cloudinary.com/dhg4fafod/image/upload/v1686700069/vyyrk3v6ilacs50d8aac.png");
                        }
                        usuarioMutable.postValue(usuarioLogeado);
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

    public void setFoto(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE, int objetivo){
        if(objetivo == R.id.btnCambiarFoto){
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
                                    if(response.isSuccessful()){
                                        if (response.body() != null){
                                            Toast.makeText(context, "Foto cambiada", Toast.LENGTH_SHORT).show();
                                            fotoMutable.postValue(response.body());
                                        }
                                    }else{
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
        }else{
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
                            Call<String> cambiarPortadaCall = end.cambiarPortada(token, imagenParte);
                            cambiarPortadaCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if(response.isSuccessful()){
                                        if(response != null){
                                            Toast.makeText(context, "Foto cambiada", Toast.LENGTH_SHORT).show();
                                            portadaMutable.postValue(response.body());
                                        }
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