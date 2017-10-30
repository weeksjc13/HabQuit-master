package com.aquamorph.habquit;

import android.app.Activity;
import android.widget.TextView;

import com.aquamorph.habquit.activities.MainActivity;
import com.aquamorph.habquit.fragments.AssistantFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for the assistant
 *
 * @author Christian Colglazier
 * @version 2/22/2017
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "../../../../../src/main/AndroidManifest.xml",
		sdk = 21)
public class AssistantFragmentTest {
	private Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
	private TextView assistantMessage = (TextView) activity.findViewById(R.id.assistant_message);

	@Test
	public void changeMood() throws Exception {
		// Tests mood being set above range
		double expected1 = 100.0;
		AssistantFragment.changeMood(150);
		assertEquals(expected1, AssistantFragment.getMood());

		// Tests mood being set below range
		double expected2 = 0.0;
		AssistantFragment.changeMood(-1500);
		assertEquals(expected2, AssistantFragment.getMood());

		// Tests increasing mood
		double expected3 = 50.0;
		AssistantFragment.changeMood(expected3);
		assertEquals(expected3, AssistantFragment.getMood());

		// Tests decreasing mood
		double expected4 = expected3-2.0;
		AssistantFragment.changeMood(-2.0);
		assertEquals(expected4, AssistantFragment.getMood());
	}

	@Test
	public void clearMessages() throws Exception {
		// Tests single message display
		String expected1 = "";
		AssistantFragment.sendMessage("Test Message 1");
		AssistantFragment.clearMessages();
		assertEquals(expected1, assistantMessage.getText());
	}

	@Test
	public void sendMessage() throws Exception {
		// Tests single message display
		String expected1 = "Test Message 1";
		AssistantFragment.clearMessages();
		AssistantFragment.sendMessage(expected1);
		assertEquals(expected1, assistantMessage.getText());

		// Tests message queue
		String expected2 = "Test Message 2";
		AssistantFragment.clearMessages();
		AssistantFragment.sendMessage(expected2);
		AssistantFragment.sendMessage("1", 0);
		AssistantFragment.sendMessage("2", 0);
		AssistantFragment.sendMessage("3", 0);
		assertEquals(expected2, assistantMessage.getText());
	}
}
