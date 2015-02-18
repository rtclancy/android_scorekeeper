package com.example.scorekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
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

    List<golf_course_front_end> values;
    ArrayAdapter<golf_course_front_end> adapter;
    ListView gc_list;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_course);
	msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
	//	my_et = (EditText)findViewById(R.id.asc_confirm_code);
	//	my_et.setText(Integer.toString(0));
	datasource = new golf_course_db_dao(this);
	datasource.open();
	values.addAll(datasource.getAllGCs());
	
	adapter = new ArrayAdapter<golf_course_front_end>(this,android.R.layout.simple_list_item_1, values);
	gc_list = (ListView)findViewById(R.id.list);
	gc_list.setAdapter(adapter);
	my_tv=(TextView)findViewById(R.id.asc_current_course);
	my_tv.setText("Current Course: "+msd.current_course);
	
	gc_list.setOnItemClickListener(new OnItemClickListener() {
	      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
//	        String selectedFromList =(String) (gc_list.getItemAtPosition(myItemInt));
		  msd.current_course=gc_list.getItemAtPosition(myItemInt).toString();
		  msd.current_course=msd.current_course.replaceAll("\"",""); //RTC_TBD
	    	my_tv=(TextView)findViewById(R.id.asc_current_course);
	    	my_tv.setText("Current Course: "+msd.current_course);
	      }                 
	});
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
	
	AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
	builder.setMessage("Are You Sure You Want To Save This Course? Old Course Data Of Same Name (if it exists) Will Be Overwritten!!!!")
	    .setTitle("Save Course");

	builder.setPositiveButton("Save Course", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int id) {
		    save_course();
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


    public void Load(View view)
    {
    	context=getApplicationContext();

	AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
	builder.setMessage("Are You Sure You Want To Load This Course? Current Course Data Will Be Discarded And If Not Previously Saved Will Be Lost!!!!")
	    .setTitle("Load Course");

	builder.setPositiveButton("Load Course", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int id) {
		    load_course();
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

    public void Delete(View view)
    {
    	context=getApplicationContext();

	AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
	builder.setMessage("Are You Sure You Want To Delete This Course?")
	    .setTitle("Delete Course");

	builder.setPositiveButton("Delete Course", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int id) {
		    delete_course();
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
    

    public void save_course ()
    {
	golf_course_front_end golf_course = null;
	
	if (datasource.checkGCs(msd.current_course)==0)//(Boolean.TRUE) //
	    {
		golf_course = datasource.createGolfCourse(msd.current_course);
		values.add(golf_course);
		adapter.notifyDataSetChanged();
	    }
	datasource.add_golf_course_table(msd.current_course, msd.course);
	toast = Toast.makeText(context, "Saved", Toast.LENGTH_SHORT);
	toast.show();
    }
    
    public void load_course()
    {
	msd.course = datasource.get_golf_course_table(msd.current_course);
	toast = Toast.makeText(context, "Course Loaded", Toast.LENGTH_SHORT);
	toast.show();
    }

   public void delete_course()
    {
	   golf_course_front_end my_golf_course;
	   my_golf_course=datasource.getGC(msd.current_course);
	if (datasource.checkGCs(msd.current_course)!=0)//(Boolean.TRUE) //
	    {
		if (values.remove(datasource.getGC(msd.current_course)))
		    {
			toast = Toast.makeText(context, "Found and Deleted", Toast.LENGTH_SHORT);
		    }
		else
		    {
			toast = Toast.makeText(context, "Not Found and Deleted", Toast.LENGTH_SHORT);
		    }

		datasource.delete_golf_course_table(msd.current_course);
		datasource.deleteGolfCourse(msd.current_course);
	//	values = datasource.getAllGCs();
		adapter.notifyDataSetChanged();
	    }
	else 
	    {
		toast = Toast.makeText(context, "Course Not Found", Toast.LENGTH_SHORT);
	    }
	toast.show();
    }
}
