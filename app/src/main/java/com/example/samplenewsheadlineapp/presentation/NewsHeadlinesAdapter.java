package com.example.samplenewsheadlineapp.presentation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplenewsheadlineapp.R;
import com.example.samplenewsheadlineapp.data.model.Article;

import java.util.List;

public class NewsHeadlinesAdapter extends RecyclerView.Adapter<NewsHeadlineViewHolder> {

    private List<Article> newsArticles;
    private final Handler handler = new Handler(Looper.getMainLooper());

    NewsHeadlinesAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }
    @NonNull
    @Override
    public NewsHeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_headline, parent, false);
        return new NewsHeadlineViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHeadlineViewHolder holder, int position) {
        Article article = newsArticles.get(position);
        holder.setTitle(article.getTitle());
        holder.setHeadlineImage(handler, article.getUrlToImage());
        holder.setPublishDate("Published: "+article.getPublishDate().getDayOfMonth() +"/" + article.getPublishDate().getMonth().getValue()+ "/" + article.getPublishDate().getYear());
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public void updateList(List<Article> newList) {
        this.newsArticles = newList;
        notifyDataSetChanged();
    }
}
