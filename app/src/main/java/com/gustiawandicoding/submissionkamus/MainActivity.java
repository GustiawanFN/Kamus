package com.gustiawandicoding.submissionkamus;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gustiawandicoding.submissionkamus.adapter.EnglishAdapter;
import com.gustiawandicoding.submissionkamus.adapter.IndAdapter;
import com.gustiawandicoding.submissionkamus.db.KamusHelper;
import com.gustiawandicoding.submissionkamus.model.EngModel;
import com.gustiawandicoding.submissionkamus.model.IndModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final static String SELECTED_TAG = "selected_index";
    private static int selectedIndex;

    private EnglishAdapter englishAdapter;
    private ArrayList<EngModel> engModels;

    private IndAdapter indAdapter;
    private ArrayList<IndModel> indModels;

    private KamusHelper kamusHelper;

    private RecyclerView recyclerView;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        kamusHelper = new KamusHelper(this);
        englishAdapter= new EnglishAdapter(this);
        indAdapter = new IndAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(englishAdapter);
        kamusHelper.open();
        indModels = kamusHelper.getAllDataINA();
        engModels = kamusHelper.getAllDataENG();
        kamusHelper.close();
        englishAdapter.addItem(engModels);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null){
            navigationView.getMenu().findItem(savedInstanceState.getInt(SELECTED_TAG)).setChecked(true);

            if (savedInstanceState.getInt(SELECTED_TAG) == R.id.english){
                toolbar.setTitle(R.string.English_indo);
                recyclerView.setAdapter(englishAdapter);
                englishAdapter.addItem(engModels);

            }else if (savedInstanceState.getInt(SELECTED_TAG) == R.id.indo){
                toolbar.setTitle(R.string.indo_eng);
                recyclerView.setAdapter(indAdapter);
                indAdapter.addItem(indModels);
            }
            return;
        }
        selectedIndex = R.id.english;
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
    public boolean onCreateOptionsMenu(Menu menu) { //pencarian
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        final MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setMaxWidth(R.dimen.search_max_width);
        searchView.setQueryHint(getResources().getString(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                kamusHelper.open();

                engModels = kamusHelper.getDataByNameENG(newText);
                indModels = kamusHelper.getDataByNameINA(newText);
                kamusHelper.close();

                if (selectedIndex == R.id.english){
                    recyclerView.setAdapter(englishAdapter);
                    englishAdapter.addItem(engModels);
                }else if (selectedIndex == R.id.indo){
                    recyclerView.setAdapter(indAdapter);
                    indAdapter.addItem(indModels);
                }
                return false;
            }
        });
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.english:
                if (!item.isChecked()){
                    selectedIndex = R.id.english;
                    item.setChecked(true);
                    toolbar.setTitle(R.string.English_indo);
                    recyclerView.setAdapter(englishAdapter);
                    englishAdapter.addItem(engModels);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;

            case R.id.indo :
                if (!item.isChecked()){
                    selectedIndex = R.id.indo;
                    item.setChecked(true);
                    toolbar.setTitle(R.string.indo_eng);
                    recyclerView.setAdapter(indAdapter);
                    indAdapter.addItem(indModels);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;

    }
}
