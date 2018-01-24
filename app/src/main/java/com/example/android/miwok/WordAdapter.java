package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Md Rana Mahmud on 1/21/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    private final int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0,words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }
        Word currentWord = getItem(position);
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwok());
        ImageView imageView = listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()==true){

            imageView.setImageResource(currentWord.getmImageResourceId());
        }else {
            imageView.setVisibility(View.GONE);

        }
        View text_container = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        text_container.setBackgroundColor(color);

        return listItemView;
    }
}
