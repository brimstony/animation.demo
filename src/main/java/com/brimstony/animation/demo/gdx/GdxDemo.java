package com.brimstony.animation.demo.gdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brimstony.animation.demo.gdx.player.Player;
import com.brimstony.animation.demo.gdx.stage.DemoStage;

public class GdxDemo extends ApplicationAdapter {



    private DemoStage demoStage;

    float stateTime;

    @Override
    public void create() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        demoStage = new DemoStage(new ScreenViewport());

        Group actorGroup = new Group();
        Player player = new Player();


        actorGroup.addActor(player);
        demoStage.addActor(actorGroup);
        Gdx.input.setInputProcessor(demoStage);

        configureControls();

    }

    private void configureControls() {
        //Gdx.input.setInputProcessor(new MouseController(this));
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        demoStage.act(stateTime);
        demoStage.draw();







        // Get current frame of animation for the current stateTime
/* Not sure if i need this....
        spriteBatch.begin();

        spriteBatch.draw(getCurrentFrame(), position.x, position.y);

        spriteBatch.end();
        */
    }

    /*
    private TextureRegion getCurrentFrame(){
        if (currentXDirection == LEFT){
            return walkLeftAnimation.getKeyFrame(stateTime, true);
        } else if(currentXDirection == RIGHT){
            return walkRightAnimation.getKeyFrame(stateTime, true);
        } else if(currentXDirection == STOPPED){
            return standing[lastXDirection];
        }

         return null;
    }

    public void setCurrentXDirection(int newDirection){
        if(newDirection != STOPPED) {
            lastXDirection = newDirection;
        }
        currentXDirection = newDirection;
    }

    public void setCurrentYDirection(int newDirection){
        lastYDirection = currentYDirection;
        currentYDirection = newDirection;
    }
    */
    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        demoStage.dispose();
        //spriteBatch.dispose();
        //spriteSheet.dispose();
    }
}