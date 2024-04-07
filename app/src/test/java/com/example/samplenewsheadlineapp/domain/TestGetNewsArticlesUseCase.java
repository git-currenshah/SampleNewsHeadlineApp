package com.example.samplenewsheadlineapp.domain;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.Status;
import com.example.samplenewsheadlineapp.data.model.Article;
import com.example.samplenewsheadlineapp.data.model.Response;
import com.example.samplenewsheadlineapp.data.repository.FakeNewsRepository;

import org.junit.Before;
import org.junit.Test;

public class TestGetNewsArticlesUseCase {

    private GetNewsArticlesUseCase getNewsArticlesUseCase;
    private FakeNewsRepository fakeNewsRepository;

    @Before
    public void setup() {
        fakeNewsRepository = new FakeNewsRepository();
        Article article1 = new Article();
        article1.setTitle("Title 1");
        article1.setPublishedAt("2024-01-09T21:41:56Z");
        Article article2 = new Article();
        article2.setTitle("Title 2");
        article2.setPublishedAt("2024-01-10T22:41:25Z");
        Article article3 = new Article();
        article3.setTitle("Title 2");
        article3.setPublishedAt("2024-01-08T22:41:25Z");
        fakeNewsRepository.addArticle(article1);
        fakeNewsRepository.addArticle(article2);
        fakeNewsRepository.addArticle(article3);
        fakeNewsRepository.setArticleList();
        getNewsArticlesUseCase = new GetNewsArticlesUseCase(fakeNewsRepository);
    }

    @Test
    public void getNewsArticlesReturnsResult() {
        Resource<Response> value = getNewsArticlesUseCase.getNewsArticles();
        assert value.getStatus() == Status.SUCCESS;
        assert value.getData().getArticles().size() > 0;
    }

    @Test
    public void getNewsArticlesReturnsError() {
        fakeNewsRepository.setShouldShowError(true);
        Resource<Response> value = getNewsArticlesUseCase.getNewsArticles();
        assert value.getStatus() == Status.ERROR;
    }

    @Test
    public void getNewsArticlesReturnsResultVerifyDates() {
        Resource<Response> value = getNewsArticlesUseCase.getNewsArticles();
        assert value.getStatus() == Status.SUCCESS;
        assert value.getData().getArticles().get(0).publishDate.isAfter(value.getData().getArticles().get(1).publishDate);
        assert value.getData().getArticles().get(1).publishDate.isAfter(value.getData().getArticles().get(2).publishDate);
    }
}
