package com.brimstony.animation.demo.gdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tbrim on 5/26/2017.
 */


public class Player extends Actor{
    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 6, FRAME_ROWS = 3;

    Animation<TextureRegion>[] walkingAnimations;

    Sprite[] standingSprites;
    TextureRegion idleTexture;
    Texture spriteSheet;

    // A variable for tracking elapsed time for the animation
    float movementSpeed = 4.0f; // 10 pixels per second.



    private boolean isMoving = false;
    private int currentDirection = LEFT;
    private float animationTime = 0.0f;
    private float frameDuration = 0.1f;


    public static int RIGHT = 0;
    public static int LEFT = 1;

    private Vector2 destination;



    public Player() {
        // Load the sprite sheet as a Texture
        spriteSheet = new Texture(Gdx.files.internal("gb_walk.png"));


        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / FRAME_COLS,
                spriteSheet.getHeight() / FRAME_ROWS);


        TextureRegion[] walkRight = tmp[0];
        TextureRegion[] walkLeft = tmp[1];

        //Have to cheat a little bit... the sprite sheet I'm using isn't perfectly laid out. Need to shave
        //the top row of pixels off so his shoes from the row about don't show.
        //If I had any skill with Paint I would modify the PNG directly :(.
        standingSprites = new Sprite[] {new Sprite(tmp[2][0], 0, 1, tmp[2][0].getRegionWidth(), tmp[2][0].getRegionHeight() - 1 ), new Sprite(tmp[2][1], 0, 1, tmp[2][1].getRegionWidth(), tmp[2][1].getRegionHeight() - 1)};

        walkingAnimations = new Animation[] {new Animation<>(frameDuration, walkRight), new Animation<>(frameDuration, walkLeft)};

        idleTexture = standingSprites[LEFT];

        this.setOrigin(idleTexture.getRegionWidth()/2 , idleTexture.getRegionHeight()/2);
    }

    @Override
    public void draw(Batch batch, float alpha){
        TextureRegion thisTexture  = idleTexture;
        if (isMoving){
            thisTexture = walkingAnimations[currentDirection].getKeyFrame(animationTime, true);
        }
        batch.draw(thisTexture,this.getX(),getY());
    }

    public void act (float delta) {
        super.act(delta);

        animationTime = delta;





        Vector2 position = new Vector2(this.getX(), this.getY() );
        if (isMoving) {
            //See if we can get the center to move where we click
            //We cant.
            //destination.sub(this.getOriginX(), this.getOriginY());
            Vector2 velocity = destination.cpy().sub(position).nor().scl(movementSpeed);
            //If the distance between velocity and the destination is less than the distance between position and destination, set position = destination
            Vector2 newPosition = position.cpy().add(velocity);
            float velocityDistance = destination.dst2(newPosition);
            float positionDistance = destination.dst2(position);
            if (velocityDistance > positionDistance) {
                this.setPosition(destination.x, destination.y);
                isMoving = false;
                idleTexture = standingSprites[currentDirection];
            } else {

                this.setPosition(newPosition.x, newPosition.y);
                isMoving = true;

                if (position.x >= newPosition.x){
                    currentDirection = LEFT;
                } else {
                    currentDirection = RIGHT;
                }

            }
        }
    }


    public Vector2 getDestination() {
        return destination;
    }

    public void setDestination(Vector2 destination) {
        destination.sub(idleTexture.getRegionWidth()/2, idleTexture.getRegionHeight()/2);
        this.destination = destination;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
