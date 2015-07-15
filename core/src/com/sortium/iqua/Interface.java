package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.scene.Scene;

public class Interface implements Entity
{

	protected Button btn_up;
	protected Button btn_down;
	protected Button btn_left;
	protected Button btn_right;
	protected Button btn_pause;
	protected Button btn_map;
	protected Button btn_quest;
	protected Button btn_dial;
	protected Button btn_inventory;
	
	public Interface(Scene scene, String img_path, String up, String down, String left, String right)
	{
		//btn
		ChangeSceneEvent csm = new ChangeSceneEvent("mainMenu");
		btn_pause = new Button(scene, "images/Btn/btnPause.png", null, 8, 8, 32, 32, "scene.change", csm);
		btn_inventory = new Button(scene, "images/Btn/btnInv.png", null, 8+32+8, 8, 32, 32, "scene.inventory", new Event());
			
		if( up != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(up);
			btn_up = new Button(scene, "images/Btn/haut.png", null, (Gdx.graphics.getWidth() -16)/2, 0, 32, 32,"scene.change", cse);
		}
		
		
		if( down != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(down);
			btn_down = new Button(scene, "images/Btn/bas.png", null, (Gdx.graphics.getWidth() -16)/2, Gdx.graphics.getHeight()-32, 32, 32, "scene.change", cse);
		}
		
		if( left != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(left);
			btn_left = new Button(scene, "images/Btn/gauche.png", null, 0, (Gdx.graphics.getHeight()-32)/2, 32, 32, "scene.change", cse);
		}
		
		if( right != null )
		{
			ChangeSceneEvent cse = new ChangeSceneEvent(right);
			btn_right = new Button(scene, "images/Btn/droite.png", null, Gdx.graphics.getWidth() - 32, (Gdx.graphics.getHeight()-32)/2, 32, 32, "scene.change", cse);
		}
	}
	
	@Override
	public void display(SpriteBatch sb) {
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
		
		this.btn_inventory.display(sb);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
