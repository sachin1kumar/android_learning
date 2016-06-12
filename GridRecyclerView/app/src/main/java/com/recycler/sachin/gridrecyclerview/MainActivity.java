package com.recycler.sachin.gridrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.recycler.sachin.gridrecyclerview.helper.GetterSetter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new Adapter(this,getData()));



    }

    public static List<GetterSetter> getData(){

        List<GetterSetter> list = new ArrayList<>();

        String[] titles = {"Hello 1","Set Goal","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1"};
        String[] images = {"http://cache.netne.net/images/ic_add_circle_black_24dp.png","http://cache.netne.net/images/ic_content_paste_black_24dp.png","http://cache.netne.net/images/ic_report_black_24dp.png","http://cache.netne.net/images/ic_save_black_24dp.png","http://cache.netne.net/images/ic_send_black_24dp.png","http://cache.netne.net/images/ic_weekend_black_24dp.png","http://cache.netne.net/images/ic_add_circle_black_24dp.png","http://cache.netne.net/images/ic_add_circle_black_24dp.png","http://cache.netne.net/images/ic_add_circle_black_24dp.png","http://cache.netne.net/images/ic_add_circle_black_24dp.png"};

        for (int i = 0; i < titles.length && i < images.length; i++){

            GetterSetter getSet = new GetterSetter();
            getSet.id = images[i];
            getSet.title = titles[i];

            list.add(getSet);
        }
        return list;
    }
}
