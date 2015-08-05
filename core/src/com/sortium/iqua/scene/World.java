package com.sortium.iqua.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Interface;
import com.sortium.iqua.IquaGame;

public class World extends Scene
{
	
	protected Interface inter;
	
	public World(IquaGame game, String img_path, String up, String down, String left, String right)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal(img_path));
		this.inter = new Interface(this.game, this, img_path, up, down, left, right);
		
		
	}
	
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.fullDisplay(sb, camera);
		this.inter.display(sb, camera);
	}
	
	public void update()
	{
		super.update();
		//this.inter.update();
	}
	
	@Override
	public void resize(int w,int h)
	{
		this.inter.resize(w, h);
	}

}
