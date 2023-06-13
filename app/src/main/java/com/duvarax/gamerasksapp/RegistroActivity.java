package com.duvarax.gamerasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.duvarax.gamerasksapp.Models.Usuario;
import com.duvarax.gamerasksapp.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {

    private ActivityRegistroBinding binding;
    private RegistroActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        mv.getRegistro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(getApplicationContext(), "Registro Completado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etRegistroNombre.getText().toString();
                String apellido = binding.etRegistroApellido.getText().toString();
                String nickname = binding.etRegistroNombreUsuario.getText().toString();
                String email = binding.etRegistroEmail.getText().toString();
                String clave = binding.etRegistroClave.getText().toString();
                Usuario usuario = new Usuario(0, nombre, apellido, nickname,email, clave, null, null);
                mv.registrarse(usuario);
            }
        });
        setContentView(binding.getRoot());
    }


}