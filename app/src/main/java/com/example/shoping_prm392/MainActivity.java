package com.example.shoping_prm392;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.shoping_prm392.adapter.SlideAdapter;
import com.example.shoping_prm392.model.Account;
import com.example.shoping_prm392.model.Slide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.shoping_prm392.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private ViewPager viewPager;

    private CircleIndicator circleIndicator;
    private List<Slide> listSlide;
    private Account currentAccount;
    private Handler handler = new Handler();
    private TextView navbarEmail;
    private TextView navbarRole;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == listSlide.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    @SuppressLint("SetTextI18n")
    private void bindingView() {
        NavigationView nav_draw = (NavigationView) findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle_indicator);
        navbarRole = nav_draw.getHeaderView(0).findViewById(R.id.navbar_role);
        navbarEmail = nav_draw.getHeaderView(0).findViewById(R.id.navbar_email);
    }

    private List<Slide> getListSlide() {
        List<Slide> list = new ArrayList<>();
        list.add(new Slide(R.drawable.clothes_slide));
        list.add(new Slide(R.drawable.shoes_silde));
        list.add(new Slide(R.drawable.accessories_slider));
        list.add(new Slide(R.drawable.watch_slide));
        return list;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        bindingView();


        listSlide = getListSlide();
        SlideAdapter adapter = new SlideAdapter(listSlide);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable, 2000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        currentAccount = getCurrentAccount();
        navbarRole.setText(currentAccount.getRole());
        navbarEmail.setText(currentAccount.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    private Account getCurrentAccount() {
        Intent intent = getIntent();
        Account currentAccount = (Account) intent.getSerializableExtra("currentAccount");
        Log.i("ac", currentAccount.toString());
        navbarEmail.setText(currentAccount.getEmail());
        navbarRole.setText(currentAccount.getRole());
        return currentAccount;
    }

}