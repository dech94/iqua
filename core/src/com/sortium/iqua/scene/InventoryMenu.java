package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sortium.iqua.Button;
import com.sortium.iqua.Inventory;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.Event;

public class InventoryMenu extends Scene
{
	protected Vector2 pos;
	protected Button quitBtn;
	protected Inventory inventory;
	
	public InventoryMenu(IquaGame game, Inventory inv)
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
		this.inventory = inv;
	}
	
	public void display(SpriteBatch sb)
	{
		if( this.background != null )
		{
			sb.draw(this.background,this.pos.x, this.pos.y);
		}
		
		this.quitBtn.display(sb);
		
		/* DISPLAY ITEMS */
		int item_w = 32;
		int item_h = 32;
		int item_x = 1*Gdx.graphics.getWidth()/8 + 10;
		int item_y = 7*Gdx.graphics.getHeight()/8 - item_h - 10;
		
		for(int i=0; i < this.inventory.size(); i++)
		{
			//item_y += item_h * i;
			sb.draw(this.inventory.getItem(i).getTexture(), item_x, item_y, item_w, item_h);
			item_x += item_w + 5;
			
			if( i%9 == 8 )
			{
				item_x = 1*Gdx.graphics.getWidth()/8 + 10;
				item_y -= item_h + 10;
			}
		}
	}
	
	public void update()
	{
		this.quitBtn.update();
	}
	
}
