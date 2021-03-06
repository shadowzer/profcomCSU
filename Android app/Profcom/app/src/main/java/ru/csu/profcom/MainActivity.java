package ru.csu.profcom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.Space;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentQuestions.OnFragmentInteractionListener, FragmentPersonalArea.OnFragmentInteractionListener,
        FragmentNews.OnFragmentInteractionListener, FragmentCategories.OnFragmentInteractionListener {
    private UserInfoStorage userInfoStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfoStorage = new UserInfoStorage(MainActivity.this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = FragmentNews.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.FrameLayoutMain, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);

        TextView usernameTextView = (TextView) headerLayout.findViewById(R.id.drawerUsernameTextView);
        TextView namesTextView = (TextView) headerLayout.findViewById(R.id.drawerNameTextView);
        usernameTextView.setText(userInfoStorage.getUsername());
        namesTextView.setText(userInfoStorage.getNames());

        ImageView drawerAppIcon = (ImageView) headerLayout.findViewById(R.id.drawerAppIcon);
        Picasso.with(getApplicationContext())
                .load(R.drawable.app_icon)
                .transform(new CircularTransformation())
                .into(drawerAppIcon);

        ImageView userIcon = (ImageView) headerLayout.findViewById(R.id.drawerUserIcon);
        if (userInfoStorage.getUserAvatar() != null) {
            byte[] bytes = Base64.decode(userInfoStorage.getUserAvatar(), Base64.DEFAULT);
            SavePhotoTask savePhotoTask = new SavePhotoTask("profcomAvatar.jpg", getApplicationContext());
            savePhotoTask.execute(bytes);
            Picasso.with(getApplicationContext())
                    .load(new File(Environment.getExternalStorageDirectory().getPath() + "/profcomAvatar.jpg"))
                    .error(R.drawable.ic_launcher)
                    .resize(100, 100)
                    .transform(new CircularTransformation())
                    .into(userIcon);
        } else {
            Picasso.with(getApplicationContext())
                    .load(R.drawable.ic_launcher)
                    .resize(100, 100)
                    .transform(new CircularTransformation())
                    .into(userIcon);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            UserInfoStorage userInfoStorage = new UserInfoStorage(MainActivity.this);
            userInfoStorage.clear();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_news) {
            fragmentClass = FragmentNews.class;
        } else if (id == R.id.nav_personalarea) {
            fragmentClass = FragmentPersonalArea.class;
        } else if (id == R.id.nav_questions) {
            fragmentClass = FragmentQuestions.class;
        } else if (id == R.id.nav_categories) {
            fragmentClass = FragmentCategories.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.FrameLayoutMain, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
