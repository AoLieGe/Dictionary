package com.example.dictionary.app.dictionary

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val word : String, val translation : String) : Parcelable, Comparable<Item> {
    override fun compareTo(other: Item): Int {
        return word.compareTo(other.word);
    }
}