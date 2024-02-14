package com.android.cookbook.screens.favorites;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.RadioAccessSpecifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cookbook.R;
import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.placeholder.PlaceholderContent;
import com.android.cookbook.screens.MainViewModel;
import com.android.cookbook.screens.categories.CategoriesAdapter;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class FavoritesFragment extends Fragment {

    private MainViewModel viewModel;

    private RecyclerView recyclerView;
    private TextView txtError;

    FavoritesAdapter adapter;

    ArrayList<MealModel> items = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().setTitle("Favorites");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Favorites");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.rvFavorites);
        txtError = view.findViewById(R.id.txtErrorText);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getFavorites();
        return view;
    }

    private void getFavorites() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("cookbook_prefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", "");
        viewModel.getUserFavorites(userId).observe(getViewLifecycleOwner(), new Observer<Response<List<MealModel>>>() {
            @Override
            public void onChanged(Response<List<MealModel>> listResponse) {
                if (listResponse.isSuccessful()) {
                    items.clear();
                    items.addAll(listResponse.body());
                    adapter = new FavoritesAdapter(items, new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, MealModel item) {
                            Bundle bundle = new Bundle();
                            bundle.putString("mealId", item.getMealId());
                            findNavController(view).navigate(R.id.action_favoritesFragment_to_foodDetailFragment, bundle);
                        }

                        @Override
                        public void removeFromFavorites(MealModel item) {
                            items.remove(item);
                            removeMealFromFavorites(item.getMealId());
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getContext(), listResponse.message(), Toast.LENGTH_SHORT).show();
                    txtError.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void removeMealFromFavorites(String mealId) {
        SharedPreferences sharedPref = getContext().getSharedPreferences("cookbook_prefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", "");
        viewModel.removeMealFromFavorites(userId, mealId).observe(getViewLifecycleOwner(), new Observer<Response<String>>() {
            @Override
            public void onChanged(Response<String> stringResponse) {
                if (stringResponse.isSuccessful()) {
                    getFavorites();
                    Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), stringResponse.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}