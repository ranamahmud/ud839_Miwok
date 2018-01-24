package com.example.android.miwok;

/**
 * Created by Md Rana Mahmud on 1/18/2018.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwok;
    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;
    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;
    public Word(String mDefaultTranslation, String mMiwok, int audio_id) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwok = mMiwok;
        mAudioResourceId = audio_id;

    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audio_id) {
        mDefaultTranslation = defaultTranslation;
        mMiwok = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audio_id;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwok() {
        return mMiwok;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }
    public int getmAudioResourceId(){return mAudioResourceId;}
}
