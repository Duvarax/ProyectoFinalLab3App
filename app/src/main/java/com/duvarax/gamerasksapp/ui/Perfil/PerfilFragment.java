package com.duvarax.gamerasksapp.ui.Perfil;

import static android.Manifest.permission_group.CAMERA;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.duvarax.gamerasksapp.MenuActivity;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentPerfilBinding;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel mv;
    private Handler sliderHandler = new Handler();
    private Runnable sliderCambio;
    public int objetivoCamara;
    private static int REQUEST_IMAGE_CAPTURE=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        View root = binding.getRoot();



        binding.btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaPermisos();
                tomarFoto(v,R.id.ivImagenPerfil);
            }
        });

        binding.btnCambiarPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaPermisos();
                tomarFoto(v, R.id.ivPortadaPerfil);
            }
        });

        mv.getUsuarioLogeado().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvNombrePerfil.setText(usuario.getNombre() + " " + usuario.getApellido());
                binding.tvEmailPerfil.setText(usuario.getEmail());
                binding.tvNombreUsuarioPerfil.setText(usuario.getNombreusuario());
                Glide.with(getActivity()).load(usuario.getImagen()).into(binding.ivImagenPerfil);
                Glide.with(getActivity()).load(usuario.getPortada()).into(binding.ivPortadaPerfil);
            }
        });

        mv.getFotoMutable().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Glide.with(getActivity()).load(s).into(binding.ivImagenPerfil);
            }
        });

        mv.getListaRecientes().observe(getActivity(), new Observer<ArrayList<Juego>>() {
            @Override
            public void onChanged(ArrayList<Juego> strings) {
                PerfilSliderAdapter adapter = new PerfilSliderAdapter(getContext(), strings, getLayoutInflater(),binding.carousel, getActivity());
                binding.carousel.setAdapter(adapter);
                binding.carousel.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        sliderHandler.removeCallbacks(sliderCambio);
                        sliderHandler.postDelayed(sliderCambio, 5000);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        super.onPageScrollStateChanged(state);
                    }

                });

            }
        });




        mv.setUsuarioLogeado();


        mv.setListaJuegosRecientes();





        return root;
    }
    public void tomarFoto(View v, int objetivo){
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            objetivoCamara = objetivo;
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri imagenUri = data.getData();
                if (imagenUri != null) {

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        ImageView iv = getActivity().findViewById(objetivoCamara);
                        iv.setImageBitmap(bitmap);
                        mv.setFoto(requestCode, resultCode, data, REQUEST_IMAGE_CAPTURE, objetivoCamara);
                        objetivoCamara = 0;
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
    public void onResume() {
        super.onResume();
        sliderCambio = new Runnable() {
            @Override
            public void run() {
                binding.carousel.setCurrentItem(binding.carousel.getCurrentItem() + 1);
            }
        };


    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderCambio);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        sliderHandler.removeCallbacks(sliderCambio);
    }
}