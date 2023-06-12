package com.duvarax.gamerasksapp.ui.Preguntas;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;


import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentHacerPreguntaBinding;

public class HacerPreguntaFragment extends Fragment {

    private HacerPreguntaViewModel mViewModel;
    private FragmentHacerPreguntaBinding binding;
    private Context context;

    public static HacerPreguntaFragment newInstance() {
        return new HacerPreguntaFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Obtener URI, mostrar imagen y subir esa URI A CLOUDINARY en caso de crear la pregunta
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHacerPreguntaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = root.getContext();
        Glide.with(getContext())
                .load("https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg")
                .into(binding.ivPortadaJuegoPreguntar);
        binding.btnAdjuntarCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Si no tienes permisos, solicítalos
                    ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 200);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 100);
                }

            }
        });


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HacerPreguntaViewModel.class);
        // TODO: Use the ViewModel
    }

}