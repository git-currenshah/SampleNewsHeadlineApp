package com.example.samplenewsheadlineapp.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.samplenewsheadlineapp.Resource;
import com.example.samplenewsheadlineapp.Status;
import com.example.samplenewsheadlineapp.data.model.Article;
import com.example.samplenewsheadlineapp.data.model.Response;
import com.example.samplenewsheadlineapp.domain.GetNewsArticlesUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    GetNewsArticlesUseCase getNewsArticlesUseCase;

    @Inject
    MainViewModel(GetNewsArticlesUseCase getNewsArticlesUseCase) {
        this.getNewsArticlesUseCase = getNewsArticlesUseCase;
    }


    private final MutableLiveData<List<Article>> newsHeadlinesMutable = new MutableLiveData<>();
    final LiveData<List<Article>> newsHeadlines = newsHeadlinesMutable;
    private final MutableLiveData<Boolean> showErrorMutable = new MutableLiveData<>(false);
    final LiveData<Boolean> showError = showErrorMutable;

    private final MutableLiveData<Boolean> showLoadingMutable = new MutableLiveData<>(false);
    final LiveData<Boolean> showLoading = showLoadingMutable;

    void getNewsArticles() {

        Resource<Response> result = getNewsArticlesUseCase.getNewsArticles();

        if (result.getStatus() == Status.ERROR) {
            setShowErrorMutable(true);
        } else if (result.getStatus() == Status.SUCCESS) {
            setNewsHeadlinesMutable(result.getData().getArticles());
        } else if (result.getStatus() == Status.LOADING) {
            setShowLoadingMutable(true);
        }
    }

    List<Article> getFilteredArticles(List<Article> availableArticles, String token) {
        List<Article> filteredArticles = new ArrayList<>();
        for (Article article : availableArticles) {
            if (article.getTitle().toLowerCase(Locale.getDefault()).trim().replace(" ", "").contains(token.toLowerCase(Locale.getDefault()).trim().replace(" ", ""))) {
                filteredArticles.add(article);
            }
        }
        return filteredArticles;
    }

    private void setNewsHeadlinesMutable(List<Article> response) {
        newsHeadlinesMutable.postValue(response);
    }

    private void setShowErrorMutable(Boolean showError) {
        this.showErrorMutable.postValue(showError);
    }

    private void setShowLoadingMutable(Boolean showLoading) {
        this.showLoadingMutable.postValue(showLoading);
    }
}
