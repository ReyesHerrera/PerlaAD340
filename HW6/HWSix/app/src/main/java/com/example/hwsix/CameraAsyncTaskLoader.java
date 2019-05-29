package com.example.hwsix;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class CameraAsyncTaskLoader  extends AsyncTaskLoader<String> {
    private String mUrl;

    CameraAsyncTaskLoader(Context context, String url){
        super(context);
        mUrl=url;

    }

    @Nullable
    @Override
    public String loadInBackground() {
        return null;
    }
}
