package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sortium.iqua.Button;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.Event;

public class InventoryMenu extends Scene
{
	protected Vector2 pos;
	protected Button quitBtn;
	
	public InventoryMenu(IquaGame game)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal("images/baseInventaire.png"));
		
		this.pos = new Vector2();
		this.pos.x = (Gdx.graphics.getWidth() - this.background.getWidth())/2;
		this.pos.y = (Gdx.graphics.getHeight() - this.background.getHeight())/2;
		
		int btnW = 96;
		int btnH = 32;
		int btnX = 3*Gdx.graphics.getWidth()/4 - btnW/2;
		int btnY = 6*Gdx.graphics.getHeight()/8;
		
		this.quitBtn = new Button(this, "images/Btn/btnQuit.png", null, btnX, btnY, btnW, btnH, "scene.pop", new Event());
	}
	
	public void display(SpriteBatch sb)
	{
		if( this.background != null )
		{
			sb.draw(this.background,this.pos.x, this.pos.y);
		}
		
		this.quitBtn.display(sb);
	}
	
	public void update()
	{
		this.quitBtn.update();
	}
	
}
