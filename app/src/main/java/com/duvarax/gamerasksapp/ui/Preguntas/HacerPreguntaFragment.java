package com.duvarax.gamerasksapp.ui.Preguntas;

import static android.Manifest.permission_group.CAMERA;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentHacerPreguntaBinding;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HacerPreguntaFragment extends Fragment {

    private HacerPreguntaViewModel mv;
    private FragmentHacerPreguntaBinding binding;
    private Context context;
    private static int REQUEST_IMAGE_CAPTURE=1;

    public static HacerPreguntaFragment newInstance() {
        return new HacerPreguntaFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHacerPreguntaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HacerPreguntaViewModel.class);
        Bundle data = getArguments();
        Juego juego =(Juego) data.getSerializable("juego");
        binding.btnAdjuntarCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaPermisos();
                tomarFoto(v);
            }
        });

        mv.getJuego().observe(getActivity(), new Observer<Juego>() {
            @Override
            public void onChanged(Juego juego) {
                Glide.with(getActivity()).load(juego.getPortada()).into(binding.ivPortadaJuegoPreguntar);
                binding.tvIdJuegoPreguntar.setText(juego.getId()+"");
                binding.tvNombreJuegoPreguntar.setText(juego.getNombre());
            }
        });

        mv.getEnvioSatisfactorio().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_preguntas_x_juego, data);
            }
        });

        binding.btnEnviarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.enviarPregunta(binding.etPregunta.getText().toString(), "");
            }
        });

        mv.obtenerJuego(juego);


        return root;
    }

    public void tomarFoto(View v){
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (data != null) {
            Uri imagenUri = data.getData();
            if (imagenUri != null) {

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    ImageView iv = getActivity().findViewById(R.id.ivCapturaView);
                    iv.setImageBitmap(bitmap);
                    mv.setCaptura(requestCode, resultCode, data,REQUEST_IMAGE_CAPTURE);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((getActivity().checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{CAMERA},100);
        }

        return false;
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{CAMERA},100);
            }
        });
        dialogo.show();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}