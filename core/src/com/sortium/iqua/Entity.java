package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity
{
	public Entity()
	{
		
	}
	
	public abstract void display(SpriteBatch sb);
	public abstract void update();
}
