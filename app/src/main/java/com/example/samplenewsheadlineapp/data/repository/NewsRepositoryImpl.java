package com.example.samplenewsheadlineapp.data.repository;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.data.local.NewsHeadlinesDataSource;
import com.example.samplenewsheadlineapp.data.model.Response;
import com.example.samplenewsheadlineapp.domain.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.inject.Inject;

public class NewsRepositoryImpl implements NewsRepository {

    private final NewsHeadlinesDataSource newsHeadlinesDataSource;
    private final ObjectMapper objectMapper;

    @Inject
    public NewsRepositoryImpl(NewsHeadlinesDataSource newsHeadlinesDataSource) {
        this.newsHeadlinesDataSource = newsHeadlinesDataSource;
        objectMapper = new ObjectMapper();
    }

    @Override
    public Resource<Response> getNewsArticles() {
        Resource<Response> response;
        try {
            Response result = objectMapper.readValue(newsHeadlinesDataSource.getNewsHeadlinesResponse(), Response.class);
            response = Resource.success(result);

        } catch (IOException e) {
            e.printStackTrace();
            response = Resource.error("Error with mapping", null);
        }
        return response;
    }
}
