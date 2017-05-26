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

    TextureRegion standingRight;
    TextureRegion standingLeft;

    TextureRegion currentStandingTexture;
    Texture guybrushSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation

    int screenWidth;
    int screenHeight;

    float guybrushSpeed = 100.0f; // 10 pixels per second.
    float guybrushX = 50;
    float guybrushY = 50;

    private static int STOPPED = 0;

    private static int LEFT = -1;
    private static int RIGHT = 1;

    private static int DOWN = -1;
    private static int UP = 1;

    private int currentXDirection = STOPPED;
    private int currentYDirection = STOPPED;

    float stateTime;

    @Override
    public void create() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
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

        //Have to cheat a little bit... the sprite sheet I'm using isn't perfectly laid out. Need to shave
        //the top row of pixels off so his shoes from the row about don't show.
        //If I had any skill with Paint I would modify the PNG directly :(.
        standingRight = new TextureRegion(tmp[2][0], 0, 1, tmp[2][0].getRegionWidth(), tmp[2][0].getRegionHeight() -1 );
        standingLeft = new TextureRegion(tmp[2][1], 0, 1, tmp[2][1].getRegionWidth(), tmp[2][1].getRegionHeight() - 1);

        // Initialize the Animation with the frame interval and array of frames
        walkRightAnimation = new Animation<>(0.1f, walkRight);
        walkLeftAnimation = new Animation<>(0.1f, walkLeft);

        //currentAnimation = walkLeftAnimation;
        currentStandingTexture = standingLeft;

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
                        currentStandingTexture = standingLeft;
                        currentXDirection = LEFT;
                        return true;
                    case Input.Keys.RIGHT:
                        currentAnimation = walkRightAnimation;
                        currentStandingTexture = standingRight;
                        currentXDirection = RIGHT;
                        return true;
                    case Input.Keys.UP:
                        currentYDirection = UP;
                        return true;
                    case Input.Keys.DOWN:
                        currentYDirection = DOWN;
                        return true;
                }
                return false;
            }

            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        currentAnimation = walkLeftAnimation;
                        currentXDirection = STOPPED;
                        return true;
                    case Input.Keys.RIGHT:
                        currentAnimation = walkRightAnimation;
                        currentXDirection = STOPPED;
                        return true;
                    case Input.Keys.UP:
                        currentYDirection = STOPPED;
                        return true;
                    case Input.Keys.DOWN:
                        currentYDirection = STOPPED;
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

        if(currentXDirection == LEFT){
            guybrushX -=  Gdx.graphics.getDeltaTime() * guybrushSpeed;
        } else if (currentXDirection == RIGHT){
            guybrushX +=  Gdx.graphics.getDeltaTime() * guybrushSpeed;
        }

        if(currentYDirection == DOWN){
            guybrushY -=  Gdx.graphics.getDeltaTime() * guybrushSpeed;
        } else if (currentYDirection == UP){
            guybrushY +=  Gdx.graphics.getDeltaTime() * guybrushSpeed;
        }


        // Get current frame of animation for the current stateTime

        spriteBatch.begin();
        if(currentXDirection != STOPPED)
            spriteBatch.draw(currentAnimation.getKeyFrame(stateTime, true), guybrushX, guybrushY);
        else
            spriteBatch.draw(currentStandingTexture, guybrushX, guybrushY);
        spriteBatch.end();
    }

    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        guybrushSheet.dispose();
    }
}