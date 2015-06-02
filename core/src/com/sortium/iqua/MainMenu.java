package com.sortium.iqua;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu extends Menu
{
	private Texture background;
	
	public MainMenu()
	{
		super();
		
		this.background = new Texture("images/menuprincipalwip.png");
	}
	
	public void display(SpriteBatch sb)
	{
		sb.draw(this.background,0,0);
	}
	
	
}
