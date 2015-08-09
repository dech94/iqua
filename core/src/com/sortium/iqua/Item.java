package com.sortium.iqua;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.scene.Scene;

public class Item extends Button implements Entity 
{
	static long id_count = 0;
	
	protected String description;
	protected String name;
	protected long id;
	protected boolean taken;  
	
	public Item(Scene owner, String pathTexture, String pathSound, int x,
			int y, int width, int height, String name, String description) 
	{
		super(owner, pathTexture, pathSound, x, y, width, height);
		
		Reaction r = new Reaction("item.take", new GetEvent<Item>(this));
		this.reactions.add(r);
		
		this.name = name;
		this.description = description;
		
		Item.id_count++;
		this.id = Item.id_count;
		
		this.taken = false;
	}

	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.display(sb, camera);
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
	
	public boolean taken()
	{
		return this.taken;
	}
	
	public void taken(boolean b)
	{
		this.taken = b;
	}
	
}