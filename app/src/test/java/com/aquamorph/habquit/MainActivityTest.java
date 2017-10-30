package com.aquamorph.habquit;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.aquamorph.habquit.activities.MainActivity;
import com.aquamorph.habquit.activities.ManageHabitActivityOne;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for the main activity
 *
 * @author Christian Colglazier
 * @version 2/23/2017
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "../../../../../src/main/AndroidManifest.xml",
		sdk = 21)
public class MainActivityTest {
	private MainActivity activity;

	@Before
	public void setup() {
		activity = Robolectric.buildActivity(MainActivity.class).create().get();
	}

	@Test
	public void shouldNotBeNull() {
		// Tests creation of activity
		assertNotNull(activity);
	}

	@Test
	public void bottomNav() throws Exception {
		// Tests creation of bottom navigation buttons
		ViewPager viewPager = (ViewPager) activity.findViewById(R.id.fragment);
		assertNotNull(viewPager);
	}

	@Test
	public void addHabitMenuButton() {
		// Tests the add habit menu button
		MenuItem addHabit = new RoboMenuItem(R.id.add_habit);
		activity.onOptionsItemSelected(addHabit);
		Intent expectedIntent = new Intent(activity, ManageHabitActivityOne.class);
		ShadowActivity shadowActivity = Shadows.shadowOf(activity);
		Intent actualIntent = shadowActivity.getNextStartedActivity();
		assertTrue(actualIntent.filterEquals(expectedIntent));
	}

	@Test
	public void manageHabitMenuButton() {
		// Tests the add habit menu button
		MenuItem manageHabit = new RoboMenuItem(R.id.manage_habit);
		activity.onOptionsItemSelected(manageHabit);
		Intent expectedIntent = new Intent(activity, ManageHabitActivityOne.class);
		ShadowActivity shadowActivity = Shadows.shadowOf(activity);
		Intent actualIntent = shadowActivity.getNextStartedActivity();
		assertTrue(actualIntent.filterEquals(expectedIntent));
	}
}
