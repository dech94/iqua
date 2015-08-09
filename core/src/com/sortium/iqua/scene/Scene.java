package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sortium.iqua.Entity;
import com.sortium.iqua.IquaGame;

public class Scene implements Entity, Comparable
{
	protected ArrayList<Entity> entities;
	protected IquaGame game;
	protected long startTime = System.nanoTime();

	protected boolean activated;
	protected Vector2 pos;
	protected Vector2 size;
	
	protected Texture background;
	
	public Scene(IquaGame game)
	{
		//ready = false;
		this.activated = false;
		this.entities = new ArrayList<Entity>();
		this.game = game;
		this.pos = new Vector2(0, 0);
		this.size = new Vector2(0, 0);
	}
	
	public void update()
	{
		for( Entity entity : this.entities )
		{
			entity.update();
		}
	}
	
	void setSize(int w, int h)
	{
		this.size = new Vector2(w, h);
	}
	
	void setSizeToBackground()
	{
		try
		{
			setSize(this.background.getWidth(), this.background.getHeight());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fullDisplay(SpriteBatch sb, OrthographicCamera camera)
	{
		if( this.background != null )
		{
			sb.draw(this.background,pos.x, pos.y, camera.viewportWidth, camera.viewportHeight);
		}
		
		for( Entity entity : this.entities )
		{
			entity.display(sb, camera);
		}
	}
	
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		if( this.background != null )
		{
			if( size.x == 0 && size.y == 0 )
			{
				sb.draw(this.background,pos.x, pos.y);
			}
			else
			{
				sb.draw(this.background,pos.x, pos.y, size.x, size.y);
			}
		}
		
		for( Entity entity : this.entities )
		{
			entity.display(sb, camera);
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
	
	public int getArea()
	{
		if( this.size.x == 0 && this.size.y == 0 )
		{
			return this.background.getWidth() * this.background.getHeight();
		}
		return (int)(this.size.x*this.size.y);
	}
	
	@Override
	public void resize(int w, int h) 
	{
		
	}

	@Override
	public int compareTo(Object obj) 
	{
		Scene scene = (Scene)(obj);
		
		return  scene.getArea() - getArea();
	}
	
}
