package com.example.scorekeeper;

import java.io.Serializable;
import java.util.Date;

public class scorekeeper_data implements Serializable  {
    public String[] players = new String[4];

    public String current_course;
    public int current_player=0;
    public int current_hole=0;
    //    public int[] color=new int[4];

    //per player statistics
    public int[][] score = new int[4][18];
    public int[][] fwy = new int[4][18];
    public int[][] gir = new int[4][18];
    public int[][] distance_in = new int[4][18];

    public int[][] course =new int[5][18]; //[0]=par,[1]=hcp,[3]=blue,[2]=white,[4]=red
    
    private int byte_ctr;
    private byte[] msd_bytes;

    public static final int COURSE_PAR =   0;
    public static final int COURSE_HCP =   1;
    public static final int COURSE_BLUE =  2;
    public static final int COURSE_WHITE = 3;
    public static final int COURSE_RED =   4;
    public final int[] color = {
	android.R.color.holo_blue_light,
	android.R.color.holo_red_light,
	android.R.color.holo_orange_light,
	android.R.color.holo_purple
    };

    private void addStringToByteArray(String stringin)
    {
	byte[] tmp_bytes = new byte[32];
	int tmp;
	tmp_bytes=stringin.getBytes();
	tmp=tmp_bytes.length;
	msd_bytes[byte_ctr++]=(byte)tmp;
	for (int j=0;j<tmp;j++)
	    {
		msd_bytes[byte_ctr++]=tmp_bytes[j];
	    }
    }

    private String removeStringFromByteArray()
    {
	int tmp;
	tmp=msd_bytes[byte_ctr++];
	byte[] tmp_bytes = new byte[tmp];
	for (int j=0;j<tmp;j++)
	    {
		tmp_bytes[j]=msd_bytes[byte_ctr++];
	    }
	return new String(tmp_bytes);
    }

    private void add2dIntArrayToByteArray(int[][] int_array_2d)
    {
	for (int d1=0;d1<int_array_2d.length;d1++)
	    {
		add1dIntArrayToByteArray(int_array_2d[d1]);
	    }
    }
    private boolean compare2dIntArray(int[][] array_2d1, int[][] array_2d2)
    {
   	boolean result=Boolean.TRUE;
	if (array_2d1.length != array_2d2.length) result=Boolean.FALSE;
	if (result == Boolean.TRUE)
	    {
		for (int d1=0;d1<array_2d1.length;d1++)
		    {
			result=compare1dIntArray(array_2d1[d1],array_2d2[d1]);
		    }
	    }
	return result;
    }
 
    private boolean compare1dIntArray(int[] array_1d1, int[] array_1d2)
    {
    	boolean result=Boolean.TRUE;

	if (array_1d1.length != array_1d2.length) result=Boolean.FALSE;
	if (result == Boolean.TRUE)
	    {
		for (int d1=0;d1<array_1d1.length;d1++)
		    {
			if (array_1d1[d1] != array_1d2[d1]) result=Boolean.FALSE;
		    }
	    }
	return result;
    }

    private int[][] remove2dIntArrayFromByteArray(int[][] int_array_2d)
    {
    	int[][] out_int_array_2d = new int[int_array_2d.length][int_array_2d[0].length];
    	
	for (int d1=0;d1<int_array_2d.length;d1++)
	    {
		out_int_array_2d[d1]=remove1dIntArrayFromByteArray(int_array_2d[d1]);
	    }
		return out_int_array_2d;
    }
    private void add1dIntArrayToByteArray(int[] int_array_1d)
    {
	for (int d1=0;d1<int_array_1d.length;d1++)
	    {
		addIntToByteArray(int_array_1d[d1]);
	    }
    }
    private int[] remove1dIntArrayFromByteArray(int[] int_array_1d)
    {
    	int[] out_int_array_1d=new int[int_array_1d.length];
 	
	for (int d1=0;d1<int_array_1d.length;d1++)
	    {
		out_int_array_1d[d1]=removeIntFromByteArray();
	    }
		return out_int_array_1d;
    }
    private void addIntToByteArray(int intin)
    {
	msd_bytes[byte_ctr++]=(byte)((intin & 0x000000ff) >>  0);
	msd_bytes[byte_ctr++]=(byte)((intin & 0x0000ff00) >>  8);
	msd_bytes[byte_ctr++]=(byte)((intin & 0x00ff0000) >> 16);
	msd_bytes[byte_ctr++]=(byte)((intin & 0xff000000) >> 24);
    }
    private int removeIntFromByteArray()
    {
	int tmp_int=0;
	tmp_int|=(msd_bytes[byte_ctr++] & 0xff) <<  0;
	tmp_int|=(msd_bytes[byte_ctr++] & 0xff) <<  8;
	tmp_int|=(msd_bytes[byte_ctr++] & 0xff) << 16;
	tmp_int|=(msd_bytes[byte_ctr++] & 0xff) << 24;
	return tmp_int;
    }
    
