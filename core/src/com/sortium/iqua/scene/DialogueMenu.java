package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sortium.iqua.Button;
import com.sortium.iqua.Dialogue;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.NPC;
import com.sortium.iqua.Response;
import com.sortium.iqua.Sentence;
import com.sortium.iqua.TextZone;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.EventListener;

public class DialogueMenu extends Scene
{
	protected Dialogue dialogue;
	
	protected Button quitBtn;
	protected TextZone text;
	protected ArrayList<ResponseEntry> responses;
	protected float xoffset;
	protected float yoffset;

	public class ResponseEntry
	{
		public TextZone tz;
		public Response resp;
		
		public ResponseEntry(TextZone tz, Response resp)
		{
			this.tz = tz;
			this.resp = resp;
		}
	}
	
	public class Clicked implements EventListener
	{

		@Override
		public boolean execute(Event event) 
		{
			if( DialogueMenu.this.dialogue == null ){ return true; }
			
			if(DialogueMenu.this.activated)
			{
				for(ResponseEntry re : DialogueMenu.this.responses)
				{
					TextZone txt = re.tz;
					
					if( mouseOver(txt) )
					{
						if(re.resp.getNextSentence() != null)
						{
							Dialogue d = DialogueMenu.this.dialogue;
							d.choose(re.resp);
							DialogueMenu.this.text.setText(d.getCurrentSentence().getMessage());
							updateResponses();
						}
						else
						{
							EventEngine.get().trigger("scene.pop");
						}
					}
				}
			}
			
			return true;
		}
		
	}
	public DialogueMenu(IquaGame game) 
	{
		super(game);
		this.background = new Texture(Gdx.files.internal("images/dialogue.png"));
		
		this.dialogue = null;
		this.responses = null;
		
		EventEngine.get().subscribe("input.click", new Clicked());
		initPositions();
	}
	
	private void initPositions()
	{
		// SIZE
		this.size = new Vector2(0.75f*this.game.getWidth(), 0.75f*this.game.getHeight());
		this.xoffset = this.size.x/42;
		this.yoffset = this.size.y/42;
		// POSITION
		this.pos.x = (this.game.getWidth() - this.size.x)/2;
		this.pos.y = (this.game.getHeight() - this.size.y)/2;
		
		// QUIT BUTTON
		int btnW = 96;
		int btnH = 32;
		int btnX = this.game.getWidth() - btnW;
		int btnY = (this.game.getHeight() - btnH);

		this.quitBtn = new Button(this, "images/Btn/btnClose.png", btnX, btnY, btnW, btnH);
		this.quitBtn.addReaction("scene.pop");
		
		// TEXT ZONE
		this.text = new TextZone(this.game, "test",
				new Rectangle(this.pos.x + this.xoffset, this.pos.y + this.size.y/3 - this.yoffset,
						this.size.x, 3*this.size.y/12) , 1f);
		
		//this.text.setText("Tellus, id interdum velit laoreet id donec ultrices tincidunt arcu, non sodales neque sodales ut etiam sit amet nisl purus, in mollis nunc sed. Consequat semper viverra nam libero justo, laoreet sit amet cursus sit amet? At volutpat diam ut venenatis tellus! Ultricies mi eget mauris pharetra et ultrices neque ornare aenean euismod elementum nisi, quis eleifend quam adipiscing vitae proin sagittis, nisl rhoncus mattis rhoncus, urna? Euismod lacinia at quis risus sed vulputate odio ut enim blandit volutpat maecenas volutpat blandit aliquam etiam erat velit, scelerisque in dictum non, consectetur a erat nam. Massa tincidunt nunc pulvinar sapien et ligula ullamcorper? Placerat orci nulla pellentesque dignissim enim, sit amet venenatis urna cursus eget nunc. Tristique et, egestas quis ipsum suspendisse ultrices gravida dictum fusce. Et malesuada fames ac turpis egestas sed tempus, urna et pharetra pharetra? Vitae sapien pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas sed tempus, urna et pharetra! Volutpat sed cras ornare arcu dui vivamus arcu felis, bibendum ut tristique et, egestas quis ipsum suspendisse ultrices gravida dictum fusce ut placerat. Ultrices in iaculis nunc sed augue lacus, viverra vitae! Vitae justo eget magna fermentum iaculis eu non diam phasellus vestibulum? Tempus imperdiet nulla malesuada pellentesque elit eget gravida cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus mauris vitae ultricies leo. Posuere ac ut consequat semper viverra nam libero justo, laoreet sit amet cursus sit amet, dictum sit amet justo donec enim diam, vulputate ut pharetra sit. Quisque non tellus orci, ac auctor augue mauris augue neque, gravida in fermentum et, sollicitudin ac orci phasellus egestas tellus rutrum tellus pellentesque eu! Tellus, id interdum velit laoreet id donec ultrices tincidunt arcu, non sodales neque sodales ut etiam sit amet nisl purus, in mollis nunc sed id semper risus in hendrerit. Volutpat ac tincidunt vitae, semper quis lectus nulla at volutpat diam ut venenatis tellus in metus. Hac habitasse platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper dignissim cras tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius duis at consectetur lorem donec massa! Nulla facilisi etiam dignissim diam quis enim lobortis scelerisque fermentum dui faucibus in ornare quam viverra orci sagittis eu volutpat odio facilisis mauris sit amet massa vitae tortor condimentum lacinia. Lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis aenean et tortor at risus viverra adipiscing at in tellus. Dignissim cras tincidunt lobortis feugiat vivamus. Orci sagittis eu volutpat odio facilisis mauris sit amet massa vitae tortor condimentum lacinia quis? Sed viverra ipsum nunc aliquet bibendum enim, facilisis gravida neque convallis a cras semper auctor neque, vitae tempus quam pellentesque nec nam aliquam sem et tortor consequat id. Condimentum id venenatis a, condimentum vitae sapien pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas sed tempus, urna et pharetra pharetra, massa. Amet nulla facilisi morbi tempus iaculis urna, id volutpat lacus laoreet non curabitur gravida arcu ac tortor dignissim convallis aenean et tortor at risus viverra adipiscing at!");
		
	}
	
