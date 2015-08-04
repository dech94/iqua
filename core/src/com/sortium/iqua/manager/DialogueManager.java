package com.sortium.iqua.manager;

import com.sortium.iqua.Dialogue;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.GetEvent;

public class DialogueManager extends Manager<Dialogue>
{

	public DialogueManager(IquaGame game)
	{
		super(game, "dialogue");
	}
	
	public void save(Dialogue d)
	{
		
	}
	
	public void load(Dialogue d)
	{
		
	}
	
	public void run(Dialogue d)
	{
		EventEngine.get().trigger("scene.dialogue", new GetEvent<Dialogue>(d) );
	}

}
