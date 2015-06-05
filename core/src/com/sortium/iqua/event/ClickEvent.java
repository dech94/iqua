package com.sortium.iqua.event;

public class ClickEvent extends Event
{
	private int x;
	private int y;
	
	public ClickEvent(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
