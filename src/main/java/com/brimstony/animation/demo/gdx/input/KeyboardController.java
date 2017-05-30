package com.brimstony.animation.demo.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.brimstony.animation.demo.gdx.GdxDemo;

/**
 * Created by tbrim on 5/26/2017.
 */
public class KeyboardController extends InputAdapter{
    private GdxDemo application;
    public KeyboardController(GdxDemo application) {
        this.application = application;
    }

/*
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.debug("keyDown", Input.Keys.toString(keycode));
        switch (keycode) {
            case Input.Keys.LEFT:
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    return false; //Ingore event and continue
                application.setCurrentXDirection(GdxDemo.LEFT);
                return true;
            case Input.Keys.RIGHT:
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    return false; //Ingore event and continue
                application.setCurrentXDirection(GdxDemo.RIGHT);
                return true;
            case Input.Keys.UP:
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                    return false; //Ingore event and continue
                application.setCurrentYDirection(GdxDemo.UP);
                return true;
            case Input.Keys.DOWN:
                if(Gdx.input.isKeyPressed(Input.Keys.UP))
                    return false; //Ingore event and continue
                application.setCurrentYDirection(GdxDemo.DOWN);
                return true;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        Gdx.app.debug("keyUp", Input.Keys.toString(keycode));


        switch (keycode) {
            case Input.Keys.LEFT:
                if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                    Gdx.app.debug("keyUp", "Ignoring keyUp, RIGHT is pressed.");
                    return false;
                }
                application.setCurrentXDirection(GdxDemo.STOPPED);
                return true;
            case Input.Keys.RIGHT:
                if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                    Gdx.app.debug("keyUp", "Ignoring keyUp, LEFT is pressed.");
                    return false;
                }
                application.setCurrentXDirection(GdxDemo.STOPPED);
                return true;
            case Input.Keys.UP:
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                    Gdx.app.debug("keyUp", "Ignoring keyUp, DOWN is pressed.");
                    return false;
                }
                application.setCurrentYDirection(GdxDemo.STOPPED);
                return true;
            case Input.Keys.DOWN:
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    Gdx.app.debug("keyUp", "Ignoring keyUp, UP is pressed.");
                    return false;
                }
                application.setCurrentYDirection(GdxDemo.STOPPED);
                return true;
        }
        return false;
    }
    */
}
