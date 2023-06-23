package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

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
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentDetalleRespuestaBinding;

public class DetalleRespuestaFragment extends Fragment {

    private DetalleRespuestaViewModel mv;
    private FragmentDetalleRespuestaBinding binding;

    public static DetalleRespuestaFragment newInstance() {
        return new DetalleRespuestaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleRespuestaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleRespuestaViewModel.class);
        Bundle data = getArguments();
        Respuesta respuesta = (Respuesta) data.getSerializable("respuesta");

        mv.getRespuestaMutable().observe(getActivity(), new Observer<Respuesta>() {
            @Override
            public void onChanged(Respuesta respuesta) {
                binding.tvFechaCreacionDetalleRespuesta.setText(respuesta.getFechaCreacion());
                binding.tvNicknameUsuarioDetalleRespuesta.setText(respuesta.getUsuario().getNombreusuario());
                binding.tvRespuestaDetalleRespuesta.setText(respuesta.getTexto());
                binding.tvPreguntaDetalleRespuesta.setText(respuesta.getPregunta().getTexto());
                Glide.with(getContext()).load(respuesta.getUsuario().getImagen()).into(binding.ivImagenUsuarioDetalleRespuesta);
                Glide.with(getContext()).load(respuesta.getPregunta().getJuego().getPortada()).into(binding.ivJuegoPortadaDetalleRespuesta);
            }
        });

        mv.setRespuestaMutableLiveData(respuesta);
        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}