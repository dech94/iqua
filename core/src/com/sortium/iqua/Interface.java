package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.scene.Scene;

public class Interface implements Entity
{

	protected IquaGame game;
	protected Button btn_up;
	protected Button btn_down;
	protected Button btn_left;
	protected Button btn_right;
	protected Button btn_pause;
	protected Button btn_map;
	protected Button btn_quest;
	protected Button btn_dial;
	protected Button btn_inventory;
	
	protected Scene scene;
	protected String up;
	protected String down;
	protected String left;
	protected String right;
	
	public Interface(IquaGame game, Scene scene, String img_path, String up, String down, String left, String right)
	{
		this.game = game;
		this.scene = scene;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		
		ChangeSceneEvent csm = new ChangeSceneEvent("mainMenu");
		btn_pause = new Button(scene, "images/Btn/btnPause.png", null, 8, 8, 32, 32, "scene.change", csm);
		btn_inventory = new Button(scene, "images/Btn/btnInv.png", null, 8+32+8, 8, 32, 32, "scene.inventory", new Event());
		
		this.initButtons();
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		if( this.btn_up != null ) 
		{
			this.btn_up.display(sb, camera);
		}
		
		if( this.btn_down != null ) 
		{
			this.btn_down.display(sb, camera);
		}
		
		if( this.btn_left != null ) 
		{
			this.btn_left.display(sb, camera);
		}
		
		if( this.btn_right != null ) 
		{
			this.btn_right.display(sb, camera);
		}
		
		this.btn_pause.display(sb, camera);
		
		this.btn_inventory.display(sb, camera);
		
	}
	
	
	public void initButtons()
	{
		int width = this.game.getWidth();
		int height = this.game.getHeight();
		
		//btn
		if( this.up != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(up);
			this.btn_up = new Button(scene, "images/Btn/haut.png", null, (width -16)/2, 0, 32, 32,"scene.change", cse);
		}
		
		if( this.down != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(down);
			this.btn_down = new Button(scene, "images/Btn/bas.png", null, (width -16)/2, height-32, 32, 32, "scene.change", cse);
		}
		
		if( this.left != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(left);
			this.btn_left = new Button(scene, "images/Btn/gauche.png", null, 0, (height-32)/2, 32, 32, "scene.change", cse);
		}
		
		if( this.right != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(right);
			this.btn_right = new Button(scene, "images/Btn/droite.png", null, width - 32, (height-32)/2, 32, 32, "scene.change", cse);
		}

	}
	@Override
	public void update() 
	{

	}

	@Override
	public void resize(int w, int h)
	{
		initButtons();
	}
	
}
