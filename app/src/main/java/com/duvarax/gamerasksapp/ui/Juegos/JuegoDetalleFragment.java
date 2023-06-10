package com.duvarax.gamerasksapp.ui.Juegos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.gamerasksapp.R;

public class JuegoDetalleFragment extends Fragment {

    private JuegoDetalleViewModel mViewModel;

    public static JuegoDetalleFragment newInstance() {
        return new JuegoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_juego_detalle, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(JuegoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}