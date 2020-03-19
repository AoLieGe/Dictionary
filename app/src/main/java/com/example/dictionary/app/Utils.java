package com.example.dictionary.app;

import android.widget.Button;

import com.example.dictionary.R;

public class Utils {
    public static final int TRANSLATE_ACTIVITY_REQUEST_ADD = 1;
    public static final int TRANSLATE_ACTIVITY_REQUEST_EDIT = 2;
    public static final String TRANSLATE_MODE_TAG = "TranslateMode";
    public static final String TRANSLATE_ADD_TAG = "TranslateAdd";
    public static final String TRANSLATE_EDIT_TAG = "TranslateEdit";
    public static final String TRANSLATE_BEFORE_EDIT_TAG = "TranslateBeforeEdit";
    public static final String TRANSLATE_RESULT_TAG = "TranslateResult";
    public static final String DICTIONARY_NEW_TAG = "DictionaryNew";
    public static final String DICTIONARY_OPEN_TAG = "DictionaryOpen";

    public static Integer validateAddDictionary(String dictionaryName, Language from, Language to) {
        if (dictionaryName.isEmpty()) {
            return R.string.dictionary_add_enter_name_msg;
        }
        if (from == to) {
            return R.string.dictionary_add_same_lang_msg;
        }

        return null;
    }

    public static Integer validateTranslation(String word, String translation, DictionaryViewItem beforeEdit) {
        if (word.isEmpty()) {
            return R.string.translate_word_empty_msg;
        }

        if (translation.isEmpty()) {
            return R.string.translate_translation_empty_msg;
        }

        if (word.equals(beforeEdit.getWord()) &&
                translation.equals(beforeEdit.getTranslation())) {
            return R.string.translate_edit_result_same_msg;
        }

        return null;
    }
}
