package com.sortium.iqua.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.EventManager;

public class World extends Scene
{

	protected Button btn_up;
	protected Button btn_down;
	protected Button btn_left;
	protected Button btn_right;
	
	public World(IquaGame game, String img_path, String up, String down, String left, String right)
	{
		super(game);
		this.background = new Texture(img_path);
		
		//btn
		if( up != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = up;
			btn_up = new Button(this, "images/Btn/haut.png", null, 0, 0, 32, 32, this.eventManager, "scene.change", cse);
		}
		
		
		if( down != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = down;
			btn_up = new Button(this, "images/Btn/bas.png", null, 0, 200, 32, 32, this.eventManager, "scene.change", cse);
		}
	}
	
	public void display(SpriteBatch sb)
	{
		super.display(sb);
		
		if( this.btn_up != null ) 
		{
			this.btn_up.display(sb);
		}
		
		if( this.btn_down != null ) 
		{
			this.btn_down.display(sb);
		}
	}

}
