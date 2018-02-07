package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer media;
    private AudioManager mAudioManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten,R.raw.number_ten));
        final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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

        ArrayAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list_numbers);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                if(media!=null)
                    media.release();


                int result = mAudioManger.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId());
                    media.start();
                }

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
        mAudioManger.abandonAudioFocus(audioFocusChangeListener);


    }
    @Override
    protected void onStop() {
        super.onStop();
        if(media!=null)
            media.release();
        media = null;
    }
}
