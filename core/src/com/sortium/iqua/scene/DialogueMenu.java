package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sortium.iqua.Button;
import com.sortium.iqua.Dialogue;
import com.sortium.iqua.IquaGame;
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
		this.dialogue = null;
		
		// POSITION
		this.pos.x = (Gdx.graphics.getWidth() - this.background.getWidth())/2;
		this.pos.y = (Gdx.graphics.getHeight() - this.background.getHeight())/2;
		
		// QUIT BUTTON
		int btnW = 96;
		int btnH = 32;
		int btnX = 3*Gdx.graphics.getWidth()/4 - btnW/2;
		int btnY = 6*Gdx.graphics.getHeight()/8;
		this.quitBtn = new Button(this, "images/Btn/btnClose.png", null, btnX, btnY, btnW, btnH, "scene.pop", new Event());
	
		// TEXT ZONE
		int bg_x = (Gdx.graphics.getWidth()-this.background.getWidth())/2;
		int bg_y = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() - this.background.getHeight())/2;
		
		this.text = new TextZone("test",
				new Rectangle(bg_x, bg_y,
						100, 100), 1f);
		
	}
	
	public void setDialogue(Dialogue d)
	{
		if( d == null ){ return; }
		
		this.dialogue = d;
	}
	
	@Override
	public void display(SpriteBatch sb)
	{
		super.display(sb);
		this.text.display(sb);
		this.quitBtn.display(sb);
	}
	
	@Override
	public void update()
	{
		this.quitBtn.update();
		this.text.update();
	}
}
