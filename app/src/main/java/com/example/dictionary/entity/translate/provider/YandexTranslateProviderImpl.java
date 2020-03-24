package com.example.dictionary.entity.translate.provider;

import com.example.dictionary.entity.translate.YandexTranslatePost;

import io.reactivex.Observable;
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(YandexTranslateRequest.class);
    }

    @Override
    public Observable<YandexTranslatePost> translate(String word, String langFrom, String langTo) {
        return translateRequest.getTranslation(API_KEY,
                word,
                langTo)
                .doOnNext(yandexTranslatePost -> yandexTranslatePost.setWord(word));
    }
}
