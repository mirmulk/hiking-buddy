package com.menthoven.arduinoandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;

public class ImageChangingActivity extends Activity {
/*
    private Integer images[] = {R.drawable.waterdrop, R.drawable.waterdrop75, R.drawable.waterdrop50};
    private int currImage = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Constants.TAG, "AAAAAAAAAAAAAAAA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_message);

        setInitialImage();
        setImageRotateListener();
    }

    public void setImageRotateListener() {
        Log.d(Constants.TAG, "AAAAAAAAAA");
        final Button rotatebutton = (Button) findViewById(R.id.btnRotateImage);
        rotatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 3) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    public void setInitialImage() {
        Log.d(Constants.TAG, "AAAAAAAAAAAAAAA");
        setCurrentImage();
    }

    public void setCurrentImage() {
        Log.d(Constants.TAG, "AAAAAAAAAAAAAAr");
        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageResource(images[currImage]);

    }*/
}
