package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.Entity;
import com.sortium.iqua.Interface;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.Event;

public class World extends Scene
{
	
	protected Interface inter;
	
	public World(IquaGame game, String img_path, String up, String down, String left, String right)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal(img_path));
		this.inter = new Interface(this, img_path, up, down, left, right);
		
		
	}
	
	public void display(SpriteBatch sb)
	{
		super.display(sb);
		this.inter.display(sb);
	}
	
	public void update()
	{
		super.update();
		this.inter.update();
	}

}
