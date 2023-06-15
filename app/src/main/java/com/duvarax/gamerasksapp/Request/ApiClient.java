package com.duvarax.gamerasksapp.Request;

import com.duvarax.gamerasksapp.Models.EditContraseña;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.String64;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Models.UsuarioLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public class ApiClient {
    private static final String PATH="http://192.168.0.10:5200/";
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
        @PUT("Usuario/editar")
        Call<Integer> editarPerfil(@Header("Authorization") String token,@Body Usuario usuarioEditado);
        @PUT("Usuario/editar-contraseña")
        Call<Integer> editarContraseña(@Header("Authorization") String token, @Body EditContraseña edit);

        @PUT("Usuario/cambiar-foto")
        Call<String> cambiarFoto(@Header("Authorization") String token, @Body String imagen);
        @Multipart
        @PUT("Usuario/cambiar-portada")
        Call<String> cambiarPortada(@Header("Authorization") String token, @Part MultipartBody.Part imagen);

        @GET("Reciente")
        Call<ArrayList<Juego>> obtenerJuegosRecientes(@Header("Authorization") String token);


        @GET("Pregunta")
        Call<ArrayList<Pregunta>> obtenerPreguntasDeUsuarioLogeado(@Header("Authorization") String token);

        @POST("Pregunta/guardar")
        Call<Integer> altaPregunta(@Header("Authorization") String token,@Body Pregunta pregunta);

        @POST("Pregunta/cantidad")
        Call<Integer> obtenerCantidadPreguntasXJuego(@Header("Authorization") String token,@Body Juego juego);

        @GET("Juego/obtener")
        Call<ArrayList<Juego>> obtenerJuegos(@Header("Authorization") String token);


    }
}
