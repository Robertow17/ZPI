package com.zpi.searcher.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zpi.R;
import com.zpi.searcher.fragments.FragmentBasic;
import com.zpi.searcher.fragments.FragmentExtended;
import com.zpi.Data;
import com.zpi.searcher.utils.FragmentViewPagerAdapter;
import com.zpi.searcher.utils.PageTransformer;

public class SearcherActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int[] tabIcons = {R.drawable.baseline_restaurant_menu_white_18dp, R.drawable.music,
            R.drawable.baseline_photo_camera_white_18dp, R.drawable.baseline_card_giftcard_white_18dp,
            R.drawable.baseline_directions_car_white_18dp, R.drawable.baseline_brush_white_18dp, R.drawable.baseline_more_vert_white_18dp};
    public static  final String EXTRA_SERVICES = "services";
    public static  final String EXTRA_SUBCATEORIES = "subcategories";


    private static final String[] tabArray = {"SALE", "MUZYKA", "FOTOGRAFIA", "DEKORACJE",
        "TRANSPORT", "MODA", "INNE"};

   /* private static final String[] DECORATION_SUBCATEGORIES = new String[] {"Dekoracje weselne", "Florystyka"};
    private static final String[] FASHION_SUBCATEGORIES = new String[] {"Fryzjer", "Kosmetyczka", "Suknie ślubne", "Garnitury", "Dodatki"};
    private static final String[] MUSIC_SUBCATEGORIES = new String[] {"Zespół weselny", "DJ"};
    private static final String[] TRANSPORT_SUBCATEGORIES = new String[] {"Limuzyna", "Samochody zabytkowe", "Inne pojazdy"};
    private static final String[] OTHERS_SUBCATEGORIES = new String[] {"Jubiler", "Catering", "Cukiernia", "Szkoła tańca"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher_activity);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutListener();
        setupTabIcons();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }


    private void setupViewPager(ViewPager viewPager)
    {
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentBasic.newInstance(Data.getServices(tabArray[0])), tabArray[0]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getServices(tabArray[1])), tabArray[1]);
        adapter.addFragment(FragmentBasic.newInstance(Data.getServices(tabArray[2])), tabArray[2]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getServices(tabArray[3])), tabArray[3]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getServices(tabArray[4])), tabArray[4]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getServices(tabArray[5])), tabArray[5]);
        adapter.addFragment(FragmentExtended.newInstance(Data.getServices(tabArray[6])), tabArray[6]);

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
            customTab.setCompoundDrawablesWithIntrinsicBounds(tabIcons[i], 0, 0, 0);
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

    private void setTabLayoutListener()
    {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int colorFrom = ((ColorDrawable) tabLayout.getBackground()).getColor();
                Log.d("COLOR", String.valueOf(colorFrom));

                int colorTo = getColorForTab(tab.getPosition());

                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(250);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int color = (int) animator.getAnimatedValue();

                        tabLayout.setBackgroundColor(color);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(color);
                        }
                    }

                });

                colorAnimation.start();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    public int getColorForTab(int position) {
        if (position == 0) return ContextCompat.getColor(this, R.color.colorPrimary);
        else if (position == 1) return ContextCompat.getColor(this, R.color.lightRed);
        else if (position == 2) return ContextCompat.getColor(this, R.color.purple);
        else if (position == 3) return ContextCompat.getColor(this, R.color.teal);
        else if (position == 4) return ContextCompat.getColor(this, R.color.orange);
        else if (position == 5) return ContextCompat.getColor(this, R.color.brown);
        else if (position == 6) return ContextCompat.getColor(this, R.color.blue_grey);
        else return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.d("aktywnocs","back");

    }
}
