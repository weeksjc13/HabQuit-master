package com.aquamorph.habquit.fragments;

import android.view.animation.Animation;

public class AssistantAnimationListener implements Animation.AnimationListener {

	@Override
	public void onAnimationStart(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		AssistantFragment.checkMood();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}
}
