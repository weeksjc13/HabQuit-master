package com.aquamorph.habquit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aquamorph.habquit.R;


/**
 * Created by JuiceBox1 on 1/18/2017.
 */

public class GraphDisplayActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_display);
		Button btn = (Button) findViewById(R.id.seven_line);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				openSevenDayGraph();
			}
		});
	}

	public void openSevenDayGraph() {
		startActivity(new Intent(this, BarGraphActivity.class));
	}

}


