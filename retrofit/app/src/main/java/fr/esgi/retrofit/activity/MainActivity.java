package fr.esgi.retrofit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.esgi.retrofit.R;
import fr.esgi.retrofit.fragment.GithubRepoFragment;
import fr.esgi.retrofit.fragment.GithubUserFragment;
import fr.esgi.retrofit.fragment.TabLayoutFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    public void buildGithubUserFragment(String username) {
        Fragment gitUserFragment = new GithubUserFragment().newInstance(username);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.listFragment, gitUserFragment);
        fm.commit();
    };

    public void buildGithubRepoFragment(String username) {
        Fragment gitRepoFragment = new GithubRepoFragment().newInstance(username);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.listFragment, gitRepoFragment);
        fm.commit();
    };

    public void buildTabLayout(String username) {
        Fragment pagerFragment = new TabLayoutFragment().newInstance(username);
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.listFragment, pagerFragment);
        fm.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        String username = getIntent().getStringExtra(InputActivity.GITHUB_NAME);
        buildGithubUserFragment(username);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String username = getIntent().getStringExtra(InputActivity.GITHUB_NAME);
        if (id == R.id.nav_repo) {
            buildGithubRepoFragment(username);
            closeOptionsMenu();
        } else if (id == R.id.nav_tab){
            buildTabLayout(username);
            closeOptionsMenu();
        } else if (id == R.id.nav_user){
            buildGithubUserFragment(username);
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}