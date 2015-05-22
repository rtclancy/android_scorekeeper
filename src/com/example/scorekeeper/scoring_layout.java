package com.example.scorekeeper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.FrameLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.HorizontalScrollView;

public class scoring_layout extends Activity { 
    HorizontalScrollView my_sv;
    HorizontalScrollView.LayoutParams sv_parameters;
    TableLayout.LayoutParams layout_parameters;
    TableLayout layout;
    LinearLayout llayout;
    TableRow[] header_row;
    TableRow course_row;
    TableRow.LayoutParams header_row_lp;
    TextView[][] header_cells;
    TextView tv_course_name;
    public Button b_done;
    public Button b_clear_scores;
    public Button b_email;
    int header_row_width;
    scorekeeper_data msd;
    //    enum e_row {hole,par,hcp};
    
    
    final int hole_row=0;
    final int blue_row=1;
    final int white_row=2;
    final int red_row=3;
    final int par_row=4;
    final int hcp_row=5;
    final int p1_row=6;
    final int p2_row=7;
    final int p3_row=8;
    final int p4_row=9;
    //done row is 10
    
    
    
    scoring_layout(Context context,scorekeeper_data msd)
    {
	int cell,row;
    this.msd = msd;
	
    course_row = new TableRow(context);
    header_row_lp = new TableRow.LayoutParams();
    header_row_lp.width =TableRow.LayoutParams.MATCH_PARENT;
    header_row_lp.height =TableRow.LayoutParams.MATCH_PARENT;
    header_row_lp.span=10;
    course_row.setLayoutParams(header_row_lp);
	tv_course_name = new TextView(context);
    tv_course_name.setText(msd.current_course);
	tv_course_name.setBackgroundColor(Color.WHITE);
	tv_course_name.setTextColor(Color.BLACK);
    course_row.addView(tv_course_name);

	sv_parameters=new HorizontalScrollView.LayoutParams(HorizontalScrollView.LayoutParams.MATCH_PARENT,HorizontalScrollView.LayoutParams.MATCH_PARENT);
	sv_parameters.height=HorizontalScrollView.LayoutParams.MATCH_PARENT;
	sv_parameters.width=HorizontalScrollView.LayoutParams.MATCH_PARENT;

	my_sv = new HorizontalScrollView(context);
	my_sv.setLayoutParams(sv_parameters);

	layout_parameters = new TableLayout.LayoutParams();
	layout_parameters.height=TableLayout.LayoutParams.MATCH_PARENT;
	layout_parameters.width=TableLayout.LayoutParams.MATCH_PARENT;
	
	llayout = new LinearLayout(context);
	layout = new TableLayout(context);
	layout.setLayoutParams(layout_parameters);
	layout.setBackgroundColor(context.getResources().getColor(android.R.color.black));
	layout.setStretchAllColumns(true);

	header_row = new TableRow[13];
	for (row=0;row<13;row++)
	    {
		header_row[row] = new TableRow(context);
		//		header_row[row].setBackgroundColor(Color.RED);
		header_row_lp = new TableRow.LayoutParams();
		header_row_lp.width =TableRow.LayoutParams.MATCH_PARENT;
		header_row_lp.height =TableRow.LayoutParams.MATCH_PARENT;
		//	header_row_lp.span=1;
		header_row[row].setLayoutParams(header_row_lp);
		//cell_width=header_row[row].getWidth();
	    }
			
	header_cells=new TextView[10][25]; //hole
	//header_row_lp.height=TableRow.LayoutParams.MATCH_PARENT;
	//header_row_lp.width=20;
	
	for (row=0;row < 10; row++)
	    {
		for (cell=0;cell<25;cell++)
		    {
			header_cells[row][cell] = new TextView(context);
			header_cells[row][cell].setGravity(Gravity.RIGHT);
			if (row==red_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.RED);
				header_cells[row][cell].setTextColor(Color.WHITE);
			    }
			if (row==blue_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.BLUE);
				header_cells[row][cell].setTextColor(Color.WHITE);
			    }
			if (row==white_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.WHITE);
				header_cells[row][cell].setTextColor(Color.BLACK);
			    }
			if (row==par_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.GREEN);
				header_cells[row][cell].setTextColor(Color.BLACK);
			    }
			if (row==hole_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.YELLOW);
				header_cells[row][cell].setTextColor(Color.BLACK);
			    }
			if (row==hcp_row)
			    {
				header_cells[row][cell].setBackgroundColor(Color.BLACK);
				header_cells[row][cell].setTextColor(Color.WHITE);
			    }
			if (row==p1_row)
			    {
				header_cells[row][cell].setTextColor(Color.WHITE);
				header_cells[row][cell].setBackgroundColor(context.getResources().getColor(msd.color[0]));
			    }
			if (row==p2_row)
			    {
				header_cells[row][cell].setTextColor(Color.WHITE);
				header_cells[row][cell].setBackgroundColor(context.getResources().getColor(msd.color[1]));
			    }
			if (row==p3_row)
			    {
				header_cells[row][cell].setTextColor(Color.WHITE);
				header_cells[row][cell].setBackgroundColor(context.getResources().getColor(msd.color[2]));
			    }
			if (row==p4_row)
			    {
				header_cells[row][cell].setTextColor(Color.WHITE);
				header_cells[row][cell].setBackgroundColor(context.getResources().getColor(msd.color[3]));
			    }
		    }
	    }

	header_cells[hole_row][0].setGravity(Gravity.LEFT);
	header_cells[hole_row][ 0].setText("Hole    ");
	header_cells[hole_row][ 1].setText("       1");
	header_cells[hole_row][ 2].setText("       2");
	header_cells[hole_row][ 3].setText("       3");
	header_cells[hole_row][ 4].setText("       4");
	header_cells[hole_row][ 5].setText("       5");
	header_cells[hole_row][ 6].setText("       6");
	header_cells[hole_row][ 7].setText("       7");
	header_cells[hole_row][ 8].setText("       8");
	header_cells[hole_row][ 9].setText("       9");
	header_cells[hole_row][10].setText("     OUT");
	header_cells[hole_row][11].setText("      10");
	header_cells[hole_row][12].setText("      11");
	header_cells[hole_row][13].setText("      12");
	header_cells[hole_row][14].setText("      13");
	header_cells[hole_row][15].setText("      14");
	header_cells[hole_row][16].setText("      15");
	header_cells[hole_row][17].setText("      16");
	header_cells[hole_row][18].setText("      17");
	header_cells[hole_row][19].setText("      18");
	header_cells[hole_row][20].setText("      IN");
	header_cells[hole_row][21].setText("     TOT");
	header_cells[hole_row][22].setText("     Hcp");
	header_cells[hole_row][23].setText("     NET");
	header_cells[hole_row][24].setText("     ADJ");
	
	header_cells[p1_row][ 0].setText(this.msd.players[0]);
	update_scores();
	{
	    int tmp_hole=0;
	    for (cell=0;cell<22;cell++)
		{
		    switch (cell)
			{
			case 0:{header_cells[red_row][cell].setGravity(Gravity.LEFT);header_cells[red_row][cell].setText("Red");break;}
			case 10:{header_cells[red_row][cell].setText(Integer.toString(msd.course_totals_out(msd.COURSE_RED)));break;}	
			case 20:{header_cells[red_row][cell].setText(Integer.toString(msd.course_totals_in(msd.COURSE_RED)));break;}
			case 21:{header_cells[red_row][cell].setText(Integer.toString(msd.course_totals_out_in(msd.COURSE_RED)));break;}
			default: {header_cells[red_row][cell].setText(Integer.toString(msd.course[msd.COURSE_RED][tmp_hole]));tmp_hole++;break;}
			}
		}
	    
	    tmp_hole=0;
	    for (cell=0;cell<22;cell++)
		{
		    switch (cell)
			{
			case 0:{header_cells[white_row][cell].setGravity(Gravity.LEFT);header_cells[white_row][cell].setText("White");break;}
			case 10:{header_cells[white_row][cell].setText(Integer.toString(msd.course_totals_out(msd.COURSE_WHITE)));break;}	
			case 20:{header_cells[white_row][cell].setText(Integer.toString(msd.course_totals_in(msd.COURSE_WHITE)));break;}
			case 21:{header_cells[white_row][cell].setText(Integer.toString(msd.course_totals_out_in(msd.COURSE_WHITE)));break;}
			default: {header_cells[white_row][cell].setText(Integer.toString(msd.course[msd.COURSE_WHITE][tmp_hole]));tmp_hole++;break;}
			}
		}
	    tmp_hole=0;
	    for (cell=0;cell<22;cell++)
		{
		    switch (cell)
			{
			case 0:{header_cells[blue_row][cell].setGravity(Gravity.LEFT);header_cells[blue_row][cell].setText("Blue");break;}
			case 10:{header_cells[blue_row][cell].setText(Integer.toString(msd.course_totals_out(msd.COURSE_BLUE)));break;}	
			case 20:{header_cells[blue_row][cell].setText(Integer.toString(msd.course_totals_in(msd.COURSE_BLUE)));break;}
			case 21:{header_cells[blue_row][cell].setText(Integer.toString(msd.course_totals_out_in(msd.COURSE_BLUE)));break;}
			default: {header_cells[blue_row][cell].setText(Integer.toString(msd.course[msd.COURSE_BLUE][tmp_hole]));tmp_hole++;break;}
			}
		}
	    tmp_hole=0;
	    for (cell=0;cell<21;cell++)
		{
		    switch (cell)
			{
			case 0:{header_cells[hcp_row][cell].setGravity(Gravity.LEFT);header_cells[hcp_row][cell].setText("Hcp");break;}
			case 10:
			case 20:{header_cells[hcp_row][cell].setText("  ");break;}
			default: {header_cells[hcp_row][cell].setText(Integer.toString(msd.course[msd.COURSE_HCP][tmp_hole]));tmp_hole++;break;}
			}
		}
	    tmp_hole=0;
	    for (cell=0;cell<22;cell++)
		{
		    switch (cell)
			{
			case 0:{header_cells[par_row][cell].setGravity(Gravity.LEFT);header_cells[par_row][cell].setText("Par");break;}
			case 10:{header_cells[par_row][cell].setText(Integer.toString(msd.course_totals_out(msd.COURSE_PAR)));break;}	
			case 20:{header_cells[par_row][cell].setText(Integer.toString(msd.course_totals_in(msd.COURSE_PAR)));break;}
			case 21:{header_cells[par_row][cell].setText(Integer.toString(msd.course_totals_out_in(msd.COURSE_PAR)));break;}
			default: {header_cells[par_row][cell].setText(Integer.toString(msd.course[msd.COURSE_PAR][tmp_hole]));tmp_hole++;break;}
			}
		}
	}

	b_done=new Button(context);
	b_done.setText("Done");
	b_done.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
	
	b_clear_scores=new Button(context);
	b_clear_scores.setText("Clear All Scores");
	b_clear_scores.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));

	b_email=new Button(context);
	b_email.setText("Email Scores");
	b_email.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
	//	b_done.setOnClickListener(new View.OnClickListener() {
	//		
	//		@Override
	//		public void onClick(View v) {
	//		    // TODO Auto-generated method stub
	//		    Done(v);
	//		}
	//	    });
	//	
	layout.addView(course_row);
	for (row=0;row<10;row++)
	    {
		for (cell=0;cell<22;cell++)
		    {
			header_row[row].addView(header_cells[row][cell]);
		    }
		layout.addView(header_row[row]);
	    }
	header_row[10].addView(b_done);
	header_row[11].addView(b_clear_scores);
	header_row[12].addView(b_email);
	
	layout.addView(header_row[10]);
	layout.addView(header_row[11]);
	layout.addView(header_row[12]);
	my_sv.addView(layout);
	llayout.addView(my_sv);
    }


    
    //    public void Done(View view)
    //    {
    //    	
    //    	Intent intent = new Intent(this, MainActivity.class);
    //    	intent.putExtra("myobj",msd);
    //    	setResult(RESULT_OK, intent);
    //    	finish();
    //      }
    //

    public void update_scores ()
    {
	for (int player=0;player<4;player++)
	    {
		int irow=0,out=0,in=0;
		switch (player)
		    {
		    case 0: {irow=p1_row;break;}
		    case 1: {irow=p2_row;break;}
		    case 2: {irow=p3_row;break;}
		    case 3: {irow=p4_row;break;}
		    }
		header_cells[irow][0].setGravity(Gravity.LEFT);
		header_cells[irow][ 0].setText(this.msd.players[player]);
		for (int cell=1;cell<10;cell++)
		    {
			header_cells[irow][cell].setText(Integer.toString(msd.score[player][cell-1]));
			out+=msd.score[player][cell-1];
		    }
		header_cells[irow][10].setText(Integer.toString(out)); //out total
		for (int cell=11;cell<20;cell++)
		    {
			header_cells[irow][cell].setText(Integer.toString(msd.score[player][cell-2]));
			in+=msd.score[player][cell-2];
		    }
		header_cells[irow][20].setText(Integer.toString(in)); //in total
		header_cells[irow][21].setText(Integer.toString(in+out)); //in total

		header_cells[irow][22].setText("   ");
		header_cells[irow][23].setText("   ");
		header_cells[irow][24].setText("   ");
	    }
    }
}
