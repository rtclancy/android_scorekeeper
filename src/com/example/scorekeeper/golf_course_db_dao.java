package com.example.scorekeeper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class golf_course_db_dao {
    private SQLiteDatabase database;
    private String[] allColumns = { golf_course_db.COLUMN_ID,
				    golf_course_db.COLUMN_GC };
    private String[] golf_course_columns = {"hole","par","hcp","blue","white","red"};
	
    golf_course_db dbHelper;
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }
	
    public golf_course_db_dao(Context context) {
	dbHelper = new golf_course_db(context);
    }

    public void open() throws SQLException {
	database = dbHelper.getWritableDatabase();
    }

    public void close() {
	dbHelper.close();
    }

    public golf_course_front_end createGolfCourse(String golf_course) {
	ContentValues values = new ContentValues();
	values.put(golf_course_db.COLUMN_GC, "\""+golf_course+"\""); //RTC_TBD
	long insertId = database.insert(golf_course_db.TABLE_GCS, null,
					values);
	Cursor cursor = database.query(golf_course_db.TABLE_GCS,
				       allColumns, golf_course_db.COLUMN_ID + " = " + insertId, null,
				       null, null, null);
	cursor.moveToFirst();
	golf_course_front_end newGolfCourse = cursorToGolfCourse(cursor);
	cursor.close();
	return newGolfCourse;
    }

    public void deleteGolfCourse(String golf_course) {
    	String [] my_string = new String[1];
	my_string[0]="\""+golf_course+"\"";
	int tmp=database.delete(golf_course_db.TABLE_GCS, golf_course_db.COLUMN_GC + " = ?",my_string);
    }

    public golf_course_front_end cursorToGolfCourse(Cursor cursor) {
	golf_course_front_end golf_course = new golf_course_front_end();
	golf_course.setId(cursor.getLong(0));
	golf_course.setGolfCourse(cursor.getString(1));
	return golf_course;
    }

    private int[][] cursorToCourse(Cursor cursor)
    {
	int [][] tmp=new int[5][18];
	cursor.moveToFirst();
	for (int hole=0;hole<18;hole++)
	    {
		tmp[scorekeeper_data.COURSE_PAR]  [hole]=cursor.getInt(1);
		tmp[scorekeeper_data.COURSE_HCP]  [hole]=cursor.getInt(2);
		tmp[scorekeeper_data.COURSE_BLUE] [hole]=cursor.getInt(3);
		tmp[scorekeeper_data.COURSE_WHITE][hole]=cursor.getInt(4);
		tmp[scorekeeper_data.COURSE_RED]  [hole]=cursor.getInt(5);
		cursor.moveToNext();
	    }
	return tmp;
    }

    public List<golf_course_front_end> getAllGCs() {
	List<golf_course_front_end> golf_courses = new ArrayList<golf_course_front_end>();

	Cursor cursor = database.query(golf_course_db.TABLE_GCS	,
				       allColumns, null, null, null, null, null);

	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    golf_course_front_end golf_course = cursorToGolfCourse(cursor);
	    golf_courses.add(golf_course);
	    cursor.moveToNext();
	}
	// make sure to close the cursor
	cursor.close();
	return golf_courses;
    }

    public golf_course_front_end getGC(String golf_course)
    {
	String [] my_string =new String[1];
		  
	my_string[0]="\""+golf_course+"\"";

	Cursor cursor = database.query(golf_course_db.TABLE_GCS	,
				       allColumns, golf_course_db.COLUMN_GC + " = ?", my_string, null, null, null);
	cursor.moveToFirst();
	return cursorToGolfCourse(cursor);
    }

    public int checkGCs(String golf_course) {
	String [] my_string =new String[1];
		  
	my_string[0]="\""+golf_course+"\"";
		 
	Cursor cursor = database.query(golf_course_db.TABLE_GCS	,
				       allColumns, golf_course_db.COLUMN_GC + " = ?", my_string, null, null, null);
	return cursor.getCount();
    }

    public void delete_golf_course_table (String golf_course)
    {
	golf_course="\""+golf_course+"\""; //RTC_TBD
	database.execSQL("drop table if exists "+golf_course+";"); //delete all existing entries
    }

    public void add_golf_course_table (String golf_course, int[][] course_details)
    {
	golf_course="\""+golf_course+"\""; //RTC_TBD

	try {
	database.execSQL("create table "+golf_course+ "(" + "_id integer primary key autoincrement," + 
			     "hole text not null, par text not null, hcp text not null, blue txt not null, white text not null, red text not null);");
	}
	catch (SQLException sqle)
	    {
		;
	    }
	database.execSQL("delete from "+golf_course+";"); //delete all existing entries
	
	for (int hole=0;hole<18;hole++)
	    {
		ContentValues values = new ContentValues();
		values.put("hole", hole);
		values.put("par", course_details  [scorekeeper_data.COURSE_PAR]  [hole]);
		values.put("hcp", course_details  [scorekeeper_data.COURSE_HCP]  [hole]);
		values.put("blue", course_details [scorekeeper_data.COURSE_BLUE] [hole]);
		values.put("white", course_details[scorekeeper_data.COURSE_WHITE][hole]);
		values.put("red", course_details  [scorekeeper_data.COURSE_RED]  [hole]);
		database.insert(golf_course, null,
			        values); 
	    }
    }
    public int [][] get_golf_course_table (String golf_course)
    {
	int [][] tmp = new int[5][18];
	golf_course="\""+golf_course+"\""; //RTC_TBD

	Cursor cursor = database.query(golf_course,golf_course_columns, null,null,null,null,null);
	tmp[0][0]=cursor.getCount();
	tmp=cursorToCourse(cursor);
	cursor.close();
	return tmp;
    }
}
//		    if (cursor.getCount() > 1) //golf course was found in database
//		    {
//		    	return Boolean.TRUE;
//		    }
//		    else
//	    {
//	    	return Boolean.FALSE;
//	    }
//	  }
