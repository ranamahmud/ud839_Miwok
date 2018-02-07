package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer media;
    private AudioManager mAudioManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        mAudioManger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red,R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white,R.raw.color_white));
        ArrayAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list_colors);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                if(media!=null)
                    media.release();
                media = MediaPlayer.create(ColorsActivity.this,word.getmAudioResourceId());
                media.start();
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if(media!=null)
                            media.release();
                        media = null;
                    }
                });
            }
        });
        AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                        focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                    media.pause();
                    media.seekTo(0);
                }else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                    media.start();
                }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                    if(media!=null)
                        media.release();
                }

            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(media!=null)
            media.release();
        media = null;
    }
}
