package com.example.samplenewsheadlineapp.data.local;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.inject.Inject;


public class NewsHeadlinesDataSource {

    private final Context context;

    @Inject
    public NewsHeadlinesDataSource(Context context) {
        this.context = context;
    }

    public String getNewsHeadlinesResponse() {
        String newsResponse = "";
        try {
            newsResponse = new BufferedReader(new InputStreamReader(context.getAssets().open("news.json")))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsResponse;
    }
}