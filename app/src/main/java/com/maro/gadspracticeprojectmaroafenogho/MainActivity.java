package com.maro.gadspracticeprojectmaroafenogho;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapter;
    ProgressBar progressBar;
    TextView loading;
    TextView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        vPager = findViewById(R.id.viewpager);

        submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubmissionPage.class);
            startActivity(intent);
        });

       ViewPager vPager = findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this);
        vPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(vPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}