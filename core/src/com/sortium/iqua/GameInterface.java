package com.sortium.iqua;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.scene.Scene;

public class GameInterface implements Entity
{

	protected IquaGame game;

	protected Button btn_pause;
	protected Button btn_map;
	protected Button btn_quest;
	protected Button btn_dial;
	protected Button btn_inventory;
	
	protected ArrayList<Button> buttons;
	protected Scene scene;
	protected String up;
	protected String down;
	protected String left;
	protected String right;
	
	public GameInterface(IquaGame game, Scene scene, String img_path)
	{
		this.buttons = new ArrayList<Button>();
		
		this.game = game;
		this.scene = scene;

		ChangeSceneEvent csm = new ChangeSceneEvent("mainMenu");
		btn_pause = new Button(scene, "images/Btn/btnPause.png", 8, 8, 32, 32);
		btn_pause.addReaction( "scene.change", csm);
		this.buttons.add(btn_pause);
		
		btn_inventory = new Button(scene, "images/Btn/btnInv.png", 8+64+8, 8, 32, 32);
		btn_inventory.addReaction("scene.inventory");
		this.buttons.add(btn_inventory);
		
		btn_quest = new Button(scene, "images/Btn/btnQuete.png", 8+128+16, 8, 32, 32);
		btn_quest.addReaction("scene.quest");
		this.buttons.add(btn_quest);
		
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		for(int i=0; i<this.buttons.size(); i++)
		{
			this.buttons.get(i).display(sb, camera);
		}
	}
	
	@Override
	public void update() 
	{
		for(int i=0; i<this.buttons.size(); i++)
		{
			this.buttons.get(i).update();
		}
	}

	@Override
	public void resize(int w, int h)
	{
		for(int i=0; i<this.buttons.size(); i++)
		{
			this.buttons.get(i).resize(w, h);
		}
	}
	
}
