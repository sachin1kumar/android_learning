package com.recycler.sachin.gridrecyclerview;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycler.sachin.gridrecyclerview.helper.GetterSetter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by SACHIN on 01-May-16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<GetterSetter> data= Collections.emptyList();
    private Context context;



    public Adapter(Context context,List<GetterSetter> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }


    @Override
    public  Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.subcat_cell_layout,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        GetterSetter myData = data.get(position);
        holder.textView.setText(myData.title);
        //holder.imageView.setImageResource(myData.id);

        /*OkHttpClient okHttpClient = new OkHttpClient();
        File customCacheDirectory = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/MyCache");
        okHttpClient.setCache(new Cache(customCacheDirectory, Integer.MAX_VALUE));
        OkHttpDownloader okHttpDownloader = new OkHttpDownloader(okHttpClient);
        Picasso picasso = new Picasso.Builder(context).downloader(okHttpDownloader).build();*/
        //picasso.load(imageURL).into(viewHolder.image);

        Picasso mBuilder = new Picasso.Builder(context)
                .loggingEnabled(BuildConfig.DEBUG)
                .indicatorsEnabled(BuildConfig.DEBUG)
                .downloader(new OkHttpDownloader(context, 30000))
                .build();

        mBuilder.load(myData.id).into(holder.imageView);

        /*Picasso.with(context)
                .load(myData.id)
                .into(holder.imageView);*/
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {

            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.subcattext_id);
            imageView = (ImageView) itemView.findViewById(R.id.subcatimage_id);

        }
    }
}
