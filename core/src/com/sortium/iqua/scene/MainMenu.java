package com.sortium.iqua.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventManager;

public class MainMenu extends Menu
{
	private Button demoBtn;
	
	public MainMenu(IquaGame game)
	{
		super(game);
		
		ChangeSceneEvent event = new ChangeSceneEvent();
		event.newScene = "0";
		this.background = new Texture("images/menuprincipalwip.png");
		this.demoBtn = new Button(this, "images/Btn/btnRep.png", null, 100, 100, 96, 32, 
											this.eventManager, "scene.change", event );
	}
	
	public void display(SpriteBatch sb)
	{
		//super.display(sb);
		
		sb.draw(this.background, 0, -960+480);
		this.demoBtn.display(sb);
	}
	
	
}
