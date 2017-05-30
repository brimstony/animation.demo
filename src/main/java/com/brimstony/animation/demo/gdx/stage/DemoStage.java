package com.brimstony.animation.demo.gdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tbrim on 5/28/2017.
 */
public class DemoStage extends Stage {


    public Vector2 lastClicked;


    public DemoStage (Viewport viewport) {
        super(viewport);
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        lastClicked = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }



}
