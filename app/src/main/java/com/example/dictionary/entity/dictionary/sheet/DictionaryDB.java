package com.example.dictionary.entity.dictionary.sheet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DictionaryDB {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String langFrom;
    private String langTo;

    public DictionaryDB(Integer id, String name, String langFrom, String langTo) {
        this.id = id;
        this.name = name;
        this.langFrom = langFrom;
        this.langTo = langTo;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangFrom() {
        return langFrom;
    }

    public void setLangFrom(String langFrom) {
        this.langFrom = langFrom;
    }

    public String getLangTo() {
        return langTo;
    }

    public void setLangTo(String langTo) {
        this.langTo = langTo;
    }
}
