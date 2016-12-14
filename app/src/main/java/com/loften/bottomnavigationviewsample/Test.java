package com.loften.bottomnavigationviewsample;

import android.util.Log;

/**
 * Created by lcw on 2016/12/3.
 */

public class Test {
    String name;
    public int age;
    String weight;

    public Test(String name, int age, String weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public void showName(){
        Log.e("name:",name);
    }

    protected void showAge(){
        Log.e("age:",age+"");
    }

    private void showWeight(){
        Log.e("weight:",weight);
    }

}
