package com.example.dictionary.entity.dictionary.sheet

import com.example.dictionary.app.Language

data class DictionaryDB(val id : Integer, val name : String, val langFrom : Language, val langTo : Language)