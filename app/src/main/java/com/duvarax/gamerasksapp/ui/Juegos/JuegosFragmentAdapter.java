package com.duvarax.gamerasksapp.ui.Juegos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;

import java.util.ArrayList;

public class JuegosFragmentAdapter extends RecyclerView.Adapter<JuegosFragmentAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Juego> listaJuegos;

    public JuegosFragmentAdapter(Context context, LayoutInflater inflater, ArrayList<Juego> listaJuegos) {
        this.context = context;
        this.inflater = inflater;
        this.listaJuegos = listaJuegos;
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
                Toast.makeText(context, "Enviar al juego", Toast.LENGTH_SHORT).show();
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
