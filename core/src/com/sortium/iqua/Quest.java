package com.sortium.iqua;

import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.scene.Scene;

public class Quest {
	static long id_count = 0;
	
	protected String description;
	protected String name;
	protected long id;
	protected boolean taken;  
	
	public Quest(Scene owner, String name, String description) 
	{
		this.description=description;
		this.name=name;
	}
	
	public Quest(Scene owner, String pathTexture, String pathSound, int x,
			int y, int width, int height, String name, String description) 
	{
		super();
		
		this.name = name;
		this.description = description;
		
		Item.id_count++;
		this.id = Item.id_count;
		
		this.taken = false;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public boolean taken()
	{
		return this.taken;
	}
	
	public void taken(boolean b)
	{
		this.taken = b;
	}
	
}