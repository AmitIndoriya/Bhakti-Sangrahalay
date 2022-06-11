package com.bhakti_sangrahalay.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.fragment.DashboardCategoryFragment;
import com.google.android.material.navigation.NavigationView;

public class DashBoardActivity  extends BaseActivity implements View.OnClickListener {
    Resources resources;
    private NavigationView navigationView;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private TextView aartiTitleTV;
    private TextView sunderKandTV;
    private TextView chalishaTitleTV;
    private TextView kathaTitleTV;
    private TextView aartiViewAllTV;
    private TextView chalishaViewAllTV;
    private TextView navTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        }
        resources = getResources();
        setContentView(R.layout.dashboard_main);
        aartiTitleTV = findViewById(R.id.aarti_title);
        sunderKandTV = findViewById(R.id.sunserkaand_title);
        chalishaTitleTV = findViewById(R.id.chalisha_title);
        kathaTitleTV = findViewById(R.id.katha_title);
        aartiViewAllTV = findViewById(R.id.aarti_view_all);
        chalishaViewAllTV = findViewById(R.id.chalisha_view_all);
        //getSupportActionBar().setTitle(resources.getString(R.string.Dash_board));
        drawer =  findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        navTV =  navigationView.findViewById(R.id.textView);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpNavigationView();
        toolbar.setTitleTextColor(resources.getColor(R.color.white));
        getSupportActionBar().setTitle(resources.getString(R.string.Dash_board));
        DashboardCategoryFragment fragment1 = DashboardCategoryFragment.newInstance(1);
        DashboardCategoryFragment fragment2 = DashboardCategoryFragment.newInstance(2);
        DashboardCategoryFragment fragment3 = DashboardCategoryFragment.newInstance(3);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft1 = fm.beginTransaction();
        FragmentTransaction ft2 = fm.beginTransaction();
        FragmentTransaction ft3 = fm.beginTransaction();
        ft1.add(R.id.fragment1, fragment1).commit();
        ft2.add(R.id.fragment2, fragment2).commit();
        ft3.add(R.id.fragment3, fragment3).commit();
        LinearLayout linearLayout = findViewById(R.id.sunder_kand_container);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(DashBoardActivity.this, AudioAartiActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(DashBoardActivity.this, SunderKandActivity.class);
                startActivity(intent);
            }
        });
        setTypeface();
        aartiViewAllTV.setOnClickListener(this);
        chalishaViewAllTV.setOnClickListener(this);
    }

    private void setTypeface() {
        aartiTitleTV.setTypeface(boldTypeface);
        sunderKandTV.setTypeface(mediumTypeface);
        chalishaTitleTV.setTypeface(boldTypeface);
        kathaTitleTV.setTypeface(boldTypeface);
        aartiViewAllTV.setTypeface(mediumTypeface);
        chalishaViewAllTV.setTypeface(mediumTypeface);
        //navTV.setTypeface(mediumTypeface);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                /*int index;
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        index = 0;
                        break;
                    case R.id.nav_photos:
                        index = 1;
                        CURRENT_TAG = TAG_HOLIDAYS;
                        break;
                    case R.id.nav_movies:
                        index = 2;
                        CURRENT_TAG = TAG_EVENTS;
                        break;
                    case R.id.nav_notifications:
                        index = 3;
                        CURRENT_TAG = TAG_NOTES;
                        break;

                    default:
                        index = 0;
                        CURRENT_TAG = TAG_HOME;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);*/


                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.aarti_view_all:
                bundle.putInt("type", GlobalVariables.aarti);
                bundle.putString("title", resources.getString(R.string.aartiyan));
                break;
            case R.id.chalisha_view_all:
                bundle.putInt("type", GlobalVariables.chalisha);
                bundle.putString("title", resources.getString(R.string.chalisha));
                break;
        }

        Intent intent = new Intent(DashBoardActivity.this, MoreItemActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