    public void reconstructFromBytes(byte[] byte_array)
    {
	byte_ctr=0;
	msd_bytes=byte_array;
	//Convert player names to bytes and add length of each players name as prefix to name
	for (int i=0;i<4;i++)
	    {
		players[i]=removeStringFromByteArray();
	    }
	current_course=removeStringFromByteArray();
	current_player=removeIntFromByteArray();
	current_hole=removeIntFromByteArray();
	score=remove2dIntArrayFromByteArray(score);
	fwy=remove2dIntArrayFromByteArray(fwy);
	gir=remove2dIntArrayFromByteArray(gir);
	course=remove2dIntArrayFromByteArray(course);
	distance_in=remove2dIntArrayFromByteArray(distance_in);
    }
    
    public String print_scorecard_txt()
    {
    	String scorecard_txt=null;
		int out=0,in=0;
		Date todays_date = new Date();
		final String line_seperator = new String(new char[123]).replace("\0", "-");
		final String line_seperator2 = new String(new char[123]).replace("\0", "+");
    	
		scorecard_txt= line_seperator + "\n";
		scorecard_txt = scorecard_txt + this.current_course + "\n";
		scorecard_txt = scorecard_txt + todays_date.toString() + "\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";
    	////////
    	//header line, players holes, etc
    	////////////
    	scorecard_txt= scorecard_txt + String.format("%-12s", "Hole");
    	scorecard_txt= scorecard_txt + String.format("%1s", ":");
    	
    	for (int i=1;i<10;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",i);
    	}
		scorecard_txt=scorecard_txt + "  OUT |";
		for (int i=10;i<19;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",i);
    	}
		scorecard_txt=scorecard_txt + "  IN |";
		scorecard_txt=scorecard_txt + "  TOT |\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";
		/////////////////////////////////
		// Blue Distance Per Hole
		/////////////////////////////////
    	scorecard_txt= scorecard_txt + String.format("%-12s", "Blue");
    	scorecard_txt= scorecard_txt + String.format("%1s", ":");
    	for (int i=0;i<9;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_BLUE][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out(COURSE_BLUE));
		for (int i=9;i<18;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_BLUE][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%4d |",this.course_totals_in(COURSE_BLUE));
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out_in(COURSE_BLUE)) +"\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";
				
		/////////////////////////////////
		// White Distance Per Hole
		/////////////////////////////////
    	scorecard_txt= scorecard_txt + String.format("%-12s", "White");
    	scorecard_txt= scorecard_txt + String.format("%1s", ":");
    	for (int i=0;i<9;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_WHITE][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out(COURSE_WHITE));
		for (int i=9;i<18;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_WHITE][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%4d |",this.course_totals_in(COURSE_WHITE));
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out_in(COURSE_WHITE)) +"\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";

		/////////////////////////////////
		// RED Distance Per Hole
		/////////////////////////////////
    	scorecard_txt= scorecard_txt + String.format("%-12s", "Red");
    	scorecard_txt= scorecard_txt + String.format("%1s", ":");
    	for (int i=0;i<9;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_RED][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out(COURSE_RED));
		for (int i=9;i<18;i++)
    	{
    		scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_RED][i]);
    	}
		scorecard_txt=scorecard_txt + String.format("%4d |",this.course_totals_in(COURSE_RED));
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out_in(COURSE_RED)) +"\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";

		/////////////////////////////////
		// PAR Per Hole
		/////////////////////////////////
		scorecard_txt= scorecard_txt + String.format("%-12s", "PAR");
		scorecard_txt= scorecard_txt + String.format("%1s", ":");
		for (int i=0;i<9;i++)
		{
			scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_PAR][i]);
		}
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out(COURSE_PAR));
		for (int i=9;i<18;i++)
		{
			scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_PAR][i]);
		}
		scorecard_txt=scorecard_txt + String.format("%4d |",this.course_totals_in(COURSE_PAR));
		scorecard_txt=scorecard_txt + String.format("%5d |",this.course_totals_out_in(COURSE_PAR)) +"\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";

		/////////////////////////////////
		// HCP Per Hole
		/////////////////////////////////
		scorecard_txt= scorecard_txt + String.format("%-12s", "HCP");
		scorecard_txt= scorecard_txt + String.format("%1s", ":");
		for (int i=0;i<9;i++)
		{

			scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_HCP][i]);
			
		}
		scorecard_txt=scorecard_txt + String.format("%5s |"," ");
		for (int i=9;i<18;i++)
		{
			scorecard_txt=scorecard_txt + String.format("%3d |",this.course[COURSE_HCP][i]);
		}
		scorecard_txt=scorecard_txt + String.format("%4s |"," ");
		scorecard_txt=scorecard_txt + String.format("%5s |\n"," ");
		scorecard_txt=scorecard_txt + line_seperator + "\n";
		scorecard_txt=scorecard_txt + line_seperator2 + "\n";
		scorecard_txt=scorecard_txt + line_seperator + "\n";

		/////////////////////////////////
		//player scores
		/////////////////////////////////
		out=0;in=0;
		for (int j=0;j<4;j++)
		{
			scorecard_txt=scorecard_txt+String.format("%-12s",this.players[j]);
			scorecard_txt= scorecard_txt + String.format("%1s", ":");
			for (int i=0;i<9;i++)
			{
				scorecard_txt=scorecard_txt + String.format("%3d |",this.score[j][i]);
				out+=this.score[j][i];
			}
			scorecard_txt=scorecard_txt + String.format("%5s |",out);
			for (int i=9;i<18;i++)
			{
				scorecard_txt=scorecard_txt + String.format("%3d |",this.score[j][i]);
				in+=this.score[j][i];
			}
			scorecard_txt=scorecard_txt + String.format("%4d |",in);
			scorecard_txt=scorecard_txt + String.format("%5d |\n",out+in);
			scorecard_txt=scorecard_txt + line_seperator + "\n";
		}
		
    	return scorecard_txt;
    }

     public boolean Equals(scorekeeper_data compare_obj)
    {
	boolean result=Boolean.TRUE;
	for (int i=0;i<4;i++)
	    {
		if ((players[i].compareTo(compare_obj.players[i]))!=0) result&=Boolean.FALSE;
	    }
	if ((current_course.compareTo(compare_obj.current_course))!=0) result=Boolean.FALSE;
	if (current_player != compare_obj.current_player) result=Boolean.FALSE;
	if (current_hole != compare_obj.current_hole) result=Boolean.FALSE;
	
	result&=compare2dIntArray(this.score,compare_obj.score);
	result&=compare2dIntArray(this.fwy,compare_obj.fwy);
	result&=compare2dIntArray(this.gir,compare_obj.gir);
	result&=compare2dIntArray(this.course,compare_obj.course);
	return result;
    }
    public byte[] toBytes()
    {
	msd_bytes = new byte[1024*2];
	byte_ctr=0;
	//Convert player names to bytes and add length of each players name as prefix to name
	for (int i=0;i<4;i++)
	    {
		addStringToByteArray(players[i]);
	    }
	addStringToByteArray(current_course);
	addIntToByteArray(current_player);
	addIntToByteArray(current_hole);
	add2dIntArrayToByteArray(score);
	add2dIntArrayToByteArray(fwy);
	add2dIntArrayToByteArray(gir);
	add2dIntArrayToByteArray(course);
	add2dIntArrayToByteArray(distance_in);
	return msd_bytes;
    }

    public int course_totals_out(int tees)
    {
	int total=0;
	for(int hole=0;hole<9;hole++)
	    {
		total+=course[tees][hole];
	    }
	return total;
    }

    public int course_totals_in(int tees)
    {
	int total=0;
	for(int hole=9;hole<18;hole++)
	    {
		total+=course[tees][hole];
	    }
	return total;
    }

    public int course_totals_out_in(int tees)
    {
	int total=0;
	total+=course_totals_out(tees);
	total+=course_totals_in(tees);
	return total;
    }

    
    public scorekeeper_data() //constructor
    {
	int player,hole;
	

  	players[0]="Moe";
	players[1]="Larry";
	players[2]="Schemp";
	players[3]="Curly";
	current_course="Guilford Lakes";
	for (player=0;player<4;player++)
	    {
		for (hole=0;hole<18;hole++)
		    {
			score[player][hole]=0;
			fwy[player][hole]=0;
			gir[player][hole]=0;
		    }
	    }
	for (player=0;player<5;player++)
	    {
		for (hole=0;hole<18;hole++)
		    {
			course[player][hole]=0;
		    }
	    }
    }

    public void resetAllScores()
    {
	for (int player=0;player<4;player++)
	    {
		for (int hole=0;hole<18;hole++)
		    {
			this.score[player][hole]=0;
			this.fwy[player][hole]=0;
			this.gir[player][hole]=0;
			this.distance_in[player][hole]=0;
		    }
	    }
    }
    public int  fwys_hit (int player)
    {
	int fwys_hit=0;
	for (int hole=0;hole<18;hole++)
	{
	    fwys_hit+=this.fwy[player][hole];
	}    
	return fwys_hit;
    }
    public int  girs (int player)
    {
	int girs=0;
	for (int hole=0;hole<18;hole++) 
	{
	    girs+=this.gir[player][hole];
	}    
	return girs;
    }


}
