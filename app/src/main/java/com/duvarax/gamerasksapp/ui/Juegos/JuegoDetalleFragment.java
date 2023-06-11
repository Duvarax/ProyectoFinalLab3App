package com.duvarax.gamerasksapp.ui.Juegos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentJuegoDetalleBinding;

public class JuegoDetalleFragment extends Fragment {

    private JuegoDetalleViewModel mViewModel;
    private FragmentJuegoDetalleBinding binding;

    public static JuegoDetalleFragment newInstance() {
        return new JuegoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentJuegoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Glide.with(getContext())
                .load("https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg")
                .into(binding.imageView4);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(JuegoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}