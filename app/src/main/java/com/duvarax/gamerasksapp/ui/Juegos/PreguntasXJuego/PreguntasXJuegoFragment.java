package com.duvarax.gamerasksapp.ui.Juegos.PreguntasXJuego;

import androidx.lifecycle.Observer;
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

    private PreguntasXJuegoViewModel mv;
    private FragmentPreguntasXJuegoBinding binding;
    private Context context;

    public static PreguntasXJuegoFragment newInstance() {
        return new PreguntasXJuegoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPreguntasXJuegoBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PreguntasXJuegoViewModel.class);
        View root = binding.getRoot();
        Bundle data = getArguments();
        Juego juego =(Juego) data.getSerializable("juego");

        mv.getJuegoMutable().observe(getActivity(), new Observer<Juego>() {
            @Override
            public void onChanged(Juego juego) {
                binding.tvNombrePreguntaXJuego.setText(juego.getNombre());
                Glide.with(getActivity()).load(juego.getPortada()).into(binding.ivPortadaJuegoPregunta);
            }
        });
        mv.getCantidadPreguntas().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCantidadPreguntasPreguntaXJuego.setText(integer.toString());
            }
        });
        mv.getListaPreguntas().observe(getActivity(), new Observer<ArrayList<Pregunta>>() {
            @Override
            public void onChanged(ArrayList<Pregunta> preguntas) {
                PreguntasXJuegoFragmentAdapter adapter = new PreguntasXJuegoFragmentAdapter(getContext(), inflater, preguntas, getActivity());
                binding.rvListaPreguntasXJuego.setAdapter(adapter);
                GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvListaPreguntasXJuego.setLayoutManager(grid);
            }
        });

        mv.setJuego(juego);
        mv.obtenerCantidadPreguntas(juego);
        mv.obtenerPreguntasXJuego(juego);
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
        // TODO: Use the ViewModel
    }

}