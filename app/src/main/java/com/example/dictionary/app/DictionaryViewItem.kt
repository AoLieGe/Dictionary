package com.example.dictionary.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DictionaryViewItem(val word : String, val translation : String) : Parcelable, Comparable<DictionaryViewItem> {
    override fun compareTo(other: DictionaryViewItem): Int {
        return word.compareTo(other.word);
    }
}