package com.sortium.iqua;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Menu
{
	private Texture background;
	
	public Main()
	{
		super();
		
		this.background = new Texture("menuprincipalwip.png");
	}
	
	public void display(SpriteBatch sb)
	{
		sb.draw(this.background,0,0);
	}
	
	
}
