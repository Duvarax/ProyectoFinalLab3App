package com.duvarax.gamerasksapp.ui.Preguntas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.databinding.FragmentPreguntasBinding;

import java.util.ArrayList;

public class PreguntasFragment extends Fragment {

    private FragmentPreguntasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPreguntasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
        listaPreguntas.add(new Pregunta(1,"Pregunta", "10/06/2023", new Usuario(1, "claudio", "","","","","",""), new Juego(1, "",""," https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg", "", ""), ""));
        PreguntasFragmentAdapter adapter = new PreguntasFragmentAdapter(root.getContext(), getLayoutInflater(),listaPreguntas, getActivity());
        binding.rvListaPreguntas.setAdapter(adapter);
        GridLayoutManager grid = new GridLayoutManager(root.getContext(), 1, GridLayoutManager.VERTICAL, false);
        binding.rvListaPreguntas.setLayoutManager(grid);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}