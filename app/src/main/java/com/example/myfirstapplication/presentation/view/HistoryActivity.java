package com.example.myfirstapplication.presentation.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.R;
import com.squareup.picasso.Picasso;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getIncomingIntent();
    }

    private void getIncomingIntent() {

        if(getIntent().hasExtra("region image") && getIntent().hasExtra("region story")
                && getIntent().hasExtra("country") && getIntent().hasExtra("legend 1")
                && getIntent().hasExtra("pic 1") && getIntent().hasExtra("legend 2")
                && getIntent().hasExtra("pic 2") && getIntent().hasExtra("legend 3")) {

            String regionImage = getIntent().getStringExtra("region image");
            String regionStory = getIntent().getStringExtra("region story");
            String country = getIntent().getStringExtra("country");
            String lg1 = getIntent().getStringExtra("legend 1");
            String pic1 = getIntent().getStringExtra("pic 1");
            String lg2 = getIntent().getStringExtra("legend 2");
            String pic2 = getIntent().getStringExtra("pic 2");
            String lg3 = getIntent().getStringExtra("legend 3");

            set(regionImage, regionStory, country, lg1, pic1, lg2, pic2, lg3);
        }
    }

    private void set(String regionImage, String regionStory,String country, String lg1, String pic1, String lg2, String pic2, String lg3) {

       TextView story = findViewById(R.id.story);
       story.setText(regionStory);

       ImageView image = findViewById(R.id.regionPicture);
       Picasso.with(this)
               .load(regionImage)
               .into(image);

       TextView countryLeg = findViewById(R.id.country);
       countryLeg.setText(country);

       TextView pokeLegend1 = findViewById(R.id.legendary1);
       pokeLegend1.setText(lg1);

       ImageView picLegend1 = findViewById(R.id.legendPic1);
       Picasso.with(this)
                .load(pic1)
                .into(picLegend1);

        TextView pokeLegend2 = findViewById(R.id.legendary2);
        pokeLegend2.setText(lg2);

        ImageView picLegend2 = findViewById(R.id.legendPic2);
        Picasso.with(this)
                .load(pic2)
                .into(picLegend2);

        TextView pokeLegend3 = findViewById(R.id.legendary3);
        pokeLegend3.setText(lg3);

    }

}
