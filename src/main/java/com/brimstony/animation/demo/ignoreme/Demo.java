package com.brimstony.animation.demo.ignoreme;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tbrim on 5/25/2017.
 */
public class Demo {
    // Images for each animation
    private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(1, 1), Sprite.getSprite(2, 1), Sprite.getSprite(3, 1), Sprite.getSprite(4, 1), Sprite.getSprite(5, 1)};
    private BufferedImage[] walkingRight = {Sprite.getSprite(0, 0), Sprite.getSprite(1, 0), Sprite.getSprite(2, 0), Sprite.getSprite(3, 0), Sprite.getSprite(4, 0), Sprite.getSprite(5, 0)};
    private BufferedImage[] standingSprites = {Sprite.getSprite(1, 2)};

    // These are animation states
    private Animation walkLeft = new Animation(walkingLeft, 10);
    private Animation walkRight = new Animation(walkingRight, 10);
    private Animation standing = new Animation(standingSprites, 10);

    // This is the actual animation
    private Animation animation = walkRight;



    JFrame frame;
    DrawPanel drawPanel;


    public static void main(String[] args){


        Demo demo = new Demo();
        demo.run();
    }

    public void run(){
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        animation.start();
        moveIt();
            //Graphics g = walkLeft.getSprite().getGraphics();
            //g.drawImage(animation.getSprite(), 0, 0, null);

    }

    private void moveIt()
    {
        while (true)
        {
            try
            {
                animation.update();
                //drawPanel.getGraphics().drawImage(animation.getSprite(), 20, 20, null);
                //System.out.println("hello");
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }


    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
            g.drawImage(animation.getSprite(), 0, 0, null);
        }
    }
}
