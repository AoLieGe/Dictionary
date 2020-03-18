package com.example.dictionary.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DictionaryViewItem(val id : Int, val word : String, val translation : String) : Parcelable