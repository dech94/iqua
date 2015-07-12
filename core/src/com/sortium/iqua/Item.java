package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.scene.Scene;

public class Item extends Button implements Entity 
{
	static long id_count = 0;
	
	protected String description;
	protected String name;
	protected Item me = this;
	protected long id;
	protected boolean taken;
	
	public Item(Scene owner, String pathTexture, String pathSound, int x,
			int y, int width, int height, String name, String description) 
	{
		super(owner, pathTexture, pathSound, x, y, width, height);
		this.taken = false; 
		
		this.event = new GetEvent<Item>(this);
		this.eventid = "item.take";
		
		this.name = name;
		this.description = description;
		
		Item.id_count++;
		this.id = Item.id_count;
	}

	@Override
	public void display(SpriteBatch sb)
	{
		super.display(sb);
	}

	@Override
	public void update()
	{
		super.update();
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

	public boolean isTaken()
	{
		return this.taken;
	}
	
	public void take()
	{
		this.taken = true;
	}
	
}
