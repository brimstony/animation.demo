package com.brimstony.animation.demo.gdx.input;

import com.brimstony.animation.demo.gdx.GdxDemo;

/**
 * Created by tbrim on 5/26/2017.
 */
public class MouseController {

    private GdxDemo application;

    public MouseController(GdxDemo application) {
        this.application = application;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }


}
