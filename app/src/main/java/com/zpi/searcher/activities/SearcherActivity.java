package com.zpi.searcher.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.fragments.DecorationsFragment;
import com.zpi.searcher.fragments.FashionFragment;
import com.zpi.searcher.fragments.MusicFragment;
import com.zpi.searcher.fragments.OthersFragment;
import com.zpi.searcher.fragments.PhotographyFragment;
import com.zpi.searcher.fragments.TransportFragment;
import com.zpi.searcher.fragments.WeddingHallFragment;
import com.zpi.searcher.utils.ViewPagerAdapter;

public class SearcherActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int[] tabIcons = {R.drawable.music};
  /*  private  final String[] tabArray = {getString(R.string.wedding_hall), getString(R.string.music), getString(R.string.photography), getString(R.string.decorations),
            getString(R.string.transport), getString(R.string.fashion), getString(R.string.others)};*/

    private static final String[] tabArray = {"SALE", "MUZYKA", "FOTOGRAFIA", "DEKORACJE",
        "TRANSPORT", "MODA", "INNE"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher_activity);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WeddingHallFragment(), tabArray[0]);
        adapter.addFragment(new MusicFragment(), tabArray[1]);
        adapter.addFragment(new PhotographyFragment(), tabArray[2]);
        adapter.addFragment(new DecorationsFragment(), tabArray[3]);
        adapter.addFragment(new TransportFragment(), tabArray[4]);
        adapter.addFragment(new FashionFragment(), tabArray[5]);
        adapter.addFragment(new OthersFragment(), tabArray[6]);
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons()
    {
        for(int i = 0; i < tabArray.length; i++)
        {
            TextView customTab =
                    (TextView) LayoutInflater.from(this).inflate(R.layout.searcher_custom_tab,
                            null);
            customTab.setText(tabArray[i]);
            customTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[0], 0, 0, 0);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if(tab != null)
                tab.setCustomView(customTab);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
