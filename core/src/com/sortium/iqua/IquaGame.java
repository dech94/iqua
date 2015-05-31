package com.sortium.iqua;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IquaGame extends ApplicationAdapter {
	SpriteBatch batch;

	Scene mainMenu;
	Scene currentScene;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		this.mainMenu = new Main();
		this.currentScene = this.mainMenu;
	}
	
	public void update()
	{
		this.currentScene.update();
	}
	
	public void display()
	{
		this.currentScene.display(this.batch);
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
