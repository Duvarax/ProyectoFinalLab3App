package com.duvarax.gamerasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;

import com.duvarax.gamerasksapp.databinding.ActivityMainBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.imageView2.setImageResource(R.drawable.joystick_icon2);
        binding.imageView2.setColorFilter(ContextCompat.getColor(this, R.color.yellowMain));
        setContentView(binding.getRoot());
    }
}