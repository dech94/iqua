package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventManager;

public class Button implements Entity
{
	//Declaration 
	protected Rectangle button;
	protected Texture buttonTexture;
	protected Sound clickSound;
	protected EventManager eventManager;
	protected String eventid;
	
	private class Clicked implements EventListener
	{

		@Override
		public boolean execute(Event event) 
		{
			ClickEvent ce = (ClickEvent) event;
			
			if( Button.this.button.contains(ce.getX(), ce.getY()) )
			{
				if( clickSound != null )
				{
					clickSound.play();
				}
				
				Button.this.eventManager.trigger(Button.this.eventid);
			}
			
			return true;
		}
		
	}
	
	public Button(String pathTexture, String pathSound, int x, int y, int width, int height, EventManager em, String eventid) 
	{
		this.buttonTexture = new Texture (Gdx.files.internal(pathTexture));

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
		
		this.eventManager = em;
		this.eventid = eventid;
		
		this.eventManager.subscribe("controles.click", new Clicked());
	}
	
	@Override
	public void display(SpriteBatch sb) 
	{
		sb.draw(buttonTexture,this.button.x , Gdx.graphics.getHeight() - this.button.y - this.button.height , this.button.width , this.button.height);
	}

	@Override
	public void update() 
	{
		
	}
	
}