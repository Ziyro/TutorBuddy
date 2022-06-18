package com.safe.tutorbuddy;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> tutorNames;
    ArrayList<String> contacts;
    ArrayList<String> location;
    ArrayList<String> subjects;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> tutorNames, ArrayList<String> contacts, ArrayList<String> location, ArrayList<String> subjects) {
        this.context = context;
        this.tutorNames = tutorNames;
        this.contacts = contacts;
        this.location = location ;
        this.subjects = subjects;
    }

    //this method to inflate the layout item xml and pass it to the View Holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    //this will set the data to the UI
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        // set the texxt to the cards/rows on the view
        holder.name.setText(tutorNames.get(position));
        holder.contact.setText(contacts.get(position));
        holder.location.setText(location.get(position));
        holder.subject.setText(subjects.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, tutorNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return tutorNames.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, location, subject;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = itemView.findViewById(R.id.name);
            contact =  itemView.findViewById(R.id.contact);
            location =  itemView.findViewById(R.id.location);
            subject = itemView.findViewById(R.id.subject);

        }
    }
}