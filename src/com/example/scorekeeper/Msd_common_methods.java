
package com.example.scorekeeper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class Msd_common_methods {
	final static public String[] possible_scores={"0","1","2","3","4","5","6","7","8","9"};


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public static int catch_parseInt(String intstring)
    {
	try 
	    {
		return Integer.parseInt(intstring);
	    }
	catch (NumberFormatException nfe)
	    {
		return 0;
	    }
    }


	public static void write_msd_file(byte[] byte_array, Context Context)
	{

		try 
		{
			FileOutputStream fos = Context.openFileOutput("scorekeeper_msd.dat", Context.MODE_PRIVATE);
			fos.write(byte_array);
			fos.close();
		}
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		
	}

	public static byte[] read_msd_file(Context Context)
	{
byte[] byte_array=new byte[1024*2];	
int tmp_byte;
int byte_ctr=0;
		try 
		{
			FileInputStream fis = Context.openFileInput("scorekeeper_msd.dat");
			while (Boolean.TRUE)
			{
			tmp_byte=fis.read();
				if (tmp_byte == -1)
				{
					break;
				}
				else
				{
					byte_array[byte_ctr++]=(byte)tmp_byte;
				}
			}
			fis.close();
		}
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		return byte_array;
	}
}
