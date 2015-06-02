package com.sortium.iqua;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IquaGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Scene mainMenu;
	private Scene currentScene;
	
	public static EventManager eventManager;
	
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.mainMenu = new MainMenu();
		this.currentScene = this.mainMenu;
		
		IquaGame.eventManager = new EventManager();
	}
	
	public void update()
	{
		if( this.currentScene != null )
		{
			this.currentScene.update();
		}
		
		if( Gdx.input.isTouched() )
		{
			// for testing
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
