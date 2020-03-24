package com.example.dictionary.app;

import com.example.dictionary.R;
import com.example.dictionary.app.dictionary.Item;

public class Utils {
    public static Integer validateAddDictionary(String dictionaryName, Language from, Language to) {
        if (dictionaryName.isEmpty()) {
            return R.string.dictionary_add_enter_name_msg;
        }
        if (from == to) {
            return R.string.dictionary_add_same_lang_msg;
        }

        return null;
    }

    public static Integer validateTranslation(String word, String translation, Item beforeEdit) {
        if (word.isEmpty()) {
            return R.string.translate_word_empty_msg;
        }

        if (translation.isEmpty()) {
            return R.string.translate_translation_empty_msg;
        }

        if (beforeEdit != null &&
                word.equals(beforeEdit.getWord()) &&
                translation.equals(beforeEdit.getTranslation())) {
            return R.string.translate_edit_result_same_msg;
        }

        return null;
    }
}
