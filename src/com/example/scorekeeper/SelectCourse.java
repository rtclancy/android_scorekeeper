package com.example.scorekeeper;

import android.app.Activity;
import android.app.ListActivity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class SelectCourse extends Activity {
    File my_course_file = new File("course_file");
    scorekeeper_data msd;
    
    golf_course_db_dao datasource;
    TextView my_tv;
    EditText my_et;
	Toast toast;
	Context context;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_course);
	msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
	my_et = (EditText)findViewById(R.id.asc_confirm_code);
	my_et.setText(Integer.toString(0));
	datasource = new golf_course_db_dao(this);
	datasource.open();
	List<golf_course_front_end> values = datasource.getAllGCs();
	
	ArrayAdapter<golf_course_front_end> adapter = new ArrayAdapter<golf_course_front_end>(this,
											      android.R.layout.simple_list_item_1, values);
	final ListView gc_list = (ListView)findViewById(R.id.list);
	gc_list.setAdapter(adapter);
	my_tv=(TextView)findViewById(R.id.asc_current_course);
	my_tv.setText("Current Course: "+msd.current_course);
	
	gc_list.setOnItemClickListener(new OnItemClickListener() {
	      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
//	        String selectedFromList =(String) (gc_list.getItemAtPosition(myItemInt));
		  msd.current_course=gc_list.getItemAtPosition(myItemInt).toString();
	    	my_tv=(TextView)findViewById(R.id.asc_current_course);
	    	my_tv.setText("Current Course: "+msd.current_course);
	      }                 
	});
	//	ScrollView sv = new ScrollView(this);
	//	LinearLayout ll = new LinearLayout(this);
	//	TextView tv = new TextView(this);
	//	TextView tv1 = new TextView(this);
	//
	//	ll.setOrientation(LinearLayout.VERTICAL);
	//	sv.addView(ll);
	//	tv1.setText("Checking for course file");
	//	if (!my_course_file.isFile())
	//	    {
	//		tv.setText("Course file does not exist. You need to create a course");
	//		try {
	//		    my_course_file.createNewFile();
	//		}	
	//		catch (IOException ioe) 
	//		    {
	//			ioe.printStackTrace();
	//		    }
	//	    }
	//	else
	//	    {
	//		tv.setText("Course file exists.");
	//	    }
	//	ll.addView(tv1);
	//	ll.addView(tv);
	//	Button b_done = new Button(this);
	//	//		b_done.setWidth(LayoutParams.WRAP_CONTENT);
	//	b_done.setText("Done");
	//	b_done.setBackgroundColor(this.getResources().getColor(android.R.color.holo_green_light));
	//
	//	ll.addView(b_done);
	//	this.setContentView(sv);
	//	b_done.setOnClickListener(new View.OnClickListener() {
	//			
	//		@Override
	//		public void onClick(View v) {
	//		    // TODO Auto-generated method stub
	//		    Done(v);
	//		}
	//	    });
	//		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.select_course, menu);
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
    	intent.putExtra("myobj",msd);
    	setResult(RESULT_OK, intent);
	finish();
    }
    public void Save(View view)
    {
    	context=getApplicationContext();
    	my_et = (EditText)findViewById(R.id.asc_confirm_code);
    	int tmp = Msd_common_methods.catch_parseInt(my_et.getText().toString());
    	if (tmp == 1234)
    	{
    	ListView gc_list = (ListView)findViewById(R.id.list);
	@SuppressWarnings("unchecked")
	    ArrayAdapter<golf_course_front_end> adapter = (ArrayAdapter<golf_course_front_end>) gc_list.getAdapter();
	golf_course_front_end golf_course = null;
	// save the new comment to the database
	
	if (datasource.checkGCs(msd.current_course)==0)//(Boolean.TRUE) //
	    {
		golf_course = datasource.createGolfCourse(msd.current_course);
		adapter.add(golf_course);
		adapter.notifyDataSetChanged();
	    }
	datasource.add_golf_course_table(msd.current_course, msd.course);
			toast = Toast.makeText(context, "Saved", Toast.LENGTH_SHORT);
    	}
    	else
    	{
     		toast = Toast.makeText(context, "Invalid Confirmation Code", Toast.LENGTH_SHORT);
    	}
		toast.show();
		my_et.setText(Integer.toString(0));
	       }


    public void Load(View view)
    {
    	context=getApplicationContext();
    	my_et = (EditText)findViewById(R.id.asc_confirm_code);
    	int tmp = Msd_common_methods.catch_parseInt(my_et.getText().toString());
    	if (tmp == 1234)
    	{
		msd.course = datasource.get_golf_course_table(msd.current_course);
 		toast = Toast.makeText(context, "Course Loaded", Toast.LENGTH_SHORT);
    	}
    	else
    	{
     		toast = Toast.makeText(context, "Invalid Confirmation Code", Toast.LENGTH_SHORT);	
    	}
		toast.show();
		my_et.setText(Integer.toString(0));
    }

    @Override
    protected void onResume() {
	datasource.open();
	super.onResume();
    }
    
    @Override
    protected void onPause() {
	datasource.close();
	super.onPause();
    }
    
}
