package com.vrashinoriginals.greatvocab;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.vrashinoriginals.greatvocab.R.id.favtxt;

/**
 * Created by vrashinaggarwal on 8/3/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Words> words;

    //TextView sentencetxt = new TextView(R.id.sentencetxt);

    public MyAdapter(Context c, ArrayList<Words> words) {
        this.c = c;
        this.words = words;
    }
    //INITIALIZE VIEWHODER
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        //HOLDER
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    //BIND VIEW TO DATA
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        //holder.img.setImageResource(R.drawable.marker);
        holder.wordtxt.setText(words.get(position).getWord());
        holder.meantxt.setText(words.get(position).getMeaning());
        holder.sentencetxt.setText(words.get(position).getSentence());
        holder.scuttxt.setText(words.get(position).getScut());
        holder.favtxt.setText(Integer.toString(words.get(position).getFav()));
        if(words.get(position).getFav() == 1){
            holder.chkbox.setChecked(true);
        }else{
            holder.chkbox.setChecked(false);
        }



        holder.chkbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;

                Toast.makeText(view.getContext(),Boolean.toString(cb.isChecked()),Toast.LENGTH_SHORT).show();
                DBAdapter db=new DBAdapter(view.getContext());
                Log.d("loggingchk","inside chkbox");
                db.openDB();
                if (cb.isChecked()){
                    Log.d("loggingchk","inside chkbox if");
                    db.updateFav(words.get(position).getWord().toString(),1);}
                else
                    db.updateFav(words.get(position).getWord().toString(),0);
            }
        });

        //CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Snackbar.make(v,words.get(pos).getWord(),Snackbar.LENGTH_SHORT).show();
                //TransitionManager.beginDelayedTransition(cardview);
                //sentencetxt.setVisibility(View.VISIBLE);


            }
        });
    }
    @Override
    public int getItemCount() {
        return words.size();
    }
}