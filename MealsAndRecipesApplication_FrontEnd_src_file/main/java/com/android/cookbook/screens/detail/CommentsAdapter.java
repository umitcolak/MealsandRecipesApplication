package com.android.cookbook.screens.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.cookbook.databinding.ItemCategoryBinding;
import com.android.cookbook.databinding.ItemCommentBinding;
import com.android.cookbook.model.CategoryModel;
import com.android.cookbook.model.CommentModel;
import com.android.cookbook.placeholder.PlaceholderContent.PlaceholderItem;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private final List<CommentModel> comments;


    public CommentsAdapter(List<CommentModel> categories) {
        this.comments = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txtUserName.setText(comments.get(position).getUsername());
        holder.txtDate.setText(getRandomDate());
        holder.txtComment.setText(comments.get(position).getCommentText());
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtUserName;
        public final TextView txtDate;
        public final TextView txtComment;

        public ViewHolder(ItemCommentBinding binding) {
            super(binding.getRoot());
            txtUserName = binding.txtCommentUserName;
            txtDate = binding.txtCommentDate;
            txtComment = binding.txtComment;
        }

    }


    private String getRandomDate() {
        SimpleDateFormat dfDateTime = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault());
        int year = randBetween(2023, 2024);// Here you can set Range of years you need
        int month = randBetween(0, 11);
        int hour = randBetween(9, 22); //Hours will be displayed in between 9 to 22
        int min = randBetween(0, 59);
        int sec = randBetween(0, 59);


        GregorianCalendar gc = new GregorianCalendar(year, month, 1);
        int day = randBetween(1, gc.getActualMaximum(gc.DAY_OF_MONTH));

        gc.set(year, month, day, hour, min, sec);

        return dfDateTime.format(gc.getTime());
    }

    private int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}