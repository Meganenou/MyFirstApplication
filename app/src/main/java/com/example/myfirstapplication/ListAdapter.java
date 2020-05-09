package com.example.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Region> values;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        ImageView image;
        View layout;
        LinearLayout linearLayout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            image = (ImageView) v.findViewById(R.id.icon_List);
            linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Region> myDataset, Context context) {
        this.values = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Region currentRegion = values.get(position);

        holder.txtHeader.setText(currentRegion.getName());

        holder.txtFooter.setText(currentRegion.getDescription());

        Picasso.with(context)
                .load(currentRegion.getImage())
                .into(holder.image);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Exploring region ... " + currentRegion.getName() + " ! ", Toast.LENGTH_LONG).show();
                if (position != 7) {

                    Intent intent = new Intent(context, HistoryActivity.class);
                    intent.putExtra("region image", currentRegion.getImage());
                    intent.putExtra("region story", currentRegion.getStory());
                    intent.putExtra("country", currentRegion.getCountry());
                    intent.putExtra("legend 1", currentRegion.getLegendary_primary());
                    intent.putExtra("pic 1", currentRegion.getPrimary_pic());
                    intent.putExtra("legend 2", currentRegion.getLegendary_secondary());
                    intent.putExtra("pic 2", currentRegion.getSecondary_pic());
                    intent.putExtra("legend 3", currentRegion.getLegendary_fabulous());

                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, " Coming soon ...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}