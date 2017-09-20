package edu.orangecoastcollege.cs273.ttran1272.paintestimator;

/**
 * Created by ttran1272 on 9/19/2017.
 */

public class InteriorRoom {

    public static final float DOOR_AREA = 21f;
    public static final float WINDOWS_AREA = 16f;
    public static final float SQ_FEET_PER_GALLON = 275f;


    private float mLength;
    private float mWidth;
    private float mHeight;

    private int mDoors;
    private int mWindows;

    public float getLength() {
        return mLength;
    }

    public void setLength(float length) {
        mLength = length;
    }

    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float width) {
        mWidth = width;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setHeight(float height) {
        mHeight = height;
    }

    public int getDoors() {
        return mDoors;
    }

    public void setDoors(int doors) {
        mDoors = doors;
    }

    public int getWindows() {
        return mWindows;
    }

    public void setWindows(int windows) {
        mWindows = windows;
    }

    public float doorAndWindowArea(){
        return mDoors * DOOR_AREA + mWindows * WINDOWS_AREA;
    }

    public float wallSurfaceArea(){
        return (2 * mLength * mHeight + 2 * mWidth * mHeight);
    }

    public float totalSurfaceArea(){
        return wallSurfaceArea() - doorAndWindowArea();
    }

    public float gallonsOfPaintRequired(){
        return totalSurfaceArea() / SQ_FEET_PER_GALLON;
    }
}
