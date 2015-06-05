package com.sortium.iqua;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventManager;
import com.sortium.iqua.scene.MainMenu;
import com.sortium.iqua.scene.Scene;

public class IquaGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Scene mainMenu;
	private Scene currentScene;
	
	private EventManager eventManager;
	private Button button;

	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.eventManager = new EventManager();
		
		
		this.mainMenu = new MainMenu(this.eventManager);
		this.currentScene = this.mainMenu;
		this.button = new Button("images/badlogic.jpg", null, 200, 200,200, 200, eventManager, "test");
	}
	
	public void update()
	{
		if( this.currentScene != null )
		{
			this.currentScene.update();
			this.button.update();
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
			this.button.display(this.batch);
		}
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		
		batch.begin();
		display();
		batch.end();
	}
	
	public void changeScene()
	{
		
	}
}
