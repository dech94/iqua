package com.sortium.iqua.event;
import com.sortium.iqua.event.Event;

public class Reaction
	{
		public String id;
		public Event event;
		
		public Reaction(String id, Event event)
		{
			this.id = id;
			this.event = event;
		}
		
	}