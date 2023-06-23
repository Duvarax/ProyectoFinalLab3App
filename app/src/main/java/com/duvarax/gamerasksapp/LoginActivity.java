package com.duvarax.gamerasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duvarax.gamerasksapp.databinding.ActivityMainBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        setContentView(binding.getRoot());
        binding.imageView2.setImageResource(R.drawable.joystick_icon2);
        binding.imageView2.setColorFilter(ContextCompat.getColor(this, R.color.yellowMain));

        mv.getLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.logear(binding.etEmailLogin.getText().toString(), binding.etClave.getText().toString());
            }
        });

        binding.btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.etClave.setText("");
        binding.etEmailLogin.setText("");
    }
}