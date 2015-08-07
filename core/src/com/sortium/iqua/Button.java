package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.scene.Scene;

public class Button implements Entity
{
	//Declaration 
	protected Rectangle button;
	protected Texture buttonTexture;
	protected Sound clickSound;
	protected String eventid;
	protected Event event;

	protected long delay = 250000000l ;
	protected long now = System.nanoTime();
	protected Scene owner;
	
	protected class Clicked implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			ClickEvent ce = (ClickEvent) event;
			
			//if( Button.this.button.contains( ce.getRect() )
			if( ce.on(Button.this.button)
				&& Button.this.owner.getGame().getCurrentScene() == Button.this.owner
				&& System.nanoTime() - Button.this.owner.getStartTime() >= 250000000l
				&& Gdx.input.getDeltaX() == 0 && Gdx.input.getDeltaY() == 0 
				&& Gdx.input.justTouched() )
			{
				if( System.nanoTime() - Button.this.now >= Button.this.delay )
				{
					if( clickSound != null )
					{
						clickSound.play();
					}
					
					if(Button.this.event != null)
					{
						EventEngine.get().trigger(Button.this.eventid, Button.this.event);
					}
					
					Button.this.now = System.nanoTime();
				}
			}
			
			return true;
		}
		
	}
	
	public Button(Scene owner, String pathTexture, String pathSound, int x, int y, int width, int height)
	{
		this(owner, pathTexture, pathSound, x, y, width, height, null, null);
	}
	
	public Button(Scene owner, String pathTexture, String pathSound, int x, int y, int width, int height, String eventid, Event event) 
	{
		this.owner = owner;
		this.buttonTexture = new Texture(Gdx.files.internal(pathTexture));
		
		try
		{
			this.clickSound = Gdx.audio.newSound(Gdx.files.internal(pathSound));
		}
		catch(Exception e)
		{
			this.clickSound = null;
		}
		
		button = new Rectangle();
		this.button.x = x;
		this.button.y = y;
		button.width = width;
		button.height = height;
		
		this.eventid = eventid;
		this.event = event;
		
		EventEngine.get().subscribe("input.click", new Clicked());
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		Vector3 v = new Vector3(this.button.x, camera.viewportHeight - this.button.y, 0);
		camera.project(v);
		
		sb.draw(buttonTexture, v.x , v.y - this.button.height , this.button.width , this.button.height);
	}

	@Override
	public void update() 
	{
		
	}
	
	public Scene getScene()
	{
		return this.owner;
	}
	
	public Texture getTexture()
	{
		return this.buttonTexture;
	}

	@Override
	public void resize(int w, int h) 
	{
		
	}
	
}