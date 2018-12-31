package kn.beautynow.gui.fornecedor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import kn.beautynow.R;
import kn.beautynow.dominio.controller.Session;
import kn.beautynow.dominio.usuario.Usuario;
import kn.beautynow.gui.usuario.Login;
import kn.beautynow.gui.usuario.Perfil;

public class FornecedorMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Perfil.OnFragmentInteractionListener,
        AgendaFornecedor.OnFragmentInteractionListener,
        ServicosFornecedor.OnFragmentInteractionListener,
        NovoServico.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("Perfil");
        navigationView.setCheckedItem(R.id.perfil_fornecedor);
        Fragment fragment = new Perfil();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fornecedor_frame, fragment);
        ft.commit();
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
        getMenuInflater().inflate(R.menu.fornecedor_menu, menu);
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
            Session sessao = new Session();
            Usuario user = new Usuario();
            sessao.editSessao(user, getApplicationContext());
            Intent login = new Intent(FornecedorMenu.this, Login.class);
            startActivity(login);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil_fornecedor) {
            setTitle("Perfil");
            Fragment fragment = new Perfil();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fornecedor_frame, fragment);
            ft.commit();
        } else if (id == R.id.agenda_fornecedor) {
            setTitle("Agenda");
            Fragment fragment = new AgendaFornecedor();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fornecedor_frame, fragment);
            ft.commit();
        } else if (id == R.id.servicos) {
            setTitle("Servicos");
            Fragment fragment = new ServicosFornecedor();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fornecedor_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
