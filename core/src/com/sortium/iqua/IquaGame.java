package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventManager;
import com.sortium.iqua.scene.MainMenu;
import com.sortium.iqua.scene.Scene;
import com.sortium.iqua.scene.World;

public class IquaGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Scene mainMenu;
	private String current = "1";
	private ArrayList<World> worlds;
	private Scene currentScene;
	
	private EventManager eventManager;
	
	private class ChangeScene implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			String sc = ((ChangeSceneEvent)event).newScene;
			
			if( sc.equals("mainMenu") )
			{
				IquaGame.this.currentScene = IquaGame.this.mainMenu;
				return true;
			}
			
			if( sc.equals("quit") )
			{
				IquaGame.this.currentScene = null;
				return true;
			}
			
			int num_sc = Integer.parseInt(sc) - 1;
			Scene next = IquaGame.this.worlds.get(num_sc);
			next.resetStartTime();
			IquaGame.this.currentScene = next;
			System.out.println("load scene " + (num_sc+1));
			current=Integer.toString(num_sc+1);
			return true;
		}
		
	}
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.eventManager = new EventManager();
		
		this.worlds = new ArrayList<World>();
		createWorlds();
		
		this.mainMenu = new MainMenu(this,current);
		this.currentScene = this.mainMenu;
				
		this.eventManager.subscribe("scene.change", new ChangeScene());
		
	}
	
	public void createWorlds()
	{
		this.worlds.add(new World(this, "images/Background/screen1.png", "2", null, null,  null));
		this.worlds.add(new World(this, "images/Background/screen2.png", "4", "1", null,  "3"));
		this.worlds.add(new World(this, "images/Background/screen3.png", null, "2", null,  null));
		this.worlds.add(new World(this, "images/Background/screen4.png", null, null, "5",  "2"));
		this.worlds.add(new World(this, "images/Background/screen5.png", null, "4",null, "6"));
		this.worlds.add(new World(this, "images/Background/screen6.png", "7", "5", null,  null));
		this.worlds.add(new World(this, "images/Background/screen7.png", null, null, "8",  "6"));
		this.worlds.add(new World(this, "images/Background/screen8.png", null, "10", "9",  "7"));
		this.worlds.add(new World(this, "images/Background/screen9.png", null, null, null,  "8"));
		this.worlds.add(new World(this, "images/Background/screen10.png", null, "8", null,  null));
		this.worlds.add(new World(this, "images/work.png", null, null, null, null));
		
	}
	
	public void update()
	{
		if( this.currentScene != null )
		{
			this.currentScene.update();
		}
		
		if( Gdx.input.isTouched() )
		{
			Event event = new ClickEvent(Gdx.input.getX(), Gdx.input.getY());
			this.eventManager.trigger("controles.click", event);
		}
	}
	
	public void display()
	{
		if( this.currentScene != null )
		{
			this.currentScene.display(this.batch);
		}
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		
		batch.begin();
		display();
		batch.end();
	}
	
	public EventManager getEventManager()
	{
		return this.eventManager;
	}
	
	public Scene getCurrentScene()
	{
		return this.currentScene;
	}
}
