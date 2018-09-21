package br.edu.infnet.icracha;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.icracha.DAO.ReportDAO;
import br.edu.infnet.icracha.DAO.UserDAO;
import br.edu.infnet.icracha.fragments.AttendanceReportFragment;
import br.edu.infnet.icracha.fragments.ProfileFragment;
import br.edu.infnet.icracha.fragments.StatusFragment;
import br.edu.infnet.icracha.report.AttendanceReport;
import br.edu.infnet.icracha.user.User;

public class ManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
    public static UserDAO mUserDao;
    public static ReportDAO mReportDao;
    public static User user;
    public static List<AttendanceReport> mReportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        user = (User) getIntent().getSerializableExtra("user");

        mUserDao = new UserDAO();

        mReportDao = new ReportDAO(user.getCpf());

        mReportList = mReportDao.listar();

        setFirstFragment();

        // atribui um "listener" ao navigationView
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            /*case R.id.action_settings:
                return true;*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(Gravity.START);
        int itemId = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (itemId){
            case R.id.navigationItemStatus:
                mFragment = new StatusFragment();
                transaction.replace(R.id.content_frame, mFragment);
                transaction.commit();
                break;
            case R.id.navigationItemProfile:
                mFragment = new ProfileFragment();
                transaction.replace(R.id.content_frame, mFragment);
                transaction.commit();
                break;
            case R.id.navigationItemAttendanceReport:
                mFragment = new AttendanceReportFragment();
                transaction.replace(R.id.content_frame, mFragment);
                transaction.commit();
                break;
            case R.id.Logout:
                Intent intent = new Intent(this, SigninActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                Toast.makeText(this, "Escolha inválida!", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }


    private void setFirstFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragment = new StatusFragment();
        transaction.replace(R.id.content_frame, mFragment);
        transaction.commit();
    }

    public void goToFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragment = fragment;
        transaction.replace(R.id.content_frame, mFragment);
        transaction.commit();
    }

}