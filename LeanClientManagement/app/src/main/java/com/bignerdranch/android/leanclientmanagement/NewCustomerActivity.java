package com.bignerdranch.android.leanclientmanagement;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Josh on 4/8/2016.
 */
public class NewCustomerActivity extends FragmentActivity {
    ClientDatabase db;
    EditText newClientName;
    EditText newClientAddress;
    EditText newClientPhone;
    EditText newClientBilling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            LoggedInFragment loggedFragment = new LoggedInFragment();

            loggedFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, loggedFragment).commit();

        }
        newClientName = (EditText)findViewById(R.id.addName);
        newClientAddress = (EditText)findViewById(R.id.addAddress);
        newClientPhone = (EditText)findViewById(R.id.addPhone);
        newClientBilling = (EditText)findViewById(R.id.addBilling);


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

    public void createClient(View view){
           int flag = 0;
           String errorMessage = "";

            if (newClientName.getText().toString() == "Add A Name"){flag = 1;};
            if (newClientAddress.getText().toString() == ""){flag = 2;};
            if (newClientPhone.getText().toString() == ""){flag = 3;};
            if (newClientBilling.getText().toString() == ""){flag = 4;};

            if (flag == 0) {
                ClientDatabase db = new ClientDatabase(getBaseContext());
                db.addNewClient(newClientName.getText().toString(), newClientAddress.getText().toString(), newClientPhone.getText().toString(), newClientBilling.getText().toString());
                Toast toast = Toast.makeText(getBaseContext(), "Client Created", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            } else {
                switch (flag) {
                    case 1: errorMessage = "Please add a name";
                    case 2: errorMessage = "Please add an address";
                    case 3: errorMessage = "Please add a phone number";
                    case 4: errorMessage = "Please add billing info";
                        default: errorMessage = "Please check all fields and try again.";

                }
                Toast toast = Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT);
                toast.show();

            }

    }

    public void cancelClient(View view){
        Toast toast = Toast.makeText(getBaseContext() , "Session Cancelled", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}

