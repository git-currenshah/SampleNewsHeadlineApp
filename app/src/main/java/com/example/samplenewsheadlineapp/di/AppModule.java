package com.example.samplenewsheadlineapp.di;

import android.content.Context;

import com.example.samplenewsheadlineapp.data.local.NewsHeadlinesDataSource;
import com.example.samplenewsheadlineapp.data.repository.NewsRepositoryImpl;
import com.example.samplenewsheadlineapp.domain.GetNewsArticlesUseCase;
import com.example.samplenewsheadlineapp.domain.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public NewsHeadlinesDataSource provideNewsHeadlineDataSource(@ApplicationContext Context context) {
        return new NewsHeadlinesDataSource(context);
    }

    @Provides
    @Singleton
    public NewsRepository provideNewsRepository(NewsHeadlinesDataSource newsHeadlinesDataSource) {
        return new NewsRepositoryImpl(newsHeadlinesDataSource);
    }

    @Provides
    @Singleton
    public GetNewsArticlesUseCase provideGetNewsArticlesUseCase(NewsRepository newsRepository) {
        return new GetNewsArticlesUseCase(newsRepository);
    }
}
