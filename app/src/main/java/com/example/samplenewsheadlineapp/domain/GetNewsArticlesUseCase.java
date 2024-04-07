package com.example.samplenewsheadlineapp.domain;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.Status;
import com.example.samplenewsheadlineapp.data.model.Response;

import java.util.Collections;

import javax.inject.Inject;

public class GetNewsArticlesUseCase {

    private final NewsRepository newsRepository;
    @Inject
    public GetNewsArticlesUseCase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Resource<Response> getNewsArticles() {
        Resource<Response> result = newsRepository.getNewsArticles();
        if (result.getStatus() != Status.ERROR) {
            Collections.sort(result.getData().getArticles());
        }
        return result;
    }


}
