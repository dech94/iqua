package com.sortium.iqua.manager;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;

public abstract class Manager<T>
{
	protected class Add implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			GetEvent<T> add_event = (GetEvent<T>) event;
			
			if( add_event != null )
			{
				Manager.this.bucket.add(add_event.thing);
			}
			
			return true;
		}
		
	}
	
	protected class Remove implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			GetEvent<T> remove_event = (GetEvent<T>) event;
			
			if( remove_event != null )
			{
				Manager.this.bucket.remove(remove_event.thing);
			}
			
			return true;
		}
	}
	
	protected ArrayList<T> bucket= new ArrayList<T>();
	
	protected String name;
	
	public Manager(IquaGame game, String name)
	{
		this.name = name;
		
		game.getEventManager().subscribe(name+".add", new Add());
		game.getEventManager().subscribe(name+".remove", new Remove());
		
	}
	
	public void add(T thing)
	{
		this.bucket.add(thing);
	}
	
	public void remove(T thing)
	{
		int index = this.bucket.indexOf(thing);
		this.bucket.remove(index);
	}
	
}
