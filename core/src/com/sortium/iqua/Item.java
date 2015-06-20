package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.TakeItemEvent;
import com.sortium.iqua.scene.Scene;

public class Item extends Button implements Entity 
{

	public Item(Scene owner, String pathTexture, String pathSound, int x,
			int y, int width, int height) 
	{
		super(owner, pathTexture, pathSound, x, y, width, height);
		this.eventid = "item.take";
		this.event = new TakeItemEvent(this);
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

}
