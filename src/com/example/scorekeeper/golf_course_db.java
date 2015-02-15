package com.example.scorekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class golf_course_db extends SQLiteOpenHelper {
	
	  public static final String TABLE_GCS = "golf_courses";
	private static final String DATABASE_NAME="golf_course.db";
	private static final int DATABASE_VERSION=1;
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_GC = "GC";

	
  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_GCS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_GC
      + " text not null);";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
		
	  public golf_course_db(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }

		  @Override
		  public void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(golf_course_db.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GCS);
		    onCreate(db);
		  }
		  
		  

}
