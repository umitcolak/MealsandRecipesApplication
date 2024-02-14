package com.android.cookbook.screens.categories;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cookbook.R;
import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.screens.MainViewModel;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class CategoriesFragment extends Fragment {
    private MainViewModel viewModel;

    private CircularProgressIndicator progressIndicator;

    private RecyclerView recyclerView;
    private TextView txtError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Categories");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = view.findViewById(R.id.list);
        progressIndicator = view.findViewById(R.id.categoryProgress);
        txtError = view.findViewById(R.id.txtErrorText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCategories();
    }

    private void getCategories() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<Response<List<CategoryModel>>>() {
            @Override
            public void onChanged(Response<List<CategoryModel>> listResponse) {
                if (listResponse.isSuccessful()) {
                    if (!listResponse.body().isEmpty()) {
                        progressIndicator.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(new CategoriesAdapter(listResponse.body(), new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, CategoryModel item) {
                                viewModel.setSelectedCategory(item);
                                findNavController(view).navigate(R.id.action_categoriesFragment_to_foodsFragment);
                            }
                        }));
                    }
                } else {
                    Toast.makeText(getContext(), listResponse.message(), Toast.LENGTH_SHORT).show();
                    txtError.setVisibility(View.VISIBLE);
                    progressIndicator.setVisibility(View.GONE);
                }
            }
        });
    }


}