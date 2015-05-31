package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity
{
	public abstract void display(SpriteBatch sb);
	public abstract void update();
}
