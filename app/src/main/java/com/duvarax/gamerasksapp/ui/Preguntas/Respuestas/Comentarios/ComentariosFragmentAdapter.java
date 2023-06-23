package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

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
import com.duvarax.gamerasksapp.Models.Comentario;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.R;

import java.util.ArrayList;

public class ComentariosFragmentAdapter extends RecyclerView.Adapter<ComentariosFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Comentario> listaComentarios;
    private Activity activity;

    public ComentariosFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Comentario> listacomentarios, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaComentarios = listacomentarios;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ComentariosFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.respuesta_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentariosFragmentAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(listaComentarios.get(position).getUsuario().getImagen())
                .into(holder.imagenUsuario);

        holder.comentario.setText(listaComentarios.get(position).getTexto());
        holder.btnDetalle.setText("Detalle");
        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("comentario", listaComentarios.get(position));
                Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_detalle_comentario, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagenUsuario;
        TextView comentario;
        Button btnDetalle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenUsuario = itemView.findViewById(R.id.ivImagenUserRespuesta);
            comentario = itemView.findViewById(R.id.tvRespuestaXPregunta);
            btnDetalle = itemView.findViewById(R.id.btnComentarios);
        }
    }
}
