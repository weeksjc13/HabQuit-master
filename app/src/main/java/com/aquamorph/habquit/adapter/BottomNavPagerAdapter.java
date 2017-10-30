package com.aquamorph.habquit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.aquamorph.habquit.fragments.ChartsFragment;
import com.aquamorph.habquit.fragments.HabitFragment;
import com.aquamorph.habquit.fragments.SavingsFragment;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/3/2017
 */

public class BottomNavPagerAdapter extends FragmentPagerAdapter {

	Fragment[] fragments = {new HabitFragment(), new ChartsFragment(),
			new SavingsFragment()};

	public BottomNavPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}
}
