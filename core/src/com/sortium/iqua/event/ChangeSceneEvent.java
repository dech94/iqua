package com.sortium.iqua.event;

public class ChangeSceneEvent extends Event
{
	private String newScene;
	
	public ChangeSceneEvent(String str)
	{
		this.setNewScene(str);
	}

	public String getNewScene() {
		return newScene;
	}

	public void setNewScene(String newScene) {
		this.newScene = newScene;
	}
}
