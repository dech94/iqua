package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
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
		event.newScene = "1";
		
		this.background = new Texture(Gdx.files.internal("images/screenlaunch.png"));
		this.demoBtn = new Button(this, "images/Btn/btndemo.png", null, (Gdx.graphics.getWidth()-100)/2, 3*(Gdx.graphics.getHeight()-32)/4, 96, 32, 
											this.eventManager, "scene.change", event );
	}
	
	public void display(SpriteBatch sb)
	{
		//super.display(sb);
		
		sb.draw(this.background, 0, 0);
		this.demoBtn.display(sb);
	}
	
	
}
