package com.brimstony.animation.demo.gdx.stage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brimstony.animation.demo.gdx.player.Player;

/**
 * Created by tbrim on 5/28/2017.
 */
public class DemoStage extends Stage {

    private Player playerCharacter;

    public DemoStage (Viewport viewport) {
        super(viewport);

        Group gameAreaGroup = new Group();
        gameAreaGroup.setBounds(0, 200, 1024, 568);
        gameAreaGroup.setColor(1.0f,1.0f,1.0f,1.0f);

        Group controlGroup = new Group(){};
        controlGroup.setBounds(0, 0, 1024, 200);
        controlGroup.setColor(0.0f,0.0f,0.0f,1.0f);
        controlGroup.setVisible(true);

        playerCharacter = new Player();

        gameAreaGroup.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event,
                                float x,
                                float y){
                playerCharacter.setDestination(new Vector2(x,y));
                playerCharacter.setMoving(true);
            }
        });


        gameAreaGroup.addActor(playerCharacter);
        addActor(controlGroup);
        addActor(gameAreaGroup);

    }

    public void dispose () {
        super.dispose();
    }


}
