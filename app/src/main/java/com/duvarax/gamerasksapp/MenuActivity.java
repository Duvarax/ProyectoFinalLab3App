package com.duvarax.gamerasksapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.duvarax.gamerasksapp.ui.Perfil.Dialogo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.duvarax.gamerasksapp.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked }, // Estado seleccionado
                new int[] {} // Estado no seleccionado
        };

        int[] colors = new int[] {
                Color.YELLOW, // Color cuando está seleccionado (amarillo)
                Color.GRAY // Color cuando no está seleccionado (gris)
        };

        ColorStateList colorStateList = new ColorStateList(states, colors);

        navView.setItemTextColor(colorStateList);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_juegos, R.id.navigation_perfil, R.id.navigation_preguntas)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        Log.d("salida", id+"");
        if(id == R.id.menu1){
            Navigation.findNavController(this,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_editar_perfil);
        }
        if(id == R.id.menu2){
            Navigation.findNavController(this,R.id.nav_host_fragment_activity_menu).navigate(R.id.navigation_editar_contraseña);
        }
        if(id == R.id.menu3){
            Dialogo.mostrarDialogoBotones(this);
        }
        return super.onOptionsItemSelected(item);
    }

}