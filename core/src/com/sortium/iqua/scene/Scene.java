package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Entity;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.EventManager;

public class Scene
{
	protected ArrayList<Entity> entities;
	protected EventManager eventManager;
	protected IquaGame game;
	protected long startTime = System.nanoTime();
	
	protected Texture background;
	
	public Scene(IquaGame game)
	{
		this.entities = new ArrayList<Entity>();
		this.game = game;
		this.eventManager = this.game.getEventManager();
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
	
}
