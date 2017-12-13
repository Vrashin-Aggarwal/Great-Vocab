package com.vrashinoriginals.greatvocab;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WordList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WordList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Words> words = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WordList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WordList.
     */
    // TODO: Rename and change types and number of parameters
    public static WordList newInstance(String param1, String param2) {
        WordList fragment = new WordList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    private void retrieve()
    {
        //words.clear();
        DBAdapter db=new DBAdapter(getContext());
        db.openDB();
        //RETRIEVE
        Cursor c= db.getAllWords();
        //LOOP AND ADD TO ARRAYLIST
        //Log.d("logging",getString(c.getCount()));
        if(c != null && c.moveToFirst()){

            Log.d("logging","inside if");

            do
            {
                //c.moveToPosition(0);
                //Log.d("logging","inside while");
                //int id=c.getInt(c.getColumnIndex("ID"));
                String word=c.getString(c.getColumnIndex(Constants.WORD));
                String meaning=c.getString(c.getColumnIndex(Constants.MEANING));
                String sentence = c.getString(c.getColumnIndex(Constants.SENTENCE));
                //Log.d("logging","beforescut");
                String scut = c.getString(c.getColumnIndex(Constants.SCUT));
                int fav = c.getInt(c.getColumnIndex(Constants.FAV));
                /*Log.d("logging",word);
                Log.d("logging",meaning);
                Log.d("logging",sentence);
                Log.d("logging",scut);*/
                Words p= new Words(/*id,*/word,meaning,sentence,scut,fav);
                //ADD TO ARRAYLIS
                words.add(p);
            }while (c.moveToNext());
        }
        c.close();
        Log.d("retreive",words.toString());
        //CHECK IF ARRAYLIST ISNT EMPTY
        if(!(words.size()<1))
        {
            rv.setAdapter(adapter);
        }
        db.closeDB();;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootview = inflater.inflate(R.layout.fragment_word_list, container, false);


        rv= (RecyclerView) rootview.findViewById(R.id.mRecycler);
        //SET PROPS
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
        Log.d("oncreate",words.toString());
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter=new MyAdapter(getContext(),words);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        //RETRIEVE

        retrieve();
        return rootview;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
