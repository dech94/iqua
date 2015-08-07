package com.sortium.iqua.event;

import com.badlogic.gdx.math.Rectangle;

public class ClickEvent extends Event
{
	private Rectangle rect;
	
	public long time = System.nanoTime();
	
	public ClickEvent(int x, int y, int radius)
	{
		this.rect = new Rectangle( x, y, 1, 1);
		makeRadius(radius);
	}
	
	public ClickEvent(int x, int y)
	{
		this(x, y, 1);
	}
	
	public Rectangle getRect()
	{
		return this.rect;
	}
	
	public boolean on(Rectangle target)
	{
		return !(this.rect.x + this.rect.width < target.x
				|| this.rect.x > target.x + target.width
				|| this.rect.y > target.y + target.height
				|| this.rect.y + this.rect.height < target.y);
	}

	public void makeRadius(int radius)
	{
		this.rect.width = radius;
		this.rect.height = radius;
		this.rect.setCenter(this.rect.x, this.rect.y);
	}
}
