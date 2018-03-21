package com.example.devinsmith.pongsmithdev20;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 *
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private Button smallButton, medButton, largeButton;
    private ImplementAnimation animation;


    /**
     * creates an AnimationSurface containing a TestAnimator.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface) this.findViewById(R.id.animationSurface);
        animation = new ImplementAnimation();
        mySurface.setAnimator(animation);

        //buttons for paddle size
        smallButton = (Button)findViewById(R.id.smallPaddleButton);
        medButton = (Button)findViewById(R.id.medPaddleButton);
        largeButton = (Button)findViewById(R.id.largePaddleButton);

        smallButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (smallButton.isPressed()) {
                    animation.setPaddleWidth(600);
                }
            }
        });
        medButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (medButton.isPressed()) {
                    animation.setPaddleWidth(450);
                }
            }
        });
        largeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (largeButton.isPressed()) {
                    animation.setPaddleWidth(300);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
