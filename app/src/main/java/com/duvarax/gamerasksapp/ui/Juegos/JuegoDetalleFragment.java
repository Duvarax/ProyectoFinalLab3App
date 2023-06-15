package com.duvarax.gamerasksapp.ui.Juegos;

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
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentJuegoDetalleBinding;

public class JuegoDetalleFragment extends Fragment {

    private JuegoDetalleViewModel mv;
    private FragmentJuegoDetalleBinding binding;

    public static JuegoDetalleFragment newInstance() {
        return new JuegoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentJuegoDetalleBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(JuegoDetalleViewModel.class);
        View root = binding.getRoot();

        Bundle data = getArguments();
        Juego juego =(Juego) data.getSerializable("juego");


        binding.btnPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_preguntas_x_juego);
            }
        });
        binding.btnPreguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_hacer_pregunta, data);
            }
        });

        mv.getJuego().observe(getActivity(), new Observer<Juego>() {
            @Override
            public void onChanged(Juego juego) {
                binding.tvJuegoDetalleNombre.setText(juego.getNombre());
                binding.tvJuegoDetalleAutor.setText(juego.getAutor());
                binding.tvJuegoDetalleFecha.setText(juego.getFechaLanzamiento());
                binding.tvJuegoDetalleDescripcion.setText(juego.getDescripcion());
                Glide.with(getContext())
                        .load(juego.getPortada())
                        .into(binding.ivJuegoDetallePortada);
            }
        });

        mv.getCantidadPreguntas().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String aux = "Cantidad de preguntas:";
                binding.tvCantidadPreguntas.setText(aux + " " + integer);
            }
        });

        mv.obtenerJuego(juego);
        mv.obtenerCantidadPreguntas(juego);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}