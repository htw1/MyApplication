package com.example.htw.myapplication.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.htw.myapplication.Model.RecyclerNavDrawModel;
import com.example.htw.myapplication.R;
import com.example.htw.myapplication.adapter.NavDrawAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String KEY_USER_LEARN_DRAWER = "user_learn_drawer";
    private static final int DEFAULT_KEY = 0;
    public static String SHARE_PREF_NAME = "testpref";

    private NavDrawAdapter navDrawAdapter;
    private RecyclerView recyclerView;
    private ActionBarDrawerToggle InsideDrawerToggle;
    private View conteinerView;
    private DrawerLayout InsideDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean mfromSavedInstantedStated;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer= Boolean.valueOf( readStateOfNavigationDrawer(getActivity(), KEY_USER_LEARN_DRAWER,"false"));
        if (savedInstanceState!=null) {
        mUserLearnedDrawer=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerViewDrawer);
        navDrawAdapter=new NavDrawAdapter(getActivity(), getData());
        /*final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(navDrawAdapter);

        return layout;

    }

    public  List<RecyclerNavDrawModel> getData(){

        List <RecyclerNavDrawModel> dataArrayOfNavModel = new ArrayList <>();

        int  icon [] = {R.drawable.ic_group_black_24dp,R.drawable.ic_group_black_24dp, R.drawable.ic_group_black_24dp };
        String title [] = {"1", "2", "3"};

        for (int i=0; i<100; i++) {

            RecyclerNavDrawModel current = new RecyclerNavDrawModel();
            current.setIconID(icon [i%title.length]);
            current.setTitle(title[i%icon.length]);
            dataArrayOfNavModel.add(current);

        }
        return dataArrayOfNavModel;
    };

    public void delete (int position){

    }

    public void setUp(int fragmentId, DrawerLayout drawerLayoutFromMainActivity, Toolbar toolbar) {
        conteinerView=getActivity().findViewById(fragmentId);
        InsideDrawerLayout = drawerLayoutFromMainActivity;
        InsideDrawerToggle = new ActionBarDrawerToggle(getActivity(),  drawerLayoutFromMainActivity, toolbar, R.string.open, R.string.close )
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUserLearnedDrawer) {
                    mUserLearnedDrawer=true;
                    saveStateOfNavigationDrawer(getActivity(),KEY_USER_LEARN_DRAWER, mUserLearnedDrawer+"" );
                }
                getActivity().invalidateOptionsMenu();
                }



            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if(!mUserLearnedDrawer && !mfromSavedInstantedStated )
        {
            InsideDrawerLayout.openDrawer(conteinerView);
        }
        InsideDrawerLayout.addDrawerListener(InsideDrawerToggle);
        InsideDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                InsideDrawerToggle.syncState();;
            }
        });

    }


    public void saveStateOfNavigationDrawer (Context context, String prefName, String prefValue)
    {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(prefName, prefValue);
                    edit.apply();
    }

    public static String readStateOfNavigationDrawer (Context context, String prefsName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE );
        return sharedPreferences.getString(prefsName,defaultValue );
    }

}
