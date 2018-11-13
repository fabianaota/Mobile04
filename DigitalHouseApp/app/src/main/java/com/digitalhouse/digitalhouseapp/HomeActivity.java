package com.digitalhouse.digitalhouseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.digitalhouse.digitalhouseapp.adapter.ViewPagerDigitalHouseAdapter;
import com.digitalhouse.digitalhouseapp.fragments.PeopleFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostDetailFragment;
import com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import com.digitalhouse.digitalhouseapp.model.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PostsFragment.ComunicacaoPostFragment {

    // Constantes que servem de chave para o bundle (comunicação de Activity com Fragment)
    public final static String POST_TITLE = "POST_TITLE";
    public final static String POST_DESCRIPTION = "POST_DESCRIPTION";

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Busca a toolbar. Atenção na hora de fazer o import. Devemos utilizar o toolbar de support
        // para que não haja incompatibilidade com versões antigas o Android
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup do navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Setup do navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Busca os dados que vieram no bundle (comunicação entre activities Login -> Home)
        TextView texto = navigationView.getHeaderView(0).findViewById(R.id.text_nav_header_user_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String emailDigitado = bundle.getString(LoginActivity.CHAVE_EMAIL);
        texto.setText(emailDigitado);

        // Setup do Tablayout e ViewPager
        TabLayout tabLayout = findViewById(R.id.tablayout_id);

        // criação dos fragments do Tablayout/ViewPager
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PostsFragment());
        fragmentList.add(new PeopleFragment());

        // criação dos titulos do Tablayout
        List<String> tituloList = new ArrayList<>();
        tituloList.add("Posts");
        tituloList.add("Pessoas");

        // Criação do adapter do view pager
        ViewPagerDigitalHouseAdapter adapter = new ViewPagerDigitalHouseAdapter(getSupportFragmentManager(), fragmentList, tituloList);

        viewPager = findViewById(R.id.viewpager_id);
        viewPager.setAdapter(adapter);

        // Associar o tablayout ao viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        // Quando o botão back for pressionado, fechar o navigation drawer (se ele estiver aberto)
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

        // Se algum item for selecionado no navigation drawer, exibir o fragment correspondente
        if (id == R.id.nav_posts) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_people) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_location){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

        // Adicionar ao backstack
        transaction.addToBackStack(null);

        // Efetivo a transacao
        transaction.commit();
    }
}
