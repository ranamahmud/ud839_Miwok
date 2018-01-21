package com.example.android.miwok;

/**
 * Created by Md Rana Mahmud on 1/18/2018.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwok;

    public Word(String mDefaultTranslation, String mMiwok) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwok = mMiwok;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwok() {
        return mMiwok;
    }
}
