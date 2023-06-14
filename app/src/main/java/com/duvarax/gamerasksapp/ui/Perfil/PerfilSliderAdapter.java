package com.duvarax.gamerasksapp.ui.Perfil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;

import java.util.List;

public class PerfilSliderAdapter extends RecyclerView.Adapter<PerfilSliderAdapter.ViewHolder> {

    private Context context;
    private List<Juego> listaJuegos;
    private LayoutInflater inflater;
    private ViewPager2 viewpager2;
    private Activity activity;

    public PerfilSliderAdapter(Context context, List<Juego> listaJuegos, LayoutInflater inflater, ViewPager2 viewpager2, Activity activity) {
        this.context = context;
        this.listaJuegos = listaJuegos;
        this.inflater = inflater;
        this.viewpager2 = viewpager2;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PerfilSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context =parent.getContext();
        View root = inflater.inflate(R.layout.slider_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilSliderAdapter.ViewHolder holder, int position) {
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
        if (position == listaJuegos.size() - 2){
            viewpager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            listaJuegos.addAll(listaJuegos);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ivSliderFoto);
        }
    }
}
