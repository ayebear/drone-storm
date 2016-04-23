package com.example.nirmesh.dronestorm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

/**
 * Created by Nirmesh on 4/23/2016.
 */
public class Splash extends ActionBarActivity {

    Handler handler;
   /* ImageView drone;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for no title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
      /*  drone = (ImageView)findViewById(R.id.drone);*/
      /* final  Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);*/
        //handler
        handler = new Handler();
        new Thread(new Mythread()).start();

    }
    class Mythread implements Runnable
    {

        @Override
        public void run() {

            handler.postDelayed(

                    new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            startActivity(i);
                            Splash.this.finish();
                        }
                    }


                    , 5000
            );
        }
    }


}
