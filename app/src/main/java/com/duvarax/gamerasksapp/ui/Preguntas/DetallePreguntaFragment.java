package com.duvarax.gamerasksapp.ui.Preguntas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
                binding.ivCapturaDetallePregunta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mv.setUrlCapturaMutable(pregunta.getCaptura());
                    }
                });
            }
        });

        mv.getUrl().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("captura", s);
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_captura_ver, bundle1);
            }
        });

        mv.getEliminarPreguntaMutable().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_preguntas);
            }
        });


        binding.btnDetallePreguntaEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.bajaPregunta(pregunta);
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