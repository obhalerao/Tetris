package trap1.bhaleraoomkar.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawView extends SurfaceView {


    SurfaceHolder surface;
    Paint paint=new Paint();

    int[][] board = new int[20][10];

    Canvas canvas;
    boolean isRunning=true;
    int frames=0;
    private static final int MAX_STREAMS=100;
    private int soundIdExplosion;
    private int soundIdBackground;
    private boolean soundPoolLoaded;
    private SoundPool soundPool;
    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        surface=getHolder();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long lastTime = System.nanoTime(); // get current time to the nanosecond
                double amountOfTicks = 60; // set the number of updates per second
                double ns = 1000000000 / amountOfTicks; // this determines how many times we can devide 60 into 1e9 of nano seconds or about 1 second
                long timer = System.currentTimeMillis(); // get current time
                int updates = 0; // set frame variable
                while(true){
                    long now = System.nanoTime(); // get current time in nonoseconds durring current loop
                    if(now - lastTime<ns){//if running fast
                        try{
                            Thread.sleep((long)((ns - (now-lastTime)))/1000000);//pause until time for next update
                        }catch(Exception e){}
                    }
                    lastTime = System.nanoTime();  // set lastTime to current time to mark beginning of next loop
                    if(isRunning){
                        if(!surface.getSurface().isValid())continue;
                        canvas = surface.lockCanvas();//lock canvas
                        synchronized (getHolder()){
                            onDraw(canvas);
                        }
                        surface.unlockCanvasAndPost(canvas);//unlock the canvas
                    }
                    updates++; // note that a frame has passed
                    if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
                        timer+= 1000; // add a thousand to our timer for next time
                        System.out.println("UPS: " +updates +" FPS: "+frames); // print out how many frames have happend in the last second
                        updates = 0; // reset the update count for the next second
                        frames = 0;// reset the frame count for the next second
                    }
                }
            }
        }).start();

    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //note, at this point, getWidth() and getHeight() will have access the the dimensions

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);//set paint to gray
        canvas.drawRect(getLeft(),0,getRight(),getBottom(),paint);//paint background gray


        frames++;
    }



    public void pause() {//pause-resume
        isRunning=!isRunning;
    }
}