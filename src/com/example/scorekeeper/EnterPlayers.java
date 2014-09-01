package com.example.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class EnterPlayers extends Activity {
	scorekeeper_data my_scorekeeper_data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		EditText my_view;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_players);
		my_scorekeeper_data=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
		my_view=(EditText)findViewById(R.id.editText0);
		my_view.setText(my_scorekeeper_data.players[0]);
		my_view=(EditText)findViewById(R.id.editText1);
		my_view.setText(my_scorekeeper_data.players[1]);
		my_view=(EditText)findViewById(R.id.editText2);
		my_view.setText(my_scorekeeper_data.players[2]);
		my_view=(EditText)findViewById(R.id.editText3);
		my_view.setText(my_scorekeeper_data.players[3]);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_players, menu);
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
		EditText my_view;
		CharSequence text_cs;
		my_view=(EditText)findViewById(R.id.editText0);
		text_cs=my_view.getText();
		my_scorekeeper_data.players[0]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.editText1);
		text_cs=my_view.getText();
		my_scorekeeper_data.players[1]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.editText2);
		text_cs=my_view.getText();
		my_scorekeeper_data.players[2]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.editText3);
		text_cs=my_view.getText();
		my_scorekeeper_data.players[3]=text_cs.toString();

		Intent intent = new Intent(this, MainActivity.class);
    	intent.putExtra("myobj",my_scorekeeper_data);
    	setResult(RESULT_OK, intent);
 		finish();
    }
	
}
