package com.example.scorekeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCourse extends Activity {
    scorekeeper_data msd;
    EditText my_et;
    CharSequence text_cs;
    Button my_button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_course);
	msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");

	display_current_data();
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
    public void Quit (View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
	setResult(RESULT_OK, intent);
	finish();
	
    }
    
    
    public void incr_current_hole(View view)
    {
   	if (msd.current_hole<17)
	    msd.current_hole++;
	my_button=(Button)findViewById(R.id.aec_b_current_hole);
	my_button.setText(Integer.toString(msd.current_hole+1));
	display_current_data();
    }
    public void decr_current_hole(View view)
    {
      	if (msd.current_hole>0)
	    msd.current_hole--;
	my_button=(Button)findViewById(R.id.aec_b_current_hole);
	my_button.setText(Integer.toString(msd.current_hole+1));
	display_current_data();
    }

    public void ui_undo(View view)
    {
    		display_current_data();
    		Context context = getApplicationContext();
    		Toast toast = Toast.makeText(context, "Change Cancelled", Toast.LENGTH_SHORT);
    		toast.show();
   }
    
    public void display_current_data()
    {
    	my_et=(EditText)findViewById(R.id.aec_et_course_name);
    	my_et.setText((msd.current_course));

    	my_button=(Button)findViewById(R.id.aec_b_current_hole);
	my_button.setText(Integer.toString(msd.current_hole+1));

	my_et=(EditText)findViewById(R.id.aec_et_white_dist);
	my_et.setText(Integer.toString(msd.course[msd.COURSE_WHITE][msd.current_hole]));

	my_et=(EditText)findViewById(R.id.aec_et_hcp);
	my_et.setText(Integer.toString(msd.course[msd.COURSE_HCP][msd.current_hole]));

	my_et=(EditText)findViewById(R.id.aec_et_par);
	my_et.setText(Integer.toString(msd.course[msd.COURSE_PAR][msd.current_hole]));

	my_et=(EditText)findViewById(R.id.aec_et_blue_dist);
	my_et.setText(Integer.toString(msd.course[msd.COURSE_BLUE][msd.current_hole]));

	my_et=(EditText)findViewById(R.id.aec_et_red_dist);
	my_et.setText(Integer.toString(msd.course[msd.COURSE_RED][msd.current_hole]));
    }
	
    public void ui_save(View view)
    {
    	my_et=(EditText)findViewById(R.id.aec_et_course_name);
    	text_cs=my_et.getText();
    	msd.current_course=text_cs.toString();

    my_et=(EditText)findViewById(R.id.aec_et_white_dist);
	text_cs=my_et.getText();
	msd.course[msd.COURSE_WHITE][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
	
	my_et=(EditText)findViewById(R.id.aec_et_blue_dist);
	text_cs=my_et.getText();
	msd.course[msd.COURSE_BLUE][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
	
	my_et=(EditText)findViewById(R.id.aec_et_red_dist);
	text_cs=my_et.getText();
	msd.course[msd.COURSE_RED][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
	
	my_et=(EditText)findViewById(R.id.aec_et_hcp);
	text_cs=my_et.getText();
	msd.course[msd.COURSE_HCP][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());
	
	my_et=(EditText)findViewById(R.id.aec_et_par);
	text_cs=my_et.getText();
	msd.course[msd.COURSE_PAR][msd.current_hole]=Msd_common_methods.catch_parseInt(text_cs.toString());

	Context context = getApplicationContext();

	Toast toast = Toast.makeText(context, "Save", Toast.LENGTH_SHORT);
	toast.show();
	
    }

    public void Done(View view)
    {
	
	ui_save(view);

	Intent intent = new Intent(this, MainActivity.class);
	intent.putExtra("myobj",msd);
	setResult(RESULT_OK, intent);
	finish();
    }
}
