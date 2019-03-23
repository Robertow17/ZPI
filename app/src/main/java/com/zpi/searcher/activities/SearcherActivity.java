package com.zpi.searcher.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.fragments.FragmentBasic;
import com.zpi.searcher.fragments.FragmentExtended;
import com.zpi.searcher.model.Data;
import com.zpi.searcher.utils.FragmentViewPagerAdapter;
import com.zpi.searcher.utils.PageTransformer;

public class SearcherActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int[] tabIcons = {R.drawable.music};


    private static final String[] tabArray = {"SALE", "MUZYKA", "FOTOGRAFIA", "DEKORACJE",
        "TRANSPORT", "MODA", "INNE"};

    private static final String[] DECORATION_SUBCATEGORIES = new String[] {"Dekoracje weselne", "Florystyka"};
    private static final String[] FASHION_SUBCATEGORIES = new String[] {"Fryzjer", "Kosmetyczka", "Suknie ślubne", "Garnitury", "Dodatki"};
    private static final String[] MUSIC_SUBCATEGORIES = new String[] {"Zespół weselny", "DJ"};
    private static final String[] TRANSPORT_SUBCATEGORIES = new String[] {"Limuzyna", "Samochody zabytkowe", "Inne pojazdy"};
    private static final String[] OTHERS_SUBCATEGORIES = new String[] {"Jubiler", "Catering", "Cukiernia", "Szkoła tańca"};

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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private void setupViewPager(ViewPager viewPager)
    {
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentBasic.newInstance(Data.getWeddingHalls()), tabArray[0]);
        adapter.addFragment(FragmentExtended.newInstance( Data.getWeddingHalls(), MUSIC_SUBCATEGORIES), tabArray[1]);
        adapter.addFragment(FragmentBasic.newInstance(Data.getWeddingHalls()), tabArray[2]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getWeddingHalls(), DECORATION_SUBCATEGORIES), tabArray[3]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getWeddingHalls(), TRANSPORT_SUBCATEGORIES), tabArray[4]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getWeddingHalls(), FASHION_SUBCATEGORIES), tabArray[5]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getWeddingHalls(), OTHERS_SUBCATEGORIES), tabArray[6]);


        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new PageTransformer());
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.d("aktywnocs","back");

    }
}
