package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
	protected ArrayList<Reaction> reactions;

	protected long delay = 250000000l ;
	protected long now = System.nanoTime();
	protected Scene owner;
	
	protected class Reaction
	{
		public String id;
		public Event event;
		
		public Reaction(String id, Event event)
		{
			this.id = id;
			this.event = event;
		}
		
	}
	protected class Clicked implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			ClickEvent ce = (ClickEvent) event;
			
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
					
					for(Reaction reaction : Button.this.reactions)
					{
						EventEngine.get().trigger(reaction.id, reaction.event);
					}
					
					Button.this.now = System.nanoTime();
				}
			}
			
			return true;
		}
		
	}
	
	public Button(Scene owner, String pathTexture, int x, int y, int width, int height) 
	{
		this(owner, pathTexture, null, x, y ,width, height);
	}
	
	public Button(Scene owner,String pathTexture,  String pathSound, int x, int y, int width, int height) 
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
		
		this.reactions = new ArrayList<Reaction>();
		
		EventEngine.get().subscribe("input.click", new Clicked());
	}
	
	public void addReaction(Reaction reaction)
	{
		if( reaction != null )
		{
			this.reactions.add(reaction);
		}
	}
	
	public void addReaction(String id, Event event)
	{
		if(event != null)
		{
			addReaction(new Reaction(id, event));
		}
	}
	
	public void addReaction(String id)
	{

		addReaction(id, new Event());
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		sb.draw(buttonTexture, this.button.x , camera.viewportHeight - this.button.y - this.button.height , this.button.width , this.button.height);
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
		this.button.width = (this.button.width * w)/this.owner.getGame().getLastWidth();
		this.button.height= (this.button.height* h)/this.owner.getGame().getLastHeight();
		
		this.button.x = (this.button.x * w)/this.owner.getGame().getLastWidth();
		this.button.y = (this.button.y * h)/this.owner.getGame().getLastHeight();
	}
	
}