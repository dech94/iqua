package com.sortium.iqua;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity
{
	public abstract void display(SpriteBatch sb, OrthographicCamera camera);
	public abstract void update();
	public abstract void resize(int w,int h);
}
