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
    // constructor
    public RecyclerViewAdapter(String[][] subjects){
        SubjectValues = subjects;
    }
    // Created new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        // create a new view
        LinearLayout v = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems, parent, false);
        ViewHolder myHolder = new ViewHolder(v);
        return myHolder;

    }
    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position){
        // - get element from dataset at this position
        // - replace the contents of the view with that element
        TextView movie_title = myHolder.layout.findViewById(R.id.movie_title);
        TextView movie_year = myHolder.layout.findViewById(R.id.movie_year);
        TextView movie_url = myHolder.layout.findViewById(R.id.movie_url);
        TextView movie_review = myHolder.layout.findViewById(R.id.movie_review);
        movie_title.setText(SubjectValues[position][0]);
        movie_year.setText(SubjectValues[position][1]);
        movie_url.setText(SubjectValues[position][3]);
        movie_review.setText(SubjectValues[position][4]);
        //movie_url.setText(SubjectValues[position][2]);

    }

    @Override
    public int getItemCount(){

        return SubjectValues.length;
    }
}