package com.xlip.soundcarrier.Objs;

import java.util.Random;

/**
 * Created by Xlip on 17.08.2016.
 */
public class Obj {
    public Random random;
    public Object datas ;


    public int id;

    public int get_rnd10(){
        if(random.nextBoolean())return 1;
        else return -1;

    }

    public Obj(){
        random=new Random();

    }

    public  Obj set_id(int id){
        this.id=id;
        return  this;
    }
    public  Obj set_data(Object o){
        this.datas=o;
        return  this;
    }

    public float get_distance(Box a,Box b){
      return  (float) Math.sqrt( Math.abs( Math.pow( Math.abs(a.get_position().x- b.get_position().x),2) +Math.pow( Math.abs(a.get_position().y- b.get_position().y),2)));

    }


}
