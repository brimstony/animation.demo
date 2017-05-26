package com.brimstony.animation.demo.gdx;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class GdxDemo extends ApplicationAdapter {
    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 6, FRAME_ROWS = 3;

    // Objects used
    Animation<TextureRegion> walkRightAnimation;
    Animation<TextureRegion> walkLeftAnimation;
    Animation<TextureRegion> currentAnimation;
    Texture guybrushSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    @Override
    public void create() {

        // Load the sprite sheet as a Texture
        guybrushSheet = new Texture(Gdx.files.internal("gb_walk.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(guybrushSheet,
                guybrushSheet.getWidth() / FRAME_COLS,
                guybrushSheet.getHeight() / FRAME_ROWS);


        TextureRegion[] walkRight = tmp[0];
        TextureRegion[] walkLeft = tmp[1];

        // Initialize the Animation with the frame interval and array of frames
        walkRightAnimation = new Animation<>(0.1f, walkRight);
        walkLeftAnimation = new Animation<>(0.1f, walkLeft);

        currentAnimation = walkLeftAnimation;

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
        configureControls();

    }

    private void configureControls() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        currentAnimation = walkLeftAnimation;
                        return true;
                    case Input.Keys.RIGHT:
                        currentAnimation = walkRightAnimation;
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime

        spriteBatch.begin();
        spriteBatch.draw(currentAnimation.getKeyFrame(stateTime, true), 50, 50); // Draw current frame at (50, 50)
        //spriteBatch.draw(walkLeftAnimation.getKeyFrame(stateTime, true), 250, 50); // Draw current frame at (50, 50)
        spriteBatch.end();
    }

    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        guybrushSheet.dispose();
    }
}