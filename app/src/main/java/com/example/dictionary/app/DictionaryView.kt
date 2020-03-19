package com.example.dictionary.app

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DictionaryView(val name : String, val langFrom : Language, val langTo : Language) : Parcelable