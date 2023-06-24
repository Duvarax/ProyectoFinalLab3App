package com.duvarax.gamerasksapp.ui.Juegos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;

import java.util.ArrayList;

public class JuegosFragmentAdapter extends RecyclerView.Adapter<JuegosFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Juego> listaJuegos;
    private Activity activity;

    public JuegosFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Juego> listaJuegos, Activity activity) {
        this.context = context;
        this.inflater = inflater;
        this.listaJuegos = listaJuegos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public JuegosFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.item_juego, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull JuegosFragmentAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(listaJuegos.get(position).getPortada())
                .into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle juego = new Bundle();
                juego.putSerializable("juego", listaJuegos.get(position));
                Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_detalle_juego, juego);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.portada_juego);
        }
    }
}
