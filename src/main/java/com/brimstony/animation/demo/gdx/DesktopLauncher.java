package com.brimstony.animation.demo.gdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by tbrim on 5/26/2017.
 */
public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "GdxDemo";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new GdxDemo(), config);
    }
}