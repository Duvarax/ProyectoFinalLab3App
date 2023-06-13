package com.duvarax.gamerasksapp.ui.Perfil;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.duvarax.gamerasksapp.MenuActivity;
import com.duvarax.gamerasksapp.Models.Juego;
import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentPerfilBinding;

import java.util.ArrayList;
import java.util.List;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel mv;
    private Handler sliderHandler = new Handler();
    private Runnable sliderCambio;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        View root = binding.getRoot();
        binding.imageView3.setImageResource(R.drawable.palanca_icon);



        mv.getUsuarioLogeado().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvNombrePerfil.setText(usuario.getNombre());
                binding.tvEmailPerfil.setText(usuario.getEmail());
                binding.tvNombreUsuarioPerfil.setText(usuario.getNombreUsuario());
            }
        });
        mv.setUsuarioLogeado();

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

        mv.setListaJuegosRecientes();


        return root;
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