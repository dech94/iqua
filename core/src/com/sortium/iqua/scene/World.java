package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
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
	protected Button btn_pause;
	protected Button btn_map;
	protected Button btn_quest;
	protected Button btn_dial;
	
	
	public World(IquaGame game, String img_path, String up, String down, String left, String right)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal(img_path));
		
		//btn
		
		ChangeSceneEvent csm = new ChangeSceneEvent();
		csm.newScene = "mainMenu";
		btn_pause = new Button(this, "images/btn/btnPause.png", null, 8, 8, 32, 32, this.eventManager, "scene.change", csm);
			
		if( up != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = up;
			btn_up = new Button(this, "images/Btn/haut.png", null, (Gdx.graphics.getWidth() -16)/2, 0, 32, 32, this.eventManager, "scene.change", cse);
		}
		
		
		if( down != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = down;
			btn_down = new Button(this, "images/Btn/bas.png", null, (Gdx.graphics.getWidth() -16)/2, Gdx.graphics.getHeight()-32, 32, 32, this.eventManager, "scene.change", cse);
		}
		
		if( left != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = left;
			btn_left = new Button(this, "images/Btn/gauche.png", null, 0, (Gdx.graphics.getHeight()-32)/2, 32, 32, this.eventManager, "scene.change", cse);
		}
		
		if( right != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent();
			cse.newScene = right;
			btn_right = new Button(this, "images/Btn/droite.png", null, Gdx.graphics.getWidth() - 32, (Gdx.graphics.getHeight()-32)/2, 32, 32, this.eventManager, "scene.change", cse);
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
		
		if( this.btn_left != null ) 
		{
			this.btn_left.display(sb);
		}
		
		if( this.btn_right != null ) 
		{
			this.btn_right.display(sb);
		}
		this.btn_pause.display(sb);
	}

}
