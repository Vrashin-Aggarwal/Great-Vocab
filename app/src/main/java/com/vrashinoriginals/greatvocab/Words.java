package com.vrashinoriginals.greatvocab;

/**
 * Created by vrashinaggarwal on 8/2/2017.
 */

public class Words {

    private int id;
    private String word;
    private String meaning;
    private String sentence;
    private String scut;
    private int b3;
    private int b8;
    private int m1;
    private int fav;

    public Words(/*int id,*/ String word, String meaning, String sentence, String scut,  int fav) {
        //this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.scut = scut;
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getScut() {
        return scut;
    }

    public void setScut(String scut) {
        this.scut = scut;
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        this.b3 = b3;
    }

    public int getB8() {
        return b8;
    }

    public void setB8(int b8) {
        this.b8 = b8;
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
