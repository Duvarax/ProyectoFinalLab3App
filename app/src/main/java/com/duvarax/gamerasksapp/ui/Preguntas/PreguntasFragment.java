package com.duvarax.gamerasksapp.ui.Preguntas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.databinding.FragmentPreguntasBinding;

import java.util.ArrayList;

public class PreguntasFragment extends Fragment {

    private FragmentPreguntasBinding binding;
    private PreguntasViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPreguntasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PreguntasViewModel.class);


        mv.getListaPreguntas().observe(getActivity(), new Observer<ArrayList<Pregunta>>() {
            @Override
            public void onChanged(ArrayList<Pregunta> preguntas) {
                PreguntasFragmentAdapter adapter = new PreguntasFragmentAdapter(getContext(), getLayoutInflater(),preguntas, getActivity());
                binding.rvListaPreguntas.setAdapter(adapter);
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvListaPreguntas.setLayoutManager(grid);
            }
        });

        mv.obtenerPreguntas();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}