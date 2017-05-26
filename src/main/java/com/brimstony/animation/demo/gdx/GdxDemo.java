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

    TextureRegion[] standing;
    TextureRegion currentStandingTexture;
    Texture guybrushSheet;
    SpriteBatch spriteBatch;

    // A variable for tracking elapsed time for the animation

    int screenWidth;
    int screenHeight;

    float guybrushSpeed = 200.0f; // 10 pixels per second.
    float guybrushX = 50;
    float guybrushY = 50;

    private static int STOPPED = 0;

    private static int RIGHT = 1;
    private static int LEFT = 2;
    private static int UP = 3;
    private static int DOWN = 4;


    private int currentXDirection = STOPPED;
    private int lastXDirection = LEFT;
    private int currentYDirection = STOPPED;
    private int lastYDirection;

    float stateTime;

    @Override
    public void create() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

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
        standing = new TextureRegion[] {null, new TextureRegion(tmp[2][0], 0, 1, tmp[2][0].getRegionWidth(), tmp[2][0].getRegionHeight() - 1 ), new TextureRegion(tmp[2][1], 0, 1, tmp[2][1].getRegionWidth(), tmp[2][1].getRegionHeight() - 1)};


        // Initialize the Animation with the frame interval and array of frames
        walkRightAnimation = new Animation<>(0.1f, walkRight);
        walkLeftAnimation = new Animation<>(0.1f, walkLeft);

        currentStandingTexture = standing[LEFT];

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
                Gdx.app.debug("keyDown", Input.Keys.toString(keycode));
                switch (keycode) {
                    case Input.Keys.LEFT:
                        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                            return false; //Ingore event and continue
                        setCurrentXDirection(LEFT);
                        return true;
                    case Input.Keys.RIGHT:
                        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                            return false; //Ingore event and continue
                        setCurrentXDirection(RIGHT);
                        return true;
                    case Input.Keys.UP:
                        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
                            return false; //Ingore event and continue
                        setCurrentYDirection(UP);
                        return true;
                    case Input.Keys.DOWN:
                        if(Gdx.input.isKeyPressed(Input.Keys.UP))
                            return false; //Ingore event and continue
                        setCurrentYDirection(DOWN);
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
                        currentXDirection = STOPPED;
                        return true;
                    case Input.Keys.RIGHT:
                        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                            Gdx.app.debug("keyUp", "Ignoring keyUp, LEFT is pressed.");
                            return false;
                        }
                        currentXDirection = STOPPED;
                        return true;
                    case Input.Keys.UP:
                        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                            Gdx.app.debug("keyUp", "Ignoring keyUp, DOWN is pressed.");
                            return false;
                        }
                        currentYDirection = STOPPED;
                        return true;
                    case Input.Keys.DOWN:
                        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                            Gdx.app.debug("keyUp", "Ignoring keyUp, UP is pressed.");
                            return false;
                        }
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

        if(guybrushX < 0){
            guybrushX = 0;
            Gdx.input.getInputProcessor().keyUp(Input.Keys.LEFT);
        } else if (guybrushX + standing[LEFT].getRegionWidth() > Gdx.graphics.getWidth() ){
            guybrushX = Gdx.graphics.getWidth() - standing[LEFT].getRegionWidth();
            Gdx.input.getInputProcessor().keyUp(Input.Keys.RIGHT);
        }

        if(guybrushY < 0){
            guybrushY = 0;
            Gdx.input.getInputProcessor().keyUp(Input.Keys.DOWN);
        } else if (guybrushY + standing[LEFT].getRegionHeight() > Gdx.graphics.getHeight() ){
            guybrushY = Gdx.graphics.getHeight() - standing[LEFT].getRegionHeight();
            Gdx.input.getInputProcessor().keyUp(Input.Keys.UP);
        }






        // Get current frame of animation for the current stateTime

        spriteBatch.begin();

        spriteBatch.draw(getCurrentFrame(), guybrushX, guybrushY);

        spriteBatch.end();
    }

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

    private void setCurrentXDirection(int newDirection){
        if(newDirection != STOPPED) {
            lastXDirection = newDirection;
        }
        currentXDirection = newDirection;
    }

    private void setCurrentYDirection(int newDirection){
        lastYDirection = currentYDirection;
        currentYDirection = newDirection;
    }
    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        guybrushSheet.dispose();
    }
}