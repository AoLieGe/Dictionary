package com.example.dictionary.app.dictionary

import android.os.Parcelable
import com.example.dictionary.app.Language
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SheetItem(val name : String, val langFrom : Language, val langTo : Language) : Parcelable