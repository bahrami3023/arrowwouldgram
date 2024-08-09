package com.arrowwould.arrowwouldgram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.arrowwould.arrowwouldgram.Search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;

import  com.arrowwould.arrowwouldgram.Like.LikeFragment;
import  com.arrowwould.arrowwouldgram.Post.PostActivity;
import  com.arrowwould.arrowwouldgram.Profile.ProfileFragment;
import  com.arrowwould.arrowwouldgram.Search.SearchFragment;
import com.arrowwould.arrowwouldgram.Utils.UniversalImageLoader;
import  com.arrowwould.arrowwouldgram.home.HomeFragment;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigationView = findViewById(R.id.insta_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        if (name != null){
            loadfragment(new HomeFragment());

        }else{
            loadfragment(new HomeFragment());

        }

    }
    private boolean loadfragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();
        if (id ==R.id.Home){
            fragment = new HomeFragment();
        }
        else
            if (id ==R.id.search){
                fragment = new SearchFragment();
            }
            else
            if (id ==R.id.post){
                startActivity(new Intent(Home.this, PostActivity.class));


            }
            else
            if (id ==R.id.likes){
                fragment = new LikeFragment();
            }
            else
            if (id ==R.id.profile){
                fragment = new ProfileFragment();

            }
        //switch (item.getItemId()) {
            //case R.id.Home:
                //fragment = new HomeFragment();
                //break;

          //  case R.id.search:
                //fragment = new SearchFragment();
              //  break;

            //case R.id.post:
                //fragment = null;
                //startActivity(new Intent(Home.this, PostActivity.class));
              //  break;
            //case R.id.likes:
            //    fragment = new LikeFragment();
          //      break;
        //    case R.id.profile:
      //          fragment = new ProfileFragment();
              //  break;
    //    }
        return loadfragment(fragment);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


}