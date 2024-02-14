package com.android.cookbook.screens.categories;

import static androidx.navigation.Navigation.findNavController;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cookbook.databinding.ItemCategoryBinding;
import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.placeholder.PlaceholderContent.PlaceholderItem;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private final List<CategoryModel> categories;

    private final OnItemClickListener listener;


    public CategoriesAdapter(List<CategoryModel> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, categories.get(position));
            }
        });
        holder.txtCategory.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtCategory;

        public ViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            txtCategory = binding.itemNumber;
        }

    }
}

interface OnItemClickListener {
    void onItemClick(View view, CategoryModel item);
}


