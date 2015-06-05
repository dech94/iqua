package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventManager;

public class Button implements Entity
{
	//Declaration 
	private Rectangle button;
	private Texture buttonTexture;
	private Sound clickSound;
	private EventManager eventmanager;
	private String eventid;
	
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
		
		this.eventmanager = em;
		this.eventid = eventid;
		
	}
	
	@Override
	public void display(SpriteBatch sb) 
	{
		sb.draw(buttonTexture,this.button.x , Gdx.graphics.getHeight() - this.button.y - this.button.height , this.button.width , this.button.height);
	}

	@Override
	public void update() 
	{
		if(this.button.contains(Gdx.input.getX(), Gdx.input.getY()) && Gdx.input.isTouched())
		{
			if( clickSound != null )
			{
				clickSound.play();
			}
			
			this.eventmanager.trigger(this.eventid);
		}
	}
	
}