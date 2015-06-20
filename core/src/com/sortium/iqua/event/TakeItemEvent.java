package com.sortium.iqua.event;

import com.sortium.iqua.Item;

public class TakeItemEvent extends Event 
{
	private Item item;
	
	public TakeItemEvent(Item it)
	{
		this.item = item;
	}
}
