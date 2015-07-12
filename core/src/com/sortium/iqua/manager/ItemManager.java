package com.sortium.iqua.manager;

import com.sortium.iqua.IquaGame;
import com.sortium.iqua.Item;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

public class ItemManager extends EntityManager
{
	private class RemoveItem implements EventListener
	{
		@Override
		public boolean execute(Event event)
		{
			@SuppressWarnings("unchecked")
			GetEvent<Item> to_remove = (GetEvent<Item>)event;
			Item item = to_remove.thing;

			if(ItemManager.this.bucket.indexOf(item) !=-1)
			{
				ItemManager.this.remove(item);	
			}

			return true;
		}
	}
	
	public ItemManager(IquaGame game) 
	{
		super(game, "item");
		EventEngine.get().subscribe("item.take", new RemoveItem());
	}
	
}
