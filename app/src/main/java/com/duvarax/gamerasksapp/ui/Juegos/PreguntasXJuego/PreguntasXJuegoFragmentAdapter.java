package com.duvarax.gamerasksapp.ui.Juegos.PreguntasXJuego;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.R;

import java.util.ArrayList;

public class PreguntasXJuegoFragmentAdapter extends RecyclerView.Adapter<PreguntasXJuegoFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Pregunta> listaPreguntas;
    private Activity activity;

    public PreguntasXJuegoFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Pregunta> listapreguntas, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaPreguntas = listapreguntas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PreguntasXJuegoFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.item_pregunta_x_juego, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PreguntasXJuegoFragmentAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(listaPreguntas.get(position).getUsuario().getImagen())
                .into(holder.imagenUsuario);

        holder.pregunta.setText(listaPreguntas.get(position).getTexto());
        if(listaPreguntas.get(position).getCaptura() == null || listaPreguntas.get(position).getCaptura() == ""){
            holder.captura.setImageResource(R.drawable.not_icon);
        }else{
            Glide.with(context)
                    .load(listaPreguntas.get(position).getCaptura())
                    .into(holder.captura);
            holder.captura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("captura", listaPreguntas.get(position).getCaptura());
                    Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_captura_ver, bundle);
                }
            });
        }
        holder.btRespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //navegar a las respuestas de esa pregunta
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPreguntas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagenUsuario;
        ImageView captura;
        TextView pregunta;
        Button btRespuestas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenUsuario = itemView.findViewById(R.id.civUserPreguntaList);
            captura = itemView.findViewById(R.id.ivCapturaXPreguntaXJuego);
            pregunta = itemView.findViewById(R.id.tvPreguntaXJuego);
            btRespuestas = itemView.findViewById(R.id.btnDetallePregunta);
        }
    }
}
