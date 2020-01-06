package com.richinfo.cliprectdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

public class PorterDuffDemo extends View {
    private int width = 800;
    private int height = 800;
    private Bitmap dstOvalBmp;
    private Bitmap srcRectBmp;
    private Paint mPaint;

    public PorterDuffDemo(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        srcRectBmp = makeSrcRect(width, height);
        dstOvalBmp = makeDstOval(width, height);
        mPaint = new Paint();
    }

    /**
     * create a bitmap with a rect, used for the "src" image
     * @param w
     * @param h
     * @return
     */
    static Bitmap makeSrcRect(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        //此处坐标不要随意更改

        // Phase 1
        //c.drawRect(w/3, h/3, w*19/20, h*19/20, p);

        // Phase 2
        c.drawRect(0, 0, w, h, p);
        return bm;
    }

    /**
     * create a bitmap with a circle, used for the "dst" image
     * @param w
     * @param h
     * @return
     */
    static Bitmap makeDstOval(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        //此处坐标不要随意更改

        // Phase 1
        //c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);

        // Phase 2
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
        int layerID = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);

        // Phase 1
        //两个bitmap的位置相同
        //canvas.drawBitmap(dstOvalBmp, 0, 0, mPaint);
        //canvas.drawBitmap(srcRectBmp,0,0, mPaint);

        //两个bitmap的位置相同
//        canvas.drawBitmap(dstOvalBmp, 0, 0, mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        canvas.drawBitmap(srcRectBmp,0,0, mPaint);


        // Phase 2
        //两个bitmap的位置不相同
        canvas.drawBitmap(dstOvalBmp, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(srcRectBmp,width/2,height/2, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }
}