package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene
{
	ArrayList<Entity> entities;
	
	public Scene()
	{
		this.entities = new ArrayList<Entity>();
	}
	
	public void update()
	{
		for( Entity entity : this.entities )
		{
			entity.update();
		}
	}
	
	public void display(SpriteBatch sb)
	{
		for( Entity entity : this.entities )
		{
			entity.display(sb);
		}
	}
	
}
