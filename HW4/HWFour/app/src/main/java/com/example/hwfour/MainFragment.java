package com.example.hwfour;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public interface OnNextClickListener{
        void OnMainFragmentNextClick(MainFragment fragment);

    }
    OnNextClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnNextClickListener) context;
        }catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnMainFragmentNExtClick'");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button nextBtn = this.getView().findViewById(R.id.fragment_main_next);
        nextBtn.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (listener != null) {
            listener.OnMainFragmentNextClick(this);

        }
    }


}
