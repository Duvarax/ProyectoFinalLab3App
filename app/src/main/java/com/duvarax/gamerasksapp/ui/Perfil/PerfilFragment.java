package com.duvarax.gamerasksapp.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentPerfilBinding;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.imageView3.setImageResource(R.drawable.palanca_icon);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}