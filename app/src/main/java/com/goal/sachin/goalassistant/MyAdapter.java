package com.goal.sachin.goalassistant;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goal.sachin.goalassistant.Fragments.SetGoalFragment;

import java.util.Collections;
import java.util.List;

/**
 * Created by SACHIN on 03-Apr-16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<Data> data= Collections.emptyList();
    private Context context;

    public MyAdapter(Context context,List<Data> data) {

       this.data = data;
       inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row,parent,false);

        context = parent.getContext();

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Data current = data.get(position);
        holder.textView.setText(current.title);
        holder.imageView.setImageResource(current.id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            switch (position)
            {
                case 1:

                    Fragment newFragment = new SetGoalFragment();
                    FragmentManager manager = context.getFragmentManager();
                   // FragmentTransaction transaction = .beginTransaction();

                    transaction.add(newFragment,"Set Goal");
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
            }
        }
    }
}
