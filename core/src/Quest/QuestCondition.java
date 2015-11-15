package Quest;

import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.GetEvent;

public abstract class QuestCondition
{
	protected Quest quest;
	
	public QuestCondition(Quest quest)
	{
		this.quest = quest;
	}
	
	public abstract boolean isDone();
	
	public void conditionDone()
	{
		GetEvent<QuestCondition> ge = new GetEvent<QuestCondition>(this);
		EventEngine.get().trigger("quest.condition.done", ge);
	}
	
	public Quest getQuest()
	{
		return this.quest;
	}
}
