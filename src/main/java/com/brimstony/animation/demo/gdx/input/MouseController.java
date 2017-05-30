package com.brimstony.animation.demo.gdx.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.brimstony.animation.demo.gdx.GdxDemo;

/**
 * Created by tbrim on 5/26/2017.
 */
public class MouseController extends InputAdapter {

    private GdxDemo application;

    public MouseController(GdxDemo application) {
        this.application = application;
    }
/*
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        application.destination = new Vector2(screenX, screenY);


        application.direction.set(application.destination.x - application.guybrushPosition.x, application.destination.y - application.guybrushPosition.y);


        return true;

    }
*/
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }


}
