package com.android.cookbook.screens;

import static androidx.navigation.ActivityKt.findNavController;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.android.cookbook.R;
import com.android.cookbook.screens.categories.CategoriesFragment;
import com.android.cookbook.screens.favorites.FavoritesFragment;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    Boolean favoritesOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.my_toolbar));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            int id = findNavController(this, R.id.nav_host_fragment_activity_bottem_navigation).getCurrentDestination().getId();

            if (id != R.id.favoritesFragment && !favoritesOpened) {
                favoritesOpened = true;
                findNavController(this, R.id.nav_host_fragment_activity_bottem_navigation).navigate(R.id.favoritesFragment);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int id = findNavController(this, R.id.nav_host_fragment_activity_bottem_navigation).getCurrentDestination().getId();
        if (id == R.id.favoritesFragment) {
            favoritesOpened = false;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}