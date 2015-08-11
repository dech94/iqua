package com.sortium.iqua.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.Button;
import com.sortium.iqua.Entity;
import com.sortium.iqua.IquaGame;

public class EntityManager extends Manager<Entity> implements Entity
{

	public EntityManager(IquaGame game, String name)
	{
		super(game, name);
	}

	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		for(int i=0;i <this.bucket.size(); i++)
		{
			if( this.bucket.get(i) instanceof Button )
			{
				Button b = (Button)this.bucket.get(i);
				
				if( this.game.getCurrentScene() == b.getScene() )
				{
					this.bucket.get(i).display(sb, camera);
				}
				
			}
			else
			{
				this.bucket.get(i).display(sb, camera);
			}
		}
	}

	@Override
	public void update() 
	{
		for(int i=0;i <this.bucket.size(); i++)
		{
			this.bucket.get(i).update();
		}
	}

	@Override
	public void resize(int w, int h)
	{
		
	}
	
	

}
