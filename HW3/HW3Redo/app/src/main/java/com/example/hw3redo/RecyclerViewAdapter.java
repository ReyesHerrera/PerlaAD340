package com.example.hw3redo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.content.Intent;


//Moved intent here
//basic adapter extending from RecyclerView.Adapter
//ViewHolder gives access to views
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    //Data set
    String[][] SubjectValues;
    static final String TAG = "RecyclerViewAdapter";

    //added context to fixed old homework
    private Context context;
    static final String MOVIE = "movie";
    static final String YEAR = "year";
    static final String DIRECTOR = "director";
    static final String DESCRIPTION = "description";

    //Per developer.android.com
    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parent;
        TextView movie;
        TextView year;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.linearLayout);
            movie = itemView.findViewById(R.id.movie_title);
            year = itemView.findViewById(R.id.movie_year);

        }
    }
    //constructor, now takes Context context
    public RecyclerViewAdapter(Context context, String[][] movies) {
        this.context = context;
        this.SubjectValues = movies;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int n) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.movie.setText(SubjectValues[i][0]);
        viewHolder.year.setText(SubjectValues[i][1]);
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Movies.class);
                intent.putExtra(MOVIE,SubjectValues[i][0]);
                intent.putExtra(YEAR,SubjectValues[i][1]);
                intent.putExtra(DIRECTOR,SubjectValues[i][2]);
                intent.putExtra(DESCRIPTION,SubjectValues[i][4]);
                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return this.SubjectValues.length;

    }
}