package com.goal.sachin.goalassistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public NavigationDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<Data> getData(){

        List<Data> data = new ArrayList<>();

        String[] titles = {"Hello 1","Set Goal","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1","Hello 1"};
        int[] images = {R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};

        for (int i = 0; i < titles.length && i < images.length;i++){

            Data current = new Data();
            current.title = titles[i];
            current.id = images[i];

            data.add(current);
        }

        return data;
    }

    public void setUp(int viewId,DrawerLayout drawerLayout,Toolbar toolbar){

        containView = getActivity().findViewById(viewId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //mDrawerLayout.openDrawer(containView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //mDrawerLayout.closeDrawer(containView);
            }


        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //To display icon on toolbar for navigation drawer.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mDrawerToggle.syncState();
            }
        });
    }


}
