package com.example.phonedialerusingintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener{

    EditText NameEditText,locationEditText,webEditText,phoneEditText;
    ImageView happy,sad,okay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        NameEditText = (EditText)findViewById(R.id.NameEditText);
        locationEditText = (EditText)findViewById(R.id.LocationEditText);
        webEditText = (EditText) findViewById(R.id.WebEditText);
        phoneEditText = (EditText)findViewById(R.id.PhoneEditText);

        happy = (ImageView)findViewById(R.id.HappyImageView3);
        sad = (ImageView)findViewById(R.id.SadImageView);
        okay = (ImageView)findViewById(R.id.OkayImageView);

        //since for the image view here we have to back to the main activity for the result i.e we are performing the same thing. Therefore we will do it in a different manner.
        //either set up the onClick Function(which i can do easily). Therefore try learn this method, which is new to you.
        /*
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        happy.setOnClickListener(this);
        sad.setOnClickListener(this);
        okay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(NameEditText.getText().toString().isEmpty() || locationEditText.getText().toString().isEmpty() || webEditText.getText().toString().isEmpty() ||phoneEditText.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter all the data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra("name",NameEditText.getText().toString());
            intent.putExtra("number",phoneEditText.getText().toString());
            intent.putExtra("location",locationEditText.getText().toString());
            intent.putExtra("web",webEditText.getText().toString());
            if(v.getId() == R.id.HappyImageView3)
            {
                intent.putExtra("mood","happy");
            }
            else if(v.getId() == R.id.OkayImageView)
            {
                intent.putExtra("mood","okay");
            }
            else
            {
                intent.putExtra("mood","sad");
            }
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
