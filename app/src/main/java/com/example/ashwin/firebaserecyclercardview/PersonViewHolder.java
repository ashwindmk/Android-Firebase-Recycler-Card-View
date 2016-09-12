package com.example.ashwin.firebaserecyclercardview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ashwin on 12/9/16.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public PersonViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setName(String name) {
        TextView textview_name = (TextView) mView.findViewById(R.id.name);
        textview_name.setText(name);
    }

    public void setAddress(String address) {
        TextView textview_address = (TextView) mView.findViewById(R.id.address);
        textview_address.setText(address);
    }

    public void setPhoto(String url) {
        ImageView imageview_photo = (ImageView) mView.findViewById(R.id.photo);
        new AsyncTaskLoadPhoto(imageview_photo).execute(url);
    }

}

