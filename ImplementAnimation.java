package com.example.devinsmith.pongsmithdev20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

/**
 * animates the ball's movement
 *
 * @author Devin Smith
 * @version March 2018
 */

public class ImplementAnimation implements Animator {
    private int xCoord, yCoord, xCount, yCount, paddleWidth, paddleR, paddleL;
    private boolean goBackwardsX, goBackwardsY;
    private boolean paused;


    @Override
    public int interval() {
        return 30;
    }

    /**
     * colors the background
     *
     * @return background color
     */
    @Override
    public int backgroundColor() {
        return Color.rgb(0, 0, 0);
    }

    @Override
    public boolean doPause() {
        return paused;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

        if (goBackwardsX) {
            xCount--;
        } else {
            xCount++;
        }

        if (goBackwardsY) {
            yCount--;
        } else {
            yCount++;
        }


        //draws the walls
        Paint wallColor = new Paint();
        wallColor.setColor(Color.WHITE);
        canvas.drawRect(0f, 0f, 100f, 1500f, wallColor);//left wall
        canvas.drawRect(1950f, 0f, 2050f, 1500f, wallColor);//right wall
        canvas.drawRect(0f, 0f, 2000f, 100f, wallColor);//top wall

        //draws paddle
        paddleCoords();
        canvas.drawRect(paddleR, 1000f, paddleL, 1300f, wallColor);//paddle

        //draws ball
        Paint ballColor = new Paint();
        ballColor.setColor(Color.RED);
        if(yCoord == 1300){

        }
        else {
            xCoord = getXCoordinate(xCount);

            yCoord = getYCoordinate(yCount);
        }

        canvas.drawCircle(xCoord, yCoord, 100f, ballColor);


        if (yCoord == 900) {
            if (xCoord >= paddleL && xCoord <= paddleR) {
                goBackwardsY = true;
            } else if (xCoord < paddleL || xCoord > paddleR) {
                goBackwardsY = false;
                if (yCoord == 1100) {
                    paused = false;
                    yCoord = 1300;
                }
            }
        }

    }

    public int getXCoordinate(int xCoord) {
        xCoord = (xCoord * 20) % 1850;

        if (xCoord == 200) {
            goBackwardsX = false;
            return xCoord;
        }

        if (xCoord == 1800) {
            goBackwardsX = true;
            return xCoord;
        }

        return xCoord;
    }

    /**
     * getYCordinate moves the ball in the y direction, if the ball hits a wall or the paddle then
     * it will change directions
     *
     * @param yCoord
     * @param xCoord
     * @return yCoord
     */
    public int getYCoordinate(int yCoord) {
        yCoord = (yCoord * 20) % 1850;

        if (yCoord == 200) {
            goBackwardsY = false;
            return yCoord;
        }

        return yCoord;
    }

    /**
     * randomX will set both the x cordinates and the horizontal direction for the new ball
     */
    public void randomX() {
        Random rand = new Random();
        int randCoord = rand.nextInt(2);
        if (randCoord == 0) {
            goBackwardsX = true;
        } else if (randCoord == 1) {
            goBackwardsX = false;
        }
    }

    /**
     * randomY will set both the y cordinates and the vertical direction for the new ball
     */
    public void randomY() {
        Random rand = new Random();
        int randCoord = rand.nextInt(2);
        if (randCoord == 0) {
            goBackwardsY = true;
        } else if (randCoord == 1) {
            goBackwardsY = false;
        }
    }

    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    public void paddleCoords() {
        //hitPaddle = false;
        if (paddleWidth == 0) {
            paddleWidth = 600;
        }
        paddleR = 2050 - paddleWidth;
        paddleL = paddleWidth;
    }

    public void reset() {
        randomX();
        randomY();

    }

    //external citation
    //https://stackoverflow.com/questions/3476779/how-to-get-the-touch-position-in-android
    @Override
    public void onTouch(MotionEvent event) {
        Random rand = new Random();
        xCoord = rand.nextInt(1650) + 200;
        yCoord = rand.nextInt(800) + 200;
        randomX();
        randomY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

                paused = false;

        }
    }
}
