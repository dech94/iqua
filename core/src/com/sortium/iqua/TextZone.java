package com.sortium.iqua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class TextZone implements Entity
{
	protected BitmapFont font;
	protected GlyphLayout layout;
	
	protected Rectangle rect;
	protected String[] words;
	protected String raw_str;
	protected String render_str;
	
	protected int begin;
	protected long scrollDelay;
	protected long scrollTimer;
	protected IquaGame game;
	protected Rectangle rect_tmp;
	
	public static int sizeFor(float xy)
	{
		BitmapFont bf = new BitmapFont();
		bf.getData().scaleX = xy;
		bf.getData().scaleY = xy;
		return (int)bf.getLineHeight();
	}
	
	public TextZone(IquaGame game, String str, Rectangle rect, float xScale, float yScale)
	{
		this.font = new BitmapFont();
		this.font.setColor(Color.BLACK);
		
		this.rect_tmp = new Rectangle();
		
		this.game = game;
		
		this.begin = 0;
		
		this.rect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
		
		this.raw_str = str;
		
		this.scrollDelay = 125000000;
		this.scrollTimer = System.nanoTime();
		
		setScale(xScale, yScale);
		setText(this.raw_str);
	}
	
	public TextZone(IquaGame game, String str, Rectangle rect, float xyScale)
	{
		this(game, str, rect, xyScale, xyScale);
	}
	
	public TextZone(IquaGame game, String str, Rectangle rect)
	{
		this(game, str, rect, 1.2f);
	}
	
	public TextZone(IquaGame game, String str)
	{
		this(game, str,  new Rectangle(0, 0, game.getWidth(), game.getHeight()));
	}
	
	public TextZone(IquaGame game, String str, float xy)
	{
		this(game, str,  new Rectangle(0, 0, game.getWidth(), game.getHeight()), xy);
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
		updateText();
	}
	
	public void setZone(Rectangle rect)
	{
		this.rect = rect;
		updateText();
	}
	
	public String getText()
	{
		return this.raw_str;
	}
	
	public Rectangle getZone()
	{
		return this.rect;
	}
	
	public int getHeight()
	{
		return (nbLine()+1) * (int)this.font.getLineHeight();
	}

	public void updateText()
	{
		this.words = this.raw_str.split(" ");
		
		GlyphLayout layout = new GlyphLayout();
		
		int x_offset = 0;
		int line_nb= 0;
		
		this.render_str = "";
		
		for(int i=0; i<this.words.length; i++)
		{	
			layout.setText(this.font, this.words[i]+" ");
			
			// check if the word can be one the current line
			if(x_offset + layout.width >= this.rect.width)
			{
				this.render_str += "\n";
				x_offset = 0;
				line_nb++;

				if( (line_nb - begin) * (this.font.getLineHeight()) > this.rect.height) 
				{
					break;
				}
			}
			
			this.render_str += " " + this.words[i];
			x_offset += layout.width;
			
		}

	}
	
	public int nthLine(int n)
	{
		int pos = 0;
		int i = 0;
		
		while(i < n && pos != -1)
		{
			pos = this.render_str.indexOf('\n', pos+1);
			i++;
		}
		
		return pos+1;
	}
	
	public int nbLine()
	{
		int n = 0;
		for(int i=0; i<this.render_str.length(); i++)
		{
			if( this.render_str.charAt(i) == '\n' )
			{
				n++;
			}
		}
		return n;
	}
	
	@Override
	public void display(SpriteBatch sb, OrthographicCamera camera) 
	{

		// where to begin the line
		int begin_tmp = this.render_str.indexOf('\n');
		String tmp = this.render_str;
		
		if(begin_tmp != -1)
		{
			tmp = this.render_str.substring(nthLine(this.begin), this.render_str.length());
		}
		
		this.font.draw(sb, tmp, this.rect.x,this.rect.y);	
	}
	
	public void scrollUp(int line)
	{
		this.begin--;
		
		if(this.begin < 0)
		{
			this.begin = 0;
		}
		
		updateText();
	}
	
	public void scrollDown(int line)
	{
		if(this.begin+1 <= nbLine())
		{
			this.begin++;
		}
		
		updateText();
	}
	
	
	@Override
	public void update() 
	{	
		rect_tmp.set(this.rect.x, this.game.getHeight() -this.rect.y, this.rect.width, this.rect.height);

		if( Gdx.input.isTouched()
				&& rect_tmp.contains(Gdx.input.getX(), Gdx.input.getY()))
		{
			
			if( System.nanoTime() - this.scrollTimer >= this.scrollDelay)
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
				
				this.scrollTimer = System.nanoTime();
			}

		}
	}

	@Override
	public void resize(int w, int h) 
	{
		
	}

}
