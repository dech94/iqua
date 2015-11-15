package Quest;

import java.util.ArrayList;

import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

public class Quest 
{
	
	protected class ConditionDone implements EventListener
	{

		@Override
		public boolean execute(Event event) 
		{
			@SuppressWarnings("unchecked")
			GetEvent<QuestCondition> ge = (GetEvent<QuestCondition>)(event);
			QuestCondition condition = ge.thing;
			
			if( condition.getQuest() == Quest.this)
			{
				if( Quest.this.isDone() == true )
				{
					GetEvent<Quest> get = new GetEvent<Quest>(Quest.this);
					EventEngine.get().trigger("quest.done", get);
					System.out.println("Quête " + Quest.this.name + " terminée !");
				}
			}
			
			return true;
		}
		
	}
	static long id_count = 0;
	
	protected String description;
	protected String name;
	protected long id;
	protected ArrayList<QuestCondition> conditions;
	
	public Quest(String name, String description) 
	{
		this.conditions = new ArrayList<QuestCondition>();
		this.description=description;
		this.name=name;
		
		EventEngine.get().subscribe("quest.condition.done", new ConditionDone());
	}

	public void addCondition(QuestCondition condition)
	{
		if(condition != null)
		{
			this.conditions.add(condition);
		}
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	
	public boolean isDone()
	{
		for(QuestCondition qc : this.conditions)
		{
			if( qc.isDone() == false )
			{
				return false;
			}
		}
		
		return true;
	}
}