package com.example.dictionary.entity.dictionary.items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String word;
    private String translation;

    public ItemEntity(int id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    private ItemEntity(Parcel parcel) {
        this.id = parcel.readInt();
        this.word = parcel.readString();
        this.translation = parcel.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
