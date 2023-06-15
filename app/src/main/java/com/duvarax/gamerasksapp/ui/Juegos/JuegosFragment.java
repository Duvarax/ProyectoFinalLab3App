package com.duvarax.gamerasksapp.ui.Juegos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentJuegosBinding;

import java.util.ArrayList;

public class JuegosFragment extends Fragment {

    private FragmentJuegosBinding binding;
    private JuegosViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJuegosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(JuegosViewModel.class);

        binding.ivBuscar.setImageResource(R.drawable.lupa_icon);
        binding.ivBuscar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.black));

        mv.getListaJuegos().observe(getActivity(), new Observer<ArrayList<Juego>>() {
            @Override
            public void onChanged(ArrayList<Juego> juegos) {
                GridLayoutManager grid = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                binding.rvListaJuegos.setLayoutManager(grid);
                JuegosFragmentAdapter adapter = new JuegosFragmentAdapter(getContext(), getLayoutInflater(),juegos, getActivity());
                binding.rvListaJuegos.setAdapter(adapter);
            }
        });

        mv.obtenerListaJuegos();

        binding.ivBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), binding.etBuscarJuego.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}