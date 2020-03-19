package com.example.dictionary.entity.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YandexTranslatePost {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private List<String> text;

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String key) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", lang=" + lang +
                '}';
    }
}
