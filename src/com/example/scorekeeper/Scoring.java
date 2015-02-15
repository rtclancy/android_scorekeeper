package com.example.scorekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Scoring extends Activity {

	scorekeeper_data my_scorekeeper_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		my_scorekeeper_data=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
		scoring_layout scoring_layout = new scoring_layout(this,my_scorekeeper_data);
		//setContentView(R.layout.activity_scoring);
		//setContentView(scoring_layout.layout,scoring_layout.layout_parameters);
		setContentView(scoring_layout.my_sv,scoring_layout.sv_parameters);
		scoring_layout.b_done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    // TODO Auto-generated method stub
			    Done(v);
			}
		    });
		scoring_layout.b_clear_scores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    // TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
				builder.setMessage("Are You Sure You Want To Clear All Scores?")
			       .setTitle("Clear Scores");
				builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
							my_scorekeeper_data.resetAllScores();
							//Done(v);
		//					scoring_layout.scoring_layout(my_scorekeeper_data);
			           }
			       });
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User cancelled the dialog
			           }
			       });

			AlertDialog dialog = builder.create();
			dialog.show();
			
			}
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scoring, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void Done(View view)
    {
	
	Intent intent = new Intent(this, MainActivity.class);
	intent.putExtra("myobj",my_scorekeeper_data);
	setResult(RESULT_OK, intent);
	finish();
    }
	
}
