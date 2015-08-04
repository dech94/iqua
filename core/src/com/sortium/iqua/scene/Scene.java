package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sortium.iqua.Entity;
import com.sortium.iqua.IquaGame;

public class Scene
{
	protected ArrayList<Entity> entities;
	protected IquaGame game;
	protected long startTime = System.nanoTime();

	protected boolean activated;
	protected Vector2 pos;
	
	protected Texture background;
	
	public Scene(IquaGame game)
	{
		//ready = false;
		this.activated = false;
		this.entities = new ArrayList<Entity>();
		this.game = game;
		this.pos = new Vector2(0, 0);
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
			sb.draw(this.background,pos.x, pos.y);
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
	
	public void enable(){ this.activated = true; }
	public void disable(){ this.activated = false; }
	/*
	public boolean isReady()
	{
		return this.ready;
	}
	
	public void isReady(boolean b)
	{
		this.ready = b;
	}
	*/
	
}
