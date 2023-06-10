package com.duvarax.gamerasksapp.ui.Perfil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.R;

import java.util.List;

public class PerfilSliderAdapter extends RecyclerView.Adapter<PerfilSliderAdapter.ViewHolder> {

    private Context context;
    private List<String> listaImagenes;
    private LayoutInflater inflater;
    private ViewPager2 viewpager2;
    private Activity activity;

    public PerfilSliderAdapter(Context context, List<String> listaImagenes, LayoutInflater inflater, ViewPager2 viewpager2, Activity activity) {
        this.context = context;
        this.listaImagenes = listaImagenes;
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
                .load(listaImagenes.get(position))
                .into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(activity,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_juegos);
            }
        });
        if (position == listaImagenes.size() - 2){
            viewpager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return listaImagenes.size();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            listaImagenes.addAll(listaImagenes);
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
