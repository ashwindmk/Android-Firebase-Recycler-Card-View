package com.example.ashwin.firebaserecyclercardview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(isNetworkAvailable())
        {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android-firebase-test-a2516.firebaseio.com/Person/");

            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new FirebaseRecyclerAdapter<Person, PersonViewHolder>(Person.class, R.layout.person_list_row, PersonViewHolder.class, ref) {
                @Override
                public void populateViewHolder(PersonViewHolder pvh, Person person, int position) {
                    pvh.setName(person.getName());
                    pvh.setAddress(person.getAddress());
                    pvh.setPhoto(person.getPhoto());
                    personList.add(person);
                }

            };

            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Person person = personList.get(position);
                    Toast.makeText(getApplicationContext(), person.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClick(View view, int position) {
                    Person person = personList.get(position);
                    Toast.makeText(getApplicationContext(), person.getName() + ", " + person.getAddress() + " is selected!", Toast.LENGTH_SHORT).show();
                }
            }));
        }
        else
        {
            TextView noInternet = (TextView) findViewById(R.id.no_internet_text);
            noInternet.setVisibility(View.VISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
