package com.example.johny.boomapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import static java.security.AccessController.getContext;

/**
 * Created by johny on 23/10/2017.
 */

public final class Utilities {


    public void setTypeSignika (Context context, TextView textView, boolean bold, boolean light ) {

        Typeface tf;

        if ( bold ) tf = Typeface.createFromAsset(context.getAssets(), "fonts/Signika-SemiBold.ttf");
        else if ( light ) tf = Typeface.createFromAsset(context.getAssets(), "fonts/Signika-Light.ttf");
        else tf = Typeface.createFromAsset(context.getAssets(), "fonts/Signika-Regular.ttf");

        textView.setTypeface(tf);
    }


}