	public void setDialogue(Dialogue d)
	{
		this.dialogue = d;
		this.text.setText(this.dialogue.getCurrentSentence().getMessage());
		updateResponses();
	}
	
	protected void updateResponses()
	{
		if( this.dialogue == null ){ return; }
		
		this.responses = new ArrayList<ResponseEntry>();
		
		int offset = 0;
		ArrayList<Response> next_responses = this.dialogue.getCurrentSentence().getResponses();
		
		if( next_responses == null ){return;}
		
		//for( Response resp : next_responses )
		for(int i=0; i< next_responses.size(); i++)
		{
			String msg = next_responses.get(i).getMessage();
			
			if(next_responses.get(i).getNextSentence() == null)
			{
				msg += " <au revoir>";
			}
			
			int txtWidth = (int) (this.size.x/2 - this.xoffset);
			
			// we don't care, we just want to get his real size
			TextZone tz = new TextZone(this.game, msg, new Rectangle(
					0,
					0,
					txtWidth,
					244249840 /* the value here is not important */));
			
			
			// here we go !
			tz.setZone(new Rectangle(
					this.pos.x + this.size.x/2 + this.xoffset, 
					this.pos.y + this.size.y - this.yoffset - offset,
					txtWidth,
					tz.getHeight()));
			
			offset+= tz.getHeight() + TextZone.sizeFor(1f);
			this.responses.add( new ResponseEntry(tz, next_responses.get(i)));
		}
	}

	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.display(sb, camera);
		this.quitBtn.display(sb, camera);
		
		if( this.dialogue == null ){ return; }
		
		this.text.display(sb, camera);
		
		// NPC
		NPC npc = this.dialogue.getNPC();
		
		if( npc != null )
		{
			int npc_w = (int) (this.size.x/2);
			int npc_h = (int) (2*this.size.y/3);
			int npc_x = (int) ((this.game.getWidth() - this.size.x)/2);
			int npc_y = (int) (this.game.getHeight() - npc_h - (this.game.getHeight() - this.size.y)/2);
			
			npc.display(sb, npc_x, npc_y, npc_w, npc_h);
		}
		
		// RESPONSES
		for(int i=0; i<this.responses.size(); i++)
		{
			TextZone txt = this.responses.get(i).tz;
			txt.display(sb, camera);
		}
	}

	@Override
	public void update()
	{
		if( this.dialogue == null ){ return; }
		
		this.quitBtn.update();

		this.text.update();

		// Check if the mouse is over a response
		for(int i=0; i< this.responses.size(); i++)
		{
			TextZone txt = this.responses.get(i).tz;
			
			if( mouseOver(txt) )
			{
				txt.setScale(1.1f);
			}
			else txt.setScale(1f);
		}
	}
	
	private boolean mouseOver(TextZone txt)
	{
		return txt.getZone().contains(this.game.getMouseX(), this.game.getMouseY() + txt.getHeight());
	}
	
	@Override
	public void resize(int w, int h)
	{
		initPositions();
		updateResponses();
	}
}
