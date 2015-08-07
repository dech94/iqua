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
		
		this.currentSentence = this.sentences.get(0);
		
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
	
}
