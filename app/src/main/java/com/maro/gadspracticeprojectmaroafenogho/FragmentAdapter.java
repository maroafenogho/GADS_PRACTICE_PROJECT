package com.maro.gadspracticeprojectmaroafenogho;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return  new HoursFragment();
            case 1:
                return  new SkillIqFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return  mContext.getString(R.string.hours);
            case 1:
                return   mContext.getString(R.string.iq);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
