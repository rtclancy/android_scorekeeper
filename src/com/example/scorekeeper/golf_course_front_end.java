package com.example.scorekeeper;

public class golf_course_front_end {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 private long id;
	  private String golf_course;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getGolfCourse() {
	    return golf_course;
	  }

	  public void setGolfCourse(String golf_course) {
	      this.golf_course = golf_course.replaceAll("\"",""); //RTC_TBD
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return golf_course;
	  }

}
