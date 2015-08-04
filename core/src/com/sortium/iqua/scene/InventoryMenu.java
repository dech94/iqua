package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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

public class InventoryMenu extends Scene
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
				InventoryMenu.this.icons.add(new InventoryMenu.Icon(item, rect));
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
		
		public boolean contains(float cx,float cy)
		{
			int y = (int) (Gdx.graphics.getHeight() - cy);

			return this.rect.contains(cx, y);
		}
		
		public void initPosition()
		{
			this.rect.width = 32;
			this.rect.height = 32;
			this.rect.x = Gdx.graphics.getWidth()/8 + 10;
			this.rect.y = 7*Gdx.graphics.getHeight()/8 - rect.height - 10;
		}
		
		public void display(SpriteBatch sb)
		{
			int index = InventoryMenu.this.icons.indexOf(this);
			
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
	}
	
	protected class Clicked implements EventListener
	{
		@Override
		public boolean execute(Event event)
		{
			if( InventoryMenu.this.activated == false ){ return true; }
			
			ClickEvent ce = (ClickEvent)event;
			
			for(InventoryMenu.Icon icon : InventoryMenu.this.icons)
			{
				if( icon.contains(ce.getX(), ce.getY()) )
				{
					InventoryMenu.this.currentItem = icon.getItem();
				}
			}
			
			return true;
		}
		
	}
	
	public InventoryMenu(IquaGame game, Inventory inv)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal("images/inventory.png"));
		
		this.pos.x = (Gdx.graphics.getWidth() - this.background.getWidth())/2;
		this.pos.y = (Gdx.graphics.getHeight() - this.background.getHeight())/2;
		
		int btnW = 96;
		int btnH = 32;
		int btnX = 3*Gdx.graphics.getWidth()/4 - btnW/2;
		int btnY = 6*Gdx.graphics.getHeight()/8;
		
		this.quitBtn = new Button(this, "images/Btn/btnClose.png", null, btnX, btnY, btnW, btnH, "scene.pop", new Event());
		this.inventory = inv;
		
		this.bitmapFont = new BitmapFont();
		this.currentItem = null;
		
		EventEngine.get().subscribe("input.click", new Clicked());
		EventEngine.get().subscribe("item.take", new AddIcon());
		
		this.icons = new ArrayList<Icon>();
		

		this.description = new TextZone("",
				new Rectangle(1215*Gdx.graphics.getWidth()/2048, 55*Gdx.graphics.getHeight()/64,
						217, 217), 1f);
		
	}
	
	public void display(SpriteBatch sb)
	{
		super.display(sb);
		
		this.quitBtn.display(sb);
		
		
		for(Icon icon : this.icons)
		{
			icon.display(sb);
		}
		
		this.description.display(sb);
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
