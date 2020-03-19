package com.example.dictionary.entity.translate.provider;

import com.example.dictionary.app.Language;
import com.example.dictionary.entity.translate.YandexTranslatePost;

import java.io.IOException;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class YandexTranslateProviderImpl extends BaseYandexTranslateProvider
implements TranslateProvider<YandexTranslatePost> {

    private YandexTranslateRequest translateRequest;

    public YandexTranslateProviderImpl() {
        translateRequest = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YandexTranslateRequest.class);
    }

    private Response checkResponseResult(Response response) throws IOException {
        //TODO add some check code

        if (!response.isSuccessful())
            throw new IOException("Translate response isn't successful");

        return response;
    }

    @Override
    public Observable<YandexTranslatePost> translate(String word, String langFrom, String langTo) {
        return Observable.just(translateRequest.getTranslation(
                API_KEY,
                word,
                langTo))
                .map(Call::execute)
                .map(this::checkResponseResult)
                .retry()
                .map(response -> (YandexTranslatePost) response.body())
                .filter(response -> !response.getText().isEmpty())
                .doOnNext(post -> post.setWord(word));
    }
}
