package com.zeddysoft.movieappusingrxjava;

import android.app.Application;
import android.content.Context;

/**
 * Created by Azeez.Taiwo on 6/7/2017.
 */

public class App extends Application{

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        App.mContext = mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
    }
}
