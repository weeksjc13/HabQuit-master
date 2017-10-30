package com.aquamorph.habquit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquamorph.habquit.R;

import java.util.LinkedList;
import java.util.Queue;

import static com.aquamorph.habquit.fragments.AssistantFragment.Mood.Mad;

/**
 * This fragment creates the assistant.
 *
 * @author Christian Colglazier
 * @version 2/22/2017
 */

public class AssistantFragment extends Fragment {

	private static String TAG = "AssistantFragment";
	private static TextView assistantMessageText;
	private static ImageView assistant;
	private static double mood = 70.0;
	private static Animation happyToNeutral;
	private static Animation madToNeutral;
	private static Animation neutralToHappy;
	private static Animation neutralToMad;
	private static Queue<Message> messageQueue = new LinkedList<>();
	private static Boolean isMessageDisplayed = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_assistant, container, false);
		assistantMessageText = (TextView) view.findViewById(R.id.assistant_message);
		assistant = (ImageView) view.findViewById(R.id.assistant);

		happyToNeutral = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_happy_to_neutral);
		madToNeutral = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_mad_to_neutral);
		neutralToHappy = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_neutral_to_happy);
		neutralToMad = AnimationUtils.loadAnimation(getContext().getApplicationContext(),
				R.anim.assistant_neutral_to_mad);
		happyToNeutral.setAnimationListener(new AssistantAnimationListener());
		madToNeutral.setAnimationListener(new AssistantAnimationListener());
		neutralToHappy.setAnimationListener(new AssistantAnimationListener());
		neutralToMad.setAnimationListener(new AssistantAnimationListener());

		checkMood();
		switch (getMoodFromValue(getMood())) {
			case Mad:
				assistant.setRotation(90);
				break;
			case Neutral:
				assistant.setRotation(45);
				break;
			case Happy:
			default:
				assistant.setRotation(0);
				break;
		}
		return view;
	}

	/**
	 * Allows the assistant to display massages
	 *
	 * @param text
	 */
	public static void sendMessage(String text) {
		messageQueue.add(new Message(text));
		displayMessage();
	}

	public static void sendMessage(String text, int displayTime) {
		messageQueue.add(new Message(text, displayTime));
		displayMessage();
	}

	/**
	 * Allows for all messages to be cleared
	 */
	public static void clearMessages() {
		assistantMessageText.clearAnimation();
		assistantMessageText.setText("");
		isMessageDisplayed = false;
		messageQueue.clear();
	}

	/**
	 * Helper function to display messages in a queue
	 */
	private static void displayMessage() {
		if (assistantMessageText != null && !messageQueue.isEmpty() && !isMessageDisplayed) {
			Message message = messageQueue.poll();
			int time = message.displayTime;
			final Animation in = new AlphaAnimation(0.0f, 1.0f);
			in.setDuration(500);
			final Animation out = new AlphaAnimation(1.0f, 0.0f);
			out.setDuration(500);

			AnimationSet as = new AnimationSet(true);
			as.addAnimation(in);
			out.setStartOffset(Math.max(time-500,0));
			as.addAnimation(out);

			isMessageDisplayed = true;
			assistantMessageText.setText(message.message);
			assistantMessageText.startAnimation(as);
			assistantMessageText.setVisibility(View.VISIBLE);
			assistantMessageText.postDelayed(new Runnable() {
				public void run() {
					assistantMessageText.setVisibility(View.INVISIBLE);
					assistantMessageText.setText("");
					isMessageDisplayed = false;
					if (!messageQueue.isEmpty())
						displayMessage();
				}
			}, time);
		}
	}

	/**
	 * Gives the raw value of the assistant.
	 *
	 * @return raw mood value
	 */
	public static double getMood() {
		return mood;
	}

	/**
	 * Changes the mood value of the assistant.
	 *
	 * @param changeAmount sets how much you want to adjust the mood.
	 */
	public static void changeMood(double changeAmount) {
		double init = getMood();
		Log.i(TAG, "Init Mood is " + init);
		mood = getMood() + changeAmount;
		if (mood > 100.0) mood = 100.0;
		else if (mood < 0.0) mood = 0.0;
		// Sets transition animation if needed
		if (getMoodFromValue(init) != getMoodFromValue(getMood())) {
			if ((init - getMood()) > 0) {
				if (getMoodFromValue(getMood()) == Mood.Neutral)
					assistant.startAnimation(happyToNeutral);
				else
					assistant.startAnimation(neutralToMad);
			} else {
				if (getMoodFromValue(getMood()) == Mood.Neutral)
					assistant.startAnimation(madToNeutral);
				else
					assistant.startAnimation(neutralToHappy);
			}
		}
	}

	/**
	 * Checks the mood of the AI and changes the design based on its mood.
	 */
	protected static void checkMood() {
		if (assistant != null) {
			Log.i(TAG, "Checking mood " + getMoodFromValue(getMood()));
			switch (getMoodFromValue(getMood())) {
				case Mad:
					assistant.setImageResource(R.drawable.ai_mad);
					break;
				case Neutral:
					assistant.setImageResource(R.drawable.ai_neutral);
					break;
				case Happy:
				default:
					assistant.setImageResource(R.drawable.ai_happy);
					break;
			}
		}
	}

	/**
	 * Gives the mood of the assistant based on a given value
	 *
	 * @param moodValue mood value
	 * @return assistant mood
	 */
	private static Mood getMoodFromValue(double moodValue) {
		if (moodValue < 40.0) return Mad;
		else if (moodValue < 60.0) return Mood.Neutral;
		else return Mood.Happy;
	}

	/**
	 * Three state moods for the assistant.
	 */
	public enum Mood {
		Happy, Neutral, Mad
	}

	private static class Message {
		String message;
		int displayTime = 5000;

		public Message(String message) {
			this.message = message;
		}

		public Message(String message, int displayTime) {
			this.message = message;
			this.displayTime = displayTime;
		}
	}
}



