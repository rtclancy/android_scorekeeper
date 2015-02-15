package com.example.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EnterPlayers extends Activity {
	scorekeeper_data msd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		EditText my_view;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_players);
		msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");

		my_view=(EditText)findViewById(R.id.aep_et_0);
		my_view.setText(msd.players[0]);
		//		my_view.setBackgroundColor(this.getResources().getColor(msd.color[0]));

		my_view=(EditText)findViewById(R.id.aep_et_1);
		my_view.setText(msd.players[1]);
		//		my_view.setBackgroundColor(this.getResources().getColor(msd.color[1]));
										  
		my_view=(EditText)findViewById(R.id.aep_et_2);			  
		my_view.setText(msd.players[2]);		  
		//		my_view.setBackgroundColor(this.getResources().getColor(msd.color[2]));
										  
		my_view=(EditText)findViewById(R.id.aep_et_3);			  
		my_view.setText(msd.players[3]);		  
		//		my_view.setBackgroundColor(this.getResources().getColor(msd.color[3]));
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
		my_view=(EditText)findViewById(R.id.aep_et_0);
		text_cs=my_view.getText();
		msd.players[0]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.aep_et_1);
		text_cs=my_view.getText();
		msd.players[1]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.aep_et_2);
		text_cs=my_view.getText();
		msd.players[2]=text_cs.toString();
		my_view=(EditText)findViewById(R.id.aep_et_3);
		text_cs=my_view.getText();
		msd.players[3]=text_cs.toString();

		Intent intent = new Intent(this, MainActivity.class);
    	intent.putExtra("myobj",msd);
    	setResult(RESULT_OK, intent);
 		finish();
    }
    protected void onStop()
    {
    	super.onStop();
    //	Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    }
	
 
}
