package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.fragments.PeopleFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostDetailFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import com.digitalhouse.digitalhouseapp.model.Post;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PostsFragment.ComunicacaoPostFragment {

    public final static String POST_TITLE = "POST_TITLE";
    public final static String POST_DESCRIPTION = "POST_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextView texto = navigationView.getHeaderView(0).findViewById(R.id.text_nav_header_user_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String emailDigitado = bundle.getString(LoginActivity.CHAVE_EMAIL);
        texto.setText(emailDigitado);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_posts) {
            replaceFragment(new PostsFragment());
        } else if (id == R.id.nav_people) {
            replaceFragment(new PeopleFragment());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_id, fragment);
        transaction.commit();

    }

    @Override
    public void goToFragmentDetails(Post post) {
        // Crio bundle
        Bundle bundle = new Bundle();

        // Coloco o titulo numa chave chamada POST_TITLE
        bundle.putString(POST_TITLE, post.getTitulo());

        // Coloco o titulo numa chave chamada POST_TITLE
        bundle.putString(POST_DESCRIPTION, post.getDescricao());

        // Crio FragmentManager e FragmentTransaction
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // Crio Post Detail Fragment
        PostDetailFragment detailFragment = new PostDetailFragment();

        // Defino que o argument é o bundle criado
        detailFragment.setArguments(bundle);

        // Através da transaction eu troco o fragment do container
        transaction.replace(R.id.container_id, detailFragment);

        // Efetivo a transacao
        transaction.commit();
    }
}
