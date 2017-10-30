package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquamorph.habquit.R;

/**
 * <p></p>
 *
 * @author Christian Colglazier
 * @version 2/2/2017
 */

public class SavingsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_savings, container, false);
		return view;
	}
}
