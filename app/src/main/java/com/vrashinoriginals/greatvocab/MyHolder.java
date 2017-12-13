package com.vrashinoriginals.greatvocab;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vrashinaggarwal on 8/3/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView wordtxt, meantxt, sentencetxt, scuttxt, favtxt,meantextView,
            sentencetextView,
            scuttextView,
    favtextView;
    CheckBox chkbox;
    static int open = 0;

    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        wordtxt = (TextView) itemView.findViewById(R.id.wordtxt);
        meantxt = (TextView) itemView.findViewById(R.id.meantxt);
        sentencetxt = (TextView) itemView.findViewById(R.id.sentencetxt);
        scuttxt = (TextView) itemView.findViewById(R.id.scuttxt);
        favtxt = (TextView) itemView.findViewById(R.id.favtxt);
        meantextView=(TextView) itemView.findViewById(R.id.meantextView);
        sentencetextView=(TextView) itemView.findViewById(R.id.sentextView);
        scuttextView=(TextView) itemView.findViewById(R.id.scuttextView);
        favtextView=(TextView) itemView.findViewById(R.id.favtextView);
        chkbox = itemView.findViewById(R.id.star);
        //img= (ImageView) itemView.findViewById(R.id.playerImage);

        itemView.setOnClickListener(this);
    }

    public void checkFav(View view){

    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClick(v, getLayoutPosition());
        if (open == 0) {
            meantxt.setVisibility(View.VISIBLE);
            sentencetxt.setVisibility(View.VISIBLE);
            scuttxt.setVisibility(View.VISIBLE);
            favtxt.setVisibility(View.VISIBLE);
            meantextView.setVisibility(View.VISIBLE);
            sentencetextView.setVisibility(View.VISIBLE);
            scuttextView.setVisibility(View.VISIBLE);
            favtextView.setVisibility(View.VISIBLE);

            open = 1;
        } else {
            meantxt.setVisibility(View.GONE);
            sentencetxt.setVisibility(View.GONE);
            scuttxt.setVisibility(View.GONE);
            favtxt.setVisibility(View.GONE);
            meantextView.setVisibility(View.GONE);
            sentencetextView.setVisibility(View.GONE);
            scuttextView.setVisibility(View.GONE);
            favtextView.setVisibility(View.GONE);
            open = 0;
        }


    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
