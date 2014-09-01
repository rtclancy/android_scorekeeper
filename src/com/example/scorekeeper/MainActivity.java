package com.example.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button my_view;
	private scorekeeper_data my_scorekeeper_data;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        	my_scorekeeper_data=new scorekeeper_data();
			my_view=(Button)findViewById(R.id.b_p0);
			my_view.setText(my_scorekeeper_data.players[0]);
			my_view=(Button)findViewById(R.id.b_p1);
			my_view.setText(my_scorekeeper_data.players[1]);
			my_view=(Button)findViewById(R.id.b_p2);
			my_view.setText(my_scorekeeper_data.players[2]);
			my_view=(Button)findViewById(R.id.b_p3);
			my_view.setText(my_scorekeeper_data.players[3]);
    }
    
    public void editPlayers(View view)
    {
    	Intent intent = new Intent(this, EnterPlayers.class);
    	intent.putExtra("myobj",my_scorekeeper_data);
    	startActivityForResult(intent,Constants.UPDATE_PLAYERS_RC);
    	
    }
    public void onActivityResult(int request_code,int result_code, Intent intent)
    {
    	Button my_view;
    	if (request_code == Constants.UPDATE_PLAYERS_RC)
    	{
    		if (result_code == RESULT_OK)
    		{
    			my_scorekeeper_data=(scorekeeper_data) intent.getSerializableExtra("myobj");
    			my_view=(Button)findViewById(R.id.b_p0);
    			my_view.setText(my_scorekeeper_data.players[0]);
    			my_view=(Button)findViewById(R.id.b_p1);
    			my_view.setText(my_scorekeeper_data.players[1]);
    			my_view=(Button)findViewById(R.id.b_p2);
    			my_view.setText(my_scorekeeper_data.players[2]);
    			my_view=(Button)findViewById(R.id.b_p3);
    			my_view.setText(my_scorekeeper_data.players[3]);
    		}
    	}
//    	my_scorekeeper_data=(scorekeeper_data) getIntent().getSerializableExtra("myobj");
    }
}
