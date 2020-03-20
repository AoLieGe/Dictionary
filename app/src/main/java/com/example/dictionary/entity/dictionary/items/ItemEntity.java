package com.example.dictionary.entity.dictionary.items;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemEntity implements Parcelable {
    private Integer id;
    private String word;
    private String translation;

    public ItemEntity(Integer id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    private ItemEntity(Parcel parcel) {
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

    public static final Parcelable.Creator<ItemEntity> CREATOR = new Parcelable.Creator<ItemEntity>() {
        @Override
        public ItemEntity createFromParcel(Parcel source) {
            return new ItemEntity(source);
        }

        @Override
        public ItemEntity[] newArray(int size) {
            return new ItemEntity[size];
        }
    };
}
