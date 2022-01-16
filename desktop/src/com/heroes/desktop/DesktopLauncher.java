package com.heroes.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.heroes.HGame;

public class DesktopLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Heroes game");
        config.setWindowedMode((int) Math.round(1280), (int) Math.round(869));
        new Lwjgl3Application(new HGame(), config);
    }
}
