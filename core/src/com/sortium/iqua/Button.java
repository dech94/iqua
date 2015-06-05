package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button implements Entity
{
	//Declaration 
	private Rectangle button;
	private Texture buttonTexture;
	private Sound clickSound;
	
	public void create(String pathTexture, String pathSound, int x, int y, int width, int height) 
	{
		buttonTexture = new Texture (Gdx.files.internal(pathTexture));
		clickSound = Gdx.audio.newSound(Gdx.files.internal(pathSound));
		button = new Rectangle();
		button.x = x;
		button.y = y;
		button.width = width;
		button.height = height;
		
	}
	@Override
	public void display(SpriteBatch sb) 
	{
		// TODO Auto-generated method stub
		sb.draw(buttonTexture,button.x,button.y);
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		if(button.contains(Gdx.input.getX(), Gdx.input.getY())) {
			clickSound.play();
			//TODO
		}
	}
	
}