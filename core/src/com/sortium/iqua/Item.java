package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.scene.Scene;

public class Item extends Button implements Entity 
{
	protected String description;
	protected String name;
	protected Item me = this;
	
	public Item(Scene owner, String pathTexture, String pathSound, int x,
			int y, int width, int height, String name, String description) 
	{
		super(owner, pathTexture, pathSound, x, y, width, height);
		this.event = new GetEvent<Item>(this);
		this.eventid = "item.take";
		
		this.name = name;
		this.description = description;
				
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

}
