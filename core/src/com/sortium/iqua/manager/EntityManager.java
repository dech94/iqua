package com.sortium.iqua.manager;

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
	public void display(SpriteBatch sb) 
	{
		for( Entity entity : this.bucket)
		{
			if( entity instanceof Button )
			{
				Button b = (Button)entity;
				
				if( this.game.getCurrentScene() == b.getScene() )
				{
					entity.display(sb);
				}
				
			}
			else
			{
				entity.display(sb);
			}
		}
	}

	@Override
	public void update() 
	{
		for( Entity entity : this.bucket)
		{
			entity.update();
		}
	}
	
	

}
