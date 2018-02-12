package com.by_mohammadelsayed.pinpoper.preferencesorsettings;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<String> texts;
    private Context context;
    private int fontSize;
    private Typeface font;

    public RecyclerAdapter(List<String> texts, Context context, Typeface fontFamily, int fontSize) {
        this.texts = texts;
        this.context = context;
        this.font = fontFamily;
        this.fontSize = fontSize;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(texts.get(position));
        holder.textView.setTypeface(font);
        holder.textView.setTextSize(fontSize);
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}
