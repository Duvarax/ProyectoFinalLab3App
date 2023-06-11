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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentJuegosBinding;

import java.util.ArrayList;

public class JuegosFragment extends Fragment {

    private FragmentJuegosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJuegosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.ivBuscar.setImageResource(R.drawable.lupa_icon);
        binding.ivBuscar.setColorFilter(ContextCompat.getColor(getActivity(), R.color.black));
        binding.ivBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), binding.etBuscarJuego.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<Juego> listaJuegos = new ArrayList<Juego>();
        listaJuegos.add(new Juego(1, "dbz", "x", "https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg", "duvara", "10-06-2023"));
        listaJuegos.add(new Juego(2, "dbz", "x", "https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg", "duvara", "10-06-2023"));
        GridLayoutManager grid = new GridLayoutManager(root.getContext(), 2, GridLayoutManager.VERTICAL, false);
        binding.rvListaJuegos.setLayoutManager(grid);
        JuegosFragmentAdapter adapter = new JuegosFragmentAdapter(root.getContext(), getLayoutInflater(),listaJuegos, getActivity());
        binding.rvListaJuegos.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}