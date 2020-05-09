package com.example.myfirstapplication.presentation.controller;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.presentation.view.HistoryActivity;
import com.squareup.picasso.Picasso;

public class HistoryController {

    private HistoryActivity view;

    public HistoryController(HistoryActivity historyActivity) {
        this.view = historyActivity;
    }

    public void onGetIncomingIntent(HistoryActivity view){
        if(view.getIntent().hasExtra("region image") && view.getIntent().hasExtra("region story")
                && view.getIntent().hasExtra("country") && view.getIntent().hasExtra("legend 1")
                && view.getIntent().hasExtra("pic 1") && view.getIntent().hasExtra("legend 2")
                && view.getIntent().hasExtra("pic 2") && view.getIntent().hasExtra("legend 3")) {

            String regionImage = view.getIntent().getStringExtra("region image");
            String regionStory = view.getIntent().getStringExtra("region story");
            String country = view.getIntent().getStringExtra("country");
            String lg1 = view.getIntent().getStringExtra("legend 1");
            String pic1 = view.getIntent().getStringExtra("pic 1");
            String lg2 = view.getIntent().getStringExtra("legend 2");
            String pic2 = view.getIntent().getStringExtra("pic 2");
            String lg3 = view.getIntent().getStringExtra("legend 3");

            set(regionImage, regionStory, country, lg1, pic1, lg2, pic2, lg3);
        }
    }

    private void set(String regionImage, String regionStory,String country, String lg1, String pic1, String lg2, String pic2, String lg3) {

        TextView story = view.findViewById(R.id.story);
        story.setText(regionStory);

        ImageView image = view.findViewById(R.id.regionPicture);
        Picasso.with(view)
                .load(regionImage)
                .into(image);

        TextView countryLeg = view.findViewById(R.id.country);
        countryLeg.setText(country);

        TextView pokeLegend1 = view.findViewById(R.id.legendary1);
        pokeLegend1.setText(lg1);

        ImageView picLegend1 = view.findViewById(R.id.legendPic1);
        Picasso.with(view)
                .load(pic1)
                .into(picLegend1);

        TextView pokeLegend2 = view.findViewById(R.id.legendary2);
        pokeLegend2.setText(lg2);

        ImageView picLegend2 = view.findViewById(R.id.legendPic2);
        Picasso.with(view)
                .load(pic2)
                .into(picLegend2);

        TextView pokeLegend3 = view.findViewById(R.id.legendary3);
        pokeLegend3.setText(lg3);

    }
}
