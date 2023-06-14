package com.duvarax.gamerasksapp.ui.Perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.R;
import com.duvarax.gamerasksapp.databinding.FragmentEditarPerfilBinding;

import java.lang.reflect.Field;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel mv;
    private FragmentEditarPerfilBinding binding;

    public static EditarPerfilFragment newInstance() {
        return new EditarPerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditarPerfilBinding.inflate(inflater, container, false);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(EditarPerfilViewModel.class);

        mv.edicionSatisfactoria().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_perfil);
            }
        });
        binding.btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombrePerfil.getText().toString();
                String apellido = binding.etApellidoPerfil.getText().toString();
                String nickname = binding.etNicknamePerfil.getText().toString();
                String email = binding.etEmailPerfil.getText().toString();
                String clave = binding.etCambiarClaveAntigua.getText().toString();

                Usuario usuario = new Usuario(0, nombre, apellido, nickname,email, clave, null, null);

                mv.editarPerfil(usuario);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}