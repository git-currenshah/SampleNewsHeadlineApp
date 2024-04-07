package com.example.samplenewsheadlineapp.domain;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.data.model.Response;

public interface NewsRepository {

    Resource<Response> getNewsArticles();
}
