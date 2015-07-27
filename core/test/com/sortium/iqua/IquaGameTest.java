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

import org.junit.Test;

import static org.junit.Assert.*;

public class IquaGameTest {
		@Test
		public void thisAlwaysPasses() {
			assertTrue(true);
		}

	@Test
	public void bananas() {
		assertEquals("bananas", "bananas");
	}

}
