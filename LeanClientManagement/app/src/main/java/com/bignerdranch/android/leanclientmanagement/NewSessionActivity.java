package com.bignerdranch.android.leanclientmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Josh on 4/17/2016.
 */
public class NewSessionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            LoggedInFragment loggedFragment = new LoggedInFragment();

            loggedFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, loggedFragment).commit();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Log_Off_Item:
                Toast toast = Toast.makeText(getBaseContext() , R.string.logOff, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createSession(View view){
        Toast toast = Toast.makeText(getBaseContext() , "Session Created", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, ViewAllSessionsActivity.class);
        startActivity(intent);
    }

    public void cancelSession(View view){
        Toast toast = Toast.makeText(getBaseContext() , "Session Cancelled", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    }
