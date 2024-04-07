package com.example.samplenewsheadlineapp.data.repository;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.data.model.Article;
import com.example.samplenewsheadlineapp.data.model.Response;
import com.example.samplenewsheadlineapp.domain.NewsRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeNewsRepository implements NewsRepository {

    private List<Article> newsHeadlinesList = new ArrayList<>();
    private Response newsHeadlinesResponse;
    private Boolean shouldShowError = false;

    public void setShouldShowError(Boolean showError) {
        shouldShowError = showError;
    }

    public void addArticle(Article article) {
        newsHeadlinesList.add(article);
    }

    public void setArticleList() {
        Response response = new Response();
        response.setArticles(newsHeadlinesList);
        newsHeadlinesResponse = response;
    }

    @Override
    public Resource<Response> getNewsArticles() {
        if(shouldShowError)
            return Resource.error("Error Message", null);
        else return Resource.success(newsHeadlinesResponse);
    }
}
