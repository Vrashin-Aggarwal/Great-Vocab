package com.vrashinoriginals.greatvocab;

/**
 * Created by vrashinaggarwal on 8/2/2017.
 */

public class Constants {

    static final String ROW_ID = "id";
    static final String WORD = "word";
    static final String MEANING = "meaning";
    static final String SENTENCE = "sentence";
    static final String SCUT = "scut";
    static final String B3 = "b3";
    static final String B8 = "b8";
    static final String M1 = "m1";
    static final String FAV = "fav";

    //DB PROPERTIES
    static final String DB_NAME="d_WORDS";
    static final String TB_NAME="d_WORDS_MASTER";
    static final int DB_VERSION='5';
    static final String CREATE_TB="CREATE TABLE d_WORDS_MASTER("
            + "word TEXT, meaning TEXT, sentence TEXT, scut TEXT, b3 INTEGER, b8 INTEGER, m1 INTEGER, fav INTEGER);";

        //id INTEGER PRIMARY KEY AUTOINCREMENT,
}
