package com.sortium.iqua;

public class Player
{
	protected Inventory inventory;
	protected IquaGame game;
	
	public Player(IquaGame game)
	{
		this.inventory = new Inventory(game);
		this.game = game;
	}
}
