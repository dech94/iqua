package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene
{
	protected ArrayList<Entity> entities;
	protected Texture background;
	
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
		if( this.background != null )
		{
			sb.draw(this.background,0,0);
		}
		
		for( Entity entity : this.entities )
		{
			entity.display(sb);
		}
		
	}
	
}
