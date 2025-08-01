package com.example.timero.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timero.R;
import com.example.timero.data.model.Post;

import java.util.ArrayList;
import java.util.List;

public class LatestTopicsAdapter extends RecyclerView.Adapter<LatestTopicsAdapter.LatestTopicViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private final OnPostClickListener onPostClickListener;

    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    public LatestTopicsAdapter(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }

    @NonNull
    @Override
    public LatestTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest_topic, parent, false);
        return new LatestTopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestTopicViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.bind(currentPost, onPostClickListener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    static class LatestTopicViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView image;

        public LatestTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title_grid);
            image = itemView.findViewById(R.id.post_image_grid);
        }

        public void bind(final Post post, final OnPostClickListener listener) {
            title.setText(post.getTitle());

            // Use Glide to load the image from the URL
            Glide.with(itemView.getContext())
                    .load(post.getImageUrl())
                    .centerCrop()
                    .into(image);

            itemView.setOnClickListener(v -> listener.onPostClick(post));
        }
    }
}
