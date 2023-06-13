package com.duvarax.gamerasksapp.Request;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Models.UsuarioLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClient {
    private static final String PATH="http://192.168.0.15:5200/";
    private static  EndPointGamerAsk EndPointGamerAsk;

    public static EndPointGamerAsk getEndPointGamerAsk(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EndPointGamerAsk=retrofit.create(EndPointGamerAsk.class);

        return EndPointGamerAsk;
    }
    public interface EndPointGamerAsk{

        @POST("Usuario/login")
        Call<String> login(@Body UsuarioLogin user);
        @POST("Usuario/registro")
        Call<Integer> registro(@Body Usuario usuario);

        @GET("Usuario/perfil")
        Call<Usuario> obtenerPerfilUsuario(@Header("Authorization") String token);

        @GET("Reciente")
        Call<ArrayList<Juego>> obtenerJuegosRecientes(@Header("Authorization") String token);


    }
}
