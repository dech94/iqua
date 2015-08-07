package com.sortium.iqua;

import java.util.ArrayList;

//what the npc say
public class Sentence
{
	protected int id;
	protected String message;
	protected ArrayList<Response> responses;
	
	public Sentence(String message, ArrayList<Response> responses)
	{
		this.message = message;
		this.id = this.message.hashCode();
		this.responses = responses;
		
	}
	
	public Sentence(String message)
	{
		this(message, null);
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public ArrayList<Response> getResponses()
	{
		return this.responses;
		
	}
}