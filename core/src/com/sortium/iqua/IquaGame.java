package com.sortium.iqua;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.manager.EntityManager;
import com.sortium.iqua.manager.ItemManager;
import com.sortium.iqua.scene.InventoryMenu;
import com.sortium.iqua.scene.MainMenu;
import com.sortium.iqua.scene.Scene;
import com.sortium.iqua.scene.World;

public class IquaGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Scene mainMenu;
	private String current = "1";
	private ArrayList<World> worlds;
	private Stack<Scene> currentScenes;
	private InventoryMenu inventoryMenu;
	
	private Player player;

	// Manager
	private ArrayList<EntityManager> managers;
	private ItemManager itemManager;
	
	private class PopScene implements EventListener
	{

		@Override
		public boolean execute(Event event)
		{
			IquaGame.this.popCurrentScene();
			return true;
		}
		
	}
	
	private class PushInventory implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			superimposeScene(IquaGame.this.inventoryMenu);
			return true;
		}
		
	}
	private class ChangeScene implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			String sc = ((ChangeSceneEvent)event).getNewScene();
			
			if( sc.equals("mainMenu") )
			{
				IquaGame.this.replaceScene(IquaGame.this.mainMenu);
				return true;
			}
			
			if( sc.equals("quit") )
			{
				Gdx.app.exit();
				return true;
			}
			
			int num_sc = Integer.parseInt(sc) - 1;
			Scene next = IquaGame.this.worlds.get(num_sc);
			next.resetStartTime();
			
			IquaGame.this.replaceScene(next);
			
			System.out.println("load scene " + (num_sc+1));
			current=Integer.toString(num_sc+1);
			return true;
		}
		
	}
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();		
		this.player = new Player(this);
		this.worlds = new ArrayList<World>();
		createWorlds();
		
		this.mainMenu = new MainMenu(this,current);
		this.currentScenes = new Stack<Scene>();
		this.currentScenes.push(this.mainMenu);
		
		EventEngine.get().subscribe("scene.change", new ChangeScene());
		EventEngine.get().subscribe("scene.pop", new PopScene());
		EventEngine.get().subscribe("scene.inventory", new PushInventory());
		
		// manager
		this.managers = new ArrayList<EntityManager>();
		this.itemManager = new ItemManager(this);
		this.managers.add(this.itemManager);
		
		//items
		this.itemManager.add(new Item(this.worlds.get(2), "images/Items/itmChardon.png", null, 200, 300, 100, 100, "Fleur de chardon", "Il s'agit d'une fleur de chardon."));
		this.itemManager.add(new Item(this.worlds.get(0), "images/Items/itmPoupee.png", null, 300, 300, 100, 100, "Poupée", "Il s'agit d'une poupée."));
		
		this.inventoryMenu = new InventoryMenu(this, this.player.getInventory());
	}
	
	public void createWorlds()
	{
		this.worlds.add(new World(this, "images/Background/screen1.png", "2", null, null,  null));
		this.worlds.add(new World(this, "images/Background/screen2.png", "4", "1", null,  "3"));
		this.worlds.add(new World(this, "images/Background/screen3.png", null, "2", null,  null));
		this.worlds.add(new World(this, "images/Background/screen4.png", "5", "2", null,  null));
		this.worlds.add(new World(this, "images/Background/screen5.png", null, "4",null, "6"));
		this.worlds.add(new World(this, "images/Background/screen6.png", "7", "5", null,  null));
		this.worlds.add(new World(this, "images/Background/screen7.png", "8", "6", null,  null));
		this.worlds.add(new World(this, "images/Background/screen8.png", "9", "7", "10",  null));
		this.worlds.add(new World(this, "images/Background/screen9.png", null, "8", null,  null));
		this.worlds.add(new World(this, "images/Background/screen10.png", null, "8", null,  null));
		this.worlds.add(new World(this, "images/work.png", null, null, null, null));
		
	}
	
	public void update()
	{
		if( !this.currentScenes.empty() )
		{
			this.currentScenes.peek().update();
		}
		
		for(Scene s : this.currentScenes)
		{
			s.update();
		}
		
		if( Gdx.input.isTouched() )
		{
			Event event = new ClickEvent(Gdx.input.getX(), Gdx.input.getY());
			EventEngine.get().trigger("input.click", event);
		}
		
		for( EntityManager manager : this.managers)
		{
			manager.update();
		}

	}
	
	public void display()
	{
		if( !this.currentScenes.empty())
		{
			this.currentScenes.peek().display(this.batch);
		}
		
		for(Scene s : this.currentScenes)
		{
			s.display(this.batch);
		}
		
		for( EntityManager manager : this.managers)
		{
			manager.display(this.batch);
		}
		
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		
		batch.begin();
		display();
		batch.end();

	}

	public Scene getCurrentScene()
	{
		if(this.currentScenes.empty()) return null;
		return this.currentScenes.peek();
	}
	
	public void popCurrentScene()
	{
		if(this.currentScenes.empty()){ return; }
		
		this.currentScenes.peek().disable();
		this.currentScenes.pop();
	}
	
	public void replaceScene(Scene to_add)
	{
		popCurrentScene();
		superimposeScene(to_add);
	}
	
	public void superimposeScene(Scene to_add)
	{
		to_add.enable();
		this.currentScenes.push(to_add);
	}
	
}
