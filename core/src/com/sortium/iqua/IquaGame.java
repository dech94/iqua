package com.sortium.iqua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sortium.iqua.event.ChangeSceneEvent;
import com.sortium.iqua.event.ClickEvent;
import com.sortium.iqua.event.Event;
import com.sortium.iqua.event.EventListener;
import com.sortium.iqua.event.GetEvent;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.manager.DialogueManager;
import com.sortium.iqua.manager.EntityManager;
import com.sortium.iqua.manager.ItemManager;
import com.sortium.iqua.scene.DialogueMenu;
import com.sortium.iqua.scene.InventoryMenu;
import com.sortium.iqua.scene.MainMenu;
import com.sortium.iqua.scene.Scene;
import com.sortium.iqua.scene.World;

public class IquaGame extends ApplicationAdapter {
	
	// CONST
	private final int androidClickArea = 32;
	
	private SpriteBatch batch;
	private Scene mainMenu;
	private String current = "1";
	private ArrayList<World> worlds;
	private Stack<Scene> currentScenes;
	private InventoryMenu inventoryMenu;
	private DialogueMenu dialogueMenu;
	
	Vector2[] dimensions;
	
	private Player player;

	// Manager
	private ArrayList<EntityManager> managers;
	private ItemManager itemManager;
	private DialogueManager dialogueManager;
	

	private OrthographicCamera camera;

	private class StartGame implements EventListener
	{
		@Override
		public boolean execute(Event event) 
		{
			// INTRO DIALOGUE
			//IquaGame.this.dialogueManager.runIntro();
			
			return true;
		}
		
	}
	
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
	
	private class PushDialogue implements EventListener
	{
		
		@Override
		public boolean execute(Event event) 
		{
			@SuppressWarnings("unchecked")
			GetEvent<Dialogue> get = (GetEvent<Dialogue>)event;
			IquaGame.this.dialogueMenu.setDialogue(get.thing);
			
			superimposeScene(IquaGame.this.dialogueMenu);
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
			
			if( num_sc >= IquaGame.this.worlds.size())
			{
				return true;
			}
			
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
		// CAMERA
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// WORLD
		batch = new SpriteBatch();	
		
		this.player = new Player(this);
		this.worlds = new ArrayList<World>();
		createWorlds();

		// SCENE
		this.mainMenu = new MainMenu(this,current);
		this.currentScenes = new Stack<Scene>();
		this.currentScenes.push(this.mainMenu);
		
		// SUBSCRIBE
		EventEngine.get().subscribe("scene.change", new ChangeScene());
		EventEngine.get().subscribe("scene.pop", new PopScene());
		EventEngine.get().subscribe("scene.inventory", new PushInventory());
		EventEngine.get().subscribe("scene.dialogue", new PushDialogue());
		EventEngine.get().subscribe("iqua.start", new StartGame());
		
		// MANAGER
		this.managers = new ArrayList<EntityManager>();
		
		this.itemManager = new ItemManager(this);
		this.managers.add(this.itemManager);
		
		this.dialogueManager = new DialogueManager(this);
		this.managers.add(this.itemManager);
	
		// ITEM
		int x = (int)(0.5*getWidth());
		int y = (int)(0.5*getHeight());
	
		//this.itemManager.add(new Item(this.worlds.get(2), "images/Items/itmChardon.png", null, x, y, 100, 100, "Fleur de chardon", "Il s'agit d'une fleur de chardon."));
		//this.itemManager.add(new Item(this.worlds.get(0), "images/Items/itmPoupee.png", null, x, y, 100, 100, "Poupée", "Massa placerat duis ultricies lacus sed turpis tincidunt id aliquet risus feugiat in ante metus, dictum at tempor commodo, ullamcorper a lacus vestibulum sed arcu non odio! Malesuada fames ac turpis egestas sed tempus. Volutpat odio facilisis mauris sit amet massa vitae tortor condimentum lacinia quis vel eros donec ac odio tempor orci dapibus ultrices in. Tellus elementum sagittis vitae et leo duis ut diam quam nulla porttitor massa id neque aliquam vestibulum morbi blandit cursus risus, at ultrices? Tortor consequat id porta nibh venenatis cras sed felis eget velit aliquet sagittis id consectetur purus ut faucibus pulvinar? Et netus et malesuada fames ac turpis egestas sed. Lectus urna duis convallis convallis tellus, id interdum velit laoreet id donec ultrices tincidunt arcu, non sodales neque sodales ut etiam sit amet! Curabitur vitae nunc sed velit dignissim sodales ut eu sem integer vitae justo eget magna fermentum iaculis eu. Ut tortor pretium viverra suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse sed nisi lacus, sed! Eu sem integer vitae justo eget! Odio eu feugiat pretium, nibh ipsum consequat nisl, vel pretium lectus quam id leo in vitae turpis massa sed elementum tempus egestas sed sed risus pretium quam vulputate."));
		
		// MENU
		this.inventoryMenu = new InventoryMenu(this, this.player.getInventory());
		this.dialogueMenu = new DialogueMenu(this);

		this.dimensions = new Vector2 [2];
		this.dimensions[0] = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.dimensions[1] = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	}
	
