package com.example.hw3;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//basic adapter extending from RecyclerView.Adapter
//ViewHolder gives access to views
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    //Data set
    String[][] SubjectValues;
    //added click to old homework
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {void onClick(int position);}


    //Per developer.android.com
    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView layout;
        public ViewHolder(CardView v){

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

        // create a new view with CardView
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitems, parent, false);
        ViewHolder myHolder = new ViewHolder(v);
        return myHolder;

    }
    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(ViewHolder myHolder, final int position){
        CardView cardView = myHolder.layout;
        TextView title = cardView.findViewById(R.id.movie_title);
        TextView year = cardView.findViewById(R.id.movie_year);

        Context context = cardView.getContext();

        String[] movie = SubjectValues[position];
        title.setText(movie[0]);
        year.setText(movie[1]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount(){

        return SubjectValues.length;
    }
}