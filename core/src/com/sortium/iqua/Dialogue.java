package com.sortium.iqua;

import java.util.ArrayList;
import com.badlogic.gdx.utils.Json;

public class Dialogue 
{
	// what the player can respond to the npc
	protected class Response
	{
		protected String message;
		protected Sentence next;
		
		public Response(String msg, Sentence next)
		{
			this.message = msg;
			this.next = next;
		}
	}
	
	// what the npc say
	protected class Sentence
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
	}
	
	protected ArrayList<Sentence> sentences;
	
	public Dialogue()
	{
		sentences = new ArrayList<Sentence>();
	}
	
	
	
	
}
