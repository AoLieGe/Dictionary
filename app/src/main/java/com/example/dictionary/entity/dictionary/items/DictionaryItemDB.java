package com.example.dictionary.entity.dictionary.items;

import android.os.Parcel;
import android.os.Parcelable;

public class DictionaryItemDB implements Parcelable {
    private Integer id;
    private String word;
    private String translation;

    public DictionaryItemDB(Integer id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    private DictionaryItemDB(Parcel parcel) {
        this.id = parcel.readInt();
        this.word = parcel.readString();
        this.translation = parcel.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(translation);
    }

    public static final Parcelable.Creator<DictionaryItemDB> CREATOR = new Parcelable.Creator<DictionaryItemDB>() {
        @Override
        public DictionaryItemDB createFromParcel(Parcel source) {
            return new DictionaryItemDB(source);
        }

        @Override
        public DictionaryItemDB[] newArray(int size) {
            return new DictionaryItemDB[size];
        }
    };
}
