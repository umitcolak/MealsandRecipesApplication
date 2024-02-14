package com.android.cookbook.screens.foods;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cookbook.R;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.screens.MainViewModel;
import com.google.gson.Gson;

public class FoodsFragment extends Fragment implements OnItemClickListener {
    private MainViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(viewModel.getSelectedCategory().getCategoryName() + " Foods");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new FoodsAdapter(viewModel.getSelectedCategory().getMeals(), this));
        }
        return view;
    }

    @Override
    public void onItemClick(View view, MealModel item) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", item.getMealId());
        findNavController(view).navigate(R.id.action_foodsFragment_to_foodDetailFragment, bundle);
    }
}