package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Entity;
import com.sortium.iqua.IquaGame;

public class Scene
{
	protected ArrayList<Entity> entities;
	protected IquaGame game;
	protected long startTime = System.nanoTime();
	protected boolean ready;
	
	protected Texture background;
	
	public Scene(IquaGame game)
	{
		ready = false;
		this.entities = new ArrayList<Entity>();
		this.game = game;
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
	
	public IquaGame getGame()
	{
		return this.game;
	}
	
	public long getStartTime()
	{
		return this.startTime;
	}
	
	public void resetStartTime()
	{
		this.startTime = System.nanoTime();
	}
	
	public boolean isReady()
	{
		return this.ready;
	}
	
	public void isReady(boolean b)
	{
		this.ready = b;
	}
	
}
