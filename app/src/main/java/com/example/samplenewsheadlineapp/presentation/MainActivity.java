package com.example.samplenewsheadlineapp.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samplenewsheadlineapp.R;
import com.example.samplenewsheadlineapp.data.model.Article;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private NewsHeadlinesAdapter newsHeadlinesAdapter;
    private EditText searchHeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initializeViews();
        observeValues();
    }

    private void observeValues() {
        mainViewModel.newsHeadlines.observe(this, articles -> {
            if (articles.size() > 0) {
                searchHeadline.setVisibility(View.VISIBLE);
                newsHeadlinesAdapter.updateList(articles);
            }
        });

        mainViewModel.showError.observe(this, showError -> {
            if (showError)
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        });
    }

    private void initializeViews() {
        RecyclerView newsHeadlineList = findViewById(R.id.news_headline_list);
        searchHeadline = findViewById(R.id.edtxt_search_headline);
        searchHeadline.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchHeadline.getText().toString().length() > 0) {
                    handleSearchHeadline(searchHeadline.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        newsHeadlineList.setLayoutManager(new LinearLayoutManager(this));
        newsHeadlinesAdapter = new NewsHeadlinesAdapter(new ArrayList<>());
        newsHeadlineList.setAdapter(newsHeadlinesAdapter);
    }

    private void handleSearchHeadline(String token) {
        if (mainViewModel.newsHeadlines.getValue() != null && !mainViewModel.newsHeadlines.getValue().isEmpty()) {
            List<Article> filteredArticles = mainViewModel.getFilteredArticles(mainViewModel.newsHeadlines.getValue(), token);

            if (!filteredArticles.isEmpty()) {
                newsHeadlinesAdapter.updateList(filteredArticles);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.getNewsArticles();
    }
}