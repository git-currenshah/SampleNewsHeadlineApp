package com.example.samplenewsheadlineapp.presentation;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplenewsheadlineapp.R;
import com.squareup.picasso.Picasso;

public class NewsHeadlineViewHolder extends RecyclerView.ViewHolder {

    private final TextView headlineTitle;
    private final ImageView headlineImage;
    private final TextView headlinePublishDate;

    public NewsHeadlineViewHolder(@NonNull View itemView) {
        super(itemView);
        headlineTitle = itemView.findViewById(R.id.txt_title);
        headlineImage = itemView.findViewById(R.id.img_headline);
        headlinePublishDate = itemView.findViewById(R.id.txt_publish_date);
    }

    public void setTitle(String title) {
        headlineTitle.setText(title);
    }

    public void setHeadlineImage(Handler handler, String url) {
        handler.post(() -> Picasso.get().load((url == null || url.isEmpty()) ? null : url)
                .resize(1024, 512)
                .onlyScaleDown()
                .centerInside()
                .into(headlineImage));
    }

    public void setPublishDate(String publishedAt) {
        headlinePublishDate.setText(publishedAt);
    }
}
