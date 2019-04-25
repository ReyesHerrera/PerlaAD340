package com.example.hw3;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

//basic adapter extending from RecyclerView.Adapter
//ViewHolder gives access to views
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    //Dataset
    String[][] SubjectValues;

    //Per developer.android.com
    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout layout;
        public ViewHolder(LinearLayout v){

            super(v);
            layout = v;

        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        TextView movie_title = holder.layout.findViewById(R.id.movie_title);
        TextView movie_year = holder.layout.findViewById(R.id.movie_year);
        TextView movie_review = holder.layout.findViewById(R.id.movie_review);
        movie_title.setText(SubjectValues[position][0]);
        movie_year.setText(SubjectValues[position][1]);
        movie_review.setText(SubjectValues[position][4]);
    }
    public RecyclerViewAdapter(String[][] subjects){
        SubjectValues = subjects;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){


        LinearLayout v = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }


    @Override
    public int getItemCount(){

        return SubjectValues.length;
    }
}