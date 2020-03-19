package com.example.dictionary.entity.translate.provider;

import com.example.dictionary.entity.translate.YandexTranslatePost;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexTranslateRequest {
    @GET("/api/v1.5/tr.json/translate")
    Call<YandexTranslatePost> getTranslation(@Query("key") String apiKey,
                                             @Query("text") String textToTranslate,
                                             @Query("lang") String lang);
}
