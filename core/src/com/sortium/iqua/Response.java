package com.sortium.iqua;


//what the player can respond to the npc
public class Response
{
	protected String message;
	protected Sentence next;
	
	public Response(String msg)
	{
		this(msg, null);
	}
	
	public Response(String msg, Sentence next)
	{
		this.message = msg;
		this.next = next;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public Sentence getNextSentence()
	{
		return this.next;
		
	}
}