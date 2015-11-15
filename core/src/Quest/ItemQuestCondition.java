package Quest;
import com.sortium.iqua.Item;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;

public class ItemQuestCondition extends QuestCondition
{
	protected class PlayerTakeItem implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			@SuppressWarnings("unchecked")
			GetEvent<Item> ge = (GetEvent<Item>)(event);
			Item item = ge.thing;
			
			// the player has found the quest item !
			if( item.getId() == ItemQuestCondition.this.item.getId() )
			{
				ItemQuestCondition.this.done = true;
				ItemQuestCondition.this.conditionDone();
				
				// we don't listen others event
				return false;
			}
			
			// we haven't found the quest item yet, we continue to listen to the 
			// events
			return true;
		}
		
	}
	 // item the player need to have in order to 
	protected Item item;
	protected boolean done;
	
	public ItemQuestCondition(Quest quest, Item item)
	{
		super(quest);
		this.done = false;
		this.item = item;
		EventEngine.get().subscribe("item.take", new PlayerTakeItem());
	}
	
	
	@Override
	public boolean isDone() 
	{
		return this.done;
	}
}
