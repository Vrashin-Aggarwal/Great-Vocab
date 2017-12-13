package com.vrashinoriginals.greatvocab;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.vrashinoriginals.greatvocab.R.drawable.ic_drawer;

public class MainActivity extends AppCompatActivity implements WordCards.OnFragmentInteractionListener, WordList.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    //private String[] mTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> mWordsTitles;
    private ActionBarDrawerToggle mDrawerToggle;

    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar();
        toolbar.setTitle("GREat Vocab");
        //getSupportActionBar().setTitle("GREat Vocab");
        Log.i("onCreate", "onCreate");
        //mTitles = getResources().getStringArray(R.array.planets_array);
        //mWordsTitles = new ArrayList<String>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);
        /*mWordsTitles.add("Barron's 300");
        mWordsTitles.add("Manhattan 500");
        mWordsTitles.add("Barron's 800");
        mWordsTitles.add("Dictionary");*/
        Log.d("onCreate2", "onCreate2");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);



        Log.d("onCreate3", "onCreate3");

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, mWordsTitles));
        //mDrawerLayout.setDrawerListener(mDrawerToggle);
        Log.d("onCreate2", "onCreate4");
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupDrawer();
    }

    private void setupDrawer() {
        Log.d("setupDrawer", "setupDrawer");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("opened", "");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_barron300) {
            // Handle the camera action
            Log.d("300", String.valueOf(id));
        } else if (id == R.id.nav_barron800) {
            Log.d("800", String.valueOf(id));

        } else if (id == R.id.nav_manhattan1000) {
            Log.d("1000", String.valueOf(id));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WordList(), "List");
        adapter.addFragment(new WordCards(), "Cards");
        viewPager.setAdapter(adapter);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}

