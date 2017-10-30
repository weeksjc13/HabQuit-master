package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aquamorph.habquit.R;
import com.aquamorph.habquit.provider.LoginServiceProvider;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
		GoogleApiClient.OnConnectionFailedListener {

	private final String TAG = "LoginActivity";
	private SignInButton signIn;
	private GoogleApiClient googleApiClient;
	private static final int REQ_CODE = 9001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		getSupportActionBar().hide();
		signIn = (SignInButton) findViewById(R.id.sign_in_button);
		signIn.setOnClickListener(this);

		GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions
				.DEFAULT_SIGN_IN).requestProfile().build();
		googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi
				(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sign_in_button:
				signIn();
				break;
		}
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

	}

	private void signIn() {
		Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
		startActivityForResult(intent, REQ_CODE);
	}


	private void handleResult(GoogleSignInResult result) {
		if (result.isSuccess()) {
			GoogleSignInAccount account = result.getSignInAccount();

			assert account != null;
			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
					(getApplicationContext());
			sharedPreferences.edit().putString("firstName", account.getGivenName()).apply();
			sharedPreferences.edit().putString("googleID", account.getId()).apply();
			sharedPreferences.edit().putString("userName", account.getDisplayName()).apply();
			sharedPreferences.edit().putString("email", account.getEmail()).apply();

            Log.i(TAG, "handleResult: googleID = " + account.getId());
            Log.i(TAG, "handleResult: userName = " + account.getGivenName());
            Log.i(TAG, "handleResult: firstName = " + account.getDisplayName());
            Log.i(TAG, "handleResult: email = " + account.getEmail());

			LoginServiceProvider loginServiceProvider = new LoginServiceProvider(this);
			loginServiceProvider.postLoginInfo(Double.parseDouble(sharedPreferences
							.getString("googleID", "Error w/ googleID")),
					sharedPreferences.getString("userName", "Error w/ userName"),
					sharedPreferences.getString("email", "Error w/ email"));

			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();

		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "Failed to Login " +
							result.getStatus(),	Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_CODE) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			handleResult(result);
		}
	}
}
