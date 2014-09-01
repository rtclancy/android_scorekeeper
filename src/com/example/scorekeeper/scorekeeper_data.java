package com.example.scorekeeper;

import java.io.Serializable;

public class scorekeeper_data implements Serializable  {
	String[] players = new String[4];

	public scorekeeper_data() //constructor
	{
		players[0]="Moe";
		players[1]="Larry";
		players[2]="Schemp";
		players[3]="Curly";
	}
}
