package com.bignerdranch.android.leanclientmanagement;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Home extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            LoggedInFragment loggedFragment = new LoggedInFragment();
            CusotmerOptionFragment customerOptionFragment = new CusotmerOptionFragment();
            SessionOptionFragment sessionOptionFragment = new SessionOptionFragment();


            loggedFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, loggedFragment).commit();

            customerOptionFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.customer_container, customerOptionFragment).commit();

            sessionOptionFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.session_container, sessionOptionFragment).commit();
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

    public void openNewCustomer(View view) {
        Intent intent = new Intent(this, NewCustomerActivity.class);
        startActivity(intent);
    }

    public void openViewCustomers(View view) {
        Intent intent = new Intent(this, ViewAllCustomersActivity.class);
        startActivity(intent);
    }

    public void openNewSession(View view){
        Intent intent = new Intent(this, NewSessionActivity.class);
        startActivity(intent);
    }

    public void openViewSessions(View view) {
        Intent intent = new Intent(this, ViewAllSessionsActivity.class);
        startActivity(intent);
    }

}
