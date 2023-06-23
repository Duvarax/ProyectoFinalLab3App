package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.R;

import java.util.ArrayList;

public class RespuestasFragmentAdapter extends RecyclerView.Adapter<RespuestasFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Respuesta> listaRespuestas;
    private Activity activity;
    private RespuestasViewModel mv;

    public RespuestasFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Respuesta> listapreguntas, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaRespuestas = listapreguntas;
        this.activity = activity;
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(activity.getApplication()).create(RespuestasViewModel.class);
    }

    @NonNull
    @Override
    public RespuestasFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.respuesta_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RespuestasFragmentAdapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(listaRespuestas.get(position).getUsuario().getImagen())
                .into(holder.imagenUsuario);

        if(listaRespuestas.get(position).getValorada()){
            Glide.with(context)
                    .load(R.drawable.like_icon).into(holder.ibValorar);
        }else{
            Glide.with(context)
                    .load(R.drawable.likent_icon).into(holder.ibValorar);
        }
        holder.ibValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaRespuestas.get(position).getValorada()){
                    Glide.with(context)
                            .load(R.drawable.likent_icon).into(holder.ibValorar);
                    mv.quitarValoracion(listaRespuestas.get(position));
                }else{
                    Glide.with(context)
                            .load(R.drawable.like_icon).into(holder.ibValorar);
                    mv.valorarRespuesta(listaRespuestas.get(position));
                    }
                }

        });

        holder.respuesta.setText(listaRespuestas.get(position).getTexto());
        holder.btComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("respuesta", listaRespuestas.get(position));
                Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_comentarios, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaRespuestas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagenUsuario;
        TextView respuesta;
        Button btComentario;
        ImageButton ibValorar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenUsuario = itemView.findViewById(R.id.ivImagenUserRespuesta);
            respuesta = itemView.findViewById(R.id.tvRespuestaXPregunta);
            btComentario = itemView.findViewById(R.id.btnComentarios);
            ibValorar = itemView.findViewById(R.id.ibValorar);
        }
    }
}
