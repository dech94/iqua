package com.sortium.iqua;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TextZone implements Entity
{
	protected BitmapFont font;
	protected GlyphLayout layout;
	
	protected Rectangle rect;
	protected String[] words;
	protected String raw_str;
	protected String render_str;
	
	protected int y_scroll;
	
	public TextZone(String str)
	{
		this.font = new BitmapFont();
		this.rect = new Rectangle(200, 200, 100, 128);
		
		this.y_scroll = 0;
		
		setText(str);
	}

	public void setText(String str)
	{
		this.raw_str = str;
		this.words = this.raw_str.split(" ");
		
		GlyphLayout layout = new GlyphLayout();
		
		int x_offset = 0;
		float y_offset = 0;
		
		this.render_str = "";
		
		for(int i=0; i<this.words.length; i++)
		{	
			layout.setText(this.font, this.words[i]);
			
			if(x_offset + layout.width > this.rect.width)
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
		
		//System.out.println(this.str);
		this.font.draw(sb, this.render_str, this.rect.x, this.rect.y);
	}

	@Override
	public void update() 
	{
		
	}

}
