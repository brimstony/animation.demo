package com.brimstony.animation.demo.basic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by tbrim on 5/25/2017.
 */
public class Sprite {

    private static BufferedImage spriteSheet;
    private static final int TILE_WIDTH = 104;
    private static final int TILE_HIGHT = 150;


    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(Sprite.class.getResource(("/" + file + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("gb_walk");
        }

        return spriteSheet.getSubimage(xGrid * TILE_WIDTH, yGrid * TILE_HIGHT, TILE_WIDTH, TILE_HIGHT);
    }

}