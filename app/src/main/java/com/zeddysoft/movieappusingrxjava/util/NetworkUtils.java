package com.zeddysoft.movieappusingrxjava.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;

import com.zeddysoft.movieappusingrxjava.App;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Azeez.Taiwo on 6/8/2017.
 */

public class NetworkUtils {


    public static boolean isPhoneConnectedToInternet() {

        Context context = App.getContext();

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
