package com.example.phonedialerusingintents;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView moodImageView,locationImageView,webImageView,phoneImageView;
    TextView textView;
    String name = "",number ="",mood ="",web = "",location = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moodImageView = (ImageView)findViewById(R.id.MoodImageView);
        locationImageView = (ImageView)findViewById(R.id.LocationImageView);
        webImageView = (ImageView)findViewById(R.id.WebImageView);
        phoneImageView = (ImageView)findViewById(R.id.PhoneImageView);

        //since initially all the imageView will be hidden. They will only comeBack only when this activity comes back from the data taking activity

        moodImageView.setVisibility(View.INVISIBLE);
        locationImageView.setVisibility(View.INVISIBLE);
        webImageView.setVisibility(View.INVISIBLE);
        phoneImageView.setVisibility(View.INVISIBLE);

        //since till the time the contacts are not visible they should be not working as it may happen though invisible but the user touches there by miskake. They will get opened.
        moodImageView.setEnabled(false);
        locationImageView.setEnabled(false);
        webImageView.setEnabled(false);
        phoneImageView.setEnabled(false);

        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateContact.class);
                startActivityForResult(intent,6);
            }
        });

        //for imageView too either set up the onClick Function or do like this using setOnClickListener
        locationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+location));
                startActivity(intent);
            }
        });
        webImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
                startActivity(intent);
            }
        });
        phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 6)
        {
            moodImageView.setVisibility(View.VISIBLE);
            locationImageView.setVisibility(View.VISIBLE);
            webImageView.setVisibility(View.VISIBLE);
            phoneImageView.setVisibility(View.VISIBLE);
            moodImageView.setEnabled(true);
            locationImageView.setEnabled(true);
            webImageView.setEnabled(true);
            phoneImageView.setEnabled(true);

            Button button = (Button)findViewById(R.id.button);
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);

            //getting the information out from the passed intent to this activity.
            name = data.getStringExtra("name");
            mood = data.getStringExtra("mood");
            location = data.getStringExtra("location");
            web = data.getStringExtra("web");
            number = data.getStringExtra("number");
            if(mood.equals("happy"))
            {
                moodImageView.setImageResource(R.drawable.happy);
            }
            else if(mood.equals("sad"))
            {
                moodImageView.setImageResource(R.drawable.sad);

            }
            else
            {
                moodImageView.setImageResource(R.drawable.okay_okay);
            }

            textView.setText(name);
        }
    }
}
