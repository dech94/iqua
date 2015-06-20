package com.sortium.iqua;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC implements Entity
{
	private Texture photo;
	private String name;
	private char sex;
	private Status status;
	
	public NPC(String name, char sex, Status status, Texture photo)
	{
		this.name = name;
		this.photo = photo;
		this.sex = sex;
		this.status = status;
	}
	
	@Override
	public void display(SpriteBatch sb)
	{
		if( this.photo != null )
		{
			sb.draw(this.photo, 0, 0);
		}
	}

	@Override
	public void update()
	{
		
	}

}
