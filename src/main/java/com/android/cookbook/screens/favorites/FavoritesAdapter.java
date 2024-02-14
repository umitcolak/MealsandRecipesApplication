package com.android.cookbook.screens.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.cookbook.databinding.ItemFavoriteBinding;
import com.android.cookbook.databinding.ItemFoodBinding;
import com.android.cookbook.model.MealModel;
import com.android.cookbook.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final List<MealModel> foods;

    private final OnItemClickListener listener;


    public FavoritesAdapter(List<MealModel> foods, OnItemClickListener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, foods.get(position));
            }
        });
        holder.ivRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeFromFavorites(foods.get(position));
                notifyItemRemoved(position);
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
        public final ImageView ivRemoveFavorite;

        public ViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            txtCategory = binding.itemNumber;
            ivRemoveFavorite = binding.ivRemoveFavorite;
        }

    }
}

interface OnItemClickListener {
    void onItemClick(View view, MealModel item);

    void removeFromFavorites(MealModel item);
}


