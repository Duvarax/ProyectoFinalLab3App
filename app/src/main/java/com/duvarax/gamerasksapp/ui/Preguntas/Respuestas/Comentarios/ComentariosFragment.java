package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.Models.Comentario;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentComentariosBinding;

import java.util.ArrayList;

public class ComentariosFragment extends Fragment {

    private ComentariosViewModel mv;
    private FragmentComentariosBinding binding;

    public static ComentariosFragment newInstance() {
        return new ComentariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentComentariosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ComentariosViewModel.class);
        Bundle bundle = getArguments();
        Respuesta respuesta = (Respuesta) bundle.getSerializable("respuesta");

        mv.comentariosLiveData().observe(getActivity(), new Observer<ArrayList<Comentario>>() {
            @Override
            public void onChanged(ArrayList<Comentario> comentarios) {
                ComentariosFragmentAdapter adapter = new ComentariosFragmentAdapter(getContext(), getLayoutInflater(), comentarios, getActivity());
                binding.rvListaComentarios.setAdapter(adapter);
                GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvListaComentarios.setLayoutManager(grid);
            }
        });
        mv.respuestaLiveData().observe(getActivity(), new Observer<Respuesta>() {
            @Override
            public void onChanged(Respuesta respuesta) {
                Glide.with(getContext()).load(respuesta.getUsuario().getImagen()).into(binding.civUserRespuestaList);
                binding.tvRespuesta.setText(respuesta.getTexto());
            }
        });


        mv.setComentarios(respuesta);
        mv.setRespuesta(respuesta);

        binding.civSendComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.altaComentario(binding.etComentario.getText().toString());
            }
        });
        binding.btnDetalleRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_detalle_respuesta, bundle);

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}