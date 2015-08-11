package com.sortium.iqua.event;

import java.util.ArrayList;
import java.util.Hashtable;

public class EventEngine 
{
	private Hashtable<String, ArrayList<EventListener>> events;
	private static EventEngine instance;
	
	public static EventEngine get()
	{
		if( EventEngine.instance == null )
		{
			EventEngine.instance = new EventEngine();
		}
		
		return EventEngine.instance;
	}
	
	private EventEngine()
	{
		EventEngine.instance = this;
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
		

		for(int i=0; i<events.get(id).size(); i++)
		{
			if( events.get(id).get(i).execute(event) )
			{
				kept.add(events.get(id).get(i));
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
