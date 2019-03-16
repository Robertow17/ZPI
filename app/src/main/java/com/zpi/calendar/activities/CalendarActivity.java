package com.zpi.calendar.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.calendar.fragments.CalendarFragment;
import com.zpi.calendar.fragments.EventsFragment;
import com.zpi.searcher.utils.FragmentViewPagerAdapter;

public class CalendarActivity extends AppCompatActivity
    {
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private static final int[] tabIcons = {R.drawable.calendar_today, R.drawable.headline};

        private static final String[] tabArray = {"KALENDARZ", "WYDARZENIA"};

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.calendar_activity);

            viewPager = findViewById(R.id.viewpager_calendar);
            setupViewPager(viewPager);

            tabLayout = findViewById(R.id.tabs_calendar);
            tabLayout.setupWithViewPager(viewPager);

            setupTabIcons();
        }

        private void setupViewPager(ViewPager viewPager)
        {
            FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new CalendarFragment(), tabArray[0]);
            adapter.addFragment(new EventsFragment(), tabArray[1]);
            viewPager.setAdapter(adapter);
        }

        private void setupTabIcons()
        {
            for(int i = 0; i < tabArray.length; i++)
            {
                TextView customTab =
                        (TextView) LayoutInflater.from(this).inflate(R.layout.calendar_custom_tab,
                                null);
                customTab.setText(tabArray[i]);
                customTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[i], 0, 0, 0);
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if(tab != null)
                    tab.setCustomView(customTab);
            }

        }
    }

