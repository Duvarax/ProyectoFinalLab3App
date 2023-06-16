package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas;

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
import com.duvarax.gamerasksapp.Models.Pregunta;
import com.duvarax.gamerasksapp.Models.Respuesta;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentRespuestasBinding;
import com.duvarax.gamerasksapp.ui.Preguntas.PreguntasFragmentAdapter;

import java.util.ArrayList;

public class RespuestasFragment extends Fragment {

    private RespuestasViewModel mv;
    private FragmentRespuestasBinding binding;

    public static RespuestasFragment newInstance() {
        return new RespuestasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRespuestasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(RespuestasViewModel.class);
        Bundle bundle = getArguments();
        Pregunta pregunta = (Pregunta) bundle.getSerializable("pregunta");

        mv.preguntaLiveData().observe(getActivity(), new Observer<Pregunta>() {
            @Override
            public void onChanged(Pregunta pregunta) {
                binding.tvPreguntaXJuego.setText(pregunta.getTexto());
                Glide.with(getContext()).load(pregunta.getUsuario().getImagen()).into(binding.civUserPreguntaList);
                binding.btnDetallePregunta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //hacer detalle pregunta
                        //Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_pre, bundle);
                    }
                });
            }
        });

        mv.respuestasLiveData().observe(getActivity(), new Observer<ArrayList<Respuesta>>() {
            @Override
            public void onChanged(ArrayList<Respuesta> respuestas) {
                RespuestasFragmentAdapter adapter = new RespuestasFragmentAdapter(getContext(), getLayoutInflater(),respuestas, getActivity());
                binding.rvListaRespuestas.setAdapter(adapter);
                GridLayoutManager grid = new GridLayoutManager(root.getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.rvListaRespuestas.setLayoutManager(grid);
            }
        });

        binding.civSendRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("salida", "andando");
                mv.altaRespuesta(binding.etRespuesta.getText().toString());
            }
        });

        mv.setListaRespuestasMutable(pregunta);
        mv.setPreguntaMutable(pregunta);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}