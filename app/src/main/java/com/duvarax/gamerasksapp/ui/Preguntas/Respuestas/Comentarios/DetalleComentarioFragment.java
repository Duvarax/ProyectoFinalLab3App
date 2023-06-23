package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

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
import com.duvarax.gamerasksapp.Models.Comentario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentDetalleComentarioBinding;
import com.duvarax.gamerasksapp.databinding.FragmentDetalleRespuestaBinding;

public class DetalleComentarioFragment extends Fragment {

    private DetalleComentarioViewModel mv;
    private FragmentDetalleComentarioBinding binding;

    public static DetalleComentarioFragment newInstance() {
        return new DetalleComentarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleComentarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleComentarioViewModel.class);
        Bundle data = getArguments();
        Comentario comentario = (Comentario)data.getSerializable("comentario");
        mv.getComentarioMutable().observe(getActivity(), new Observer<Comentario>() {
            @Override
            public void onChanged(Comentario comentario) {
                binding.tvRespuestaDetalleComentario.setText(comentario.getRespuesta().getTexto());
                binding.tvFechaCreacionDetalleRespuesta.setText(comentario.getFechaCreacion());
                binding.tvNicknameUsuarioDetalleRespuesta.setText(comentario.getUsuario().getNombreusuario());
                binding.tvRespuestaDetalleRespuesta.setText(comentario.getTexto());
                Glide.with(getContext()).load(comentario.getUsuario().getImagen()).into(binding.ivImagenUsuarioDetalleRespuesta);
                Glide.with(getContext()).load(comentario.getRespuesta().getUsuario().getImagen()).into(binding.ivUsuarioRespuestaDetalleRespuesta);
            }
        });

        mv.setComentarioMutableLiveData(comentario);

        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}