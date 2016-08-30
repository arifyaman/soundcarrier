package com.xlip.soundcarrier.Utils;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Yaman on 06.02.2016.
 */
public class Sindeg {
    public float deg;
    public float angle;

    float reset;




    public Sindeg(float angle, float deg){
        this.angle=angle;
        reset=angle;

        this.deg=deg;




        
    }

    public void set_angle(float an){
        angle=an;
        reset=an;
    }

    public Sindeg set_deg(float d){
        deg=d;
        return  this;
    }

    public void reset(){
        angle=reset;
    }


    public float getAngle(){

        angle = (angle + deg * 90) % 360f;
        return (MathUtils.sinDeg(angle));
    }

}
