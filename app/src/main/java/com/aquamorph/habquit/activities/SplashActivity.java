package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

/**
 * Splash Screen for App
 *
 * @author Christian Colglazier
 * @version 3/8/2017
 */

public class SplashActivity extends AppCompatActivity
		implements GoogleApiClient.OnConnectionFailedListener {

	private String TAG = "SplashActivity";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestProfile().build();
		GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage
				(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
		OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
		if (opr.isDone()) {
			Log.d(TAG, "Got cached sign-in");
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		finish();
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}
}
