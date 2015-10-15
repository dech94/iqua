package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sortium.iqua.Button;
import com.sortium.iqua.Inventory;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.Item;
import com.sortium.iqua.TextZone;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

public class QueteMenu extends Scene
{
	protected Button quitBtn;
	protected Inventory inventory;
	protected BitmapFont bitmapFont;
	protected Item currentItem;
	protected ArrayList<Icon> icons;
	protected TextZone description;
	
	protected class AddIcon implements EventListener
	{

		@SuppressWarnings("unchecked")
		@Override
		public boolean execute(Event event) {

			GetEvent<Item> ge = (GetEvent<Item>)(event);
			Item item = ge.thing;
			Rectangle rect = new Rectangle();
			
			if( !item.taken() )
			{
				item.taken(true);
				QueteMenu.this.icons.add(new QueteMenu.Icon(item, rect));
			}
			return true;
		}
		
	}
	
	protected class Icon
	{
		protected Texture tex;
		protected Rectangle rect;
		protected Item item;
		
		public Icon(Item item, Rectangle rect)
		{
			this.item = item;
			this.tex = this.item.getTexture();
			this.rect = rect;
			
			initPosition();
		}
		
		public Item getItem()
		{
			return this.item;
		}
		
		/*public boolean contains(float cx,float cy)
		{
			int y = (int) (InventoryMenu.this.game.getHeight() - cy);

			return this.rect.contains(cx, y);
		}*/
		
		/*public boolean contains(Rectangle rect)
		{
			return this.rect.contains(new Rectangle(rect.x,
					InventoryMenu.this.game.getHeight() - rect.y,
					rect.width, 
					rect.height));
		}*/
		
		public void initPosition()
		{
			this.rect.width = 32;
			this.rect.height = 32;
			this.rect.x = QueteMenu.this.game.getWidth()/8 + 10;
			this.rect.y = 7*QueteMenu.this.game.getHeight()/8 - rect.height - 10;
		}
		
		public void display(SpriteBatch sb, OrthographicCamera camera)
		{
			int index = QueteMenu.this.icons.indexOf(this);
			
			initPosition();
			
			this.rect.x += this.rect.width*(index%10)  + 10;
			this.rect.y -= this.rect.height*((int) index/10) + 10;
	
			sb.draw(this.tex, this.rect.x, this.rect.y, this.rect.width, this.rect.height);
		}
		
		public void move(int x, int y)
		{
			this.rect.x += x;
			this.rect.y += y;
		}
		
		public Rectangle getRect()
		{
			return new Rectangle(this.rect.x,
					QueteMenu.this.game.getHeight() - this.rect.y - this.rect.height,
					this.rect.width,
					this.rect.height);
		}
	}
	
	protected class Clicked implements EventListener
	{
		@Override
		public boolean execute(Event event)
		{
			if( QueteMenu.this.activated == false ){ return true; }
			
			ClickEvent ce = (ClickEvent)event;
			
			for(QueteMenu.Icon icon : QueteMenu.this.icons)
			{
				if( ce.on(icon.getRect()) )
				{
					QueteMenu.this.currentItem = icon.getItem();
				}
			}
			
			return true;
		}
		
	}
	
	public QueteMenu(IquaGame game)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal("images/quest.png"));
		
		setSize((int)(game.getWidth()*0.75), (int)(game.getHeight()*0.75));
		
		this.pos.x = (game.getWidth() - this.size.x)/2;
		this.pos.y = (game.getHeight() - this.size.y)/2;
		
		int btnW = 96;
		int btnH = 32;
		int btnX = 3*game.getWidth()/4 - btnW/2;
		int btnY = 6*game.getHeight()/8;
		
		this.quitBtn = new Button(this, "images/Btn/btnQuit.png", btnX, btnY, btnW, btnH);
		this.quitBtn.addReaction("scene.pop");
		
		this.bitmapFont = new BitmapFont();
		this.currentItem = null;
		
		this.icons = new ArrayList<Icon>();
		
		
		this.description = new TextZone(this.game, "",
				new Rectangle(1215*game.getWidth()/2048, 55*game.getHeight()/64,
						(int)(this.size.x/3), (int)(this.size.y/2)), 1f);
		
	}
	
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.display(sb, camera);

		this.quitBtn.display(sb, camera);
		
		
		for(Icon icon : this.icons)
		{
			icon.display(sb, camera);
		}
		
		this.description.display(sb, camera);
		/* DISPLAY ITEM DESCRIPTION */
		
		if( this.currentItem != null )
		{
			String str_tmp = currentItem.getName() + ": " + currentItem.getDescription();
			this.description.setText(str_tmp);
		}
	}
	
	public void update()
	{
		this.quitBtn.update();
		this.description.update();
	}
	
}