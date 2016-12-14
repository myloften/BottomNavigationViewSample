package com.loften.bottomnavigationviewsample;

import android.util.Log;

/**
 * Created by lcw on 2016/12/3.
 */

public class TestImp extends Test implements ShowPhoto{

    public String sex;
    int height;

    public TestImp(String name, int age, String weight) {
        super(name, age, weight);
    }

    public TestImp(String name, int age, String weight, String sex){
        super(name, age, weight);
        this.sex =sex;
    }

    public TestImp(String name, int age, String weight, String sex, int height){
        super(name, age, weight);
        this.sex = sex;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private void showSex(){
        Log.e("sex:", sex);
    }

    public String toString(){
        return name + ":" + age +  ":" + weight +  ":" + sex ;
    }

    @Override
    public void showHeadImage(String img) {
        Log.e("Photo:","hahaha, is ----" + img);
    }
}
