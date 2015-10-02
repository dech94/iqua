package com.sortium.iqua;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.scene.Scene;

public class PointOfInterest extends Button
{

	private float alpha_step = 0.02f;
	private float alpha_max = 0.3f;
	private float alpha_min = 0f;
	private float alpha = this.alpha_max;
	
	private float freq_delay = 100f;
	private long freq_chrono=  System.nanoTime();
	private boolean alpha_increase = true;
	
	public PointOfInterest(Scene owner, String pathTexture, int x, int y, int width, int height) 
	{
		super(owner, pathTexture, x, y, width, height);
	}
	
	public PointOfInterest(Scene owner,String pathTexture,  String pathSound, int x, int y, int width, int height) 
	{
		super(owner, pathTexture, pathSound, x, y , width, height);
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if( System.nanoTime() - this.freq_chrono >= this.freq_delay*1000000.0)
		{
			
			if(this.alpha_increase){ this.alpha += this.alpha_step; }
			else{ this.alpha -= this.alpha_step; }
			
			if( this.alpha < this.alpha_min)
			{
				this.alpha = this.alpha_min;
				this.alpha_increase = true;
			}
			
			if( this.alpha > this.alpha_max )
			{
				this.alpha = this.alpha_max;
				this.alpha_increase = false;
			}

			//System.out.println(this.alpha);
			this.freq_chrono = System.nanoTime();
		}
		
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{
		sb.setColor(1f, 1f, 1f, (float)Math.sin(alpha));

		sb.draw(buttonTexture, this.button.x , camera.viewportHeight - this.button.y - this.button.height , this.button.width , this.button.height);
		sb.setColor(1f, 1f, 1f, 1f);
	}

}
