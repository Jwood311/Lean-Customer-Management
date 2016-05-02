package com.bignerdranch.android.leanclientmanagement;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class ViewAllCustomersActivity extends FragmentActivity{
    ClientDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new ClientDatabase(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            LoggedInFragment loggedFragment = new LoggedInFragment();

            loggedFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, loggedFragment).commit();

        }


        populateListView();

    }
    private  void populateListView() {
        Cursor cursor = db.getAccessibleHoard();

        String[] fromFieldNames = new String[]{ClientDatabase.KEY_CLIENT_NAME_COLUMN, ClientDatabase.KEY_CLIENT_ADDRESS_COLUMN};
        int[] toViewIds = new int[]{R.id.textViewClientName, R.id.textViewClientAddress};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.clientlist, cursor, fromFieldNames, toViewIds, 0);
        ListView mylist = (ListView) findViewById(R.id.listViewClients);
        mylist.setAdapter(myCursorAdapter);



       /* cursor.moveToFirst();
        long itemId = cursor.getLong(
                cursor.getColumnIndex(ClientDatabase.KEY_CLIENT_NAME_COLUMN)
        );*/
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
}
