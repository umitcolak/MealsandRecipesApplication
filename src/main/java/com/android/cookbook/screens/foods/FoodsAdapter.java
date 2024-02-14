package com.android.cookbook.screens.foods;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cookbook.databinding.ItemCategoryBinding;
import com.android.cookbook.databinding.ItemFoodBinding;
import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.placeholder.PlaceholderContent.PlaceholderItem;
import com.android.cookbook.screens.categories.CategoriesAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.ViewHolder> {

    private final List<MealModel> foods;

    private final OnItemClickListener listener;


    public FoodsAdapter(List<MealModel> foods, OnItemClickListener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    @Override
    public FoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FoodsAdapter.ViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final FoodsAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, foods.get(position));
            }
        });
        holder.txtCategory.setText(foods.get(position).getMealName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtCategory;

        public ViewHolder(ItemFoodBinding binding) {
            super(binding.getRoot());
            txtCategory = binding.itemNumber;
        }

    }
}

interface OnItemClickListener {
    void onItemClick(View view, MealModel item);
}


