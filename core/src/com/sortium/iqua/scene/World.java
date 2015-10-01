package com.sortium.iqua.scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.GameInterface;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.PointOfInterest;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.EventEngine;

public class World extends Scene
{
	
	protected GameInterface inter;
	
	protected ArrayList<PointOfInterest> pointOfInterest;
	
	public World(IquaGame game, String img_path)
	{
		super(game);
		this.background = new Texture(Gdx.files.internal(img_path));
		this.inter = new GameInterface(this.game, this, img_path);
		
		this.pointOfInterest = new ArrayList<PointOfInterest>();
	}
	
	public void addAWayTo(String world_num, String pathTexture, int x, int y, int w, int h)
	{
		PointOfInterest newPof = new PointOfInterest(this, pathTexture, x, y, w, h);
		newPof.addReaction("scene.change", new ChangeSceneEvent(world_num));
		this.pointOfInterest.add(newPof);
	}
	
	public void display(SpriteBatch sb, OrthographicCamera camera)
	{
		super.fullDisplay(sb, camera);
		this.inter.display(sb, camera);
		
		for(int i=0; i<this.pointOfInterest.size(); i++)
		{
			this.pointOfInterest.get(i).display(sb, camera);
		}
	}
	
	public void update()
	{
		super.update();
		
		for(int i=0; i<this.pointOfInterest.size(); i++)
		{
			this.pointOfInterest.get(i).update();
		}
	}
	
	@Override
	public void resize(int w,int h)
	{
		this.inter.resize(w, h);
		
		for(int i=0; i<this.pointOfInterest.size(); i++)
		{
			this.pointOfInterest.get(i).resize(w, h);
		}
	}

}
