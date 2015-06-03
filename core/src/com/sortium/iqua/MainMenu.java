package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends Menu
{
	
	public MainMenu(EventManager eventManager)
	{
		super(eventManager);
		
		this.background = new Texture("images/menuprincipalwip.png");
		
	}
	
	public void display(SpriteBatch sb)
	{
		super.display(sb);
	}
	
	
}
