package com.example.hwfour;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

//Followed in class demo for HostActivity, MainFragment and SecondFragment
// and matching layouts
public class HostActivity extends AppCompatActivity implements MainFragment.OnNextClickListener,
        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Host activity";


    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();

        //connecting the app bar items to the app bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //open and closer drawer are required
        drawer = findViewById((R.id.drawer_layout));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void swapFragments(Fragment fragment){
        Fragment newFragment;

        if(fragment instanceof MainFragment) {
            newFragment = new SecondFragment();
        }else{
            newFragment = new MainFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace((R.id.fragment_host), newFragment);
        transaction.addToBackStack((null));

        transaction.commit();
    }

    @Override
    public void OnMainFragmentNextClick(MainFragment fragment) {
        swapFragments(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(TAG, "Settings clicked");
                Toast toast = Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT);
                toast.show();
                return true;

            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Content Here";
                String shareSubject = "Subject here";

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(sharingIntent, "Share Using"));

                Log.i(TAG, "Share clicked");
                Toast toast1 = Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT);
                toast1.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @NonNull
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment newFragment;

        switch (menuItem.getItemId()) {
            case R.id.nav_code:
                Log.i(TAG, "code clicked");
                Toast toast = Toast.makeText(this, " Home Clicked", Toast.LENGTH_SHORT);
                toast.show();
                newFragment = new MainFragment();
                break;

            case R.id.nav_day_one:
                Log.i(TAG, "day one page");
                newFragment = new SecondFragment();
                break;

            case R.id.nav_help:
                Log.i(TAG, "help clicked");
                newFragment = new MainFragment();
                break;

            default:
                newFragment = new MainFragment();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_host, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();

        return false;
    }


}


