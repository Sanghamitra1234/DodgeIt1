package com.sleepygamers.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sleepygamers.game.DodgeIt;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=DodgeIt.WIDTH;
		config.height=DodgeIt.HIGHT;
		config.title="DodgeIt";
		new LwjglApplication(new DodgeIt(), config);
	}
}
