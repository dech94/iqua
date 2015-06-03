package com.sortium.iqua.event;

import java.util.ArrayList;
import java.util.Hashtable;

public class EventManager 
{
	private Hashtable<String, ArrayList<EventListener>> events;
	
	public EventManager()
	{
		this.events = new Hashtable<String, ArrayList<EventListener>>();
	}
	
	public void trigger(String id)
	{
		trigger(id, new Event());
	}
	
	public void trigger(String id, Event event)
	{
		if( !this.events.containsKey(id) )
		{
			return;
		}
		
		ArrayList<EventListener> kept = new ArrayList<EventListener>();
		
		for( EventListener listener : events.get(id) )
		{
			if( listener.execute(event) )
			{
				kept.add(listener);
			}
		}
		
		this.events.remove(id);
		this.events.put(id, kept);
		
	}
	
	public void subscribe(String id, EventListener listener)
	{
		if( !this.events.containsKey(id) )
		{
			this.events.put(id, new ArrayList<EventListener>() );
		}
		
		
		this.events.get(id).add(listener);
	}
}
