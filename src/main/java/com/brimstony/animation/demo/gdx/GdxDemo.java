package com.brimstony.animation.demo.gdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brimstony.animation.demo.gdx.stage.DemoStage;

public class GdxDemo extends Game {


    private DemoStage demoStage;
    float stateTime;

    public GdxDemo() {


    }


    @Override
    public void create() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);


        Viewport mainViewport = new ScreenViewport();
        //mainViewport.setScreenBounds(0, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        demoStage = new DemoStage(mainViewport);
        Gdx.input.setInputProcessor(demoStage);
    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        demoStage.act(stateTime);
        demoStage.draw();
    }

    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        demoStage.dispose();
    }
}