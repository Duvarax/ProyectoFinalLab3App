package com.duvarax.gamerasksapp.ui.Preguntas;

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

public class PreguntasFragmentAdapter extends RecyclerView.Adapter<PreguntasFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Pregunta> listaPreguntas;
    private Activity activity;

    public PreguntasFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Pregunta> listapreguntas, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaPreguntas = listapreguntas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PreguntasFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.item_pregunta_usuario, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PreguntasFragmentAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(listaPreguntas.get(position).getJuego().getPortada())
                .into(holder.portadaJuego);

        holder.pregunta.setText(listaPreguntas.get(position).getTexto());
        holder.btRespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pregunta", listaPreguntas.get(position));
                Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_respuestas, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPreguntas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView portadaJuego;
        TextView pregunta;
        Button btRespuestas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            portadaJuego = itemView.findViewById(R.id.ivPortadaJuego);
            pregunta = itemView.findViewById(R.id.tvPregunta);
            btRespuestas = itemView.findViewById(R.id.btnRespuestas);
        }
    }
}
