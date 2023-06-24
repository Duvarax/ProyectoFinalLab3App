package com.duvarax.gamerasksapp.Request;

import com.duvarax.gamerasksapp.Models.Comentario;
import com.duvarax.gamerasksapp.Models.Imagen;
import com.duvarax.gamerasksapp.Models.EditContraseña;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.JuegoBuscar;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.Models.UsuarioLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.MultipartBody;
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
import retrofit2.http.Path;

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

        @Multipart
        @PUT("Usuario/cambiar-foto")
        Call<Imagen> cambiarFoto(@Header("Authorization") String token, @Part MultipartBody.Part imagen);
        @Multipart
        @PUT("Usuario/cambiar-portada")
        Call<Imagen> cambiarPortada(@Header("Authorization") String token, @Part MultipartBody.Part imagen);

        @GET("Reciente")
        Call<ArrayList<Juego>> obtenerJuegosRecientes(@Header("Authorization") String token);


        @GET("Pregunta")
        Call<ArrayList<Pregunta>> obtenerPreguntasDeUsuarioLogeado(@Header("Authorization") String token);

        @POST("Pregunta/guardar")
        Call<Pregunta> altaPregunta(@Header("Authorization") String token,@Body Pregunta pregunta);

        @POST("Pregunta/cantidad")
        Call<Integer> obtenerCantidadPreguntasXJuego(@Header("Authorization") String token,@Body Juego juego);
        @Multipart
        @POST("Pregunta/captura")
        Call<Imagen> getCaptura(@Header("Authorization") String token, @Part MultipartBody.Part captura);
        @POST("Pregunta/juego")
        Call<ArrayList<Pregunta>> obtenerPreguntasXJuego(@Header("Authorization") String token, @Body Juego juego);
        @DELETE("Pregunta/eliminar/{id}")
        Call<Integer> bajaPregunta(@Header("Authorization") String token, @Path("id") int id);

        @POST("Respuesta")
        Call<ArrayList<Respuesta>> obtenerRespuestasDePregunta(@Header("Authorization") String token, @Body Pregunta pregunta);
        @POST("Respuesta/guardar")
        Call<Respuesta> altaRespuesta(@Header("Authorization") String token, @Body Respuesta respuesta);

        @POST("Comentario")
        Call<ArrayList<Comentario>> obtenerComentarios(@Header("Authorization") String token, @Body Respuesta respuesta);
        @POST("Comentario/guardar")
        Call<Comentario> altaComentario(@Header("Authorization") String token, @Body Comentario comentario);

        @POST("Juego")
        Call<Juego> añadirJuego(@Header("Authorization") String token, @Body Juego juego);
        @GET("Juego/obtener")
        Call<ArrayList<Juego>> obtenerJuegos(@Header("Authorization") String token);
        @POST("Juego/buscar")
        Call<ArrayList<Juego>> buscarJuegos(@Header("Authorization") String token, @Body JuegoBuscar juego);

        @POST("Valoracion/guardar")
        Call<Integer> altaValoracion(@Header("Authorization") String token, @Body Respuesta respuesta);

        @DELETE("Valoracion/eliminar/{id}")
        Call<Integer> bajaValoracion(@Header("Authorization") String token, @Path("id") int idRespuesta);
    }
}
