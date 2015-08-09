package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.ChangeSceneEvent;

public class MainMenu extends Menu
{
	private Button demoBtn;
	
	public MainMenu(IquaGame game, String current)
	{
		super(game);
		
		ChangeSceneEvent event = new ChangeSceneEvent(current);
				
		this.background = new Texture(Gdx.files.internal("images/screenlaunch.png"));
		this.demoBtn = new Button(this, "images/Btn/btndemo.png", (this.game.getWidth()-100)/2, 3*(this.game.getHeight()-32)/4, 96, 32);
		
		this.demoBtn.addReaction( "scene.change", event);
		this.demoBtn.addReaction("iqua.start");
		
	}
	
	
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.fullDisplay(sb, camera);
		this.demoBtn.display(sb, camera);
	}
	
	
}
