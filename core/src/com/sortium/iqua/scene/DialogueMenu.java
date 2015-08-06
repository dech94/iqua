package com.sortium.iqua.scene;

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
import com.sortium.iqua.TextZone;
import com.sortium.iqua.event.Event;

public class DialogueMenu extends Scene
{
	
	protected Dialogue dialogue;
	protected Button quitBtn;
	protected TextZone text;
	
	public DialogueMenu(IquaGame game) 
	{
		super(game);
		this.background = new Texture(Gdx.files.internal("images/dialogue.png"));
		this.size = new Vector2(0.75f*this.game.getWidth(), 0.75f*this.game.getHeight());
		
		this.dialogue = null;
		
		// POSITION
		this.pos.x = (this.game.getWidth() - this.size.x)/2;
		this.pos.y = (this.game.getHeight() - this.size.y)/2;
		
		// QUIT BUTTON
		int btnW = 96;
		int btnH = 32;
		int btnX = this.game.getWidth() - btnW;
		int btnY = (this.game.getHeight() - btnH);

		this.quitBtn = new Button(this, "images/Btn/btnClose.png", null, btnX, btnY, btnW, btnH, "scene.pop", new Event());
	
		// TEXT ZONE
		int bg_x = (int) ((this.game.getWidth()-this.size.x)/2);
		int bg_y = (int) (10 + (this.game.getHeight() - (this.game.getHeight() - this.size.y)/2));
		
		bg_y -= 2*(this.size.y)/3;
		
		this.text = new TextZone("test",
				new Rectangle(bg_x, bg_y,
						this.size.x, 7*(this.size.y/3)/8), 1f);
		
		this.text.setText("Aliquam etiam erat velit, scelerisque in dictum non, consectetur"
				+ " a erat nam at lectus urna duis convallis convallis tellus, id! Nunc lobortis "
				+ "mattis aliquam faucibus purus in massa tempor nec feugiat nisl pretium fusce id "
				+ "velit ut tortor pretium viverra suspendisse potenti nullam ac tortor. Tempus quam"
				+ " pellentesque nec nam aliquam sem et tortor! Et malesuada fames ac turpis egestas"
				+ " sed tempus, urna et pharetra pharetra, massa massa ultricies? Cursus risus, at"
				+ " ultrices mi tempus imperdiet nulla malesuada pellentesque elit eget gravida cum "
				+ "sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus "
				+ "mauris. Lacus laoreet non curabitur gravida? Feugiat scelerisque varius morbi "
				+ "enim nunc, faucibus a pellentesque sit amet, porttitor eget dolor morbi non "
				+ "arcu risus, quis varius quam! Neque, volutpat ac tincidunt vitae, semper quis "
				+ "lectus nulla at volutpat diam ut venenatis. Sed felis eget velit aliquet "
				+ "sagittis id consectetur purus ut faucibus pulvinar elementum integer enim neque,"
				+ " volutpat ac tincidunt vitae, semper quis? Elit sed vulputate mi sit amet mauris "
				+ "commodo quis imperdiet massa tincidunt nunc pulvinar. Turpis tincidunt id aliquet"
				+ " risus feugiat in ante metus, dictum at tempor commodo, ullamcorper a? "
				+ "Vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus "
				+ "egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante"
				+ " in nibh mauris, cursus mattis. Integer feugiat scelerisque varius morbi enim "
				+ "nunc. Dui sapien eget mi proin sed libero enim. Dui sapien eget mi proin sed l"
				+ "ibero enim, sed faucibus turpis in eu mi bibendum neque egestas congue quisque"
				+ " egestas? Dui faucibus in ornare quam viverra orci. Rutrum tellus pellentesque "
				+ "eu tincidunt tortor aliquam nulla facilisi cras? Ullamcorper dignissim cras "
				+ "tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius duis at"
				+ " consectetur lorem donec massa sapien, faucibus et molestie ac? Purus, in mollis "
				+ " a erat nam at lectus urna duis convallis convallis tellus, id! Nunc lobortis "
				+ "mattis aliquam faucibus purus in massa tempor nec feugiat nisl pretium fusce id "
				+ "velit ut tortor pretium viverra suspendisse potenti nullam ac tortor. Tempus quam"
				+ " pellentesque nec nam aliquam sem et tortor! Et malesuada fames ac turpis egestas"
				+ " sed tempus, urna et pharetra pharetra, massa massa ultricies? Cursus risus, at"
				+ " ultrices mi tempus imperdiet nulla malesuada pellentesque elit eget gravida cum "
				+ "sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus "
				+ "mauris. Lacus laoreet non curabitur gravida? Feugiat scelerisque varius morbi "
				+ "enim nunc, faucibus a pellentesque sit amet, porttitor eget dolor morbi non "
				+ "arcu risus, quis varius quam! Neque, volutpat ac tincidunt vitae, semper quis "
				+ "lectus nulla at volutpat diam ut venenatis. Sed felis eget velit aliquet "
				+ "sagittis id consectetur purus ut faucibus pulvinar elementum integer enim neque,"
				+ " volutpat ac tincidunt vitae, semper quis? Elit sed vulputate mi sit amet mauris "
				+ "commodo quis imperdiet massa tincidunt nunc pulvinar. Turpis tincidunt id aliquet"
				+ " risus feugiat in ante metus, dictum at tempor commodo, ullamcorper a? "
				+ "Vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus "
				+ "egestas sed sed risus pretium quam vulputate dignissim suspendisse in est ante"
				+ " in nibh mauris, cursus mattis. Integer feugiat scelerisque varius morbi enim "
				+ "nunc. Dui sapien eget mi proin sed libero enim. Dui sapien eget mi proin sed l"
				+ "ibero enim, sed faucibus turpis in eu mi bibendum neque egestas congue quisque"
				+ " egestas? Dui faucibus in ornare quam viverra orci. Rutrum tellus pellentesque "
				+ "eu tincidunt tortor aliquam nulla facilisi cras? Ullamcorper dignissim cras "
				+ "tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius duis at"
				+ " consectetur lorem donec massa sapien, faucibus et molestie ac? Purus, in mollis "
				+ "nunc sed id semper risus.");
	}
	
	public void setDialogue(Dialogue d)
	{
		if( d == null ){ return; }
		
		this.dialogue = d;
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.display(sb, camera);
		this.text.display(sb, camera);
		this.quitBtn.display(sb, camera);
		
		NPC npc = this.dialogue.getNPC();
		if( npc != null )
		{
			int n_w = (int) (this.size.x/2);
			int n_h = (int) (2*this.size.y/3);
			int n_x = (int) ((this.game.getWidth() - this.size.x)/2);
			int n_y = (int) (this.game.getHeight() - n_h - (this.game.getHeight() - this.size.y)/2);
			
			npc.display(sb, n_x, n_y, n_w, n_h);
		}
	}
	
	@Override
	public void update()
	{
		this.quitBtn.update();
		this.text.update();
	}
}
