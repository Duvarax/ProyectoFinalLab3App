package com.duvarax.gamerasksapp.ui.Preguntas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentDetallePreguntaBinding;

public class DetallePreguntaFragment extends Fragment {

    private DetallePreguntaViewModel mv;
    private FragmentDetallePreguntaBinding binding;

    public static DetallePreguntaFragment newInstance() {
        return new DetallePreguntaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetallePreguntaBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetallePreguntaViewModel.class);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        Pregunta pregunta = (Pregunta) bundle.getSerializable("pregunta");

        mv.getPregunta().observe(getActivity(), new Observer<Pregunta>() {
            @Override
            public void onChanged(Pregunta pregunta) {
                binding.tvPreguntaDetallePregunta.setText(pregunta.getTexto());
                binding.tvFechaCreacionDetallePregunta.setText(pregunta.getFechaCreacion());
                binding.tvNicknameUsuarioDetallePregunta.setText(pregunta.getUsuario().getNombreusuario());
                binding.tvNombreJuegoDetallePregunta.setText(pregunta.getJuego().getNombre());
                Glide.with(getContext()).load(pregunta.getUsuario().getImagen()).into(binding.ivImagenUsuarioDetallePregunta);
                Glide.with(getContext()).load(pregunta.getJuego().getPortada()).into(binding.ivJuegoPortadaDetallePregunta);
                Glide.with(getContext()).load(pregunta.getCaptura()).into(binding.ivCapturaDetallePregunta);
            }
        });

        mv.setPregunta(pregunta);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}