package com.duvarax.gamerasksapp.ui.Preguntas.Respuestas.Comentarios;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.gamerasksapp.R;

public class ComentariosFragment extends Fragment {

    private ComentariosViewModel mViewModel;

    public static ComentariosFragment newInstance() {
        return new ComentariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comentarios, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ComentariosViewModel.class);
        // TODO: Use the ViewModel
    }

}