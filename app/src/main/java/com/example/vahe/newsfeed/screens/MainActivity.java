package com.example.vahe.newsfeed.screens;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.service.MyJobService;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements ActivityView {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        View navigationHeaderView = navigationView.getHeaderView(0);
        navigationHeaderView.setOnClickListener(view -> {
            drawer.closeDrawer(GravityCompat.START);
        });

        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer);

        Intent startServiceIntent = new Intent(this, MyJobService.class);
        startService(startServiceIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPause() {
        super.onPause();
        scheduleJob();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        cancelAllJobs();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    public NavController getNavController() {
        return navController;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void scheduleJob() {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                MyJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(900 * 1000).setRequiredNetworkType(
                        JobInfo.NETWORK_TYPE_NOT_ROAMING)
                .setPersisted(true).build();
        jobScheduler.schedule(jobInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cancelAllJobs() {
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancelAll();
    }
}
