package com.sortium.iqua.event;

public class GetEvent<T> extends Event
{
	public T thing;
	
	public GetEvent(T thing)
	{
		this.thing = thing;
	}
}
