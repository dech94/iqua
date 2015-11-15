package com.sortium.iqua;

import java.util.ArrayList;

import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

import Quest.Quest;

public class ListQuest {
	protected ArrayList<Quest> list;
	protected IquaGame game;
	
	private class AddItem implements EventListener
	{
		@SuppressWarnings("unchecked")
		@Override
		public boolean execute(Event event)
		{
			GetEvent<Quest> get_quest = (GetEvent<Quest>)event;
			Quest quest = get_quest.thing;
			
			if( ListQuest.this.list.indexOf(quest) == -1 )
			{
				ListQuest.this.list.add(quest);
				System.out.println("Une quete a été ajouté -> " + quest.getName() + " :"+ get_quest.thing.getDescription());
			}
			
			return true;
		}
		
	}
	
	public ListQuest(IquaGame game)
	{
		this.list = new ArrayList<Quest>();
		this.game = game;
		
		EventEngine.get().subscribe("quest.take", new AddItem());
	}
	
	public int size()
	{
		return this.list.size();
	}
	
	public Quest getQuest(int index)
	{
		return this.list.get(index);
	}
}