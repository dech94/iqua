package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

public class Inventory 
{
	protected ArrayList<Item> inventory;
	protected IquaGame game;
	
	private class AddItem implements EventListener
	{
		@SuppressWarnings("unchecked")
		@Override
		public boolean execute(Event event)
		{
			GetEvent<Item> get_item = (GetEvent<Item>)event;
			Item item = get_item.thing;
			
			if( Inventory.this.inventory.indexOf(item) == -1 )
			{
				Inventory.this.inventory.add(item);
				System.out.println("Un item a été ajouté -> " + item.getName() + " :"+ get_item.thing.getDescription());//
			}
			
			return true;
		}
		
	}
	
	public Inventory(IquaGame game)
	{
		this.inventory = new ArrayList<Item>();
		this.game = game;
		
		EventEngine.get().subscribe("item.take", new AddItem());
	}
	
	public int size()
	{
		return this.inventory.size();
	}
	
	public Item getItem(int index)
	{
		return this.inventory.get(index);
	}
	
}
