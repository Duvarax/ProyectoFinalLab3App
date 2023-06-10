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

import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentJuegosBinding;

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}