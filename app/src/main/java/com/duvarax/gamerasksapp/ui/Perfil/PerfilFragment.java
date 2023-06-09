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
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.duvarax.gamerasksapp.MenuActivity;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentPerfilBinding;

import java.util.ArrayList;
import java.util.List;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private Handler sliderHandler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.imageView3.setImageResource(R.drawable.palanca_icon);
        ArrayList<String> listaImagenes = new ArrayList<>();
        listaImagenes.add("https://generacionxbox.com/wp-content/uploads/2021/04/epic-games-store-juegos-gratis.jpg");
        listaImagenes.add("https://img.freepik.com/vector-gratis/conjunto-oscuro-elementos-boton-piedra-juego-barra-progreso-brillantes-diferentes-formas-botones-juegos-aplicaciones_1150-39977.jpg");
        listaImagenes.add("https://i.pinimg.com/originals/3a/a4/ea/3aa4eaef2581fcfd78b824410bf9d61e.jpg");
        PerfilSliderAdapter adapter = new PerfilSliderAdapter(root.getContext(), listaImagenes, getLayoutInflater(), binding.carousel, getActivity());
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
                sliderHandler.postDelayed(sliderCambio, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        return root;
    }

    private Runnable sliderCambio = new Runnable() {
        @Override
        public void run() {
            binding.carousel.setCurrentItem(binding.carousel.getCurrentItem() + 1);
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}