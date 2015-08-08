package com.sortium.iqua;

import java.util.ArrayList;

public class Dialogue 
{
	protected ArrayList<Sentence> sentences;
	protected NPC npc;
	protected Sentence currentSentence;
	
	public Dialogue(NPC npc)
	{
		sentences = new ArrayList<Sentence>();
		this.npc = npc;
		this.currentSentence = null;
	}
	
	public void addSentences(String msg, ArrayList<Response> responses)
	{
		Sentence s = new Sentence(msg, responses);
		this.sentences.add(s);
		
		reset();
		
	}
	
	public NPC getNPC()
	{
		return this.npc;
	}
	
	public ArrayList<Sentence> getSentences()
	{
		return this.sentences;
	}
	
	public Sentence getCurrentSentence()
	{
		return this.currentSentence;
	}
	
	public void choose(Response resp)
	{
		Sentence next = resp.getNextSentence();
		
		if(next == null){return;}
		
		this.currentSentence = next;
	}
	
	public void reset()
	{
		this.currentSentence = this.sentences.get(0);
	}
	
}
