package com.sortium.iqua;

import java.util.ArrayList;

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
			Inventory.this.inventory.add(get_item.thing);
			System.out.println("Un item a été ajouté -> " + get_item.thing.getName() + " :"+ get_item.thing.getDescription());//
			return false;
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

}
