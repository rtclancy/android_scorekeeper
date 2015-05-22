package com.example.scorekeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class EnterAllScores extends Activity {
    scorekeeper_data msd;
    EditText my_view;
    TextView my_tview;
    Button my_button;
	Spinner my_spinner;
    CharSequence text_cs;
    int current_et = 0;
    ArrayAdapter<CharSequence> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_enter_all_scores);
	msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
	
	my_button=(Button)findViewById(R.id.aeas_b_prevhole);
	my_button.setBackgroundColor(Color.rgb(0xff, 0x00, 0xff));

	my_button=(Button)findViewById(R.id.aeas_b_nexthole);
	my_button.setBackgroundColor(Color.rgb(0xff, 0xff, 0x00));

	my_button=(Button)findViewById(R.id.aeas_b_current_hole);
	my_button.setBackgroundColor(Color.rgb(0x00, 0xff, 0xff));
	
	adapter= new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item);
	for (int i=0;i<10;i++)
	{
		adapter.add(Msd_common_methods.possible_scores[i]);
	}

	my_spinner=(Spinner)findViewById(R.id.aeas_sp_0);
	my_spinner.setAdapter(adapter);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_1);
	my_spinner.setAdapter(adapter);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_2);
	my_spinner.setAdapter(adapter);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_3);
	my_spinner.setAdapter(adapter);


	ui_update_scores();
	//next_listeners();

	my_button=(Button)findViewById(R.id.aeas_b_current_hole);
	my_button.setText(Integer.toString(msd.current_hole+1));

    }
    @Override
    protected void onStop()
    {
    	super.onStop();
    	Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    }

    public void incr_current_hole(View view)
    {
    	Button my_view;
	
   	if (msd.current_hole<17)
	    msd.current_hole++;
	my_view=(Button)findViewById(R.id.aeas_b_current_hole);
	my_view.setText(Integer.toString(msd.current_hole+1));

	ui_update_scores();
  
    }
    public void decr_current_hole(View view)
    {
    	Button my_view;
      	if (msd.current_hole>0)
	    msd.current_hole--;
	my_view=(Button)findViewById(R.id.aeas_b_current_hole);
	my_view.setText(Integer.toString(msd.current_hole+1));
	
	ui_update_scores();

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

    @Override
    public void onBackPressed() 
    {
        Done();
    }
    
    public void Done()
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	intent.putExtra("myobj",msd);
    	setResult(RESULT_OK, intent);
    	finish();
	
    }
    
    public void Done(View view)
    {
    	ui_store_scores();
    	Done();
    }
    
    public void ui_save_and_store_scores(View view)
    {
	ui_store_scores();
	ui_update_scores();
    }

    public void ui_store_scores()
    {
		Context context = getApplicationContext();
  		Toast toast = Toast.makeText(context, "Save", Toast.LENGTH_SHORT);
		toast.show();

//	my_view=(EditText)findViewById(R.id.aeas_et_0);
//	text_cs=my_view.getText();
//	msd.score[0][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		my_spinner=(Spinner)findViewById(R.id.aeas_sp_0);
		text_cs=my_spinner.getSelectedItem().toString();
		msd.score[0][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
	

//	my_view=(EditText)findViewById(R.id.aeas_et_1);
//	text_cs=my_view.getText();
//	msd.score[1][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		my_spinner=(Spinner)findViewById(R.id.aeas_sp_1);
		text_cs=my_spinner.getSelectedItem().toString();
		msd.score[1][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		
//	my_view=(EditText)findViewById(R.id.aeas_et_2);
//	text_cs=my_view.getText();
//	msd.score[2][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		my_spinner=(Spinner)findViewById(R.id.aeas_sp_1);
		text_cs=my_spinner.getSelectedItem().toString();
		msd.score[1][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		
//	my_view=(EditText)findViewById(R.id.aeas_et_3);
//	text_cs=my_view.getText();
//	msd.score[3][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
		my_spinner=(Spinner)findViewById(R.id.aeas_sp_1);
		text_cs=my_spinner.getSelectedItem().toString();
		msd.score[1][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
   }

    public void ui_void_scores(View view)
    {
		Context context = getApplicationContext();
  		Toast toast = Toast.makeText(context, "Change Cancelled", Toast.LENGTH_SHORT);
		toast.show();
	ui_update_scores();
    }

    public void ui_update_scores()
    {
    	my_tview=(TextView)findViewById(R.id.fixedText0);
	my_tview.setText(msd.players[0]);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_0);
	my_spinner.setSelection(msd.score[0][msd.current_hole]);
//	my_view=(EditText)findViewById(R.id.aeas_et_0);
//	my_view.setText(Integer.toString(msd.score[0][msd.current_hole]));
//	my_view.setSelectAllOnFocus(Boolean.TRUE);
//	my_view.requestFocus();
	

	my_tview=(TextView)findViewById(R.id.fixedText1);
    	my_tview.setText(msd.players[1]);
    	my_spinner=(Spinner)findViewById(R.id.aeas_sp_1);
    	my_spinner.setSelection(msd.score[1][msd.current_hole]);
    ///    	my_view=(EditText)findViewById(R.id.aeas_et_1);
//    	my_view.setText(Integer.toString(msd.score[1][msd.current_hole]));
//    	my_view.setSelectAllOnFocus(Boolean.TRUE);
//    	my_view.requestFocus(); //need to focus here then again back on first edittext to get select all to work
    	


										  
	my_tview=(TextView)findViewById(R.id.fixedText2);
	my_tview.setText(msd.players[2]);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_2);
	my_spinner.setSelection(msd.score[2][msd.current_hole]);
///	my_view=(EditText)findViewById(R.id.aeas_et_2);			  
//	my_view.setText(Integer.toString(msd.score[2][msd.current_hole]));
//	my_view.setSelectAllOnFocus(Boolean.TRUE);
										  
	my_tview=(TextView)findViewById(R.id.fixedText3);
	my_tview.setText(msd.players[3]);
	my_spinner=(Spinner)findViewById(R.id.aeas_sp_3);
	my_spinner.setSelection(msd.score[3][msd.current_hole]);
///	my_view=(EditText)findViewById(R.id.aeas_et_3);			  
//	my_view.setText(Integer.toString(msd.score[3][msd.current_hole]));
//	my_view.setSelectAllOnFocus(Boolean.TRUE);
    }
   }