	public void createWorlds()
	{
		World w1 = new World(this, "images/Background/screen1.png");
		w1.addAWayTo("2", "images/PointOfInterest/poi_bridge.png", 316, 118, 160, 43);
		this.worlds.add(w1);
	
		this.worlds.add(new World(this, "images/Background/screen2.png"));
	

		
		//this.worlds.add(new World(this, "images/work.png"));
		
		/*this.worlds.add(new World(this, "images/Background/screen2.png"));
		this.worlds.add(new World(this, "images/Background/screen3.png"));
		this.worlds.add(new World(this, "images/Background/screen4.png"));
		this.worlds.add(new World(this, "images/Background/screen5.png"));
		this.worlds.add(new World(this, "images/Background/screen6.png"));
		this.worlds.add(new World(this, "images/Background/screen7.png"));
		this.worlds.add(new World(this, "images/Background/screen8.png"));
		this.worlds.add(new World(this, "images/Background/screen9.png"));
		this.worlds.add(new World(this, "images/Background/screen10.png"));
		this.worlds.add(new World(this, "images/work.png"));*/
		
	}
	
	public void resize(int width, int height)
	{
		this.dimensions[0].x = this.dimensions[1].x;
		this.dimensions[0].y = this.dimensions[1].y;
		
		this.dimensions[1].x = width;
		this.dimensions[1].y = height;
		
		this.camera.setToOrtho(false, width, height);
		

		for(int i=0; i<this.worlds.size(); i++)
		{
			this.worlds.get(i).resize(width, height);
		}
		
		for(int i=0 ; i<this.currentScenes.size(); i++)
		{
			this.currentScenes.get(i).resize(width, height);
		}

	}
	
	public void update()
	{
		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);
		
		if( !this.currentScenes.empty() )
		{
			this.currentScenes.peek().update();
		}
		
		for(int i=0; i<this.currentScenes.size(); i++)
		{
			this.currentScenes.get(i).update();
		}
		
		if( Gdx.input.justTouched() )
		{
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			click(x, y);
		}

		for(int i=0; i<this.managers.size(); i++)
		{
			this.managers.get(i).update();
		}
		
		this.inventoryMenu.update();
		this.dialogueMenu.update();
	}
	
	public void display()
	{
		if( !this.currentScenes.empty())
		{
			this.currentScenes.peek().display(this.batch, this.camera);
		}
		
		for(int i=0; i<this.currentScenes.size(); i++)
		{
			this.currentScenes.get(i).display(this.batch, this.camera);
		}
		
		for(int i=0; i<this.managers.size(); i++)
		{
			this.managers.get(i).display(this.batch, this.camera);
		}
		
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		
		this.batch.begin();
		display();
		this.batch.end();
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
	
	@SuppressWarnings("unchecked")
	public void superimposeScene(Scene to_add)
	{
		to_add.enable();

		this.currentScenes.push(to_add);

		Collections.sort(this.currentScenes);
	}
	
	public int getWidth()
	{
		return (int) this.camera.viewportWidth;
	}
	
	public int getHeight()
	{
		return (int) this.camera.viewportHeight;
	}
	
	public int getLastWidth()
	{
		return (int) this.dimensions[0].x;
	}
	
	public int getLastHeight()
	{
		return (int) this.dimensions[0].y;
	}
	
	
	public int getMouseX()
	{
		return Gdx.input.getX();
	}
	
	public int getMouseY()
	{
		return getHeight() - Gdx.input.getY();
	}
	
	public boolean onAndroid()
	{
		return Gdx.app.getType().name().equals("Android");
	}
	
	public void click(int x,int y)
	{
		Event event = null;
		
		if( onAndroid() )
		{
			event = new ClickEvent(x, y, this.androidClickArea);
		}
		else
		{
			event = new ClickEvent(x, y);
		}
		
		EventEngine.get().trigger("input.click", event);
	}
	
}
