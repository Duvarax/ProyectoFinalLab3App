package com.duvarax.gamerasksapp.ui.Juegos.PreguntasXJuego;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentPreguntasXJuegoBinding;

import java.util.ArrayList;

public class PreguntasXJuegoFragment extends Fragment {

    private PreguntasXJuegoViewModel mViewModel;
    private FragmentPreguntasXJuegoBinding binding;
    private Context context;

    public static PreguntasXJuegoFragment newInstance() {
        return new PreguntasXJuegoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPreguntasXJuegoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Glide.with(getContext())
                .load("https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg")
                .into(binding.ivPortadaJuegoPregunta);
        ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
        listaPreguntas.add(new Pregunta(1,"Pregunta", "10/06/2023", new Usuario(1, "claudio", "","","","","https://cdn-icons-png.flaticon.com/512/149/149071.png",""), new Juego(1, "","","https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg", "", ""), ""));
        PreguntasXJuegoFragmentAdapter adapter = new PreguntasXJuegoFragmentAdapter(getContext(), inflater, listaPreguntas, getActivity());
        binding.rvListaPreguntasXJuego.setAdapter(adapter);
        GridLayoutManager grid = new GridLayoutManager(root.getContext(), 1, GridLayoutManager.VERTICAL, false);
        binding.rvListaPreguntasXJuego.setLayoutManager(grid);
        return root;


    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PreguntasXJuegoViewModel.class);
        // TODO: Use the ViewModel
    }

}