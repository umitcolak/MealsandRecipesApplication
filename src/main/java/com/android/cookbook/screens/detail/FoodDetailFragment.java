package com.android.cookbook.screens.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.cookbook.R;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.screens.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Response;

public class FoodDetailFragment extends Fragment {

    private TextView txtName;
    private TextView txtRecipe;
    private RecyclerView rvComments;
    private FloatingActionButton fabFavorite;

    private String mealId;
    private MainViewModel viewModel;

    private Boolean isFavorite = false;
    private MealModel meal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mealId = getArguments().getString("mealId");
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Food Detail");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        txtName = view.findViewById(R.id.txtFoodName);
        txtRecipe = view.findViewById(R.id.txtRecipe);
        fabFavorite = view.findViewById(R.id.fabFavorite);
        rvComments = view.findViewById(R.id.rvComments);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMealDetail();
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    removeMealFromFavorites();
                } else {
                    addMealToFavorites();
                }
                isFavorite = !isFavorite;
                setFavoriteButtonColor();
            }
        });

    }

    private void getMealDetail() {
        viewModel.getMealById(mealId).observe(getViewLifecycleOwner(), new Observer<Response<MealModel>>() {
            @Override
            public void onChanged(Response<MealModel> mealModelResponse) {
                if (mealModelResponse.isSuccessful()) {
                    if (mealModelResponse.body() != null) {
                        meal = mealModelResponse.body();
                        txtName.setText(meal.getMealName());
                        txtRecipe.setText(meal.getRecipe());

                        rvComments.setLayoutManager(new LinearLayoutManager(getContext()));

                        rvComments.setAdapter(new CommentsAdapter(meal.getComments()));
                        getUserFavorites();
                    }
                } else {
                    Toast.makeText(getContext(), mealModelResponse.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getUserFavorites() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("cookbook_prefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", "");
        viewModel.getUserFavorites(userId).observe(getViewLifecycleOwner(), new Observer<Response<List<MealModel>>>() {
            @Override
            public void onChanged(Response<List<MealModel>> listResponse) {
                if (listResponse.isSuccessful()) {
                    isFavorite = false;
                    if (listResponse.body().isEmpty()) {
                        fabFavorite.setImageTintList(ColorStateList.valueOf(0xFF000000));
                    } else {
                        for (MealModel meal : listResponse.body()) {
                            if (meal.getMealId().equals(mealId)) {
                                isFavorite = true;
                                break;
                            }
                        }
                    }
                    setFavoriteButtonColor();
                } else {
                    Toast.makeText(getContext(), listResponse.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setFavoriteButtonColor() {
        fabFavorite.hide();
        if (isFavorite) {
            fabFavorite.setImageTintList(ColorStateList.valueOf(0xFFFF1303));
        } else {
            fabFavorite.setImageTintList(ColorStateList.valueOf(0xFF000000));
        }
        fabFavorite.show();
    }

    private void addMealToFavorites() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("cookbook_prefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", "");
        viewModel.addMealToFavorites(userId, mealId).observe(getViewLifecycleOwner(), new Observer<Response<String>>() {
            @Override
            public void onChanged(Response<String> stringResponse) {
                if (stringResponse.isSuccessful()) {
                    getUserFavorites();
                    Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), stringResponse.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void removeMealFromFavorites() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("cookbook_prefs", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", "");
        viewModel.removeMealFromFavorites(userId, mealId).observe(getViewLifecycleOwner(), new Observer<Response<String>>() {
            @Override
            public void onChanged(Response<String> stringResponse) {
                if (stringResponse.isSuccessful()) {
                    getUserFavorites();
                    Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), stringResponse.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}