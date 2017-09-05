package com.zeddysoft.movieappusingrxjava.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Azeez.Taiwo on 27/8/2017.
 */

public class MovieFilter {
    public static final int POPULAR = 0;
    public static final int HIGH_RATED = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({POPULAR, HIGH_RATED})
    public @interface movieFilter{};


}
