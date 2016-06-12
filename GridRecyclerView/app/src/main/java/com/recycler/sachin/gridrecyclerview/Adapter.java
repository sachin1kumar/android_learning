package com.recycler.sachin.gridrecyclerview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
        File customCacheDirectory = null;
        OkHttpClient okHttpClient = new OkHttpClient();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if(PackageManager.PERMISSION_GRANTED== ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
             customCacheDirectory = new File(Environment.getExternalStorageDirectory(), "PicassoIM");
             if (!customCacheDirectory.exists())
                 customCacheDirectory.mkdirs();
            }
            else
                requestPermission(context);
        }
        okHttpClient.setCache(new Cache(customCacheDirectory, Integer.MAX_VALUE));
        OkHttpDownloader okHttpDownloader = new OkHttpDownloader(okHttpClient);
        Picasso picasso = new Picasso.Builder(context).downloader(okHttpDownloader).build();
         picasso.load(myData.id).into(holder.imageView);

        /*Picasso.with(context)
                .load(myData.id)
                .into(holder.imageView);*/
    }

    private  void requestPermission(final Context context){

        final int REQUEST_WRITE_EXTERNAL_STORAGE=1;
        if(ActivityCompat.shouldShowRequestPermissionRationale((Activity)context,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.

            new AlertDialog.Builder(context)
                    .setMessage("Allow write permission")
                    .setPositiveButton("Allow now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    }).show();

        }else {
            // permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions((Activity)context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }
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
