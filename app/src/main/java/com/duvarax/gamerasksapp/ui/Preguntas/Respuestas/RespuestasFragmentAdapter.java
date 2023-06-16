package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    public RespuestasFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Respuesta> listapreguntas, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaRespuestas = listapreguntas;
        this.activity = activity;
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenUsuario = itemView.findViewById(R.id.ivImagenUserRespuesta);
            respuesta = itemView.findViewById(R.id.tvRespuestaXPregunta);
            btComentario = itemView.findViewById(R.id.btnComentarios);
        }
    }
}
