package com.example.hwsix;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.squareup.picasso.Picasso.*;

//basic adapter extending from RecyclerView.Adapter
//ViewHolder gives access to views
//from HW3 with modifications for HW6
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements LoaderManager.LoaderCallbacks<String> {
    //Dataset, BaseUrl for seattle.gov
    // different for each
    private TraficCamera[] cams;

    private String SeattleGovBaseUrl = " http://www.seattle.gov/trafficcams/images/";
    private String WashingtonStateBaseUrl = " http://images.wsdot.wa.gov/nw/";

    private Listener listener;

    public void setListener(Listener listener){
        //metiche
        this.listener = listener;
    }
    interface Listener{
        void onClick(int position);

    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

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
    public RecyclerViewAdapter(TraficCamera[] cams){
        this.cams = cams;
    }
    // Created new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_items, parent, false);

        return new ViewHolder(v);

    }
    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(ViewHolder myHolder, final int position){
        // - get element from dataset at this position
        // - replace the contents of the view with that element
        CardView v = myHolder.layout;

        TextView camLocation = myHolder.layout.findViewById(R.id.location_camera);
        ImageView imageView = myHolder.layout.findViewById(R.id.camera_image);

        Context context = v.getContext();

        TraficCamera cam = cams[position];
        camLocation.setText((cam.getLocation()));
        Picasso.get().load(cam.getUrl()).into(imageView);
        v.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                if(listener !=null){
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount(){

        return cams.length;
    }
}