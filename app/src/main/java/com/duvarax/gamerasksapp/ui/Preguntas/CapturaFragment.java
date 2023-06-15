package com.duvarax.gamerasksapp.ui.Preguntas;

import androidx.lifecycle.Observer;
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
import com.duvarax.gamerasksapp.databinding.FragmentCapturaBinding;

public class CapturaFragment extends Fragment {

    private CapturaViewModel mv;
    private FragmentCapturaBinding binding;

    public static CapturaFragment newInstance() {
        return new CapturaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCapturaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CapturaViewModel.class);

        mv.getCapturaMutable().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Glide.with(getContext()).load(s).into(binding.ivCapturaVer);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}