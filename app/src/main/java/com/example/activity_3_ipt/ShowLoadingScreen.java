package com.example.activity_3_ipt;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ShowLoadingScreen {
    static LoadingDialog load;

    public void showDialog(Context c, Class<?> nextActivity){
        load = new LoadingDialog(c);
        load.show();
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                load.cancel();

                Intent nav = new Intent(c,nextActivity);
                c.startActivity(nav);
                ((AppCompatActivity)c).finish();
            }
        };
        h.postDelayed(r,3000);
    }
}
