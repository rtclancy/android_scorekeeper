package com.example.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button my_button;
	TextView my_tview;
	private scorekeeper_data msd;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	byte[] msd_bytes=new byte[1024*2];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        	msd=new scorekeeper_data();
 //       	msd.reconstructFromBytes(msd_bytes);
        	 
		update_buttons();
    }
    @Override
    protected void onStop()
    {
    	super.onStop();
    	Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    }
    
    @Override
    protected void onStart()
    {
    	byte[] msd_bytes=new byte[1024*2];
    	super.onStart();
    	msd_bytes=Msd_common_methods.read_msd_file(this);
    	msd.reconstructFromBytes(msd_bytes);
    	update_buttons();
    }
    
   public void incr_current_hole(View view)
    {
   	if (msd.current_hole<17)
   		msd.current_hole++;
	my_button=(Button)findViewById(R.id.am_b_current_hole);
	my_button.setText("View/Edit\nHole:"+Integer.toString(msd.current_hole+1));
    }
    public void decr_current_hole(View view)
    {
      	if (msd.current_hole>0)
      		 	msd.current_hole--;
	my_button=(Button)findViewById(R.id.am_b_current_hole);
	my_button.setText("View/Edit\nHole:"+Integer.toString(msd.current_hole+1));
    }

    public void editPlayers(View view)
    {
    	Intent intent = new Intent(this, EnterPlayers.class);
    	intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.UPDATE_PLAYERS_RC);
    	
    }
    public void enterAllScores(View view)
    {
    	Intent intent = new Intent(this, EnterAllScores.class);
    	intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.UPDATE_SCORE_RC);
    }
    public void selectCourse(View view)
    {
    	Intent intent = new Intent(this, SelectCourse.class);
    	intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.SELECT_COURSE_RC);
    	
    }

    public void editCourse(View view)
    {
    	Intent intent = new Intent(this, EditCourse.class);
    	intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.SELECT_COURSE_RC);
    }
    public void scoreCard(View view)
    {
    	Intent intent = new Intent(this, Scoring.class);
   	    intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.VIEW_SCORECARD_RC);
    }
    public void updateScore_p0(View view)
    {
	msd.current_player=0;
	updateScore(view);
    }
    public void updateScore_p1(View view)
    {
	msd.current_player=1;
	updateScore(view);
    }
    public void updateScore_p2(View view)
    {
	msd.current_player=2;
	updateScore(view);
    }
    public void updateScore_p3(View view)
    {
	msd.current_player=3;
	updateScore(view);
    }
    public void updateScore(View view)
    {
     	Intent intent = new Intent(this, EnterScore.class);
   	intent.putExtra("myobj",msd);
    	startActivityForResult(intent,Constants.UPDATE_SCORE_RC);
   }
    public void onActivityResult(int request_code,int result_code, Intent intent)
    {
    	Button my_button;
	if (result_code == RESULT_OK)
	    {
	    	if (request_code == Constants.UPDATE_PLAYERS_RC)
		    {
    			msd=(scorekeeper_data) intent.getSerializableExtra("myobj");
			Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    			update_buttons();
 		    }
	    	if (request_code == Constants.SELECT_COURSE_RC)
		    {
    			msd=(scorekeeper_data) intent.getSerializableExtra("myobj");
			Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    			update_buttons();
		    }
	    	if (request_code == Constants.UPDATE_SCORE_RC)
		    {
    			msd=(scorekeeper_data) intent.getSerializableExtra("myobj");
			//if (msd.score[0][msd.current_hole]==1234) msd.resetAllScores();
			Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
    			update_buttons();
		    }
	    	if (request_code == Constants.VIEW_SCORECARD_RC)
	    	{
	    		msd=(scorekeeper_data) intent.getSerializableExtra("myobj");
			Msd_common_methods.write_msd_file(this.msd.toBytes(),this);
	    	}
	    }
	//    	msd=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
    }
    
    private void update_buttons()
    {
	my_button=(Button)findViewById(R.id.am_b_p0);
	my_button.setText(msd.players[0]);
	my_button=(Button)findViewById(R.id.am_b_p1);
	my_button.setText(msd.players[1]);
	my_button=(Button)findViewById(R.id.am_b_p2);
	my_button.setText(msd.players[2]);
	my_button=(Button)findViewById(R.id.am_b_p3);
	my_button.setText(msd.players[3]);
	my_button=(Button)findViewById(R.id.am_b_current_hole);
	my_button.setText("View/Edit\nHole:"+Integer.toString(msd.current_hole+1));
    }

}
