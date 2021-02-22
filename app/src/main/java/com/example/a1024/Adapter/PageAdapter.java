package com.example.a1024.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.a1024.Fragments.Fragment1024;
import com.example.a1024.Fragments.TopQuizFragment;

public class PageAdapter extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to PageFragment
    //private int[] colors;

    // 2 - Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
        //this.colors = colors;
    }

    @Override
    public int getCount() {
        return(2); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return Fragment1024.newInstance();
            case 1: //Page number 2
                return TopQuizFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "1024";
            case 1: //Page number 2
                return "TopQuiz";
            default:
                return null;
        }
    }

}
