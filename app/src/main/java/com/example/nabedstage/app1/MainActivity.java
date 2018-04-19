package com.example.nabedstage.app1;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView mGivenName;
    TextView mFamilyName;
    TextView mFullName;
    ImageView mProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);

        mGivenName = (TextView) findViewById(R.id.givenName);
        mFamilyName = (TextView) findViewById(R.id.familyName);
        mFullName = (TextView) findViewById(R.id.fullName);
        mProfileView = (ImageView) findViewById(R.id.profileImage);

        String fullName = getIntent().getStringExtra("fullName");
        String givenName = getIntent().getStringExtra("givenName");
        String familyName = getIntent().getStringExtra("familyName");
        String imageUrl = getIntent().getStringExtra("imageUrl");


        Log.d("nassim", fullName);
        Log.d("nassim", givenName);
        Log.d("nassim", familyName);
        Log.d("nassim", imageUrl);


        mGivenName.setText(fullName);
        mFamilyName.setText(givenName);
        mFullName.setText(familyName);
        mProfileView.setImageURI(Uri.parse(imageUrl));*/

        Toast.makeText(this, "I'm alive", Toast.LENGTH_LONG).show();
        finish();



    }
}
