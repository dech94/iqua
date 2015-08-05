package com.sortium.iqua;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC implements Entity
{
	private Texture photo;
	private String firstname;
	private String lastname;
	private char sex;
	private Status status;
	
	public NPC(String firstname, String lastname, char sex, Status status, Texture photo)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.photo = photo;
		this.sex = sex;
		this.status = status;
	}
	
	public void display(SpriteBatch sb, int x,int y, int w, int h)
	{
		if( this.photo != null )
		{
			sb.draw(this.photo, x, y, w, h);
		}
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera)
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

	@Override
	public void resize(int w, int h)
	{
		
	}

}
