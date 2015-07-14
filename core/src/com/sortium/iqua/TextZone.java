package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class TextZone implements Entity
{
	protected BitmapFont font;
	protected GlyphLayout layout;
	
	protected Rectangle rect;
	protected String[] words;
	protected String raw_str;
	protected String render_str;
	
	protected int y_begin;
	
	public TextZone(String str, Rectangle rect, float xScale, float yScale)
	{
		this.font = new BitmapFont();
		
		this.rect = new Rectangle(rect.x, rect.y - rect.height, rect.width, rect.height);
		
		this.raw_str = str;
		
		this.y_begin = 0;
		
		setScale(xScale, yScale);
		setText(this.raw_str);
		
	}
	
	public TextZone(String str, Rectangle rect, float xyScale)
	{
		this(str, rect, xyScale, xyScale);
	}
	
	public TextZone(String str, Rectangle rect)
	{
		this(str, rect, 1.2f);
	}
	
	public TextZone(String str)
	{
		this(str,  new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}
	
	public void setScale(float x, float y)
	{
		this.font.getData().setScale(x, y);
	}
	
	public void setScale(float xy)
	{
		this.font.getData().setScale(xy);
	}
	
	public void setText(String str)
	{
		this.raw_str = str;
		this.words = this.raw_str.split(" ");
		
		GlyphLayout layout = new GlyphLayout();
		
		int x_offset = 0;
		float y_offset = 0;
		
		this.render_str = "";
		
		for(int i=this.y_begin; i<this.words.length; i++)
		{	
			layout.setText(this.font, this.words[i]+" ");
			
			if(x_offset + layout.width >= this.rect.width)
			{
				this.render_str += "\n";
				x_offset = 0;
				y_offset += this.font.getLineHeight() + layout.height;
			}
			
			if( y_offset > this.rect.height )
			{
				break;
			}
			
			this.render_str += " " + this.words[i];
			x_offset += layout.width;
		}

	}
	
	@Override
	public void display(SpriteBatch sb) 
	{
		this.font.draw(sb, this.render_str, this.rect.x, this.rect.y + this.rect.height);
		
		/*
		 * POUR VOIR LA ZONE
		 * ShapeRenderer sr = new ShapeRenderer();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BLUE);
		sr.rect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
		sr.end();*/
	
		
	}

	public void scrollUp(int word_num)
	{
		this.y_begin-= word_num;
		
		if( this.y_begin < 0 )
		{
			this.y_begin = 0;
		}
		else
		{
			setText(this.raw_str);
		}
		
		
	}
	
	public void scrollDown(int word_num)
	{
		this.y_begin += word_num;
		setText(this.raw_str);
	}
	
	@Override
	public void update() 
	{
		if( Gdx.input.isTouched() 
				&& this.rect.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
		{
			int delta = Gdx.input.getDeltaY();
			
			if(delta != 0)
			{
				if(delta > 0)
				{
					scrollUp(1);
				}
				else
				{
					scrollDown(1);
				}
			}

		}
	}

}
