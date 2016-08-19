package com.jkxy.customtestdemo;

import java.io.Serializable;

/**
 * Created by zh on 16/8/18.
 */
public class DataInfo implements Serializable{

    private int color;
    private int value;
    private float angle;
    private float percentage;

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
