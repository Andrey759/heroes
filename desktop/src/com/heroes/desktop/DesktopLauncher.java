package com.heroes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.heroes.HGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Heroes game";
		config.width = (int) Math.round(1920 * 0.9);
		config.height = (int) Math.round(1080 * 0.9);
		//config.samples = 4;
		//config.depth = 0;
		//config.vSyncEnabled = true;
		config.fullscreen = false;
		config.forceExit = false;
		new LwjglApplication(new HGame(), config);
	}
}
