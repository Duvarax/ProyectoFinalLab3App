package com.duvarax.gamerasksapp.ui.Perfil;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.duvarax.gamerasksapp.Models.CloudinaryConfig;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.String64;
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
import java.util.HashMap;
import java.util.Map;

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
    private MutableLiveData<Bitmap> fotoBitmapMutable;
    private Cloudinary cloudinary;
    String filePath;

    private HashMap<String, String> config = new HashMap<>();


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        config.put("cloud_name", "dhg4fafod");
        config.put("api_key", "585953183546733");
        config.put("api_secret", "FckXkGY2UOfIPezxwuZZGGJ7HEA");
        MediaManager.init(context, config);
        fotoBitmapMutable = new MutableLiveData<>();

    }

    public LiveData<Usuario> getUsuarioLogeado(){
        if(usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public LiveData<Bitmap> getBitmapFoto(){
        if(fotoBitmapMutable == null){
            fotoBitmapMutable = new MutableLiveData<>();
        }
        return fotoBitmapMutable;
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

    public void setFoto(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE, int objetivo) {
        if (objetivo == R.id.ivImagenPerfil) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Uri imageUri = data.getData();

                    try {
                        filePath = getRealPathFromUri(imageUri, context);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                        fotoBitmapMutable.setValue(bitmap);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        uploadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public String getRealPathFromUri(Uri imageUri, Context context){
        Cursor cursor = context.getContentResolver().query(imageUri, null, null, null, null);
        if(cursor == null){
            return imageUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            return cursor.getString(idx);
        }
    }

    private File bitmapToFile(Bitmap bitmap) {
        File file = new File(context.getCacheDir(), "temp_image.jpg");
        try {
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapData = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void uploadImage(){
        MediaManager.get().upload(filePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {

            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                Toast.makeText(context, "Subiendo...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                Toast.makeText(context, "Subida", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                Toast.makeText(context, error.getDescription(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {

            }
        });

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

